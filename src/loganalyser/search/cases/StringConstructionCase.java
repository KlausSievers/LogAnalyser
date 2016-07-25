/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.search.cases;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import loganalyser.search.Case;

/**
 *
 * @author Klaus
 */
public class StringConstructionCase<E>  extends Case<E>{
    private Class<E> type;
    
     public StringConstructionCase(String regEx, char valueLimiter, Class<E> type) {
        super(regEx, valueLimiter);
        this.type = type;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E convertStringToType(String s) {
        try {
            Constructor<?> constructor = type.getConstructor(String.class);
            return (E) constructor.newInstance(s);
        } catch (InstantiationException ex) {
            Logger.getLogger(StringConstructionCase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StringConstructionCase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(StringConstructionCase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(StringConstructionCase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(StringConstructionCase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(StringConstructionCase.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
}
