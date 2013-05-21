package artillerygame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Tank {

    public Tank() {
        this.model = new TankModel();
        this.view = new TankView(model);
    }

    public void fireBullet() {
        model.fireBullet();
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
    public static int TURRET_WIDTH = 40;
    public static int TURRET_HEIGHT= 5;
    public static int WIDTH = 20;
    public static int HEIGHT = 10;
}

class TankModel {

    public TankModel() {
        this.health = 100;
        this.boundingBox = new Rectangle2D.Double();
        this.bullet = new Bullet();
        this.oldBullets = new ArrayList<Bullet>();
    }

    public void update() {
        boundingBox.setRect(
                positionX - Tank.WIDTH/2,
                positionY - Tank.HEIGHT,
                Tank.WIDTH,
                Tank.HEIGHT
        );
    }

    public void fireBullet() {
        bullet = new Bullet();
        BulletModel bulletModel = bullet.getModel();
        bulletModel.setOwner(this);
        bulletModel.setAngle(angle);
        bulletModel.setPower(power);
        bulletModel.setPositionX(positionX);
        bulletModel.setPositionY(positionY-20);
        bulletModel.initialize();
        bulletModel.setFiring(true);
        oldBullets.add(bullet);
    }

    public void damage() {
        double dec = GameOptions.getDamage();
        if (health - dec > 0) {
            health -= dec;
            damage += dec;
            color.brighter();
        }
        else if (damage >= GameOptions.getDamageMax())
            GameOptions.gameover();

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

    public double getAngleRadians() {
        return ((angle * Math.PI)/180.0);
    }

    public double getPower() {
        return power;
    }

    public double getHealth() {
        return health;
    }

    public double getDamage() {
        return damage;
    }

    public Color getColor() {
        return color;
    }

    public Rectangle2D getBoundingBox() {
        return boundingBox;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public ArrayList<Bullet> getOldBullets() {
        return oldBullets;
    }

    public void setColor(Color color) {
        this.color = color;
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
    private double damage;
    private Color color;
    private Rectangle2D boundingBox;
    private Bullet bullet;
    private ArrayList<Bullet> oldBullets;
}

class TankView {

    public TankView(TankModel model) {
        this.model = model;
    }

    void draw(Graphics2D g) {
        model.update();
        int x = model.getPositionX();
        int y = model.getPositionY();
        Rectangle2D boundingBox = model.getBoundingBox();
        BulletModel bulletModel = model.getBullet().getModel();
        BulletView bulletView = model.getBullet().getView();

        AffineTransform orig = g.getTransform();
        Rectangle2D turret = new Rectangle2D.Double(
                x-Tank.WIDTH/4,
                y-Tank.HEIGHT,
                Tank.TURRET_WIDTH*3/4,
                Tank.TURRET_HEIGHT/2
        );

        g.setStroke(new BasicStroke(1.0F));
        g.setColor(model.getColor());
        g.fill(boundingBox);
        g.rotate((double)model.getAngleRadians());
        g.draw(turret);
        g.setTransform(orig);
        if (bulletModel.isFiring())
            bulletView.draw(g);
        if (GameOptions.isShotTrajectoryTrace())
            for (Bullet bullet : model.getOldBullets())
                bullet.getView().drawPath(g);
    }

    private TankModel model;
}
