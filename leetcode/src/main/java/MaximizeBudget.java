import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximizeBudget {



    public int maximizeBudget(int budget, int[][] ads) {

        Map<Integer, AdPrices> categoryMap = new HashMap<>();
        Map<Integer, Integer> toBuy = new HashMap<>();

        int spend = 0;
        int maxSpend = 0;

        for (int i = 0; i < ads.length; i++) {
            categoryMap.put(i, new AdPrices(ads[i]));
        }

        while (spend <= budget) {



            if (spend > maxSpend && spend < budget) {
                maxSpend = spend;
            }
        }



        return spend;

    }

    class AdPrices {
        final int prices[];
        public AdPrices(int[] row) {
            this.prices = row;
            Arrays.sort(this.prices);
        }
    }
}
