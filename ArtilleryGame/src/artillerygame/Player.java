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

    public int getDamage() {
        return damage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    private String name = "Player1";
    private Color color = Color.BLUE;
    private int score;
    private int damage;
}
