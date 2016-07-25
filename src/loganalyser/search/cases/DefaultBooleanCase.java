/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.search.cases;

/**
 *
 * @author Klaus
 */
public class DefaultBooleanCase extends BooleanCase{
    
        public DefaultBooleanCase(String regEx, char valueLimiter) {
        super(regEx, valueLimiter);
    }

    @Override
    public Boolean convertStringToType(String s) {
        return Boolean.valueOf(s);
    }
}
