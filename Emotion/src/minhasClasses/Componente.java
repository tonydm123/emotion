/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhasClasses;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Eduardo
 */
public abstract class  Componente {
    public float x;
    public float y;

    public Componente(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public abstract void update(float time, int cameraX, int cameraY);
    public abstract void draw(Graphics2D dbg,float xMundo, float yMundo);
	public void draw(Graphics2D dbg, float num, Color cor) {
		// TODO Auto-generated method stub
		
	}
}
