package com.romanv.calculator;

import com.romanv.calculator.application.api.Application;
import com.romanv.calculator.application.api.Action;
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
        application = new ApplicationImpl();
        application.subscribeToInputChange(this::onInputChanged);
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
        Action action = idToAction(view.getId());
        application.perform(action);
    }

    public void showAboutDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_about, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        //Button deleteButton = (Button) dialog.findViewById(buttonId);
        dialog.show();
    }

    private void onInputChanged(String inputString) {
        TextView userInput = (TextView) findViewById(R.id.label_input);
        userInput.setText(inputString);
    }

    private Action idToAction(@IdRes int buttonId) throws Exception {
        switch (buttonId) {
            case R.id.btn_one:   return Action.One;
            case R.id.btn_two:   return Action.Two;
            case R.id.btn_three: return Action.Three;
            case R.id.btn_four:  return Action.Four;
            case R.id.btn_five:  return Action.Five;
            case R.id.btn_six:   return Action.Six;
            case R.id.btn_seven: return Action.Seven;
            case R.id.btn_eight: return Action.Eight;
            case R.id.btn_nine:  return Action.Nine;
            case R.id.btn_zero:  return Action.Zero;

            case R.id.btn_add:      return Action.Add;
            case R.id.btn_subtract: return Action.Subtract;
            case R.id.btn_multiply: return Action.Multiply;
            case R.id.btn_divide:   return Action.Divide;

            case R.id.btn_point: return Action.Point;

            case R.id.btn_delete: return Action.Delete;

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

            private Runnable clear = () -> application.perform(Action.Clear);
            private Runnable delete = () -> application.perform(Action.Delete);

        });
    }

    private Application application;

}
