import java.util.*;

class ClosestPoints {
	public int[][] kClosest(int[][] points, int k) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> distanceToOrigin(b) - distanceToOrigin(a));

		for (int[] point : points) {
			pq.offer(point);
			if (pq.size() > k) {
				pq.poll();
			}
		}

		int[][] result = new int[k][2];
		int index = 0;
		while (!pq.isEmpty()) {
			result[index++] = pq.poll();
		}

		return result;
	}

	private int distanceToOrigin(int[] point) {
		int x = point[0];
		int y = point[1];
		return x * x + y * y;
	}

	public static void main(String[] args) {
		ClosestPoints solution = new ClosestPoints();

		int[][] points = { { 1, 3 }, { -2, 2 } };
		int k = 1;
		int[][] closest = solution.kClosest(points, k);
		System.out.println("Closest Points:");
		printPoints(closest);

		points = new int[][] { { 3, 3 }, { 5, -1 }, { -2, 4 } };
		k = 2;
		closest = solution.kClosest(points, k);
		System.out.println("Closest Points:");
		printPoints(closest);
	}

	private static void printPoints(int[][] points) {
		for (int[] point : points) {
			System.out.println(Arrays.toString(point));
		}
	}
}
