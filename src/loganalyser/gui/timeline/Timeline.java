/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.gui.timeline;

import java.awt.Component;
import java.util.Date;
import java.util.List;
import loganalyser.search.Case;
import loganalyser.search.Result;

/**
 *
 * @author Klaus
 */
public class Timeline<E> extends Component{

    public static final int Y_HEIGTH = 10;
    private Case<E> aktCase;
    private List<Result<E>> result;
    private int y = -1;
    private double widthMilliSecond = -1;
    private Date start;
    private Date end;

    public Timeline(Case<E> aktCase, List<Result<E>> result, Date start, Date end) {
        this.aktCase = aktCase;
        this.result = result;
        this.start = start;
        this.end = end;
    }
    
    public Case<E> getAktCase() {
        return aktCase;
    }

    public void setAktCase(Case<E> aktCase) {
        this.aktCase = aktCase;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<Result<E>> getResult() {
        return result;
    }

    public void setResult(List<Result<E>> result) {
        this.result = result;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public double getWidthMilliSecond() {
        return widthMilliSecond;
    }

    public void setWidthMilliSecond(double widthMilliSecond) {
        this.widthMilliSecond = widthMilliSecond;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

  
}
