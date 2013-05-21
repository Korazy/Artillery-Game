
package artillerygame;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class FireAction extends AbstractAction {

    public FireAction(boolean which) {
        putValue(Action.NAME, "FIRE!");
        putValue(Action.SHORT_DESCRIPTION, "Press to fire and end your turn");
    }

    public void actionPerformed(ActionEvent e) {
        
    }
}