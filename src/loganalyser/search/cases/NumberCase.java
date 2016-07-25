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
public abstract class NumberCase<E extends Number>  extends Case<E> {
    
       public NumberCase(String regEx, char valueLimiter) {
        super(regEx, valueLimiter);
    }

    @Override
    public abstract  E convertStringToType(String s);
    
}
