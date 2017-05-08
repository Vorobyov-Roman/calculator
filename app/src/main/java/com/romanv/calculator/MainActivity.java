package com.romanv.calculator;

import com.romanv.calculator.application.api.Application;
import com.romanv.calculator.application.api.ActionButton;
import com.romanv.calculator.application.api.Command;
import com.romanv.calculator.application.api.CommandsFactory;
import com.romanv.calculator.application.api.Environment;
import com.romanv.calculator.application.sources.ApplicationImpl;

import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Button;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {
        application = new ApplicationImpl(getEnvironment());
        application.subscribeToInputChange(this::onInputChanged);
        application.subscribeToOutputChange(this::onOutputChanged);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setupActionBar(R.id.toolbar);
        setupDeleteEventHandler(R.id.btn_delete);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                showAboutDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onButtonClick(View view) throws Exception {
        ActionButton button = idToActionButton(view.getId());
        executeAction(button);
    }

    public void showAboutDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_about, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void onInputChanged(String inputString) {
        TextView userInput = (TextView) findViewById(R.id.label_input);
        userInput.setText(inputString);
    }

    private void onOutputChanged(String outputString) {
        TextView evaluatedOutput = (TextView) findViewById(R.id.label_output);
        evaluatedOutput.setText(outputString);
    }

    private void executeAction(ActionButton button) {
        CommandsFactory commandsFactory = application.getCommandsFactory();
        Command command = commandsFactory.createCommand(button);

        application.execute(command);
    }

    private ActionButton idToActionButton(@IdRes int buttonId) throws Exception {
        switch (buttonId) {
            case R.id.btn_one:   return ActionButton.One;
            case R.id.btn_two:   return ActionButton.Two;
            case R.id.btn_three: return ActionButton.Three;
            case R.id.btn_four:  return ActionButton.Four;
            case R.id.btn_five:  return ActionButton.Five;
            case R.id.btn_six:   return ActionButton.Six;
            case R.id.btn_seven: return ActionButton.Seven;
            case R.id.btn_eight: return ActionButton.Eight;
            case R.id.btn_nine:  return ActionButton.Nine;
            case R.id.btn_zero:  return ActionButton.Zero;

            case R.id.btn_add:      return ActionButton.Add;
            case R.id.btn_subtract: return ActionButton.Subtract;
            case R.id.btn_multiply: return ActionButton.Multiply;
            case R.id.btn_divide:   return ActionButton.Divide;

            case R.id.btn_point: return ActionButton.Point;

            case R.id.btn_delete: return ActionButton.Delete;

            default:
                throw new Exception();
        }
    }

    private void setupActionBar(@IdRes int toolbarId) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);
    }

    private void setupDeleteEventHandler(@IdRes int buttonId) {
        Button deleteButton = (Button) findViewById(buttonId);

        deleteButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        handler.postDelayed(clear, 500);
                        return true;

                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacks(clear);
                        delete.run();
                        return true;

                    default:
                        return false;
                }
            }

            private Handler handler = new Handler();

            private Runnable clear = () -> executeAction(ActionButton.Clear);
            private Runnable delete = () -> executeAction(ActionButton.Delete);

        });
    }

    private Environment getEnvironment() {
        return new Environment() {

            @Override
            public String getAdditionSymbol() {
                return getResources().getString(R.string.addition_symbol);
            }

            @Override
            public String getSubtractionSymbol() {
                return getResources().getString(R.string.subtraction_symbol);
            }

            @Override
            public String getMultiplicationSymbol() {
                return getResources().getString(R.string.multiplication_symbol);
            }

            @Override
            public String getDivisionSymbol() {
                return getResources().getString(R.string.division_symbol);
            }

            @Override
            public String getPointSymbol() {
                return getResources().getString(R.string.point);
            }

        };
    }

    private Application application;

}
