/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.gui.timeline;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import loganalyser.search.Case;
import loganalyser.search.Result;
import loganalyser.search.cases.BooleanCase;
import loganalyser.search.cases.NumberCase;

/**
 *
 * @author Klaus
 */
public class TimelinePanel extends JPanel implements MouseWheelListener, MouseListener {

    private static final Font font = new Font("Serif", Font.PLAIN, 10);
    private static final int ABSTAND_DATE = 50;
    private static final int ZOOM = 10000;
    Map<Case<?>, List<Result<?>>> result;
    private Date start;
    private Date end;
    private List<Timeline<?>> lines;
    private long duration;
    private DateFormat df;

    public TimelinePanel(Map<Case<?>, List<Result<?>>> result, DateFormat df) {
        this.df = df;
        this.result = result;

        lines = new LinkedList<Timeline<?>>();

        this.setBackground(Color.WHITE);
        addMouseWheelListener(this);
        addMouseListener(this);

        start = Result.getStartDate(result);
        end = Result.getEndDate(result);

        calcDuration();

        createLines();

    }

    public TimelinePanel() {
        this(null, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    @SuppressWarnings("unchecked")
    private void createLines() {
        for (Case<?> c : result.keySet()) {
            if (c instanceof BooleanCase) {
                List<Result<Boolean>> l = new LinkedList<Result<Boolean>>();
                for (Result<?> r : result.get(c)) {
                    l.add((Result<Boolean>) r);
                }
                lines.add(new BooleanTimeline((Case<Boolean>) c, l, start, end));
            }
//            else if(c instanceof NumberCase){
//                 List<Result<Number>> l = new LinkedList<Result<Number>>();
//                for(Result<?> r : result.get(c)){
//                    l.add((Result<Number> )r);
//                }
//                lines.add(new NumberTimeline((Case<Number>)c, l, getWidthSecond(), LINE_PADDING*i));
//            }

        }
    }

    private void updateLines() {
        for (Timeline<?> line : lines) {
            line.setStart(start);
            line.setEnd(end);
        }
    }

    private void calcDuration() {
        duration = end.getTime() - start.getTime();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int h = this.getHeight() - 20;
        int w = this.getWidth();
        double hPerLine = h / (double) lines.size();
        double widthMilliSecond = w / (double) duration;

        int i = 0;
        for (Timeline<?> line : lines) {
            line.setY((int) Math.round(h - 20 - (hPerLine * i)));
            line.setWidthMilliSecond(widthMilliSecond);
            line.paint(g);
            i++;
        }

        //Zeitbeschriftung
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        double dateWidth = fm.getStringBounds(df.format(new Date()), g).getWidth();
        int dateHeigth = (int) Math.ceil(fm.getStringBounds(df.format(new Date()), g).getHeight());

        int anzZeiten = (int) (w / (ABSTAND_DATE + Math.round(dateWidth)));

        int x = 0;
        Date d;
        for (int j = 0; j < anzZeiten; j++) {
            g.setColor(Color.BLACK);
            d = new Date(start.getTime() + (long) ((j * (dateWidth + ABSTAND_DATE) + dateWidth / 2) / widthMilliSecond));
            g.drawString(df.format(d), x, h);

            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(x + ((int) dateWidth / 2), h - dateHeigth, x + ((int) dateWidth / 2), 0);

            x += dateWidth + ABSTAND_DATE;
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int anz = e.getWheelRotation();

        if (anz < 0) {
            zoomIn();
        } else {
            zoomOut();
        }
    }

    private void zoomOut() {
        start = new Date(start.getTime() - ZOOM);
        end = new Date(end.getTime() + ZOOM);
        calcDuration();
        updateLines();
        repaint();
    }

    private void zoomIn() {
        start = new Date(start.getTime() + ZOOM);
        end = new Date(end.getTime() - ZOOM);
        calcDuration();
        updateLines();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getPoint().x > this.getWidth() / 2) {
            start = new Date(start.getTime() + ZOOM);
            end = new Date(end.getTime() + ZOOM);
        } else {
            start = new Date(start.getTime() - ZOOM);
            end = new Date(end.getTime() - ZOOM);
        }
        calcDuration();
        updateLines();
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
