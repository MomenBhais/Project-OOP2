/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.logicgategui;

import java.awt.Graphics2D;

/**
 *
 * @author DELL
 */
public class OrGate extends LogicGate {
    public OrGate(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Draw OR gate
        g2d.drawArc(x - 40, y, 80, 50, 270, 180);
        g2d.drawLine(x, y, x + 20, y + 25);
        g2d.drawLine(x, y + 50, x + 20, y + 25);
        g2d.drawLine(x + 60, y + 25, x + 100, y + 25); // Line extending to the next gate
    }
}
    

