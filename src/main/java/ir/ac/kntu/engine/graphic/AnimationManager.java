package ir.ac.kntu.engine.graphic;

import javafx.scene.image.Image;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * class managing the different animations of an entity
 */
public class AnimationManager {
    // HashMap gathering the different animations of an entity
    private final HashMap<String, Animation> animations;
    // Running animation
    private Animation currentAnimation;

    public AnimationManager() {
        animations = new HashMap<>();
    }

    /**
     * Function adding an animation to the hashmap (Give it the path of a .txt grouping
     * the path of the different images of the animation)
     * @param key
     * @param init
     * @param duration
     */
    public void addAnimation(String key, String init, double duration) {
        ArrayList<Image> list = new ArrayList<>();

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(AnimationManager.class.getResourceAsStream(init)));
            String line;
            while ((line = in.readLine()) != null) {
                Image im = new Image(AnimationManager.class.getResourceAsStream(line));
                list.add(im);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        // add the Animation to the hashmap
        animations.put(key,new Animation(list, duration));
    }

    /**
     * Define the current animation
     * @param key
     */
    public void setCurrentAnimation(String key){
        currentAnimation = animations.get(key);
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    /**
     * running the next frame of the animation
     */
    public Image playAnimation(double time){
        return currentAnimation.getCurrentImage(time);
    }
}
