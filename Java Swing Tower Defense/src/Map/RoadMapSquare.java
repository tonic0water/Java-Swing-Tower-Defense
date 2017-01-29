package Map;

public class RoadMapSquare extends MapSquare {
	private MapSquare nextSquare;
	
	

	protected RoadMapSquare(int Xpos, int Ypos) {
		super(Xpos, Ypos);
	}

	public MapSquare getNextSquare() {
		return nextSquare;
	}

	public void setNextSquare(MapSquare nextSquare) {
		this.nextSquare = nextSquare;
	}

}
