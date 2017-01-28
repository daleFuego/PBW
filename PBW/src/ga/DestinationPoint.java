package ga;

public class DestinationPoint {
	int x;
	int y;

	public DestinationPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public double distanceTo(DestinationPoint city) {
		int xDistance = Math.abs(getX() - city.getX());
		int yDistance = Math.abs(getY() - city.getY());

		return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
	}

	@Override
	public String toString() {
		return getX() + ", " + getY();
	}
}