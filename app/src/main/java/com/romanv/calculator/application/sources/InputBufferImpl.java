package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.Callback;
import com.romanv.calculator.application.api.InputBuffer;

class InputBufferImpl extends EventEmitter<String> implements InputBuffer {

    InputBufferImpl() {
        buffer = "";
    }

    @Override
    public void append(String str) {
        buffer += str;
        notifySubscribers();
    }

    @Override
    public void removeLastElement() {
        if (!buffer.isEmpty()) {
            buffer = buffer.substring(0, buffer.length() - 1);
            notifySubscribers();
        }
    }

    @Override
    public void clear() {
        buffer = "";
        notifySubscribers();
    }

    void subscribeToInputChange(Callback<String> callback) {
        addSubscriber(callback);
    }

    @Override
    public String getValue() {
        return buffer;
    }

    private String buffer;

}
