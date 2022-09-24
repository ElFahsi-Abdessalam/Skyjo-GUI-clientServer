package be.he2b.atlir5.skyjo.view.viewJfx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The type General method.
 */
public class GeneralMethod {
    /**
     * Image view image view.
     *
     * @param path   the path
     * @param width  the width
     * @param height the height
     * @return image view
     */
    public static ImageView imageView(String path, int width,int height) {
        return new ImageView(new Image(Objects.requireNonNull(GeneralMethod.class.getResourceAsStream(path)),
                width, height, true, true));
    }
}
