import java.io.FileNotFoundException;
import java.util.*;


public class CalculatePath {

	static int startCol = 0;
	static int startRow = 0;
	static int endCol = 19;
	static int endRow = 7;
	
	
	
	public static void main(String[] args) throws Exception {
		
		ArrayList<Vertex> path; 
		
		//Implement different ALGORTHMS and choose one

		//calcDijkstra();
		//sailorMethod();
		path = calcAStar();

		ThymioDriver td = new ThymioDriver(path);
	}
	

	private static ArrayList<Vertex> calcAStar() throws FileNotFoundException {
		System.out.println("What a messy maze!\nLet me check if I can find a path for you!\n");

		int[][] grid = Graph.fromCSVToArray("test.csv");
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if ((j % 20) == 0 && i != 0) {
					System.out.println();
				}
				System.out.print(grid[i][j] + " ");
			}
		}

		System.out.println("\n");

		Graph g = Graph.createGraphFromArray(grid);

		Vertex start1 = Graph.findVertexByCoordinates(startRow, startCol);
		Vertex goal = Graph.findVertexByCoordinates(endRow, endCol);

		if (Graph.vertices.contains(start1) && Graph.vertices.contains(goal)) {

			ArrayList<Vertex> path = ASternAlgorithm.start(start1, goal);

			for (int i = 0; i < path .size(); i++) {
				System.out.println("Coordinate " + (i + 1) + ": ("
						+ path.get(i).getX() + "," + path.get(i).getY() + ")");
			}
			return path;
		} else {
			System.out.println("One of the two given vertices does not exist...");
		}
		return null;
	}
}
