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
public class AndGate extends LogicGate {
    public AndGate(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // هنا يرسم بوابة    and
        g2d.drawArc(x, y, 40, 50, 90, -180);
        g2d.drawLine(x + 20, y, x + 20, y + 50);
        g2d.drawLine(x + 60, y + 25, x + 100, y + 25); // خط مسند لبوابة اخرى
    }
}
    

