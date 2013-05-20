/*
package artillerygame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionFrame extends JFrame {
    public OptionFrame(final World world) {
        setSize(400,300);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel general = new JPanel(new GridLayout(4,1));
        JPanel panel1 = new JPanel(new GridLayout(1,2));
        JPanel panel2 = new JPanel(new GridLayout(1,2));
        JPanel panel3 = new JPanel(new GridLayout(1,2));
        JPanel panel4 = new JPanel(new GridLayout(1,2));
        final JComboBox theme = new JComboBox();
        final JCheckBox drawShot = new JCheckBox("Enable Shot Trajectory Trace");
        final JComboBox player1color = new JComboBox();
        final JComboBox player2color = new JComboBox();

        theme.addItem("space");
        theme.addItem("desert");
        theme.addItem("nature");

        player1color.addItem("Red");
        player1color.addItem("Blue");
        player1color.addItem("Green");

        player2color.addItem("Red");
        player2color.addItem("Blue");
        player2color.addItem("Green");

        theme.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selection = (String) theme.getSelectedItem();
                world.setBackground(selection);
            }
        });

        player1color.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //change tank color
            }
        });

        player2color.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // change tank color
            }
        });

        drawShot.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               //Add the listener
           }
        });

        panel1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        panel2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
        panel3.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        panel4.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));

        panel1.add(new JLabel("Theme: "));
        panel1.add(theme);
        panel2.add(new JLabel("Player 1 Color: "));
        panel2.add(player1color);
        panel3.add(new JLabel("Player 2 Color: "));
        panel3.add(player2color);
        panel4.add(drawShot);
        general.add(panel1);
        general.add(panel2);
        general.add(panel3);
        general.add(panel4);
        add(general);
    }
}
*/