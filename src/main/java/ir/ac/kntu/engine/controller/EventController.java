package ir.ac.kntu.engine.controller;

/**
 * System call interface for the controller, at each keyboard input
 */
public interface EventController {
    public void handle(KeyboardCode keyCode);
}
