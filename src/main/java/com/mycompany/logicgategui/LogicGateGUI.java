/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.logicgategui;

/**
 *
 * @author DELL
 */

// اعمل run هنا

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


public class LogicGateGUI extends JFrame {
    private JTextField inputField;
    private JButton drawButton;
    private GateCanvas gateCanvas;
    private JPanel inputPanel;
    private Map<String, Boolean> variables;

    public LogicGateGUI() {
        setTitle("Logic Gate Drawer");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        inputField = new JTextField(20);
        drawButton = new JButton("Draw");
        gateCanvas = new GateCanvas();

        inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter logical expression: "));
        inputPanel.add(inputField);
        inputPanel.add(drawButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(gateCanvas, BorderLayout.CENTER);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expression = inputField.getText().toUpperCase();
                gateCanvas.setExpression(expression);
                variables = new HashMap<>();

                // ربط المتغيرات من المستخدم
                for (char c : expression.toCharArray()) {
                    if (Character.isLetter(c) && !variables.containsKey(String.valueOf(c))) {
                        String varName = String.valueOf(c);
                        String valueStr = JOptionPane.showInputDialog("Enter value for " + varName + " (0/1):");
                        int value = Integer.parseInt(valueStr);
                        variables.put(varName, value == 1);
                    }
                }

                boolean result = evaluateExpression(expression, variables);
                gateCanvas.setResult(result);
                gateCanvas.setVariables(variables);
                gateCanvas.repaint();

                JOptionPane.showMessageDialog(null, "The result is: " + (result ? 1 : 0));
            }
        });
    }

    public static void main(String[] args) {
        
        LogicGateGUI g = new LogicGateGUI();
        g.setVisible(true);
    }

    public static boolean evaluateExpression(String exp, Map<String, Boolean> var) {
        return new ExpressionParser(exp, var).parseExpression();
    }
}

