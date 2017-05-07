package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.Callback;

import java.util.ArrayList;
import java.util.List;

class InputBuffer {

    InputBuffer() {
        subscribers = new ArrayList<Callback<String>>();
        buffer = "";
    }

    void append(String str) {
        buffer += str;
        notifySubscribers();
    }

    void removeLastElement() {
        if (!buffer.isEmpty()) {
            buffer = buffer.substring(0, buffer.length() - 1);
            notifySubscribers();
        }
    }

    void clear() {
        buffer = "";
        notifySubscribers();
    }

    void subscribeToInputChange(Callback<String> callback) {
        subscribers.add(callback);
    }

    private void notifySubscribers() {
        for (Callback<String> subscriber : subscribers) {
            subscriber.call(buffer);
        }
    }

    private String buffer;

    private List<Callback<String>> subscribers;

}
