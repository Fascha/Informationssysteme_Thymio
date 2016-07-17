
public class Edge {

	private Vertex source, destination;
	private double id;
	
	public Edge(double id, Vertex source, Vertex destination) {
		this.id = id;
		this.source = source;
		this.destination = destination;
	}
	
	public Vertex getSource() {
		return this.source;
	}
	
	public Vertex getDestination() {
		return this.destination;
	}
}
