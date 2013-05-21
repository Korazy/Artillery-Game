package artillerygame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class Game {

    public Game() {
        model = new GameModel();
        view = new GameView(model);
    }

    public void loop() {
        new Thread(new Runnable() {
            public void run() {
                model.running = true;
                while (model.running) {
                    try {
                        update();
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void update() {
        model.update();
        view.update();
    }

    public GameView getView() {
        return view;
    }

    private GameModel model;
    private GameView view;
    private GameController controller;
}

class GameModel {

    public GameModel() {
        this.world = new World();
    }

    public void update() {
        world.update();
    }


    boolean running = false;
    World world;
}

class GameView extends JComponent implements View {

    public GameView(GameModel model) {
        this.model = model;
        graphicsContext = (Graphics2D) getGraphics();
        setSize(GameOptions.getGameWidth(), GameOptions.getGameHeight());
    }

    public Dimension getPreferredSize() {
        return (new Dimension(GameOptions.getGameWidth(), GameOptions.getGameHeight()));
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        WorldView worldView = model.world.getView();
        worldView.draw(g2);
    }
    
    Graphics2D graphicsContext;
    GameModel model;
}

class GameController implements Controller {

    @Override
    public void initializeListeners() {
        view.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    model.running = !model.running;
                }
            }
        });
    };

    private GameModel model;
    private GameView view;
}