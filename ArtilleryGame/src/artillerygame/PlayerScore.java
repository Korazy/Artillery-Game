package artillerygame;

import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PlayerScore {

    public PlayerScore() {
        model = new PlayerScoreModel();
        view = new PlayerScoreView(model);
        controller = new PlayerScoreController(model, view);
    }

    PlayerScoreView getView() {
        return view;
    }

    private PlayerScoreModel model;
    private PlayerScoreView view;
    private PlayerScoreController controller;
}

class PlayerScoreModel {
    Player player = new Player();
}

class PlayerScoreView extends JPanel implements View {

    public PlayerScoreView(PlayerScoreModel model) {
        this.model = model;
        initialize();
    }

    @Override
    public void initialize() {
        setLayout(new GridLayout(3, 2));
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, model.player.getColor()));
        add(new JLabel("Name: "));
        playerName.setEditable(false);
        add(playerName);
        add(new JLabel("Damage Dealt: "));  // get amount of hits on player from model
        playerPlayerScore.setEditable(false);
        add(playerPlayerScore);
        add(new JLabel("Damage Taken: "));  // get amount of hits from player from model
        playerDamage.setEditable(false);
        add(playerDamage);
    }

    @Override
    public void paintComponent(Graphics g) {
        update();
        super.repaint();
    }

    @Override
    public void update() {
        Player player = model.player;
        playerName.setText(player.getName());
        playerPlayerScore.setText(player.getScore() + "");
        playerPlayerScore.setText(player.getDamage() + "");
    }

    JTextField playerName = new JTextField();
    JTextField playerPlayerScore = new JTextField();
    JTextField playerDamage = new JTextField();
    PlayerScoreModel model;
}

class PlayerScoreController {
    public PlayerScoreController(PlayerScoreModel model, PlayerScoreView view) {
        this.model = model;
        this.view = view;
    }

    private PlayerScoreModel model;
    private PlayerScoreView view;
}