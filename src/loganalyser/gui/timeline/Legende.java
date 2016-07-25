/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.gui.timeline;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import loganalyser.search.Case;

/**
 *
 * @author Klaus
 */
public class Legende extends JPanel {

    public Legende(List<Case<?>> cases) {
        super();
        this.setBackground(Color.WHITE);
        setLayout(new GridLayout(cases.size(), 2));

        JPanel color;
        for (Case<?> c : cases) {
            color = new JPanel();
            color.setBackground(c.getColor());
            color.setPreferredSize(new Dimension(10, 10));

            add(color);
            add(new JLabel(c.getName()));

        }
    }
}
