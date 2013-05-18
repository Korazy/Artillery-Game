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
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameFrame extends JFrame {

    private int score1 = 0;
    private int score2 = 0;
    private boolean turn = false;
    private PrintWriter out;
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
    private JButton Fire1 = new JButton("Fire!");
    private JButton Fire2 = new JButton("Fire!");
    private JSlider Power1 = new JSlider(0, 100);
    private JSlider Angle1 = new JSlider(0, 180);
    private JSlider Power2 = new JSlider(0, 100);
    private JSlider Angle2 = new JSlider(0, 180);

    public GameFrame() {
        varInit();
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
        player2name = new JTextField("Player 2");

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

        player1power = new JFormattedTextField(NumberFormat.getInstance());
        player2angle = new JFormattedTextField(NumberFormat.getInstance());
        player1angle = new JFormattedTextField(NumberFormat.getInstance());
        player2power = new JFormattedTextField(NumberFormat.getInstance());

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

        add(GeneralPanel);
        createNameListeners();
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

        World world = new World();
        Game.add(world.getView());
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

        JButton Exit = new JButton("Exit");
        JButton Restart = new JButton("Restart");
        JButton Save = new JButton("Save");
        JButton Options = new JButton("Options"); //add listeners

        createButtonListeners(Exit, Restart, Save, Options);
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
    }

    public void createNameListeners() {
        player1name.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                player1nameShow.setText(player1name.getText());
                GameUpdate();
            }
        });
        player2name.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                player2nameShow.setText(player2name.getText());
                GameUpdate();
            }
        });
    }

    public void createFireListeners() {
        Fire1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Proceed with firing animation
                turn = true;
                GameUpdate();
            }
        });
        Fire2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Proceed with firing animation
                turn = false;
                GameUpdate();
            }
        });
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
                    player1power.setText("");
                }
            }
        });

        player2power.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                try {
                    Power2.setValue(Integer.parseInt(player2power.getText()));
                } catch (NumberFormatException e) {
                    player2power.setText("");
                }
            }
        });

        player1angle.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                try {
                    Angle1.setValue(Integer.parseInt(player1angle.getText()));
                } catch (NumberFormatException e) {
                    player1angle.setText("");
                }
            }
        });

        player2angle.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                try {
                    Angle2.setValue(Integer.parseInt(player2angle.getText()));
                } catch (NumberFormatException e) {
                    player2angle.setText("");
                }
            }
        });
    }

    public void createButtonListeners(final JButton Exit, final JButton Restart, final JButton Save, final JButton Options) {

        Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(Exit, "Are you sure you want to exit?", "Exit?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    out.close();
                    System.exit(0);
                }
            }
        });

        Restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(Restart,
                        "Are you sure you want to restart?", "Restart?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    reset();
                }
            } //ADD OPTIONS
        });

        Save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(player1nameShow.getText() + " statistics:");
                out.println("Damage Dealt: " + player1score.getText() + "\n");
                out.println("Damage Taken: " + player1damages.getText() + "\n");
                out.println(player2nameShow.getText() + " statistics:");
                out.println("Damage Dealt: " + player2score.getText() + "\n");
                out.println("Damage Taken: " + player2damages.getText() + "\n");
            }
        });

        Options.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
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
    }
}
