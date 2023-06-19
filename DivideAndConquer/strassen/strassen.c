#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int maxCoins(int* nums, int n) {
    int* balloons = (int*)malloc((n + 2) * sizeof(int));
    balloons[0] = 1;
    balloons[n + 1] = 1;

    for (int i = 1; i <= n; i++) {
        balloons[i] = nums[i - 1];
    }

    int** dp = (int**)malloc((n + 2) * sizeof(int*));
    for (int i = 0; i < n + 2; i++) {
        dp[i] = (int*)calloc((n + 2), sizeof(int));
    }

    for (int len = 3; len <= n + 2; len++) {
        for (int left = 0; left <= n + 2 - len; left++) {
            int right = left + len - 1;
            for (int i = left + 1; i < right; i++) {
                dp[left][right] = fmax(dp[left][right], dp[left][i] + dp[i][right] + balloons[left] * balloons[i] * balloons[right]);
            }
        }
    }

    int result = dp[0][n + 1];

    for (int i = 0; i < n + 2; i++) {
        free(dp[i]);
    }
    free(dp);
    free(balloons);

    return result;
}

int main() {
    int n;
    printf("Enter the number of balloons: ");
    scanf("%d", &n);

    int* nums = (int*)malloc(n * sizeof(int));
    printf("Enter the numbers on the balloons:\n");
    for (int i = 0; i < n; i++) {
        scanf("%d", &nums[i]);
    }

    int coins = maxCoins(nums, n);
    printf("Maximum coins you can collect: %d\n", coins);

    free(nums);

    return 0;
}
