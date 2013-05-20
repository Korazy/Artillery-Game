package artillerygame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
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
    public static int TURRENT_WIDTH = 9;
    public static int TURRET_HEIGHT= 4;
    public static int WIDTH = 20;
    public static int HEIGHT = 20;
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
        int x = model.getPositionX()+100;
        int y = model.getPositionY()+100;
        Rectangle2D rect = new Rectangle2D.Double(
                x - Tank.WIDTH/2,
                y - Tank.HEIGHT,
                Tank.WIDTH,
                Tank.HEIGHT);
        System.out.println("HEllo");
        g.setColor(Color.YELLOW);
        g.fill(rect);
    }

    private TankModel model;
}
