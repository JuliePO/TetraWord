package utility.IA;

class ShapePosition {
	public int rotate; //compris entre 0 et 3
	public int xPosition;
	public int yPosition;
	public int value;
	public boolean[] field;
	
	public ShapePosition(int rotate, int xPosition, int yPosition, int value, boolean[] field) {
		this.rotate=rotate;
		this.xPosition=xPosition;
		this.yPosition=yPosition;
		this.value=value;
		this.field=field;
	}

}
