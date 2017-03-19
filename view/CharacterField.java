package view;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class CharacterField extends Canvas {

	private char c;
	private Color color;
	
	public CharacterField(double width, double height) {
		super(width, height);
	}

	public char getCharacter() {
		return c;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
		
		loadColor();
        setCharacter(c);
	}
	
	public void loadColor() {
		GraphicsContext gc = getGraphicsContext2D();
		
        gc.setFill(color);
        gc.fillRect(0,0,getWidth(),getHeight());
	}
	
	public void setCharacter(char c) {
		GraphicsContext gc = getGraphicsContext2D();
		this.c = c;
		
		loadColor();
		
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Calibri", 50));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(
            c + "", 
            Math.round(getWidth()  / 2), 
            Math.round(getHeight() / 2)
        );
	}
}
