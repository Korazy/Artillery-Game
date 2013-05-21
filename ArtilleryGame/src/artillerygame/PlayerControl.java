package artillerygame;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayerControl {

    public PlayerControl(Player player) {
        model = new PlayerControlModel(player);
        view = new PlayerControlView(model);
        controller = new PlayerControlController(model, view);
    }

    public void update() {
        view.update();
    }

    public void setActive(boolean active) {
        model.active = active;
        view.update();
    }

    public PlayerControlModel getModel() {
        return model;
    }

    public PlayerControlView getView() {
        return view;
    }

    public boolean isActive() {
        return model.active;
    }
    private PlayerControlModel model;
    private PlayerControlView view;
    private PlayerControlController controller;
}

class PlayerControlModel {

    public PlayerControlModel(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    boolean active = true;
    int power;
    int angle;
    Player player;
}

class PlayerControlView extends JPanel implements View {

    public PlayerControlView(PlayerControlModel model) {
        this.model = model;
        Player player = model.player;
        setLayout(new GridLayout(3, 3));
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, player.getColor()));
        add(new JLabel("Power:"));
        add(powerText);
        add(powerSlider);
        add(new JLabel("Angle:"));
        add(angleText);
        add(angleSlider);
        add(fireButton);
        add(new JLabel(""));
        add(playerLabel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.repaint();
        update();
    }

    @Override
    public void update() {
        Player player = model.player;
        playerLabel.setText(player.getName());
        powerText.setEnabled(model.active);
        angleText.setEnabled(model.active);
        powerSlider.setEnabled(model.active);
        angleSlider.setEnabled(model.active);
        fireButton.setEnabled(model.active);
    }
    JTextField powerText = new JTextField("0");
    JTextField angleText = new JTextField("0");
    JSlider powerSlider = new JSlider(0, 100, 0);
    JSlider angleSlider = new JSlider(0, 180, 0);
    JLabel playerLabel = new JLabel();
    JButton fireButton = new JButton("Fire!");
    PlayerControlModel model;
}

class PlayerControlController implements Controller {

    public PlayerControlController(PlayerControlModel model, PlayerControlView view) {
        this.model = model;
        this.view = view;
        initializeListeners();
    }

    public void addSliderTextListener(final JSlider slider, final JTextField text) {
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                text.setText(Integer.toString(slider.getValue()));
                view.update();
            }
        });

        text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                try {
                    slider.setValue(Integer.parseInt(text.getText()));
                } catch (NumberFormatException e) {
                    text.setText("0");
                }
                view.update();
            }
        });
    }

    public void addSliderAngleListener() {
        final JSlider slider = view.angleSlider;
        final JTextField text = view.angleText;
        view.angleSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int angle = slider.getValue();
                model.getPlayer().getTank().getModel().setAngle(angle);
                view.update();
            }
        });

        view.angleText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                try {
                    slider.setValue(Integer.parseInt(text.getText()));
                } catch (NumberFormatException e) {
                    text.setText("0");
                }
                int angle = slider.getValue();
                model.getPlayer().getTank().getModel().setAngle(angle);
                view.update();
            }
        });
    }

    public void addFireButtonListener() {
        view.fireButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Player player = model.getPlayer();
                Tank tank = player.getTank();
                int angle = view.angleSlider.getValue();
                tank.getModel().setAngle(angle);
                int power = view.powerSlider.getValue();
                tank.getModel().setPower(power);
                System.out.println(power);
                tank.fireBullet();
                model.active = !model.active;
                if (GameOptions.getCurrentPlayer() == GameOptions.getPlayer1()) {
                    GameOptions.setCurrentPlayer(GameOptions.getPlayer2());
                } else {
                    GameOptions.setCurrentPlayer(GameOptions.getPlayer1());
                }
                view.update();
            }
        });
    }

    @Override
    public void initializeListeners() {
        addSliderTextListener(view.powerSlider, view.powerText);
        addSliderTextListener(view.angleSlider, view.angleText);
        addFireButtonListener();
    }
    private PlayerControlModel model;
    private PlayerControlView view;
}
