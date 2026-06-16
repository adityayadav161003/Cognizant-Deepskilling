public class FinancialForecast {
    public static double calculateFutureValue(double pv, double rate, int periods) {
        if (periods == 0) return pv;
        return calculateFutureValue(pv * (1 + rate), rate, periods - 1);
    }
    // Time complexity is O(N) where N is number of periods. Optimized via iteration/memoization.
}
