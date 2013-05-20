package artillerygame;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
public class ArtilleryGame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JFrame jf = new GameFrame();
                jf.addWindowListener(new WindowAdapter() { // must show prompt
                    public void WindowClosing(WindowEvent e) {
                        System.out.println("exiting"); //for debugging
                        if (JOptionPane.showConfirmDialog(jf, "Are you sure you want to exit?", "Exit?",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        }
                    }
                });
            }
        });
    }
}
*/