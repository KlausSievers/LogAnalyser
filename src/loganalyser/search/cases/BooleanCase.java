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
public abstract class BooleanCase extends Case<Boolean> {

    public BooleanCase(String regEx, char valueLimiter) {
        super(regEx, valueLimiter);
    }

    @Override
    public abstract Boolean convertStringToType(String s);
}
