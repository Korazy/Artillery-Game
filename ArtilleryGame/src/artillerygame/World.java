package artillerygame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class World {

    public World() {
        model = new WorldModel();
        view = new WorldView(model);
    }

    public void restart() {
        model.restart();
        view.update();
    }

    public void update() {
        //model.update();
        view.update();
    }

    public WorldModel getModel() {
        return model;
    }

    public Tank getPlayer1Tank() {
        return model.player1Tank;
    }

    public Tank getPlayer2Tank() {
        return model.player1Tank;
    }

    public WorldView getView() {
        return view;
    }
    private WorldModel model;
    private WorldView view;
}

class WorldModel {

    public WorldModel() {
        numTanks = 2;
        terrain = new Terrain();
        player1Tank = GameOptions.getPlayer1().getTank();
        player2Tank = GameOptions.getPlayer2().getTank();
        initialize();
    }

    public void initialize() {
        randomPositionTank(player2Tank, player1Tank);
        randomPositionTank(player1Tank, player2Tank);
    }

    public void restart() {
        terrain = new Terrain();
        player1Tank = GameOptions.getPlayer1().getTank();
        player2Tank = GameOptions.getPlayer2().getTank();
        initialize();
    }

    public void positionTank(Tank tank, int x, int gap) {
        int width = GameOptions.getGameWidth();
        int x1 = x - Tank.WIDTH;
        int x2 = x + Tank.WIDTH;
        TankModel model = tank.getModel();

        if (x1 < 0) {
            x = Tank.WIDTH;
        } else if (x2 > (width - 1)) {
            x = width - Tank.WIDTH - 1;
        }

        // Lowest point the tank is standing on
        int[] landHeight = terrain.model.getLandHeight();
        int lowest = landHeight[x];
        for (int i = x1; i <= x2; i++) {
            if (lowest > landHeight[i]) {
                lowest = landHeight[i];
            }
        }

        // Flatten section + gap around it.
        for (int i = x1 - gap; i <= x2 + gap; i++) {
            if (i >= 0 && i < width) {
                landHeight[i] = lowest;
            }
        }

        model.setPositionX(x);
        model.setPositionY(lowest);
        model.update();
    }

    public void randomPositionTank(Tank newTank, Tank oldTank) {
        boolean clearSpace = false;
        int x;
        int width = GameOptions.getGameWidth();
        Random randGen = new Random();
        TankModel newModel = newTank.getModel();
        TankModel oldModel = oldTank.getModel();
        int oldX = oldModel.getPositionX();
        int oldY = oldModel.getPositionY();
        int minimumSeparation = 400;

        do {
            clearSpace = true;
            x = randGen.nextInt(width);
            if ((x - Tank.WIDTH < 0) || (x + Tank.WIDTH) > (width - 1)) {
                clearSpace = false;
            }
            if (((x + Tank.WIDTH >= oldX - Tank.WIDTH - minimumSeparation)
                    && (x + Tank.WIDTH <= oldX + Tank.WIDTH + minimumSeparation))
                    || ((x - Tank.WIDTH >= oldX - Tank.WIDTH - minimumSeparation)
                    && (x - Tank.WIDTH <= oldX + Tank.WIDTH + minimumSeparation))) {
                clearSpace = false;
            }
        } while (!clearSpace);

        positionTank(newTank, x, 5);
    }

    public Terrain getTerrain() {
        return terrain;
    }
    int numTanks;
    Bullet bullet;
    ArrayList<Bullet> oldBullets;
    Tank player1Tank;
    Tank player2Tank;
    Terrain terrain;
}

class WorldView implements View {

    public WorldView(WorldModel model) {
        this.model = model;
    }

    @Override
    public void update() {
    }

    public void draw(Graphics2D g) {
        TerrainView terrainView = model.terrain.getView();
        TankView tank1View = model.player1Tank.getView();
        TankView tank2View = model.player2Tank.getView();
        g.fillRect(0, 0, GameOptions.getGameWidth(), GameOptions.getGameWidth());
        terrainView.draw(g);
        tank1View.draw(g);
        tank2View.draw(g);
    }
    WorldModel model;
}
