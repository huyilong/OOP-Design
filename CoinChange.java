public class Solution {
    public int coinChange(int[] coins, int amount) {
        // Sort the coins arrays to used in early pruning.
        Arrays.sort(coins);

        // The DP memeory array.
        int[] cnt = new int[amount + 1];

        // Step #1:
        // Any value that is equal to a coin has the most minimum number of coins
        // possible which is one coin.
        for(int i = 0; i < coins.length && coins[i] <= amount; i++) 
            cnt[coins[i]] = 1;

        // Iterate over all the amounts to build up the DP solution from small values up to amount.
        for(int i = 1; i <= amount; i++) {

            // If we accessed the  array position in step #1 then it is currently the Minimum -> leave it.
            // Else cnt[i] == 0 so we didn't evaluate it yet -> process it.
            if(cnt[i] == 0) {

                // Set to Maximum Value and use 1e9 to prevent overflow when adding 1;
                cnt[i] = 1000000000;

                // Iterate over all coins from small value to large value "SORTED".
                // If the value of the coin >= the current value "i" BREAK.

                // Also if the cnt[i] ever came down to 2 "cnt[i] > 2" BREAK as
                // 2 is the Most MINIMUM possible value for a non-coin value.
                for(int j = 0; j < coins.length && coins[j] < i && cnt[i] > 2; j++) {
                    if(cnt[i] > cnt[i - coins[j]] + 1) 
                       cnt[i] = cnt[i - coins[j]] + 1;
                }
            }
        }

        // If we cann't make the amount then cnt[amount] == 1e9 and return -1.
        return cnt[amount] >= 1000000000 ? -1 : cnt[amount];
    }
}