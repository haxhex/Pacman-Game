package ir.ac.kntu.engine.ui;


public enum Color {
    RED(javafx.scene.paint.Color.RED), BLUE(javafx.scene.paint.Color.BLUE), GREEN(javafx.scene.paint.Color.GREEN), DARK(javafx.scene.paint.Color.BLACK), WHITE(javafx.scene.paint.Color.WHITE),
    YELLOW(javafx.scene.paint.Color.YELLOW);

    public final javafx.scene.paint.Color color;
    private Color(javafx.scene.paint.Color color){
        this.color = color;
    }
}
