/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.logicgategui;

import java.util.Map;

/**
 *
 * @author DELL
 */
public class ExpressionParser {
    private final String expr;
    private final Map<String, Boolean> variables;
    private int index;

    public ExpressionParser(String expression, Map<String, Boolean> variables) {
        this.expr = expression;
        this.variables = variables;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean parseExpression() {
        index = 0;
        return parseOr();
    }

    private boolean parseOr() {
        boolean result = parseAnd();
        while (index < expr.length() && expr.charAt(index) == '+') {
            index++;
            result = result || parseAnd();
        }
        return result;
    }

    private boolean parseAnd() {
        boolean result = parseFactor();
        while (index < expr.length() && expr.charAt(index) == '.') {
            index++;
            result = result && parseFactor();
        }
        return result;
    }

    private boolean parseFactor() {
        if (index < expr.length() && expr.charAt(index) == '~') {
            index++;
            return !parseTerm();
        } else {
            return parseTerm();
        }
    }

    private boolean parseTerm() {
        if (index < expr.length() && Character.isLetter(expr.charAt(index))) {
            String variable = String.valueOf(expr.charAt(index));
            index++;
            return variables.getOrDefault(variable, false);
        }
        throw new IllegalArgumentException(" character a index " + index);
    }
    
    
}
    

