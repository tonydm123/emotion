package stuff;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ButtonManager {
	public int TASK;
	ArrayList<Button> buttons;

	public ButtonManager() {
		// TODO Auto-generated constructor stub
		TASK = 0;
		buttons = new ArrayList<Button>();
	}
	
	public boolean verificaTres(Color outra){
		int count=0;
		for (Button button : buttons) {
			if(button.color==outra) count++;
			
			if(count==3)return true;
		}
		
		return false;
	}
	
	public void cleanColor(Color cor, Color outra){
		for (Button button : buttons) {
			if(button.color==outra) button.color=cor;
		}
	}

	public int size() {
		return buttons.size();
	}

	public Button get(int i) {
		return buttons.get(i);
	}

	public void draw(Graphics2D dbg, float x, float y) {
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).passed)
				buttons.get(i).draw(dbg, x, y);
		}
	}

	public void draw(Graphics2D dbg, int index) {
		for (int i = index; i < buttons.size(); i++) {
			buttons.get(i).draw(dbg, 0, 0);
		}
	}

	public void drawOne(Graphics2D dbg, int index) {
		buttons.get(index).draw(dbg, 0, 0);
	}

	public void pressed(float x, float y) {
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).passed)
				if (buttons.get(i).press(x, y)) {
					TASK = buttons.get(i).task;
					break;
				}
		}
	}

	/*
	 * public int stillPressed(float x, float y){ for (int i = 0; i <
	 * buttons.size(); i++) { if(buttons.get(i).press(x, y) &&
	 * buttons.get(i).pressed){ buttons.get(i).pressed = false; TASK =
	 * buttons.get(i).task; return i; }else{ buttons.get(i).pressed = false; } }
	 * return 0; }
	 */

	public void add(Button button) {
		buttons.add(button);
	}

	public void setVisible(boolean b) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).passed = b;
		}
	}

	public void setVisible(boolean b, int i) {
		buttons.get(i).passed = b;
	}

}
