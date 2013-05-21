package artillerygame;

import javax.swing.JOptionPane;

public class GameOptions {

    public static void initialize() {
        theme = new Theme("");
        player1 = new Player();
        player2 = new Player();
    }

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

    public static int getGameWidth() {
        return gameWidth;
    }

    public static int getGameHeight() {
        return gameHeight;
    }

    public static int getRefreshRate() {
        return refreshRate;
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static World getWorld() {
        return world;
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

    public static void setPlayer1(Player aPlayer) {
        player1 = aPlayer;
    }

    public static void setPlayer2(Player aPlayer) {
        player2 = aPlayer;
    }

    public static void setShotTrajectoryTrace(boolean aShotTrajectoryTrace) {
        shotTrajectoryTrace = aShotTrajectoryTrace;
    }

    public static void setLandSmoothSize(int aLandSmoothSize) {
        landSmoothSize = aLandSmoothSize;
    }

    public static void setGameWidth(int aGameWidth) {
        gameWidth = aGameWidth;
    }

    public static void setGameHeight(int aGameHeight) {
        gameHeight = aGameHeight;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static int getDamageMax() {
        return damageMax;
    }

    public static void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public static void setWorld(World aWorld) {
        world = aWorld;
    }

    public static int getTankPlaceGap() {
        return tankPlaceGap;
    }

    public static int getTankMinDistance() {
        return tankMinDistance;
    }

    public static void setTankPlaceGap(int aTankPlaceGap) {
        tankPlaceGap = aTankPlaceGap;
    }

    public static void setTankMinDistance(int aTankMinDistance) {
        tankMinDistance = aTankMinDistance;
    }

    public static double getMinPercHeight() {
        return minPercHeight;
    }

    public static double getMaxPercHeight() {
        return maxPercHeight;
    }

    public static void setMinPercHeight(double aMinPercHeight) {
        minPercHeight = aMinPercHeight;
    }

    public static void setMaxPercHeight(double aMaxPercHeight) {
        maxPercHeight = aMaxPercHeight;
    }

    public static PlayerScore getPlayer1Score() {
        return player1Score;
    }

    public static PlayerScore getPlayer2Score() {
        return player2Score;
    }

    public static void setPlayer1Score(PlayerScore aPlayer1Score) {
        player1Score = aPlayer1Score;
    }

    public static void setPlayer2Score(PlayerScore aPlayer2Score) {
        player2Score = aPlayer2Score;
    }

    public static double getDamage() {
        return damage;
    }

    public static void restart() {
        player1.clear();
        player2.clear();
        world.restart();
    }

    public static void gameover() {
        int result = JOptionPane.showConfirmDialog(null,
                "Do you want a rematch?", "Restart?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            GameOptions.restart();
        }

    }
    private static Theme theme;
    private static boolean shotTrajectoryTrace = false;
    private static int refreshRate = 20;
    private static int landSmoothing = 200;
    private static int landVariation = 10;
    private static int landSmoothSize = 5;
    private static int damage = 20;
    private static int damageMax = 100;
    private static int tankPlaceGap = 5;
    private static int tankMinDistance = 400;
    private static double minPercHeight = 0.8;
    private static double maxPercHeight = 0.9;
    private static int gameWidth = 800;
    private static int gameHeight = 600;
    // Necessary Evil.
    private static Player player1;
    private static Player player2;
    private static Player currentPlayer;
    private static PlayerScore player1Score;
    private static PlayerScore player2Score;
    private static World world;
}
