import java.util.Arrays;

class Minimum {
	public int minWastedSpace(int[] packages, int[][] boxes) {
		final int kMod = 1_000_000_007;
		final long kInf = (long) 1e11;
		final long packagesSum = Arrays.stream(packages).mapToLong(p -> p).sum();
		long minBoxesSum = kInf;

		Arrays.sort(packages);

		for (int[] box : boxes) {
			Arrays.sort(box);
			if (box[box.length - 1] < packages[packages.length - 1])
				continue;
			long accu = 0;
			long i = 0;
			for (final int b : box) {
				final long j = firstGreaterEqual(packages, b + 1);
				accu += b * (j - i);
				i = j;
			}
			minBoxesSum = Math.min(minBoxesSum, accu);
		}

		return minBoxesSum == kInf ? -1 : (int) ((minBoxesSum - packagesSum) % kMod);
	}

	private int firstGreaterEqual(int[] A, int target) {
		int l = 0;
		int r = A.length;
		while (l < r) {
			final int m = (l + r) / 2;
			if (A[m] >= target)
				r = m;
			else
				l = m + 1;
		}
		return l;
	}

	public static void main(String[] args) {
		int[] packages = { 2, 3, 5 };
		int[][] boxes = { { 1, 4 }, { 2, 3 }, {3,4} };

		Minimum minimum = new Minimum();
		int minWastedSpace = minimum.minWastedSpace(packages, boxes);
		System.out.println("Minimum wasted space: " + minWastedSpace);
	}

}
