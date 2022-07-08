package ir.ac.kntu.engine.ui;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Class used to create buttons
 */
public class ButtonUI {
    private final Button button;

    /**
     * configure button
     * @param text the text displayed on the button
     * @param x button on position on the x axis
     * @param y button on the y axis
     */
    public ButtonUI(String text, double x, double y) {
        button = new Button();
        button.setText(text);
        button.setLayoutY(y);
        button.setLayoutX(x);
        button.setStyle("-fx-background-color: transparent");

    }

    public Button getButton() {
        return button;
    }

    public void setAction(SceneHandler eventEventHandler) {
        button.setOnAction(actionEvent -> {
            try {
                eventEventHandler.handle();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Change text size as well as do
     * @param is  Font text
     * @param size text size
     */
    public void changeFont(InputStream is, int size) {
        Font font = Font.loadFont(is,size);
        button.setFont(font);
    }

    /**
     * Change the button text color
     * @param color color
     */
    public void setColor(Color color) {
        button.setTextFill(color.color);
    }
}
