/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.export;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import loganalyser.search.Case;
import loganalyser.search.Result;

/**
 *
 * @author Klaus
 */
public abstract class TxtExporter implements ResultExporter {

    private DateFormat df;
    private String path;
    private boolean append;
    private PrintModus modus;

    public TxtExporter() {
        this("yyyy-MM-dd HH:mm:ss.SSS");
    }

    public TxtExporter(String dateformat) {
        this("", false, PrintModus.VALUE, dateformat);
    }

    public TxtExporter(String path, boolean append, PrintModus modus, String dateformat) {
        this.path = path;
        this.append = append;
        this.modus = modus;
        this.df = new SimpleDateFormat(dateformat);
    }

    @Override
    public abstract void export(Map<Case<?>, List<Result<?>>> result);

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DateFormat getDf() {
        return df;
    }

    public void setDf(DateFormat df) {
        this.df = df;
    }

    public boolean isAppend() {
        return append;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public PrintModus getModus() {
        return modus;
    }

    public void setModus(PrintModus modus) {
        this.modus = modus;
    }
}
