package artillerygame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;


public class Terrain {

    public Terrain() {
        TerrainModel model = new TerrainModel(800, 600);
        TerrainView view = new TerrainView();
    }
    
    private class TerrainModel {

        public TerrainModel(int width, int height) {
            this.width = width;
            this.height = height;
            this.randYMin = height * minPercHeight;
            this.randYMax = height * maxPercHeight;
            lines.add(new Line2D.Double(0, height / 2, width, height / 2));
        }

        public ArrayList<Line2D> getLines() {
            return lines;
        }

        public void generateStep() {
            if (currentIteration >= numIterations) {
                return;
            }
            ArrayList<Line2D> newLines = new ArrayList<Line2D>();
            for (Line2D line : lines) {
                double x1 = line.getX1(), x2 = line.getX2();
                double y1 = line.getY1(), y2 = line.getY2();
                double midX = line.getBounds2D().getCenterX();
                double randY = randomDouble(randYMin, randYMax);
                Line2D leftLine = new Line2D.Double(x1, y1, midX, randY);
                Line2D rightLine = new Line2D.Double(midX, randY, x2, y2);
                newLines.add(leftLine);
                newLines.add(rightLine);
                lines = newLines;
                /*
                 System.out.printf("randYRange: %f %f\n", randYMin, randYMax);
                 System.out.printf("line1: %f %f %f %f\n", x1, y1, midX, randY);
                 System.out.printf("line2: %f %f %f %f\n\n", midX, randY, x2, y2);
                 */
                randYScaling = (randYMax - randYMin) * randScaling;
                randYMin += randYScaling;
                randYMax -= randYScaling;
            }
            currentIteration++;
        }

        public void generate() {
            for (int i = 0; i < numIterations; i++) {
                generateStep();
            }
        }

        public void clear() {
            lines.clear();
            currentIteration = 0;
            this.randYMin = height * minPercHeight;
            this.randYMax = height * maxPercHeight;
            lines.add(new Line2D.Double(0, height / 2, width, height / 2));
        }
        private int width;
        private int height;
        private double randYMin;
        private double randYMax;
        private double randYScaling;
        private int currentIteration = 0;
        private ArrayList<Line2D> lines = new ArrayList<Line2D>();
    }

    class TerrainView {

        public TerrainView() {
            File imagefile = new File(".\\outer-space_8.jpg");
            try { img = ImageIO.read(imagefile); }
            catch (IOException e) { e.printStackTrace(); }
        }
        
        public void draw(Graphics2D g) {
            g.drawImage(img, 0, 0, null);
            g.setColor(Color.WHITE);
            for (Line2D line : model.getLines()) {
                g.draw(line);
            }
        }
        
        private BufferedImage img;
    }

    private static double randomDouble(double a, double b) {
        Random randGen = new Random();
        double min = Math.min(a, b), max = Math.max(a, b);
        int range = Math.abs((int) (max - min + 1));
        return (min + randGen.nextInt(range));
    }
    
    
    public void draw(Graphics2D g) {
        view.draw(g);
    }
    
    private TerrainModel model;
    private TerrainView view;
    
    private static int numIterations = 5;
    private static double randScaling = 0.03;
    private static double minPercHeight = 0.1;
    private static double maxPercHeight = 0.9;
}
