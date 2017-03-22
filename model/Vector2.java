package model;

public class Vector2 {

	private int x;
	private int y;
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int asIndex(int size) {
		return x * size + y;
	}
	
	public boolean inBounds(int xl, int xh, int yl, int yh) {
    	return x >= xl && x < xh && y >= yl && y < yh;
    }
	
	public boolean equals(Object obj) {
		return toString().equals(obj.toString());
	}
	
	public String toString() {
		return x + " " + y;
	}
}
