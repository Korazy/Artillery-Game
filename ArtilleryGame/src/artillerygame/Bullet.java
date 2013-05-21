package artillerygame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Bullet {

    public Bullet() {
        model = new BulletModel();
        view = new BulletView(model);
    }

    public BulletModel getModel() {
        return model;
    }

    public BulletView getView() {
        return view;
    }

    public void draw(Graphics2D g) {
        view.draw(g);
    }

    private BulletModel model;
    private BulletView view;

    public static double WIDTH = 4;
    public static double HEIGHT = 4;
    public static double G = 9.81;
    public static double DT = 0.08;
}

class BulletModel {

    public BulletModel() {
         int width = GameOptions.getGameWidth() + 1;
        this.path = new double[2][width];
        initialize();
    }

    public void initialize() {
        this.firing = false;
        this.numPaths = 0;
        double radians = (180 + angle) * Math.PI / 180.0;
        velocityX = power * Math.cos(radians);
        velocityY = power * Math.sin(radians);
    }

    public void update() {
        path[0][numPaths] = positionX;
        path[1][numPaths] = positionY;
        numPaths++;

        positionX += velocityX * Bullet.DT;
        positionY += velocityY * Bullet.DT - 0.5 * Bullet.G * Bullet.DT * Bullet.DT;
        velocityY += Bullet.G * Bullet.DT;

        Terrain terrain = GameOptions.getWorld().getModel().getTerrain();
        int landHeight[] = terrain.getModel().getLandHeight();
        if (positionX < 0 || positionX > GameOptions.getGameWidth()
                || ((int) positionY) >= landHeight[(int) (positionX)]) {
            setFiring(false);
        }

        Rectangle2D projectile = new Rectangle2D.Double(positionX - 4, positionY - 4, 4, 4);
        Tank player1Tank = GameOptions.getPlayer1().getTank();
        Tank player2Tank = GameOptions.getPlayer2().getTank();

        if (player1Tank.getModel().getBoundingBox().intersects(projectile) && owner != player1Tank.getModel()) {
            player1Tank.getModel().damage();
            GameOptions.getPlayer2().score();
            GameOptions.getPlayer2Score().update();
            System.out.println("HERE");
            setFiring(false);
        }
        else if (player2Tank.getModel().getBoundingBox().intersects(projectile) && owner != player2Tank.getModel()) {
            player1Tank.getModel().damage();
            GameOptions.getPlayer2().score();
            GameOptions.getPlayer1Score().update();
            System.out.println("HERE4");
            setFiring(false);
        }
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public double getPower() {
        return power;
    }

    public double getAngle() {
        return angle;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setOwner(TankModel owner) {
        this.owner = owner;
    }

    public boolean isFiring() {
        return firing;
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    int getNumPaths() {
        return numPaths;
    }

    public double[][] getPath() {
        return path;
    }

    public TankModel getOwner() {
        return owner;
    }

    private boolean firing;
    private double power;
    private double angle;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double[][] path;
    private int numPaths;
    private TankModel owner;
}

class BulletView {

    public BulletView(BulletModel model) {
        this.model = model;
    }

    void drawPath(Graphics2D g) {
        int numPaths = model.getNumPaths();
        double[][] path = model.getPath();
        g.setColor(model.getOwner().getColor());
        for (int i = 0; i < numPaths; i++) {
            int x = (int)path[0][i];
            int y = (int)path[1][i];
            g.drawLine(x, y, x, y);
        }
    }

    void drawProjectile(Graphics2D g) {
        double x = model.getPositionX();
        double y = model.getPositionY();
        g.setColor(model.getOwner().getColor());
        g.draw(new Rectangle2D.Double(x - 4, y - 4, 4, 4));
    }

    void draw(Graphics2D g) {
        model.update();
        drawProjectile(g);
        drawPath(g);
    }
    private BulletModel model;
}