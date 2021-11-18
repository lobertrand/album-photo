package fr.albumphoto.model.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventRegister {

    private final Map<Event<Object>, List<Consumer<Object>>> callbacks = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> void on(Event<T> event, Consumer<T> callback) {
        if (!callbacks.containsKey(event)) {
            callbacks.put((Event<Object>) event, new ArrayList<>());
        }
        callbacks.get(event).add((Consumer<Object>) callback);
    }

    public <T> void remove(Event<T> event, Consumer<T> callback) {
        if (callbacks.containsKey(event)) {
            var callbackList = callbacks.get(event);
            callbackList.remove(callback);
            if (callbackList.isEmpty()) {
                callbacks.remove(event);
            }
        }
    }

    public <T> void fire(Event<T> event, T eventValue) {
        if (callbacks.containsKey(event)) {
            for (Consumer<Object> callback : callbacks.get(event)) {
                callback.accept(eventValue);
            }
        }
    }

}
