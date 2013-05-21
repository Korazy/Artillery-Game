package artillerygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class NameFrame extends JDialog {

    public NameFrame(JFrame frame) {
        super(frame, Dialog.ModalityType.DOCUMENT_MODAL);
        setTitle("Enter Names");
        setSize(300, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                GameOptions.getPlayer1().setName(p1.getText());
                GameOptions.getPlayer2().setName(p2.getText());
                setVisible(false);
                dispose();
            }
        });
    }
}

public class MainFrame {

    public static void main(String[] args) {
        GameOptions.initialize();
        final Player player1 = new Player();
        player1.setName("Player 1");
        player1.setColor(Color.GREEN);
        GameOptions.setPlayer1(player1);
        final Player player2 = new Player();
        player2.setName("Player 2");
        player2.setColor(Color.RED);
        GameOptions.setPlayer2(player2);


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                NameFrame nameFrame = new NameFrame(frame);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel score = new JPanel(new GridLayout(1, 2));
                PlayerScore player1Score = new PlayerScore(player1);
                GameOptions.setPlayer1Score(player1Score);
                PlayerScore player2Score = new PlayerScore(player2);
                GameOptions.setPlayer2Score(player2Score);
                score.add(player1Score.getView());
                score.add(player2Score.getView());
                frame.add(score, BorderLayout.NORTH);

                Game game = new Game();
                JPanel gamePanel = new JPanel();
                gamePanel.add(game.getView());
                frame.add(gamePanel, BorderLayout.CENTER);

                Control control = new Control(player1, player2);
                frame.add(control.getView(), BorderLayout.SOUTH);

                frame.setSize(GameOptions.getGameWidth(), GameOptions.getGameHeight());
                nameFrame.repaint();
                nameFrame.setVisible(true);
                frame.setVisible(true);
                frame.setResizable(true);
                frame.pack();

                game.loop();

            }
        });
    }
}
