import java.util.*;

public class AStar {
	// kosten für den thymio um ein feld zu fahren
	static final int MOVE_COST = 10;
	
	//Schachfeld erstellen (8x8)
	static Cell[][] grid = new Cell[8][8];
	
	//open durch openList ersetzen
	static PriorityQueue<Cell> openList;
	
	//closed lsit mit abgearbeiteten knoten
	static boolean closedList[][];
	
	//Startpunkt
	static int startX;
	static int startY;
	
	//Endpunkt
	static int endX;
	static int endY;
	
	//Hindernisse ins grid schreiben
	public static void setBlocked(int x, int y) {
		grid[x][y] = null;
	}
	
	//Startpunkt setzen
	public static void setStartCell(int x, int y){
		startX = x;
		startY = y;
	}
	
	//Zielpunkt setzen
	public static void setDestinationCell(int x, int y){
		endX = x;
		endY = y;
	}
	
	
	public static void checkAndUpdateCost(Cell current, Cell t, int cost){
		//wenn closed list true dann hat man knoten schon abgearbeitet
		if (t == null || closedList[t.x][t.y]) {
			return;
		}
		int t_final_cost = t.heuristicCost + cost;
		
		boolean inOpenList = openList.contains(t);
		
		
		if(!inOpenList || t_final_cost < t.finalCost) {
			t.finalCost = t_final_cost;
			t.parent = current;
			if(!inOpenList) {
				openList.add(t);
			}
		}
	}
	
	
	public void calcAStar(){
		//Startpunkt zur openList hinzufügen
		openList.add(grid[startX][startY]);
		
		Cell current;
		
		
		//boolean destinationFound = false;
		while(true){
			//gibt kopf der priorityqueue zurück (nächster zu bearbeitender knoten)
			current = openList.poll();
			
			//abfragen ob noch objekte in der queue sind
			if (current==null){
				break;
			}
			
			closedList[current.x][current.y] = true;
			
			if (current.equals(grid[endX][endY])){
				break;
			}
			
			//kosten updaten etc.
			Cell t;
			
			//if statements checken ränder
			if (current.x - 1 >= 0) {
				t = grid[current.x - 1][current.y];
				checkAndUpdateCost(current, t, current.finalCost + MOVE_COST);
				
	//          if(current.j-1>=0){                      
	//              t = grid[current.i-1][current.j-1];
	//              checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST); 
	//          }
	//
	//          if(current.j+1<grid[0].length){
	//              t = grid[current.i-1][current.j+1];
	//              checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST); 
	//          }
				
				
			}
			if (current.y - 1 >= 0) {
				t = grid[current.x][current.y - 1];
				checkAndUpdateCost(current, t, current.finalCost + MOVE_COST);
			}
			if (current.y + 1 < grid[0].length) {
				t = grid[current.x][current.y + 1];
				checkAndUpdateCost(current, t, current.finalCost + MOVE_COST);
			}
			if (current.x + 1 < grid[0].length) {
				t = grid[current.x + 1][current.y];
				checkAndUpdateCost(current, t, current.finalCost + MOVE_COST);
				
	//          if(current.j-1>=0){
	//              t = grid[current.i+1][current.j-1];
	//              checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST); 
	//          }
	//          
	//          if(current.j+1<grid[0].length){
	//             t = grid[current.i+1][current.j+1];
	//             checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST); 
	//          }  
			}			
		}
	}
}