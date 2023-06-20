
public class RangeSum {
	public int countRangeSum(int[] nums, int lower, int upper) {
		final int n = nums.length;
		long[] prefix = new long[n + 1];

		for (int i = 0; i < n; ++i)
			prefix[i + 1] = (long) nums[i] + prefix[i];

		mergeSort(prefix, 0, n, lower, upper);
		return ans;
	}

	private int ans = 0;

	private void mergeSort(long[] prefix, int l, int r, int lower, int upper) {
		if (l >= r)
			return;

		final int m = (l + r) / 2;
		mergeSort(prefix, l, m, lower, upper);
		mergeSort(prefix, m + 1, r, lower, upper);
		merge(prefix, l, m, r, lower, upper);
	}

	private void merge(long[] prefix, int l, int m, int r, int lower, int upper) {
		int lo = m + 1;
		int hi = m + 1;

		for (int i = l; i <= m; ++i) {
			while (lo <= r && prefix[lo] - prefix[i] < lower)
				++lo;
			while (hi <= r && prefix[hi] - prefix[i] <= upper)
				++hi;
			ans += hi - lo;
		}

		long[] sorted = new long[r - l + 1];
		int k = 0;
		int i = l;
		int j = m + 1;

		while (i <= m && j <= r)
			if (prefix[i] < prefix[j])
				sorted[k++] = prefix[i++];
			else
				sorted[k++] = prefix[j++];

		while (i <= m)
			sorted[k++] = prefix[i++];

		while (j <= r)
			sorted[k++] = prefix[j++];

		System.arraycopy(sorted, 0, prefix, l, sorted.length);
	}

	public static void main(String[] args) {
		RangeSum solution = new RangeSum();

		int[] nums = { -2, 5, -1 };
		int lower = -2;
		int upper = 2;
		int count = solution.countRangeSum(nums, lower, upper);
		System.out.println("Count of Range Sums: " + count);

		RangeSum solution2 = new RangeSum();
		int[] nums2 = { 0 };
		lower = 0;
		upper = 0;
		int count2 = solution2.countRangeSum(nums2, lower, upper);
		System.out.println("Count of Range Sums: " + count2);
	}

}
