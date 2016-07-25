/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.gui.timeline;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Date;
import java.util.List;
import loganalyser.search.Case;
import loganalyser.search.Result;

/**
 *
 * @author Klaus
 */
public class BooleanTimeline extends Timeline<Boolean> {

    public BooleanTimeline(Case<Boolean> aktCase, List<Result<Boolean>> result, Date start, Date end) {
        super(aktCase, result, start, end);
    }

    @Override
    public void paint(Graphics g) {
        int x = 0;
        int x2 = 0;
        int y2 = 0;

        if (getY() != -1 && getWidthMilliSecond() != -1) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getAktCase().getColor());


            for (Result<Boolean> r : getResult()) {
                x2 = (int) ((r.getDatum().getTime() - getStart().getTime()) * getWidthMilliSecond());
                y2 = getY() - Timeline.Y_HEIGTH;

                if (r.getValue()) {
                    g2.drawLine(x, getY(), x2, getY());
                    g2.drawLine(x2, getY(), x2, y2);
                } else {
                    g2.drawLine(x, y2, x2, y2);
                    g2.drawLine(x2, y2, x2, getY());
                }

                x = x2;
            }
        }
    }
}
