package artillerygame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Tank {

    public Tank() {
        this.model = new TankModel();
        this.view = new TankView(model);
    }

    public TankModel getModel() {
        return model;
    }

    public TankView getView() {
        return view;
    }

    private TankModel model;
    private TankView view;

    public static int TURRETX = 5;
    public static int TURRETY = 5;
    public static int TURRET_WIDTH = 15;
    public static int TURRET_HEIGHT= 4;
    public static int WIDTH = 20;
    public static int HEIGHT = 10;
}

class TankModel {

    public TankModel() {
        this.positionX = 0;
        this.positionY = 0;
        this.angle = 45;
        this.power = 100;
        this.health = 100;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public double getAngle() {
        return angle;
    }

    public double getPower() {
        return power;
    }

    public double getHealth() {
        return health;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    private int positionX;
    private int positionY;
    private double angle;
    private double power;
    private double health;
    private Color color;

}

class TankView {

    public TankView(TankModel model) {
        this.model = model;
    }

    void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(1.0F));
        int x = model.getPositionX();
        int y = model.getPositionY();
        Rectangle2D rect = new Rectangle2D.Double(
                x - Tank.WIDTH/2,
                y - Tank.HEIGHT,
                Tank.WIDTH,
                Tank.HEIGHT);
        g.setColor(Color.YELLOW);
        g.fill(rect);

        AffineTransform orig = g.getTransform();
        g.rotate((double)model.getAngle());
        Rectangle2D turret = new Rectangle2D.Double(
                x + Tank.TURRETX,
                y + Tank.TURRETY,
                Tank.TURRET_WIDTH,
                Tank.TURRET_HEIGHT
        );
        g.draw(turret);
        g.setTransform(orig);
    }

    private TankModel model;
}
