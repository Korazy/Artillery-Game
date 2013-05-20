package artillerygame;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Theme {

    public Theme(String themeName) {
        this.themeName = themeName;
         /*
         File imagefile = new File("C:\\Users\\Korazy\\Documents\\GitHub\\Artillery-Game\\ArtilleryGame\\src\\artillerygame\\" + name + ".jpg");
         try {
         img = ImageIO.read(imagefile);
         } catch (IOException e) {
         e.printStackTrace();
         }
         */
        if (themeName.equals("space")) {
            drawColor = Color.WHITE;
        } else if (themeName.equals("nature")) {
            drawColor = Color.RED;
        } else {
            drawColor = Color.BLACK;
        }
    }

    public String getThemeName() {
        return themeName;
    }

    public Color getDrawColor() {
        return drawColor;
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public BufferedImage getTankImage() {
        return tankImage;
    }

    public BufferedImage getBulletImage() {
        return bulletImage;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public void setDrawColor(Color drawColor) {
        this.drawColor = drawColor;
    }

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setTankImage(BufferedImage tankImage) {
        this.tankImage = tankImage;
    }

    public void setBulletImage(BufferedImage bulletImage) {
        this.bulletImage = bulletImage;
    }

    private String themeName = "4343";
    private Color drawColor = Color.BLUE;
    private BufferedImage backgroundImage;
    private BufferedImage tankImage;
    private BufferedImage bulletImage;
}
