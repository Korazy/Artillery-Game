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

    public PlayerControl() {
        model = new PlayerControlModel();
        view = new PlayerControlView(model);
        controller = new PlayerControlController(model, view);
        controller.initializeListeners();
    }

    public void update() {
        view.update();
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

    boolean active;
    int power;
    int angle;
    Player player = new Player();
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

class PlayerControlController {

    public PlayerControlController(PlayerControlModel model, PlayerControlView view) {
        this.model = model;
        this.view = view;
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

    public void addFireButtonListener() {
        view.fireButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.active = false;
                view.update();
            }
        });
    }

    public void initializeListeners() {
        addSliderTextListener(view.powerSlider, view.powerText);
        addSliderTextListener(view.angleSlider, view.angleText);
        addFireButtonListener();
    }
    private PlayerControlModel model;
    private PlayerControlView view;
}
