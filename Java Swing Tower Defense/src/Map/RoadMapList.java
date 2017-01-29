package Map;

public class RoadMapList {

	private RoadMapSquare roadStart = null;
	private RoadMapSquare roadMid = null;
	private RoadMapSquare roadEnd = null;
	
	protected void addStart(int x, int y){
		assert roadStart == null; //assert statement makes sure that a linked list of road squares doesn't already exist. If it does, an assertion error will be received
		roadStart = new RoadMapSquare(x, y);
		roadEnd = roadMid = roadStart;
	}
	
	protected void addEnd(int x, int y){
		assert roadStart != null; // should have a road start by now when trying to assign a road square to be the end square.
		RoadMapSquare temp = new RoadMapSquare(x,y);
		roadEnd.setNextSquare(temp);
		roadEnd = temp;
	}
	
	protected void addMid(int x, int y){
		assert roadStart != null;
		RoadMapSquare temp = new RoadMapSquare(x,y);
		roadMid.setNextSquare(temp);
		roadMid = temp;
		roadMid.setNextSquare(roadEnd);
	}
	
	protected RoadMapSquare getStart() {
		return roadStart;
	}

	protected RoadMapSquare getMid() {
		return roadMid;
	}

	protected RoadMapSquare getEnd() {
		return roadEnd;
	}
	
	protected void removeFirst(){
		roadStart = (RoadMapSquare) roadStart.getNextSquare(); 
	}

}
