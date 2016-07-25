/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Klaus
 */
public class Result<E> implements Comparable<Result<?>> {

    private Date datum;
    private E value;
    private String line;

    public Result(Date datum, E value) {
        this.datum = datum;
        this.value = value;
    }

    public static Date getStartDate(Map<Case<?>, List<Result<?>>> result) {
        List<Result<?>> l = toChronoligicalList(result);
        return l.get(0).getDatum();
    }

    public static Date getEndDate(Map<Case<?>, List<Result<?>>> result) {
        List<Result<?>> l = toChronoligicalList(result);
        return l.get(l.size() - 1).getDatum();
    }

    public static List<Result<?>> toChronoligicalList(Map<Case<?>, List<Result<?>>> result) {
        List<Result<?>> all = new ArrayList<Result<?>>();
        for (Case<?> c : result.keySet()) {
            all.addAll(result.get(c));
        }

        Collections.sort(all);

        return all;
    }

    public Date getDatum() {
        return new Date(datum.getTime());
    }

    public void setDatum(Date datum) {
        this.datum = new Date(datum.getTime());
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return datum + " - " + value;
    }

    @Override
    public int compareTo(Result<?> o) {
        return datum.compareTo(o.getDatum());
    }
}
