/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import loganalyser.search.Case;
import loganalyser.search.Result;

/**
 *
 * @author Klaus
 */
public class CaseSeperatetTxtExporter extends TxtExporter {

    public CaseSeperatetTxtExporter() {
        super();
    }

    public CaseSeperatetTxtExporter(String dateformat) {
        super(dateformat);
    }

    public CaseSeperatetTxtExporter(String path, boolean append, PrintModus modus, String dateformat) {
        super(path, append, modus, dateformat);
    }

    @Override
    public void export(Map<Case<?>, List<Result<?>>> result) {
        FileWriter writer = null;
        File file = new File(getPath());
        try {
            writer = new FileWriter(file, isAppend());
            for (Case<?> c : result.keySet()) {
                writer.write(c.getName() + ":");
                writer.write(System.getProperty("line.separator"));
                for (Result<?> r : result.get(c)) {
                    writer.write("\t" + (getModus() == PrintModus.VALUE ? r.toString() : r.getLine()));
                    writer.write(System.getProperty("line.separator"));
                }
                writer.write(System.getProperty("line.separator"));
                writer.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(CaseSeperatetTxtExporter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(CaseSeperatetTxtExporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
