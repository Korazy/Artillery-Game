package artillerygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameFrame extends JFrame {

    private int score1 = 0;
    private int score2 = 0;
    private boolean turn = false;
    private PrintWriter out;
    private ActionMap amap;
    private NameFrame NameEnter;
    private OptionFrame OptionsDialog;
    private TerrainView terrainview;
    private JTextField player1name;
    private JTextField player2name;
    private JTextField player1nameShow;
    private JTextField player2nameShow;
    private JTextField CurrentPlayer;
    private JTextField player1score;
    private JTextField player2score;
    private JTextField player1damages;
    private JTextField player2damages;
    private JTextField player1power;
    private JTextField player2angle;
    private JTextField player1angle;
    private JTextField player2power;
    private JButton Fire1;
    private JButton Fire2;
    private JButton Restart;
    private JButton Exit;
    private TerrainController terraincontroller;
    private JSlider Power1 = new JSlider(0, 100, 0);
    private JSlider Angle1 = new JSlider(0, 180, 0);
    private JSlider Power2 = new JSlider(0, 100, 0);
    private JSlider Angle2 = new JSlider(0, 180, 0);

    public GameFrame() {
        varInit();
        NameEnter = new NameFrame(this);
        NameEnter.setVisible(true);
        setSize(900, 700);
        setResizable(false);
        setVisible(true);
        setTitle("Tank Fight");
        createView();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameUpdate();
    }

    public void varInit() {
        try {
            out = new PrintWriter(new File("save.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        player1name = new JTextField("Player 1");
        player1name.setEditable(false);
        player2name = new JTextField("Player 2");
        player2name.setEditable(false);

        player1nameShow = new JTextField("Player 1");
        player1nameShow.setEditable(false);
        player2nameShow = new JTextField("Player 2");
        player2nameShow.setEditable(false);

        CurrentPlayer = new JTextField("Current Player: Player 1");
        CurrentPlayer.setEditable(false);

        player1score = new JTextField(score1 + "");
        player2score = new JTextField(score2 + "");

        player1damages = new JTextField(score2 + "");
        player1damages.setEditable(false);
        player2damages = new JTextField(score1 + "");
        player2damages.setEditable(false);

        player1power = new JTextField("0");
        player2angle = new JTextField("0");
        player1angle = new JTextField("0");
        player2power = new JTextField("0");

        player1score.setEditable(false);
        player2score.setEditable(false);
        

    }

    public void createView() {
        JPanel GeneralPanel = new JPanel();
        GeneralPanel.setLayout(new BorderLayout());

        JPanel PlayerPanel = createPlayers();
        JPanel GamePanel = createGame(); //draw game screen (INCOMPLETE)
        JPanel ControlPanel = createControl();

        GeneralPanel.add(PlayerPanel, BorderLayout.NORTH);
        GeneralPanel.add(GamePanel, BorderLayout.CENTER);
        GeneralPanel.add(ControlPanel, BorderLayout.SOUTH);
        createKeyMapping(GeneralPanel);
        OptionsDialog = new OptionFrame(terrainview, this);

        add(GeneralPanel);
    }

    public JPanel createPlayers() {
        JPanel players = new JPanel();
        JPanel player1 = new JPanel();
        JPanel player2 = new JPanel();

        players.setLayout(new GridLayout(1, 2));
        player1.setLayout(new GridLayout(3, 2));
        player2.setLayout(new GridLayout(3, 2));

        player1.add(new JLabel("Name: "));
        player1.add(player1name);
        player1.add(new JLabel("Damage Dealt: "));  // get amount of hits on player 2 from model
        player1.add(player1score);
        player1.add(new JLabel("Damage Taken: "));  // get amount of hits from player 2 from model
        player1.add(player1damages);
        player2.add(new JLabel("Name: "));
        player2.add(player2name);
        player2.add(new JLabel("Damage Dealt: ")); // get amount of hits on player 1 from model
        player2.add(player2score);
        player2.add(new JLabel("Damage Taken: "));  // get amount of hits from player 1 from model
        player2.add(player2damages);

        player1.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(128, 0, 0)));
        player2.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 0, 128)));

        players.add(player1);
        players.add(player2);
        return players;
    }

    public JPanel createGame() {
        JPanel Game = new JPanel();
        Game.setSize(800, 600);
        Game.setLayout(new GridLayout(1, 1));
        Game.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        Terrain terrain = new Terrain(Game.getWidth(), Game.getHeight());
        terrainview = new TerrainView(terrain);
        terraincontroller = new TerrainController(terrain, terrainview);

        Game.add(terrainview);
        terraincontroller.start();
        return Game;
    }

    public JPanel createControl() {
        JPanel Control = new JPanel();
        Control.setLayout(new BoxLayout(Control, BoxLayout.Y_AXIS));
        JPanel player1 = new JPanel(new GridLayout(3, 3));
        JPanel player2 = new JPanel(new GridLayout(3, 3));
        JPanel Players = new JPanel(new GridLayout(1, 2));
        JPanel Buttons = new JPanel();

        createFireListeners();
        createSliderListeners();
        createPowerAndAngleListeners();

        player1.add(new JLabel("Power:"));
        player1.add(player1power);
        player1.add(Power1);
        player1.add(new JLabel("Angle:"));
        player1.add(player1angle);
        player1.add(Angle1);
        player1.add(Fire1);
        player1.add(new JLabel(""));
        player1.add(player1nameShow);

        player2.add(new JLabel("Power:"));
        player2.add(player2power);
        player2.add(Power2);
        player2.add(new JLabel("Angle:"));
        player2.add(player2angle);
        player2.add(Angle2);
        player2.add(Fire2);
        player2.add(new JLabel(""));
        player2.add(player2nameShow);

        player1.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(128, 0, 0)));
        player2.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(0, 0, 128)));

        Exit = new JButton("Quit");
        Restart = new JButton("Restart");
        JButton Save = new JButton("Save");
        JButton Options = new JButton("Options");

        createButtonListeners(Save, Options);
        Buttons.add(Exit);
        Buttons.add(Save);
        Buttons.add(CurrentPlayer);
        Buttons.add(Options);
        Buttons.add(Restart);
        Players.add(player1);
        Players.add(player2);
        Control.add(Players);
        Control.add(Buttons);
        return Control;
    }

    public void reset() {
        score1 = 0;
        score2 = 0;
        player1score.setText(score1 + "");
        player2score.setText(score2 + "");
        terraincontroller.restart();
        NameEnter.setVisible(true);
    }

    public void createFireListeners() {
        Fire1 = new JButton(new FireAction(true));
        Fire2 = new JButton(new FireAction(false));
    }

    public void createSliderListeners() {

        Power1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                player1power.setText(Integer.toString(Power1.getValue()));
            }
        });

        Power2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                player2power.setText(Integer.toString(Power2.getValue()));
            }
        });

        Angle1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                player1angle.setText(Integer.toString(Angle1.getValue()));
            }
        });

        Angle2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                player2angle.setText(Integer.toString(Angle2.getValue()));
            }
        });

    }

    public void createPowerAndAngleListeners() {
        player1power.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                try {
                    Power1.setValue(Integer.parseInt(player1power.getText()));
                } catch (NumberFormatException e) {
                    player1power.setText("0");
                }
            }
        });

        player2power.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                try {
                    Power2.setValue(Integer.parseInt(player2power.getText()));
                } catch (NumberFormatException e) {
                    player2power.setText("0");
                }
            }
        });

        player1angle.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                try {
                    Angle1.setValue(Integer.parseInt(player1angle.getText()));
                } catch (NumberFormatException e) {
                    player1angle.setText("0");
                }
            }
        });

        player2angle.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                try {
                    Angle2.setValue(Integer.parseInt(player2angle.getText()));
                } catch (NumberFormatException e) {
                    player2angle.setText("0");
                }
            }
        });
    }

    public void createButtonListeners(final JButton Save, final JButton Options) {

        Exit.addActionListener(new ExitAction(Exit));

        Restart.addActionListener(new RestartAction(Restart));

        Save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        Options.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OptionsDialog.setVisible(true);
            }
        });

    }

    public void createKeyMapping(JPanel panel) {
        InputMap imap = panel.getInputMap(JComponent.WHEN_FOCUSED);
        imap.put(KeyStroke.getKeyStroke("Q"), "panel.quit");
        imap.put(KeyStroke.getKeyStroke("R"), "panel.restart");
        imap.put(KeyStroke.getKeyStroke("ctrl Q"), "panel.quit");
        imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.restart");
        imap.put(KeyStroke.getKeyStroke("ENTER"), "panel.fire");
        imap.put(KeyStroke.getKeyStroke("F"), "panel.fire");
        imap.put(KeyStroke.getKeyStroke("UP"), "panel.incAngle");
        imap.put(KeyStroke.getKeyStroke("DOWN"), "panel.decAngle");
        imap.put(KeyStroke.getKeyStroke("RIGHT"), "panel.incPower");
        imap.put(KeyStroke.getKeyStroke("LEFT"), "panel.decPower");

        amap = panel.getActionMap();
        amap.put("panel.quit", new ExitAction(Exit));
        amap.put("panel.restart", new RestartAction(Restart));
        amap.put("panel.incAngle", new changeAngle(1));
        amap.put("panel.decAngle", new changeAngle(0));
        amap.put("panel.incPower", new changePower(1));
        amap.put("panel.decPower", new changePower(0));
        fireMap();

        JPopupMenu menu = new JPopupMenu();
        menu.add(new RestartAction(Restart));
        menu.add(new ExitAction(Exit));
        panel.setComponentPopupMenu(menu);

    }

    public void fireMap() {
        amap.put("panel.fire", new FireAction(!turn));
    }

    public void GameUpdate() { // must update scores as well
        if (turn) {
            CurrentPlayer.setText("Current Player: " + player2nameShow.getText());
            Fire1.setEnabled(false);
            player1angle.setEditable(false);
            player1power.setEditable(false);
            Power1.setEnabled(false);
            Angle1.setEnabled(false);
            Fire2.setEnabled(true);
            player2angle.setEditable(true);
            player2power.setEditable(true);
            Power2.setEnabled(true);
            Angle2.setEnabled(true);
        } else {
            CurrentPlayer.setText("Current Player: " + player1nameShow.getText());
            Fire2.setEnabled(false);
            player2angle.setEditable(false);
            player2power.setEditable(false);
            Power2.setEnabled(false);
            Angle2.setEnabled(false);
            Fire1.setEnabled(true);
            player1angle.setEditable(true);
            player1power.setEditable(true);
            Power1.setEnabled(true);
            Angle1.setEnabled(true);
        }
        fireMap();
    }

    public void save() {
        out.println(player1nameShow.getText() + " statistics:");
        out.println("Damage Dealt: " + player1score.getText() + "\n");
        out.println("Damage Taken: " + player1damages.getText() + "\n");
        out.println(player2nameShow.getText() + " statistics:");
        out.println("Damage Dealt: " + player2score.getText() + "\n");
        out.println("Damage Taken: " + player2damages.getText() + "\n");
    }

    class NameFrame extends JDialog {

        public NameFrame(JFrame frame) {
            super(frame, "Enter Names", true);
            setSize(200,100);
            setLocation(500, 400);
            setResizable(false);
            JPanel general = new JPanel(new GridLayout(3, 2));
            final JTextField p1 = new JTextField("Player 1");
            final JTextField p2 = new JTextField("Player 2");
            general.add(new JLabel("Player 1 name: "));
            general.add(p1);
            general.add(new JLabel("Player 2 name: "));
            general.add(p2);
            JButton namesave = new JButton("Save");
            general.add(namesave);
            namesave.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    player1name.setText(p1.getText());
                    player2name.setText(p2.getText());
                    player1nameShow.setText(p1.getText());
                    player2nameShow.setText(p2.getText());
                    setVisible(false);
                }
            });
            add(general);
        }
    }

    class FireAction extends AbstractAction {

        public FireAction(boolean which) {
            putValue(Action.NAME, "FIRE!");
            putValue(Action.SHORT_DESCRIPTION, "Press to fire and end your turn");
            putValue("1or2", which);
        }

        public void actionPerformed(ActionEvent e) {
            // Proceed with firing animation
            turn = (boolean) getValue("1or2");
            GameUpdate();
        }
    }

    class RestartAction extends AbstractAction {

        public RestartAction(JButton res) {
            putValue(Action.NAME, "Restart");
            putValue(Action.SHORT_DESCRIPTION, "Press to restart game");
            putValue("button", res);
        }

        public void actionPerformed(ActionEvent e) {
            if (JOptionPane.showConfirmDialog((JButton) getValue("button"),
                    "Are you sure you want to restart?", "Restart?",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                reset();
            }
        }
    }

    class ExitAction extends AbstractAction {

        public ExitAction(JButton quit) {
            putValue(Action.NAME, "Quit");
            putValue(Action.SHORT_DESCRIPTION, "Press to quit game");
            putValue("Quit", quit);
        }

        public void actionPerformed(ActionEvent e) {
            if (JOptionPane.showConfirmDialog((JButton) getValue("Quit"), "Are you sure you want to quit?", "Quit?",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                if (JOptionPane.showConfirmDialog((JButton) getValue("Quit"), "Do you want to save your statistics?", "Save?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    save();
                }
                out.close();
                System.exit(0);
            }
        }
    }

    class changeAngle extends AbstractAction {

        public changeAngle(int UPorDOWN) {
            putValue("u_or_d", UPorDOWN);
        }

        public void actionPerformed(ActionEvent e) {
            int uORd = (int) getValue("u_or_d");
            if (!turn) {
                if (uORd == 1) {
                    player1angle.setText((Integer.parseInt(player1angle.getText()) + 1) + "");
                    Angle1.setValue(Integer.parseInt(player1angle.getText()));
                } else {
                    uORd = Math.max((Integer.parseInt(player1angle.getText()) - 1), 0);
                    player1angle.setText(uORd + "");
                    Angle1.setValue(Integer.parseInt(player1angle.getText()));
                }
            } else {
                if (uORd == 1) {
                    player2angle.setText((Integer.parseInt(player2angle.getText()) + 1) + "");
                    Angle2.setValue(Integer.parseInt(player2angle.getText()));
                } else {
                    uORd = Math.max((Integer.parseInt(player2angle.getText()) - 1), 0);
                    player2angle.setText(uORd + "");
                    Angle2.setValue(Integer.parseInt(player2angle.getText()));
                }
            }
        }
    }

    class changePower extends AbstractAction {

        public changePower(int LEFTorRIGHT) {
            putValue("l_or_r", LEFTorRIGHT);
        }

        public void actionPerformed(ActionEvent e) {
            int lORr = (int) getValue("l_or_r");
            if (!turn) {
                if (lORr == 1) {
                    player1power.setText((Integer.parseInt(player1power.getText()) + 1) + "");
                    Power1.setValue(Integer.parseInt(player1power.getText()));
                } else {
                    lORr = Math.max((Integer.parseInt(player1power.getText()) - 1), 0);
                    player1power.setText(lORr + "");
                    Power1.setValue(Integer.parseInt(player1power.getText()));
                }
            } else {
                if (lORr == 1) {
                    player2power.setText((Integer.parseInt(player2power.getText()) + 1) + "");
                    Power2.setValue(Integer.parseInt(player2power.getText()));
                } else {
                    lORr = Math.max((Integer.parseInt(player2power.getText()) - 1), 0);
                    player2power.setText(lORr + "");
                    Power2.setValue(Integer.parseInt(player2power.getText()));
                }
            }
        }
    }
}
