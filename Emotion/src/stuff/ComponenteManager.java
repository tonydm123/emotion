/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author EduTaToo
 */
public class ComponenteManager {
    public int TASK;
	ArrayList<Componente> components;
	
	public ComponenteManager() {
		// TODO Auto-generated constructor stub
		TASK = 0;
		components = new ArrayList<Componente>();
	}
        
        public int size(){
		return components.size();
	}
	
	public Componente get(int i){
		return components.get(i);
	}
        
        public void draw(Graphics2D dbg, float x, float y){
		for (int i = 0; i < components.size(); i++) {
                    if(components.get(i).passed)
			components.get(i).draw(dbg,x,y);
		}
	}
        
        public void pressed(float x, float y){
		for (int i = 0; i < components.size(); i++) {
                    if(components.get(i).passed)
			if(components.get(i).press(x, y)){
				TASK = components.get(i).task;
                                break;
			}
		}
	}
        
        public void setVisible(boolean b){
		for (int i = 0; i < components.size(); i++) {
                    components.get(i).passed=b;
		}
	}
        
        
        public void setVisible(boolean b, int i){
            components.get(i).passed=b;
	}
        
        public void add(Componente component){
		components.add(component);
	}
        
        public void clean(){
		for (int i = 0; i < components.size(); i++) {
                    components.get(i).text ="";
		}
	}
}
