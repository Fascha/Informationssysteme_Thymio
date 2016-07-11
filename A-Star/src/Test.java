import java.util.PriorityQueue;

public class Test {

	static AStar alg = new AStar();
	
	public static void test(int width, int height, int startX, int startY, int endX, int endY, int[][]obstacles){
		
		AStar.grid = new Cell[8][8];
		AStar.closedList = new boolean[width][height];
		AStar.openList = new PriorityQueue<>((Object o1, Object o2) -> {
            Cell c1 = (Cell)o1;
            Cell c2 = (Cell)o2;

            return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
        });
		
		AStar.setStartCell(startX, startY);
		AStar.setDestinationCell(endX, endY);
		
		//setzen der heuristikkosten (mehr oder weniger luftlinie/ohne obstacles bis zum ziel)
		for (int i = 0; i < width; i++){
			for ( int j = 0; j < height; j++){
				AStar.grid[i][j] = new Cell(i,j);
				AStar.grid[i][j].heuristicCost = Math.abs(i - endX) + Math.abs(j - endY);
			}
		}
		AStar.grid[startX][startY].finalCost = 0;
		
		//obstacles eintragen
		for (int i = 0; i < obstacles.length; i++) {
			//tuples: [][0] = x und [][1] = y koordinaten
			AStar.setBlocked(obstacles[i][0], obstacles[i][1]);
		}
		
		//Ausgabe der Map ohne Berechnung
		System.out.println("Map vor der Berechnung: ");
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (i == startX && j == startY) {
					System.out.print("S\t");
				} else if (i == endX && j == endY) {
					System.out.print("E\t");
				} else if (AStar.grid[i][j] != null) {
					System.out.print("0\t");
				} else {
					System.out.print("X\t");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		
		
		alg.calcAStar();
		
		//Ausgabe der Map mit Berechnung
		System.out.println("Map nach der Berechnung: ");
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (AStar.grid[i][j] != null) {
					System.out.print(AStar.grid[i][j].finalCost + "\t");
				} else {
					System.out.print("X\t");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		
		//Ausgabe des Pfades
		if (AStar.closedList[endX][endY]) {
			System.out.print("Path: ");
			Cell current = AStar.grid[endX][endY];
			System.out.print(current);
			while (current.parent != null) {
				System.out.print(" -> " + current.parent);
				current = current.parent;
			}
			System.out.println();
		} else {
			System.out.println("Es wurde kein Pfad gefunden!");
		}		
	}
	
	 public static void main(String[] args) throws Exception{   
	    	
		//test(breite des schachfelds, höhe des schachfelds, startX, startY, zielX, zielY, Array mit Koordinaten der Obstacles als Tupel)
		 
		 test(8, 8, 0, 0, 7, 7, new int[][]{{0,2},{0,3},{1,1},{1,4},{2,5},{6,4},{1,5},{4,6},{3,7},{7,6},{4,2},{7,3}});
	    	
		 test(8, 8, 0, 1, 0, 4, new int[][]{{0,2},{0,3},{1,1},{1,4},{2,5},{6,4},{1,5},{4,6},{3,7},{7,6},{4,2},{7,3}});
	    	
	    	
	    
	    }

}
