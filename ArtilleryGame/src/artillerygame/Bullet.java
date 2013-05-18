package artillerygame;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Bullet {

    private class BulletModel {

        public BulletModel() {
            double radians = angle * Math.PI / 180.0;
            velocityX = speed * Math.cos(radians);
            velocityY = speed * Math.sin(radians);
        }

        public void update() {
            positionX = positionX + velocityX * dt;
            positionY = positionY + velocityY * dt - 0.5 * G * dt * dt;
            velocityY = velocityY - dt * dt;
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

        public double getSpeed() {
            return speed;
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

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public void setAngle(double angle) {
            this.angle = angle;
        }

        private double positionX;
        private double positionY;
        private double velocityX;
        private double velocityY;
        private double speed;
        private double angle;
    }

    private class BulletView {
        void draw(Graphics2D g) {
            model.update();
            double x = model.getPositionX();
            double y = model.getPositionY();
            Rectangle2D rect = new Rectangle2D.Double(x - 4, y - 4, x + 4, y + 4);
            g.draw(rect);
        }
    }
    
    public void draw(Graphics2D g) {
        view.draw(g);
    }
    
    private BulletModel model;
    private BulletView view;

    private static double G = 9.81;
    private static double dt = 0.1;
}
