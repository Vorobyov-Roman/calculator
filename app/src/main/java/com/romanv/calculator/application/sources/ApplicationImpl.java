package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.Application;
import com.romanv.calculator.application.api.Action;
import com.romanv.calculator.application.api.Callback;

public class ApplicationImpl implements Application {

    public ApplicationImpl() {
        inputBuffer = new InputBuffer();
    }

    public void perform(Action action) {
        if (action == Action.Delete) {
            inputBuffer.removeLastElement();
        }
        else if (action == Action.Clear) {
            inputBuffer.clear();
        }
        else {
            inputBuffer.append(action.toString());
        }
    }

    public void subscribeToInputChange(Callback<String> callback) {
        inputBuffer.subscribeToInputChange(callback);
    }

    private InputBuffer inputBuffer;

}
