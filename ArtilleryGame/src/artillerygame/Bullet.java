package artillerygame;

import java.awt.Graphics2D;

public class Bullet {

    private BulletModel model;
    private BulletView view;
    private BulletController controller;
}

class BulletModel {

    private double startX;
    private double startY;
    private double startPower;
    private double startAngle;
    private double positionX;
    private double positionY;
    private double power;
    private double angle;
}

class BulletView {
    
    void draw(Graphics2D g) {
        
    }
}

class BulletController {
    
}