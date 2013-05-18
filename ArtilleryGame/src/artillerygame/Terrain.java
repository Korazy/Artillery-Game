package artillerygame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

import javax.swing.JComponent;

public class Terrain {

    public Terrain(int width, int height) {
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

            System.out.printf("randYRange: %f %f\n", randYMin, randYMax);
            System.out.printf("line1: %f %f %f %f\n", x1, y1, midX, randY);
            System.out.printf("line2: %f %f %f %f\n\n", midX, randY, x2, y2);

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

    private static double randomDouble(double a, double b) {
        Random randGen = new Random();
        double min = Math.min(a, b), max = Math.max(a, b);
        int range = Math.abs((int) (max - min + 1));
        return (min + randGen.nextInt(range));
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
    private static int numIterations = 5;
    private static double randScaling = 0.03;
    private static double minPercHeight = 0.1;
    private static double maxPercHeight = 0.9;
}

class TerrainView extends JComponent {

    public TerrainView(Terrain model) {
        this.model = model;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        File imagefile = new File("C:\\Users\\Korazy\\Documents\\GitHub\\Artillery-Game\\ArtilleryGame\\src\\artillerygame\\outer-space_8.jpg");
        
        try{
            img = ImageIO.read(imagefile);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        g2.drawImage(img,0,0,null);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        for (Line2D line : model.getLines()) {
            g2.draw(line);
        }
    }
    private Terrain model;
    private BufferedImage img;
}

class TerrainController {

    public TerrainController(Terrain model, TerrainView view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        model.generate();
        view.repaint();
    }

    public void restart() {
        model.clear();
        start();
    }
    private Terrain model;
    private TerrainView view;
}
