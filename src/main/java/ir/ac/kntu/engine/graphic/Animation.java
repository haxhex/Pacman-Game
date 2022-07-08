package ir.ac.kntu.engine.graphic;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Class corresponding to a particular animation
 */
public class Animation {
    // list of animation images
    private final ArrayList<Image> images;
    private final double duration;

    public Animation(ArrayList<Image> images, double duration) {
        this.images = images;
        this.duration = duration;
    }

    /**
     * method returning the next image according to the duration of the animation and the current time
     * @param time Temps courant
     * @return The following image
     */
    public Image getCurrentImage(double time){
        return images.get((int)((time % (images.size() * duration)) / duration));
    }
}
