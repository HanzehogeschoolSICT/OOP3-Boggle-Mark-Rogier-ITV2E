package boggle.view;

import boggle.Main;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class Board {

    private static final double ELEMENT_SIZE = 100;
    private static final double GAP = ELEMENT_SIZE / 50;

    private Main main;
    
    private TilePane tilePane = new TilePane();
    private Group display = new Group(tilePane);
    private int nRows;
    private int nCols;
    private String board;
    
    public Board(Main main, int nRows, int nCols, String board) {
    	this.main = main;
    	this.board = board;
    	
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        setColumns(nCols);
        setRows(nRows);
    }

    public void setColumns(int newColumns) {
        nCols = newColumns;
        tilePane.setPrefColumns(nCols);
        fillBoard(board);
    }

    public void setRows(int newRows) {
        nRows = newRows;
        tilePane.setPrefRows(nRows);
        fillBoard(board);
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
    
    public CharacterField getCharacterField(int x, int y) {
    	int index = x * nCols + y;
    	
    	return (CharacterField) tilePane.getChildren().get(index);
    }
    
    private void fillBoard(String board) {
        tilePane.getChildren().clear();
        for (int i = 0; i < nCols; i++) {
            for (int j = 0; j < nRows; j++) {
            	if(board != null) {
            		tilePane.getChildren().add(createElement(board.charAt(tilePane.getChildren().size()) + ""));
            	} else {
            		tilePane.getChildren().add(createElement(null));
            	}
            }
        }
    }

    private CharacterField createElement(String c) {
    	CharacterField characterField = new CharacterField(ELEMENT_SIZE, ELEMENT_SIZE);
        
    	characterField.setColor(Color.GRAY);
    	if(c == null) {
    		characterField.setCharacter(main.getAlphabetUtils().getRandomCharacter());
    	} else {
    		characterField.setCharacter(c.charAt(0));
    	}
    	
        return characterField;
    }
}
