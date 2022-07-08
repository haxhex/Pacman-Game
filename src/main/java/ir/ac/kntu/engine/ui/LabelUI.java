package ir.ac.kntu.engine.ui;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.InputStream;

/**
 * Class for graphically creating texts
 */
public class LabelUI {
    Label label;

    /**
     * Initializes the display of text
     * @param text text to display
     * @param x text position on x axis text position on x axis
     * @param y text position on the y axis
     */
    public LabelUI(String text, double x, double y) {
        label = new Label();
        label.setText(text);
        label.setLayoutY(y);
        label.setLayoutX(x);
        label.setStyle("-fx-background-color: transparent");
    }

    public Label getLabel() {
        return label;
    }

    /**
     * Change the size + the font of the text
     * @param is  Font text
     * @param size text size
     */
    public void changeFont(InputStream is, int size) {
        Font font = Font.loadFont(is,size);
        label.setFont(font);
    }

    /**
     * Change the text color
     * @param color color
     */
    public void setColor(Color color) {
        label.setTextFill(color.color);
    }

    /**
     * Method to update the text
     * @param text text to update
     */
    public void update(String text){
        label.setText(text);
    }
}
