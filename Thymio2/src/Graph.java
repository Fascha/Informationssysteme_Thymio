import java.io.*;
import java.util.*;

public class Graph {

	static ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	static ArrayList<Vertex> visited = new ArrayList<Vertex>();
	static ArrayList<Vertex> unvisited = vertices;
	
	public Graph(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Vertex> getVertices() {
		return this.vertices;
	}

	public static int[][] fromCSVToArray(String fileCSV)
			throws FileNotFoundException {
		Scanner scan = new Scanner(new File(fileCSV));
		int[][] arr = new int[8][20];
		int[] a = null;
		int i = 0;

		while (scan.hasNextLine()) {
			String[] line = scan.nextLine().split(",");
			a = new int[line.length];

			for (int k = 0; k < line.length; k++) {
				a[k] = Integer.parseInt(line[k]);
				arr[i][k] = a[k];
			}

			i++;
		}
		return arr;
	}

	public static Graph createGraphFromArray(int[][] array) {

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {

				if (array[i][j] == 0) {
					vertices.add(new Vertex(i + "." + j, i, j));
				}

			}
		}

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				
				Vertex current = findVertexByCoordinates(i, j);
				Vertex right = findVertexByCoordinates(i, j + 1);
				Vertex left = findVertexByCoordinates(i, j - 1);
				Vertex up = findVertexByCoordinates(i - 1, j);
				Vertex down = findVertexByCoordinates(i + 1, j);

				if (isValid(right)) {
					current.getNeighbours().add(right);
					right.setPredecessor(current);
				}
				if (isValid(left)) {
					current.getNeighbours().add(left);
					left.setPredecessor(current);
				}
				if (isValid(up)) {
					current.getNeighbours().add(up);
					up.setPredecessor(current);
				}
				if (isValid(down)) {
					current.getNeighbours().add(down);
					down.setPredecessor(current);
				}
			}
		}

		return new Graph(vertices);
	}

	public static Vertex findVertexByCoordinates(int x, int y) {

		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getX() == x && vertices.get(i).getY() == y) {
				return vertices.get(i);
			}
		}

		return new Vertex("", -1, -1);
	}

	public static boolean isValid(Vertex v) {

		if (v.getX() == -1 && v.getY() == -1) {
			return false;
		}

		return true;
	}
	
	public static int shortestDistanceFrom(Vertex v, Vertex source) {
		if(v.getX() == source.getX()) {
			return Math.max(v.getY(), source.getY()) - Math.min(v.getY(), source.getY());
		} else if(v.getY() == source.getY()) {
			return Math.max(v.getX(), source.getX()) - Math.min(v.getX(), source.getX());
		} else {
			return Math.max(v.getY(), source.getY()) - Math.min(v.getY(), source.getY()) + 
					Math.max(v.getX(), source.getX()) - Math.min(v.getX(), source.getX());
		}
	}
}
