/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.logicgategui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map;
import java.util.Stack;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class GateCanvas extends JPanel {
    private String expression;
    private boolean result;
    private Map<String, Boolean> variables;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setVariables(Map<String, Boolean> variables) {
        this.variables = variables;
    }

    public boolean getResult() {
        return result;
    }

    public Map<String, Boolean> getVariables() {
        return variables;
    }

    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (expression != null) {
            drawLogicGates(g, expression);
        }
    }

    private void drawLogicGates(Graphics g, String expression) {
        Graphics2D g2d = (Graphics2D) g;
        int x = 50;
        int y = 50;
        x = drawGate(g2d, expression, x, y);
        g2d.drawString("Result: " + (result ? 1 : 0), x, y + 50);
    }

    private int drawGate(Graphics2D g2d, String expression, int x, int y) {
        Stack<String> stack = new Stack<>();
        String postfix = infixToPostfix(expression);
        int variableSpacing = 25;  // اضافة متغيرات

        for (char ch : postfix.toCharArray()) {
            if (ch == '+') {
                String op2 = stack.pop();
                String op1 = stack.pop();
                g2d.drawLine(x, y, x + 40, y);
                g2d.drawString(op1, x + 45, y - 20);
                g2d.drawString(op2, x + 45, y + 10);
                g2d.drawString("+", x + 25, y - 5);
                OrGate orGate = new OrGate(x + 70, y - 10);
                orGate.draw(g2d);
                x += 100;
                stack.push("OR(" + op1 + "," + op2 + ")");
            } else if (ch == '.') {
                String op2 = stack.pop();
                String op1 = stack.pop();
                g2d.drawLine(x, y, x + 40, y);
                g2d.drawString(op1, x + 45, y - 20);
                g2d.drawString(op2, x + 45, y + 10);
                g2d.drawString(".", x + 25, y - 5);
                AndGate andGate = new AndGate(x + 70, y - 10);
                andGate.draw(g2d);
                x += 100;
                stack.push("and(" + op1 + "," + op2 + ")");
            } else if (ch == '~') {
                String op = stack.pop();
                g2d.drawLine(x, y, x + 40, y);
                g2d.drawString(op, x + 45, y + 5);
                g2d.drawString("~", x + 25, y - 5);
                NotGate notGate = new NotGate(x + 70, y - 10);
                notGate.draw(g2d);
                x += 100;
                stack.push("not(" + op + ")");
            } else {
                stack.push(String.valueOf(ch));
                g2d.drawString(String.valueOf(ch) + "=" + (variables.get(String.valueOf(ch)) ? 1 : 0), x - 10, y + variableSpacing);
                variableSpacing += 25;  // المتغير التالي
            }
        }

        return x;
    }

    private String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(c);
            } else if (c == '~') {
                stack.push(c);
            } else if (c == '.' || c == '+') {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    private int precedence(char c) {
        switch (c) {
            case '+':
                return 1;
            case '.':
                return 2;
            case '~':
                return 3;
            default:
                return -1;
        }
    }
}
