public class Cell {
	
	int heuristicCost = 0;
	int finalCost = 0;
	int x;
	int y;
	Cell parent;
	
	Cell(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	 @Override
     public String toString(){
         return "["+this.x+", "+this.y+"]";
     }
	
}
