/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Klaus
 */
public class Analyser {

    private File file;
    private List<Case<?>> cases;
    private DateFormat df;
    private Date lastDate;

    public Analyser() {
        this(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    public Analyser(String dateFormat) {
        this(new SimpleDateFormat(dateFormat));
    }

    public Analyser(DateFormat df) {
        cases = new LinkedList<Case<?>>();
        this.df = df;
    }
    
    public void addCase(Case<?> c){
        cases.add(c);
    }

    public boolean  removeCase(Case<?> c){
        return cases.remove(c);
    }
    
    public Map<Case<?>,List<Result<?>>> anlayse() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        Map<Case<?>,List<Result<?>>> result = new HashMap<Case<?>,List<Result<?>>>();
        try {
            String line;
            Date date;
            while ((line = in.readLine()) != null) {
                date = parseDate(line);
                if (date != null) {
                    lastDate = new Date(date.getTime());
                }

                for (Case<?> c : cases) {
                    if (c.find(line)) {
                        if(result.get(c) == null){
                            result.put(c, new LinkedList<Result<?>>());
                        }
                        result.get(c).add(c.createResult(lastDate, line));
                    }
                }
            }
        } finally {
            in.close();
        }

        return result;
    }

    private Date parseDate(String line) {
        try {
            return df.parse(line);
        } catch (ParseException ex) {
            return null;
        }
    }
    
    //***************************
    // Getter und Setter
    //**************************
    public List<Case<?>> getCases() {
        return cases;
    }

    public void setCases(List<Case<?>> cases) {
        this.cases = cases;
    }

    public DateFormat getDateFormat() {
        return df;
    }

    public void setDateFormat(DateFormat df) {
        this.df = df;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
