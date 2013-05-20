package artillerygame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                final JPanel score = new JPanel(new GridLayout(1, 2));
                score.add((new PlayerScore()).getView());
                score.add((new PlayerScore()).getView());
                frame.add(score, BorderLayout.NORTH);

                final Theme theme = new Theme("232");
                GameOptions.setTheme(theme);

                final Game game = new Game();
                JPanel gamePanel = new JPanel();
                gamePanel.add(game.getView());
                frame.add(gamePanel, BorderLayout.CENTER);

                final Control control = new Control();
                frame.add(control.getView(), BorderLayout.SOUTH);

                frame.setSize(GameOptions.getGameWidth(), GameOptions.getGameHeight());
                frame.setVisible(true);
                frame.setResizable(true);
                frame.pack();

                game.loop();

            }
        });
    }
}
