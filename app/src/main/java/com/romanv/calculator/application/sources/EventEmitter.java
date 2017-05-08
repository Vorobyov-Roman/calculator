package com.romanv.calculator.application.sources;

import com.romanv.calculator.application.api.Callback;

import java.util.ArrayList;
import java.util.List;

abstract class EventEmitter<T> {

    EventEmitter() {
        subscribers = new ArrayList<>();
    }

    void addSubscriber(Callback<T> callback) {
        subscribers.add(callback);
    }

    void notifySubscribers() {
        for (int i = subscribers.size() - 1; i >= 0; --i) {
            Callback<T> subscriber = subscribers.get(i);
            subscriber.call(getValue());
        }
    }

    public abstract T getValue();

    private List<Callback<T>> subscribers;

}
