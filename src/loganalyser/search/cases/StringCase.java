/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.search.cases;

import loganalyser.search.Case;

/**
 *
 * @author Klaus
 */
public class StringCase extends Case<String> {

    public StringCase(String regEx, char valueLimiter) {
        super(regEx, valueLimiter);
    }

    @Override
    public String convertStringToType(String s) {
        return s;
    }
}
