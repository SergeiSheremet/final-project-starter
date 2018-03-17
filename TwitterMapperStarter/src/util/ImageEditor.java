package util;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class ImageEditor {
    public static BufferedImage drawEdge(BufferedImage source, Color color, int size) {
        int w = source.getWidth();

        BufferedImage output = new BufferedImage(w + size, w + size, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fill(new Ellipse2D.Float(0, 0, w + size, w + size));
        g2.drawImage(source, size / 2 , size / 2 , null);

        g2.dispose();

        return output;
    }

    public static BufferedImage cropImage(BufferedImage source){
        int w = source.getWidth();
        BufferedImage output = new BufferedImage(w, w, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(new Ellipse2D.Float(0, 0, w, w));

        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(source, 0, 0, null);

        g2.dispose();

        return output;
    }
}
