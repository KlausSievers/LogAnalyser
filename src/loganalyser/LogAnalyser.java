/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import loganalyser.export.ResultExporter;
import loganalyser.export.TimeLineExporter;
import loganalyser.search.Analyser;
import loganalyser.search.Case;
import loganalyser.search.Result;
import loganalyser.search.cases.DefaultBooleanCase;

/**
 *
 * @author Klaus
 */
public class LogAnalyser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Analyser analyse = new Analyser("yyyy-MM-dd HH:mm:ss.SSS");

            File file = new File("C:\\myPath\\testLog.txt");
            analyse.setFile(file);

            DefaultBooleanCase case1 = new DefaultBooleanCase("Klopause 1 = ", '\n');
            case1.setName("Klo 1");
            case1.setColor(Color.RED);
            analyse.addCase(case1);

            DefaultBooleanCase case2 = new DefaultBooleanCase("Klopause 2 = ", '\n');
            case2.setName("Klo 2");
            case2.setColor(Color.GREEN);
            analyse.addCase(case2);

            DefaultBooleanCase case3 = new DefaultBooleanCase("Klopause 3 = ", '\n');
            case3.setName("Klo 3");
            case3.setColor(Color.ORANGE);
            analyse.addCase(case3);

            DefaultBooleanCase case4 = new DefaultBooleanCase("Klopause 4 = ", '\n');
            case4.setName("Klo 4");
            case4.setColor(Color.BLUE);
            analyse.addCase(case4);

            Map<Case<?>, List<Result<?>>> result = analyse.anlayse();


//            ResultExporter exporter = new ChronologicalTxtExporter("C:\\Users\\Klaus\\Desktop\\testResult.txt", false, PrintModus.LINE, "yyyy-MM-dd HH:mm:ss.SSS");
            ResultExporter exporter = new TimeLineExporter();
            exporter.export(result);


        } catch (IOException ex) {
            Logger.getLogger(LogAnalyser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
