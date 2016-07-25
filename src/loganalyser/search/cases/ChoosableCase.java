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
public enum ChoosableCase {
    BOOLEAN_CHOOSE_CASE("Boolean", DefaultBooleanCase.class),
    INTEGER_CHOOSE_CASE("Integer", IntegerCase.class),
    DOUBLE_CHOOSE_CASE("Double", DoubleCase.class),
    STRING_CHOOSE_CASE("Sting", StringCase.class);

    private ChoosableCase(String name, Class<? extends Case<?>> c) {
        this.name = name;
        this.c = c;
    }

    public Class<? extends Case<?>> getC() {
        return c;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
    
    private String name;
    private Class<? extends Case<?>> c;
    
    
}
