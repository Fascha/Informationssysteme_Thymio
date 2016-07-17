import java.util.ArrayList;

import iw.ur.thymio.Thymio.Thymio;

public class ThymioDriver {

	Thymio t;

	String orientation = "";

	int right_degree = 77;
	int left_degree = -76;

	Vertex destination;

	public ThymioDriver(ArrayList<Vertex> path) {

		ArrayList<Vertex> testpath = new ArrayList<Vertex>();

		testpath.add(new Vertex("0", 0, 0));
		testpath.add(new Vertex("1", 1, 0));
		testpath.add(new Vertex("2", 2, 0));
		testpath.add(new Vertex("3", 2, 1));
		testpath.add(new Vertex("4", 2, 2));
		testpath.add(new Vertex("5", 1, 2));
		testpath.add(new Vertex("6", 0, 2));

		path = testpath;

		setupThymio();

		boolean arrivedAtDestination = false;

		destination = path.get(path.size() - 1);

		while (!arrivedAtDestination) {
			Vertex curr = path.get(0);
			Vertex next = path.get(1);

			if (next.getX() > curr.getX()) {
				moveRight();
			} else if (next.getX() < curr.getX()) {
				moveLeft();
			} else if (next.getY() > curr.getY()) {
				moveDown();
			} else {
				moveDown();
			}

			if (t.getProxHorizontal()[5] > 1000) {
				break;
			}

			
			// eigene move implementation und abfrage von sensoren?
			/*
			 * ein stück moven sensoren abfragen wenn kante erkannt dann
			 * korrigieren (je nachdem ob linker sensor oder rechter senosr was
			 * anderes als startfeld anzeigt links/rechtsrum drehen) wenn wieder
			 * "gerade" ausgerichtet dann bis mitte des nächsten feldes fahrent
			 */


			path.remove(curr);
			if (path.get(0) == destination) {
				arrivedAtDestination = true;
			}
		}
		//

		t.stop();

	}

	private void setupThymio() {
		t = new Thymio("192.168.10.1");
		orientation = "east";
		setLeftDegree(-83);
		setRightDegree(79);
		// t.setFieldSensitivity(whiteField, blackField, bias);
		// t.setMoveSensitivity(moveBias);
		// t.setOrientation(degree);
		// t.setStartField(field);

	}

	private void measureAndCalibrateSensors() {
		System.out.println("GroundReflected: ");
		int[] ground1 = new int[20];
		int[] ground2 = new int[20];

		for (int i = 0; i < 20; i++) {
			System.out.println("Messung #" + i);
			int[] curr = t.getGroundReflected();
			ground1[i] = curr[0];
			ground2[i] = curr[1];

		}
		System.out.println("Ground1:");
		for (int i = 0; i < ground1.length; i++) {
			System.out.println(ground1[i]);
		}
		System.out.println("Ground2");
		for (int i = 0; i < ground2.length; i++) {
			System.out.println(ground2[i]);
		}

		System.out.println("ProxHorizontal: ");
		int[] prox1 = new int[20];
		int[] prox2 = new int[20];
		int[] prox3 = new int[20];
		int[] prox4 = new int[20];
		int[] prox5 = new int[20];
		int[] prox6 = new int[20];
		int[] prox7 = new int[20];

		for (int i = 0; i < 20; i++) {
			System.out.println("Messung #" + i);
			int[] curr = t.getProxHorizontal();
			prox1[i] = curr[0];
			prox2[i] = curr[1];
			prox3[i] = curr[2];
			prox4[i] = curr[3];
			prox5[i] = curr[4];
			prox6[i] = curr[5];
			prox7[i] = curr[6];
		}
		System.out.println("Prox1:");
		for (int i = 0; i < prox1.length; i++) {

			System.out.println(prox1[i]);
		}
		System.out.println("Prox2:");
		for (int i = 0; i < prox2.length; i++) {

			System.out.println(prox2[i]);
		}
		System.out.println("Prox3:");
		for (int i = 0; i < prox3.length; i++) {

			System.out.println(prox3[i]);
		}
		System.out.println("Prox4:");
		for (int i = 0; i < prox4.length; i++) {

			System.out.println(prox4[i]);
		}
		System.out.println("Prox5:");
		for (int i = 0; i < prox5.length; i++) {

			System.out.println(prox5[i]);
		}
		System.out.println("Prox6:");
		for (int i = 0; i < prox6.length; i++) {

			System.out.println(prox6[i]);
		}
		System.out.println("Prox7:");
		for (int i = 0; i < prox7.length; i++) {
			System.out.println(prox7[i]);
		}
	}

	private void moveUp() {
		switch (orientation) {
		case "north":
			orientation = "north";
			t.move();
			break;
		case "east":
			turnLeft();
			orientation = "north";
			t.move();
			break;
		case "south":
			turnLeft();
			turnLeft();
			orientation = "north";
			t.move();
			break;
		case "west":
			turnRight();
			orientation = "north";
			t.move();
			break;
		default:
			Exception ex = new Exception();
			StackTraceElement stackTop = ex.getStackTrace()[0];
			String methodName = stackTop.getMethodName();
			System.out.println("No Orientation Found: " + methodName);
			// eventuell notfallstrategie ausführen
			break;
		}
	}

	private void moveDown() {
		switch (orientation) {
		case "north":
			turnLeft();
			turnLeft();
			orientation = "south";
			t.move();
			break;
		case "east":
			turnRight();
			orientation = "south";
			t.move();
			break;
		case "south":
			orientation = "south";
			t.move();
			break;
		case "west":
			turnLeft();
			orientation = "south";
			t.move();
			break;
		default:
			Exception ex = new Exception();
			StackTraceElement stackTop = ex.getStackTrace()[0];
			String methodName = stackTop.getMethodName();
			System.out.println("No Orientation Found: " + methodName);
			// eventuell notfallstrategie ausführen
			break;
		}
	}

	private void moveLeft() {
		switch (orientation) {
		case "north":
			turnLeft();
			orientation = "west";
			t.move();
			break;
		case "east":
			turnLeft();
			turnLeft();
			orientation = "west";
			t.move();
			break;
		case "south":
			turnRight();
			orientation = "west";
			t.move();
			break;
		case "west":
			t.move();
			orientation = "west";
			break;
		default:
			Exception ex = new Exception();
			StackTraceElement stackTop = ex.getStackTrace()[0];
			String methodName = stackTop.getMethodName();
			System.out.println("No Orientation Found: " + methodName);
			// eventuell notfallstrategie ausführen
			break;
		}
	}

	private void moveRight() {
		switch (orientation) {
		case "north":
			turnRight();
			orientation = "east";
			t.move();
			break;
		case "east":
			orientation = "east";
			t.move();
			break;
		case "south":
			turnLeft();
			orientation = "east";
			t.move();
			break;
		case "west":
			turnLeft();
			turnLeft();
			orientation = "east";
			t.move();
			break;
		default:
			Exception ex = new Exception();
			StackTraceElement stackTop = ex.getStackTrace()[0];
			String methodName = stackTop.getMethodName();
			System.out.println("No Orientation Found: " + methodName);
			// eventuell notfallstrategie ausführen
			break;
		}
	}

	private void turnRight() {
		t.rotate(right_degree);
	}

	private void turnLeft() {
		t.rotate(left_degree);
	}

	private boolean checkForObstacle(int[] sensors, int distance) {
		for (int i = 0; i < sensors.length; i++) {
			if (sensors[i] > distance) {
				System.out.println("Obstacle recognized:\nSensor #" + i + "\n" + "Sensorwert: " + sensors[i]);
				return true;
			}
		}
		return false;
	}

	private void update() {
		int[] current = t.getProxHorizontal();
		if (checkForObstacle(current, 500)) {
			t.stop();
		} else {
			t.move();
		}
	}


	
	private void setLeftDegree(int degree) {
		left_degree = degree;
	}

	private void setRightDegree(int degree) {
		right_degree = degree;
	}

}
