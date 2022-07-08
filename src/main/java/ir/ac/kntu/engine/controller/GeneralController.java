package ir.ac.kntu.engine.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.List;

/**
 * Manage several Keyboard handlers
 */
public class GeneralController {
    private final List<KeyboardController> controllers;
    private EventHandler<KeyEvent> eventHandler;

    public GeneralController(List<KeyboardController> controllers){
        this.controllers = controllers;
        createHandler();
    }

    /**
     * Create a keyboard handler, and add to the associated event manager
     */
    private void createHandler(){
        eventHandler = event -> {
            for(KeyboardController controller : controllers){
                controller.getEventHandler().handle(event);
            }
        };
    }

    public EventHandler<KeyEvent> getEventHandler(){
        return eventHandler;
    }
}
