package ui.marker;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SegregateTwitterMarker extends TwitterMarker {

    private final String text;
    private boolean inner;

    public SegregateTwitterMarker(Layer layer, Coordinate coord, Color color, BufferedImage image, String text){
        super(layer, coord, color, image);
        this.text = text;
        inner = false;
    }

    @Override
    public String getText(){
        return text;
    }
    public BufferedImage getImage() {
        return image;
    }
    public boolean isInner() {
        return inner;
    }
}
