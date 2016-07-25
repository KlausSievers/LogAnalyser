/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.export;

import java.util.List;
import java.util.Map;
import loganalyser.search.Case;
import loganalyser.search.Result;

/**
 *
 * @author Klaus
 */
public interface ResultExporter {
    public void export(Map<Case<?>,List<Result<?>>> result);
}
