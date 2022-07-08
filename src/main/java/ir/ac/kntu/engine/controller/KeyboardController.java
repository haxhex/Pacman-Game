package ir.ac.kntu.engine.controller;

import ir.ac.kntu.engine.core.Entity;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;

/**
 * Keyboard controller for taking into account by the motor
 */
public abstract class KeyboardController implements ControllerComp {

    private EventHandler<? super KeyEvent> eventHandler;

    public KeyboardController(){}

    /**
     * creation of keyboard input detector
     */
    protected void createHandler(EventController eventController){
        eventHandler = (EventHandler<KeyEvent>) keyEvent -> {
            try{
                eventController.handle(KeyboardCode.valueOf(String.valueOf(keyEvent.getCode())));
            } catch (Exception e){
                System.err.println("Warning: " + e.getMessage());
            }
        };
    }

    public EventHandler<? super KeyEvent> getEventHandler() {
        return eventHandler;
    }

    @Override
    public abstract void update(Entity entity) throws FileNotFoundException;
}


