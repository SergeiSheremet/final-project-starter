package ui.marker;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class TwitterMarker extends MapMarkerCircle{

    private final BufferedImage image;
    private static final double defaultRadius = 0.5;
    private final String text;

    public TwitterMarker(Layer layer, Coordinate coord, Color color, BufferedImage image, String text) {
        super(layer, null, coord, defaultRadius, STYLE.FIXED, getDefaultStyle());

        setColor(color);
        setBackColor(color);

        this.image = paintEdge(cropImage(image));
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public double getRadius(){
        return image.getWidth() / 2 * defaultRadius;
    }

    @Override
    public void paint(Graphics g, Point position, int radius) {
        int w = (int) (image.getWidth(null) * defaultRadius);
        int w2 = w / 2;
        g.drawImage(image, position.x - w2, position.y - w2, w, w, null);
        paintText(g, position);
    }

    private BufferedImage paintEdge(BufferedImage source) {
        int w = source.getWidth();
        int addedRadius = (int)(w * 0.25);

        BufferedImage output = new BufferedImage(w + addedRadius, w + addedRadius, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(this.getColor());
        g2.fill(new Ellipse2D.Float(0, 0, w + addedRadius, w + addedRadius));

        g2.drawImage(source, addedRadius / 2, addedRadius / 2, null);

        g2.dispose();

        return output;
    }

    private BufferedImage cropImage(BufferedImage source){
        int w = source.getWidth();
        BufferedImage output = new BufferedImage(w, w, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        //g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2.setColor(this.getColor());
        g2.fill(new Ellipse2D.Float(0, 0, w, w));

        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(source, 0, 0, null);

        g2.dispose();

        return output;
    }
}
