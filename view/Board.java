package boggle.view;

import boggle.model.Vector2;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Board {

    private double ELEMENT_SIZE;
    private double GAP;
    
    private TilePane tilePane = new TilePane();
    private Group display = new Group(tilePane);
    private int nRows;
    private int nCols;
    private String boardStr;
    
    public Board(int nRows, int nCols, String boardStr, double elementSize) {    	
    	this.boardStr = boardStr;
    	
    	ELEMENT_SIZE = elementSize;
    	GAP = 0;
    	
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        setColumns(nCols);
        setRows(nRows);
    }

    public void setColumns(int newColumns) {
        nCols = newColumns;
        tilePane.setPrefColumns(nCols);
        fillBoard();
    }

    public void setRows(int newRows) {
        nRows = newRows;
        tilePane.setPrefRows(nRows);
        fillBoard();
    }

    public int getColumns() {
    	return nCols;
    }
    
    public int getRows() {
    	return nRows;
    }
    
    public double getElementSize() {
    	return ELEMENT_SIZE;
    }
    
    public Group getDisplay() {
        return display;
    }
    
    public int getSize() {
    	return nRows;
    }
    
    public String toString() {
    	return boardStr;
    }
    
    private void fillBoard() {
        tilePane.getChildren().clear();
        for (int i = 0; i < nCols; i++) {
            for (int j = 0; j < nRows; j++) {
            	tilePane.getChildren().add(createElement(tilePane.getChildren().size()));
            }
        }
    }
    
    public void resetColors() {
    	for(int x = 0; x < nRows; x++) {
			for(int y = 0; y < nRows; y++) {
				Canvas canvas = getCanvas(new Vector2(x, y));
				
				setColor(canvas, Color.GRAY);
				setCharacter(canvas, boardStr.charAt(new Vector2(x, y).asIndex(nCols)));
			}
		}
    }
    
    private Canvas createElement(int index) {
    	Canvas canvas = new Canvas(ELEMENT_SIZE, ELEMENT_SIZE);
    	setColor(canvas, Color.GREY);
    	setCharacter(canvas, boardStr.charAt(index));
    	
        return canvas;
    }
    
    public Canvas getCanvas(Vector2 cord) {
    	return (Canvas) tilePane.getChildren().get(cord.asIndex(nRows));
    }
    
    public void setColor(Canvas canvas, Color color) {
    	GraphicsContext gc = canvas.getGraphicsContext2D();
		
        gc.setFill(color);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
    
    public void setCharacter(Canvas canvas, char c) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Calibri", ELEMENT_SIZE / 1.5));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(
            c + "", 
            Math.round(canvas.getWidth()  / 2), 
            Math.round(canvas.getHeight() / 2)
        );
	}
}
