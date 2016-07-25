/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
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
public class ChronologicalTxtExporter extends TxtExporter {

    public ChronologicalTxtExporter() {
        super();
    }

    public ChronologicalTxtExporter(String dateformat) {
        super(dateformat);
    }

    public ChronologicalTxtExporter(String path, boolean append, PrintModus modus, String dateformat) {
        super(path, append, modus, dateformat);
    }

    @Override
    public void export(Map<Case<?>, List<Result<?>>> result) {
        FileWriter writer = null;
        File file = new File(getPath());
        List<Result<?>> all = new ArrayList<Result<?>>();
        try {
            writer = new FileWriter(file, isAppend());

            all = Result.toChronoligicalList(result);
            
            for(Result<?> r : all){
                writer.write(getModus() == PrintModus.VALUE ? r.toString() : r.getLine());
                writer.write(System.getProperty("line.separator"));
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
