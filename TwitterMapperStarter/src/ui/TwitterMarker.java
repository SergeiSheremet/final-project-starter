package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import util.ImageEditor;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;

public class TwitterMarker extends MapMarkerCircle{

    private final String text;
    private final long id;
    private BufferedImage image;
    private List<Color> colorList;
    private static final double defaultRadius = 1;
    private static final int borderSize = 6;

    public TwitterMarker(Layer layer, Coordinate coord, Color color, BufferedImage image, String text, long id) {
        super(layer, null, coord, defaultRadius, STYLE.FIXED, getDefaultStyle());

        colorList = new ArrayList<>();
        colorList.add(color);

        this.image = ImageEditor.drawEdge(ImageEditor.cropImage(image), colorList.get(0), borderSize);
        this.text = text;
        this.id = id;
    }

    @Override
    public double getRadius(){
        return image.getWidth() / 2 * defaultRadius;
    }
    public String getText() {
        return text;
    }
    public long getId() {
        return id;
    }

    public void addColor(Color color) {
        colorList.add(color);
        image = ImageEditor.drawEdge(image, colorList.get(colorList.size() - 1), borderSize);
    }

    @Override
    public void paint(Graphics g, Point position, int radius) {
        int w = (int) (image.getWidth(null) * defaultRadius);
        int w2 = w / 2;
        g.drawImage(image, position.x - w2, position.y - w2, w, w, null);
        paintText(g, position);
    }
}