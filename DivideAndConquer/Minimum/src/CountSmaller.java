import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CountSmaller {
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> counts = new ArrayList<>();
		int[] countArray = new int[nums.length];
		int[] indexArray = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			indexArray[i] = i;
		}
		count(nums, 0, nums.length - 1, countArray, indexArray);
		for (int count : countArray) {
			counts.add(count);
		}
		return counts;
	}

	private void count(int[] nums, int left, int right, int[] countArray, int[] indexArray) {
		if (left >= right) {
			return;
		}

		int mid = (left + right) / 2;
		count(nums, left, mid, countArray, indexArray);
		count(nums, mid + 1, right, countArray, indexArray);

		int[] merged = new int[right - left + 1];
		int i = left;
		int j = mid + 1;
		int k = 0;
		int smallerCount = 0;

		while (i <= mid && j <= right) {
			if (nums[indexArray[j]] < nums[indexArray[i]]) {
				merged[k] = indexArray[j];
				smallerCount++;
				j++;
			} else {
				merged[k] = indexArray[i];
				countArray[indexArray[i]] += smallerCount;
				i++;
			}
			k++;
		}

		while (i <= mid) {
			merged[k] = indexArray[i];
			countArray[indexArray[i]] += smallerCount;
			i++;
			k++;
		}

		while (j <= right) {
			merged[k] = indexArray[j];
			j++;
			k++;
		}

		System.arraycopy(merged, 0, indexArray, left, merged.length);
	}
	
	public static void main(String[] args) {
        CountSmaller countSmaller = new CountSmaller();

        int[] nums = {5, 2, 6, 1};
        List<Integer> result = countSmaller.countSmaller(nums);
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Output: " + result);

        nums = new int[]{-1};
        result = countSmaller.countSmaller(nums);
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Output: " + result);

        nums = new int[]{-1, -1};
        result = countSmaller.countSmaller(nums);
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Output: " + result);
    }

}
