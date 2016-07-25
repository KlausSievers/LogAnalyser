/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.search.cases;

/**
 *
 * @author Klaus
 */
public class IntegerCase extends NumberCase<Integer> {

    public IntegerCase(String regEx, char valueLimiter) {
        super(regEx, valueLimiter);
    }
    
    @Override
    public Integer convertStringToType(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
}
