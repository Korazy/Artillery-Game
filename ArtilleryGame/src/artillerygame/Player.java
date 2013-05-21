package artillerygame;

import java.awt.Color;

public class Player {

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public void clear() {
        score = 0;
        tank = new Tank();
    }

    public void score() {
        score += 20;
        totalScore += 20;
    }

    public Tank getTank() {
        return tank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public void setColor(Color color) {
        this.color = color;
        tank.getModel().setColor(color);
    }

    public void setScore(int score) {
        this.score = score;
    }

    private Tank tank = new Tank();
    private String name;
    private Color color;
    private int score = 0;
    private int totalScore = 0;
}
