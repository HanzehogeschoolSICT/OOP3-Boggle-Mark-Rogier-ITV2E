package boggle.view;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Board {

    private static final double ELEMENT_SIZE = 100;
    private static final double GAP = ELEMENT_SIZE / 50;
    
    private TilePane tilePane = new TilePane();
    private Group display = new Group(tilePane);
    private int nRows;
    private int nCols;
    private String boardStr;
    
    public Board(int nRows, int nCols, String boardStr) {    	
    	this.boardStr = boardStr;
    	
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
    
    private void fillBoard() {
        tilePane.getChildren().clear();
        for (int i = 0; i < nCols; i++) {
            for (int j = 0; j < nRows; j++) {
            	tilePane.getChildren().add(createElement(tilePane.getChildren().size()));
            }
        }
    }

    private Canvas createElement(int index) {
    	Canvas canvas = new Canvas(ELEMENT_SIZE, ELEMENT_SIZE);
    	setColor(canvas, Color.GREY);
    	setCharacter(canvas, boardStr.charAt(index));
    	
        return canvas;
    }
    
    public void setColor(Canvas canvas, Color color) {
    	GraphicsContext gc = canvas.getGraphicsContext2D();
		
        gc.setFill(color);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
    
    public void setCharacter(Canvas canvas, char c) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Calibri", 50));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(
            c + "", 
            Math.round(canvas.getWidth()  / 2), 
            Math.round(canvas.getHeight() / 2)
        );
	}
}
