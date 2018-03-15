package ui.marker;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class UnitedTwitterMarker extends TwitterMarker {

    private List<SegregateTwitterMarker> innerMarkers;

    public UnitedTwitterMarker(Layer layer, Coordinate coord, Color color) {
        super(layer, coord, color, null);

        innerMarkers = new LinkedList<>();
    }

    public UnitedTwitterMarker(SegregateTwitterMarker segregateMarker) {
        super(segregateMarker.getLayer(), segregateMarker.getCoordinate(),
                segregateMarker.getColor(), segregateMarker.getImage());
    }

    @Override
    public String getText() {
        StringBuilder text = new StringBuilder();

        int size = (4 < innerMarkers.size()) ? (4) : (innerMarkers.size());

        for(int i = 0; i < size; ++i) {
            text.append("- ");
            text.append(innerMarkers.get(i).getText());
            text.append("\n");
        }

        if (innerMarkers.size() > 4) text.append("\nZoom to see more");
        return new String(text);
    }
}
