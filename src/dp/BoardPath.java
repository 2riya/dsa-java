package dp;

/* given a board of size n and dice with possible values 1 to 6,
find out the number of ways to reach from start to end of board */
public class BoardPath {

    public static void main(String[] args) {
        int current = 0;
        int end = 10;
        System.out.println(boardPathTopDown(current, end, new int[end]));
        System.out.println(boardPathBottomUp(current, end));
        System.out.println(boardPathBottomUpSpaceEfficient(current, end));
    }

    private static int boardPathTopDown(int current, int end, int[] dp) {
        /* positive base case */
        if (current == end) {
            return 1;
        }

        /* negative base case */
        if (current > end) {
            return 0;
        }

        /* dp */
        if (dp[current] != 0) {
            return dp[current];
        }

        int count = 0;
        for (int dice = 1; dice <= 6; dice++) {
            count += boardPathTopDown(current + dice, end, dp);
        }

        dp[current] = count; /* memoize */

        return count;
    }

    private static int boardPathBottomUp(int current, int end) {
        int[] dp = new int[end + 1]; /* defining table */
        dp[end] = 1; /* defining initial state */
        for (int i = end - 1; i >= current; i--) {
            for (int dice = 1; dice <= 6; dice++) {
                if (i + dice <= end) {
                    dp[i] += dp[i + dice];
                }
            }
        }
        return dp[0];
    }

    private static int boardPathBottomUpSpaceEfficient(int current, int end) {
        int[] dp = new int[6]; /* defining table */

        dp[0] = 1; /* defining initial state */

        /* slide end number of times */
        for (int i = current; i < end; i++) {
            int currentPath = 0;
            for (int dice = 1; dice <= 6; dice++) {
                currentPath += dp[dice - 1];
            }

            /* updates should also be done from backwards */
            int j = dp.length - 1;
            for (; j > 0; j--) {
                dp[j] = dp[j - 1];
            }
            dp[j] = currentPath;
        }
        return dp[0];
    }
}
