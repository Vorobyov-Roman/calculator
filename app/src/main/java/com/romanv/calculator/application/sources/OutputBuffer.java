package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.Callback;

class OutputBuffer extends EventEmitter<String> {

    OutputBuffer() {
        output = "";
    }

    void onOutputChanged(Double result) {
        output = String.valueOf(result);
        notifySubscribers();
    }

    void subscribeToOutputChange(Callback<String> callback) {
        addSubscriber(callback);
    }

    @Override
    public String getValue() {
        return output;
    }

    private String output;

}
