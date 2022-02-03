package interview;

import java.util.*;

public class MaximizeSpend {


    public int maximizeSpend(int budget, int[][] adPrices) {

        int spend = 0;

        Map<Integer, Queue<Integer>> map = new HashMap<>();

        // build map of sorted adPrices
        // each entry in the Map<Integer, Queue> is an ordered list of the next cost for an ad in the category.
        int category = 0;
        for (; category < adPrices.length; category++) {
            Queue<Integer> q = sortAndCalculate(adPrices[category]);
            // start with the first price for each category
            int minCost = q.poll();
            spend += minCost;
            map.put(category, q);
        }

        while (spend <= budget) {
            boolean exhausted = true;
            for (category = 0; spend <= budget && category < adPrices.length; category++) {
                Queue<Integer> q = map.get(category);

                if (q.size() >= 1 && spend + q.peek() <= budget) {
                    int incrementalCost = q.poll();
                    spend += incrementalCost;
                    // also short-circuit when there are no more prices to evaluate in the category
                    exhausted = q.size() == 0;
                }
            }
            if (exhausted) { break; }
        }

        return spend;
    }

    public static Queue<Integer> sortAndCalculate(int[] array) {
        // assuming the array is small... this should be efficient enough
        for (int i= 0; i < array.length - 1; i++) {
            while (array[i] > array[i + 1]) {
                swap(array, i, i + 1);
                i = Math.max(0, i-1);
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < array.length; i++) {
            int p = array[i];
            if (queue.size() == 0) {
                queue.add(p);
            } else {
                // slightly clever decision here to store the incremental cost for each additional ad
                // this will allow the algorithm to just add this value to the total when evaluating the maximum spend
                queue.add(p - array[i - 1]);
            }
        }
        return queue;
    }

    public static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }



    public static void main(String[] args) {

        int[][] ads = new int[][] {
                {9, 4, 7},
                {8, 4, 2},
                {7, 4, 3},
                {3, 7, 9, 4}
        };

        int budget = 30;
        int spend = new MaximizeSpend().maximizeSpend(budget, ads);

        System.out.println("$"+ spend +" of $"+ budget +", $"+ (budget - spend) +" remaining.");

    }

    public static int[][] generateAdPrices(int categories) {
        int[][] ads = new int[categories][];
        int prices = 100;

        for (int c = 0; c < categories; c++) {

            ads[c] = new int[prices];

            for (int price = prices - 1; price > 0; price--) {
                ads[c][price] = new Random().nextInt(89) + 3;
            }

        }
        return ads;

    }
}
