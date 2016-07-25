/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loganalyser.search;

import java.awt.Color;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Klaus
 */
public abstract class Case<E> {

    private Matcher matcher;
    private char valueLimiter;
    private Color color;
    private String name;

    public Case(String regEx, char valueLimiter) {
        this.matcher = Pattern.compile(regEx).matcher("");
        this.valueLimiter = valueLimiter;
        color = Color.BLACK;
        name = "---";
    }

    public boolean find(String check){
       matcher.reset(check);
       return matcher.find();
    }
    
    private E findValue(String check){
        int start = matcher.end();
        String result = check.substring(start, valueLimiter != '\n' ? check.indexOf(valueLimiter,start) : check.length());
        return convertStringToType(result);
    }
    
    public Result<E> createResult(Date datum, String line){
        Result<E> r = new Result<E>(new Date(datum.getTime()), this.findValue(line));
        r.setLine(line);
        return r;               
    }
    
    public abstract E convertStringToType(String s);
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Pattern getPattern() {
        return matcher.pattern();
    }

    public char getValueLimiter() {
        return valueLimiter;
    }

    public void setValueLimiter(char valueLimiter) {
        this.valueLimiter = valueLimiter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
