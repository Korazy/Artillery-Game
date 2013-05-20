package artillerygame;

import java.awt.Color;

public class GameOptions {

    public static Theme getTheme() {
        return theme;
    }

    public static boolean isShotTrajectoryTrace() {
        return shotTrajectoryTrace;
    }

    public static int getLandSmoothing() {
        return landSmoothing;
    }

    public static int getLandVariation() {
        return landVariation;
    }

    public static int getLandSmoothSize() {
        return landSmoothSize;
    }

    public static void setLandSmoothing(int aLandSmoothing) {
        landSmoothing = aLandSmoothing;
    }

    public static void setLandVariation(int aLandVariation) {
        landVariation = aLandVariation;
    }

    public static void setTheme(Theme aTheme) {
        theme = aTheme;
    }

    public static void setShotTrajectoryTrace(boolean aShotTrajectoryTrace) {
        shotTrajectoryTrace = aShotTrajectoryTrace;
    }

    public static void setLandSmoothSize(int aLandSmoothSize) {
        landSmoothSize = aLandSmoothSize;
    }


    public static int getGameWidth() {
        return gameWidth;
    }

    public static int getGameHeight() {
        return gameHeight;
    }

    public static void setGameWidth(int aGameWidth) {
        gameWidth = aGameWidth;
    }

    public static void setGameHeight(int aGameHeight) {
        gameHeight = aGameHeight;
    }


    private static Theme theme;
    private static boolean shotTrajectoryTrace;
    private static int landSmoothing = 200;
    private static int landVariation = 10;
    private static int landSmoothSize = 5;
    private static int gameWidth = 800;
    private static int gameHeight = 600;
}
