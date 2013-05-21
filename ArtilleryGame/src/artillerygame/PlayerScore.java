package artillerygame;

import artillerygame.Player;
import artillerygame.View;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerScore {

    public PlayerScore(Player player) {
        model = new PlayerScoreModel(player);
        view = new PlayerScoreView(model);
    }

    PlayerScoreView getView() {
        return view;
    }

    public void update() {
        view.update();
    }

    private PlayerScoreModel model;
    private PlayerScoreView view;
}

class PlayerScoreModel {
    public PlayerScoreModel(Player player) {
        this.player = player;
    }
    Player player;
}

class PlayerScoreView extends JPanel implements View {

    public PlayerScoreView(PlayerScoreModel model) {
        this.model = model;
        setLayout(new GridLayout(3, 2));
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, model.player.getColor()));
        add(new JLabel("Name: "));
        playerName.setEditable(false);
        add(playerName);
        add(new JLabel("Damage Dealt: ")); // get amount of hits on player from model
        playerScore.setEditable(false);
        add(playerScore);
        add(new JLabel("Damage Taken: ")); // get amount of hits from player from model
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
        playerDamage.setText(player.getTank().getModel().getDamage() + "");
        playerScore.setText(player.getScore() + "");
    }

    JTextField playerName = new JTextField();
    JTextField playerScore = new JTextField();
    JTextField playerDamage = new JTextField();
    PlayerScoreModel model;
}
