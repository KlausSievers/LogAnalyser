/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.export;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import loganalyser.gui.timeline.DlgTimeline;
import loganalyser.search.Case;
import loganalyser.search.Result;

/**
 *
 * @author Klaus
 */
public class TimeLineExporter implements ResultExporter {

    private DateFormat df;

    public TimeLineExporter() {
        this("yyyy-MM-dd HH:mm:ss.SSS");
    }

    public TimeLineExporter(String dateformat) {
        this(new SimpleDateFormat(dateformat));
    }
    
    public TimeLineExporter(DateFormat df){
        this.df = df;
    }
    

    @Override
    public void export(Map<Case<?>, List<Result<?>>> result) {
        new DlgTimeline(new JFrame(), result, df, true);
    }
}
