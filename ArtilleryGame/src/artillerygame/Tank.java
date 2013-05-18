package artillerygame;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Tank {

    class TankModel {

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
    }

    class TankView {
        void draw(Graphics2D g) {
            g.setStroke(new BasicStroke(1.0F));
            int x = model.getPositionX();
            int y = model.getPositionY();
            Rectangle2D rect = new Rectangle2D.Double(x - 20, y - 20, y + 20, y + 20);
            g.draw(rect);
        }
    }

    class TankController {
    }
    
    public void draw(Graphics2D g) {
        view.draw(g);
    }
    
    private TankModel model;
    private TankView view;
    private TankController controller;
}
