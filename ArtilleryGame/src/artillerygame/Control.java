package artillerygame;

import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Control {

    public Control(Player player1, Player player2) {
        model = new ControlModel(player1, player2);
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

    public ControlModel(Player player1, Player player2) {
        GameOptions.setCurrentPlayer(player1);
        player1Control = new PlayerControl(player1);
        player2Control = new PlayerControl(player2);
        player2Control.setActive(false);
    }
    PlayerControl player1Control;
    PlayerControl player2Control;
}

class ControlView extends JPanel implements View {

    public ControlView(ControlModel model) {
        this.model = model;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel playerControlPanel = new JPanel(new GridLayout(1, 2));
        playerControlPanel.add(model.player1Control.getView());
        playerControlPanel.add(model.player2Control.getView());

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
        Player currentPlayer = GameOptions.getCurrentPlayer();
        if (currentPlayer == GameOptions.getPlayer1()) {
            model.player1Control.setActive(true);
            model.player2Control.setActive(false);
        } else {
            model.player2Control.setActive(true);
            model.player1Control.setActive(false);
        }
        currentPlayerLabel.setText("Current Player:   " + currentPlayer.getName());
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
        initializeListeners();
    }

    @Override
    public void initializeListeners() {
        view.exitButton.addActionListener(new ExitAction());
        view.restartButton.addActionListener(new RestartAction());
        view.optionsButton.addActionListener(new OptionAction());
    }
    private ControlModel model;
    private ControlView view;
}