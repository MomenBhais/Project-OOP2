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
public class NotGate extends LogicGate {
    public NotGate(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Draw NOT gate
        int[] xPoints = {x, x + 40, x};
        int[] yPoints = {y, y + 25, y + 50};
        g2d.drawPolygon(xPoints, yPoints, 3);
        g2d.drawOval(x + 40, y + 20, 10, 10);
        g2d.drawLine(x + 50, y + 25, x + 100, y + 25); // Line extending to the next gate
    }
}
    

