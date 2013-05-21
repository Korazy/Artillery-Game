package artillerygame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Random;

public class Terrain {

    public Terrain() {
        this.model = new TerrainModel();
        this.view = new TerrainView(model);
        model.initialize();
    }

    TerrainView getView() {
        return view;
    }

    TerrainModel getModel() {
        return model;
    }
    TerrainModel model;
    TerrainView view;
}

class TerrainModel {

    public TerrainModel() {
        this.width = GameOptions.getGameWidth();
        this.height = GameOptions.getGameHeight();
        this.lowestHeight = (int) (height * minPercHeight);
        this.highestHeight = (int) (height * maxPercHeight);
        this.landHeight = new int[width];
    }

    int[] getLandHeight() {
        return landHeight;
    }

    public void generateTerrain() {
        Random randGen = new Random();
        double rand = randGen.nextDouble();
        int heightDiff = highestHeight - lowestHeight;
        landHeight[0] = (int) (lowestHeight + Math.round(rand * (heightDiff)));
        double variation = GameOptions.getLandVariation();
        for (int i = 1; i < width; i++) {
            do {
                rand = randGen.nextDouble();
                landHeight[i] = (int) Math.round(landHeight[i - 1] + rand * variation - variation / 2);
                if (landHeight[i] < lowestHeight) {
                    landHeight[i] = lowestHeight;
                }
            } while (landHeight[i] > highestHeight);
        }
    }

    public void smoothenTerrain() {
        double mass;
        double numMassSamples;
        int smoothSize = GameOptions.getLandSmoothSize();
        for (int i = 0; i < width; i++) {
            mass = 0;
            numMassSamples = 0;
            for (int j = i - smoothSize; j < i + smoothSize; j++) {
                if (j > 0 && j < width - 1) {
                    numMassSamples++;
                    mass += landHeight[i];
                }
                landHeight[i] = (int) Math.round(mass / numMassSamples);
            }
        }
    }

    public void initialize() {

        generateTerrain();
        smoothenTerrain();
    }
    
    int width;
    int height;
    int lowestHeight;
    int highestHeight;
    int[] landHeight;
    private static double minPercHeight = 0.15;
    private static double maxPercHeight = 0.75;
}

class TerrainView {

    public TerrainView(TerrainModel model) {
        this.model = model;
    }

    public void draw(Graphics2D g) {
        Theme theme = GameOptions.getTheme();
        Color drawColor = theme.getDrawColor();
        g.setStroke(new BasicStroke(1.5f));
        g.setColor(drawColor);
        int width = model.width;
        int height = model.height;
        int[] landHeight = model.landHeight;
        for (int i = 0; i < width - 1; i++) {
            g.draw(new Line2D.Double(i, height - 1, i, height - 1 - landHeight[i]));
        }
    }
    private TerrainModel model;
}
