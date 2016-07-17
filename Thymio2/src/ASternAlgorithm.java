import java.util.*;

public class ASternAlgorithm {

	private static ArrayList<Vertex> open = new ArrayList<Vertex>();
	private static ArrayList<Vertex> closed = new ArrayList<Vertex>();
	private static ArrayList<Vertex> path;

	public static ArrayList<Vertex> start(Vertex start, Vertex goal) {
		boolean done = false;
		Vertex current = start;
		initialize(current, goal);

		while (!done) {
			Vertex next = lowestFCostInOpen();

			if (next.getX() == goal.getX() && next.getY() == goal.getY()) {
				System.out.println("Path found!\n");
				done = true;
				next.setPredecessor(current);
				return findPath(start, next);
			} else {
				Vertex temp = current;
				current = next;
				next.setPredecessor(temp);
				open.addAll(current.getNeighbours());
				open.remove(current);
				open.remove(next.getPredecessor());
				closed.add(current);
			}
		}

		System.out.println("Unable to find solution");
		return new ArrayList<Vertex>();
	}

	public static void initialize(Vertex start, Vertex goal) {
		closed.add(start);
		open.addAll(start.getNeighbours());
		open.remove(start);

		for (int i = 0; i < Graph.vertices.size(); i++) {
			Vertex cur = Graph.vertices.get(i);
			double distFromStart = Graph.shortestDistanceFrom(cur, start);
			double distFromEnd = Graph.shortestDistanceFrom(cur, goal);
			cur.setDistanceFromStart(distFromStart);
			cur.setDistanceFromEnd(distFromEnd);
			cur.setTotalDistance();
		}

	}

	public static ArrayList<Vertex> findPath(Vertex start, Vertex goal) {
		path = new ArrayList<Vertex>();
		path.add(goal);

		while (!goal.equals(start)) {
			path.add(goal.getPredecessor());
			goal = goal.getPredecessor();
		}

		Collections.reverse(path);
		return path;

	}

	public static Vertex lowestFCostInOpen() {
		Vertex cheapest = null;

		if (!open.isEmpty()) {
			cheapest = open.get(0);

			for (int i = 0; i < open.size(); i++) {
				if (open.get(i).getDistanceFromEnd() < cheapest
						.getDistanceFromEnd()) {
					cheapest = open.get(i);
				}
			}
		}

		return cheapest;
	}
}
