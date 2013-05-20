package artillerygame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Control {

    public Control() {
        model = new ControlModel();
        view = new ControlView(model);
        controller = new ControlController(model, view);
    }

    public ControlView getView() {
        return view;
    }
    private ControlModel model;
    private ControlView view;
    private ControlController controller;
}

class ControlModel {

    Player currentPlayer = new Player();
    PlayerControl player1Control = new PlayerControl();
    PlayerControl player2Control = new PlayerControl();
}

class ControlView extends JPanel implements View {

    public ControlView(ControlModel model) {
        this.model = model;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        PlayerControl player1Control = new PlayerControl();
        PlayerControl player2Control = new PlayerControl();
        JPanel playerControlPanel = new JPanel(new GridLayout(1, 2));
        playerControlPanel.add(player1Control.getView());
        playerControlPanel.add(player2Control.getView());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(currentPlayerLabel);
        buttonPanel.add(optionsButton);
        buttonPanel.add(restartButton);

        add(playerControlPanel);
        add(buttonPanel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.repaint();
        update();
    }

    @Override
    public void update() {
        Player currentPlayer = model.currentPlayer;
        currentPlayerLabel.setText(currentPlayer.getName());
    }

    JButton exitButton = new JButton("Quit");
    JButton restartButton = new JButton("Restart");
    JButton saveButton = new JButton("Save");
    JButton optionsButton = new JButton("Options");
    JLabel currentPlayerLabel = new JLabel();
    ControlModel model;
}

class ControlController implements Controller {

    public ControlController(ControlModel model, ControlView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void initializeListeners() {
    }
    private ControlModel model;
    private ControlView view;
}