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

public class ExitAction extends AbstractAction {

    public ExitAction() {
        putValue(Action.NAME, "Quit");
        putValue(Action.SHORT_DESCRIPTION, "Press to quit game");

    }

    public void actionPerformed(ActionEvent e) {
        if (JOptionPane.showConfirmDialog(new JButton("Quit"), "Are you sure you want to quit?", "Quit?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            if (JOptionPane.showConfirmDialog((JButton) getValue("Quit"), "Do you want to save your statistics?", "Save?",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            }
            System.exit(0);
        }
    }
}