/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artillerygame;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class RestartAction extends AbstractAction {

    public RestartAction() {
        putValue(Action.NAME, "Restart");
        putValue(Action.SHORT_DESCRIPTION, "Press to restart game");
    }

    public void actionPerformed(ActionEvent e) {
        if (JOptionPane.showConfirmDialog(new JButton("Restart"),
                "Are you sure you want to restart?", "Restart?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            GameOptions.restart();
        }
    }
}