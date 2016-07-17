import java.util.*;

public class Vertex {
	private String id;
	private int x, y;
	private Vertex predecessor;
	private ArrayList<Vertex> neighbours;
	private double distanceFromStart, distanceFromEnd, totalDistance;

	public Vertex(String id, int x, int y, ArrayList<Vertex> neighbours) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.neighbours = neighbours;
	}

	public Vertex(String id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.neighbours = new ArrayList<Vertex>();
	}

	public Vertex getPredecessor() {
		return this.predecessor;
	}

	public String getId() {
		return this.id;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public ArrayList<Vertex> getNeighbours() {
		return this.neighbours;
	}

	public double getDistanceFromStart() {
		return this.distanceFromStart;
	}

	public double getDistanceFromEnd() {
		return this.distanceFromEnd;
	}

	public double getTotalDistance() {
		return this.totalDistance;
	}

	public void setPredecessor(Vertex predecessor) {
		if (this.neighbours.contains(predecessor)) {
			this.predecessor = predecessor;
		}
	}

	public void setNeighbours(ArrayList<Vertex> neighbours) {
		this.neighbours = neighbours;
	}

	public void setDistanceFromStart(double distanceFromStart) {
		this.distanceFromStart = distanceFromStart;
	}

	public void setDistanceFromEnd(double distanceFromEnd) {
		this.distanceFromEnd = distanceFromEnd;
	}

	public void setTotalDistance() {
		this.totalDistance = this.distanceFromStart + this.distanceFromEnd;
	}
}
