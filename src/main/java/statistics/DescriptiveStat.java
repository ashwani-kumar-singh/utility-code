package statistics;


import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.util.Precision;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class DescriptiveStat {

    public static void main(String[] args) {

        /*for (Number n : Arrays.asList(12, 123.12345, 0.23, 0.1, 2341234.21243135)) {
            Double d = n.doubleValue();
            System.out.println(roundToNDecimalPlace(d, 3, RoundingMode.HALF_UP));
            d = Precision.round(d,7);
            System.out.println( "precision - " + d);
        }*/
        calculateMean();
    }

    private static Double roundToNDecimalPlace(Double num, int decimalPlace, RoundingMode roundingMode){
        StringBuilder sb = new StringBuilder("#.");
        for(int i =1; i<=decimalPlace; i++){
            sb.append("#");
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        df.setRoundingMode(roundingMode);
        return Double.parseDouble(df.format(num));

    }
    private static void calculateMean(){
        List<Double> riskIndexList = Arrays.asList(2.0, 4.0, 8.0);/*90.0, 85.0, 80.5, 92.0, 95.0, 84.0, 90.0,
                96.0, 99.0, 67.0, 91.0, 102.0, 97.0, 79.0, 78.0, 80.0, 86.5, 87.9, 86.9, 95.0 );*/
                //,100.0, 100.0, 100.0, 100.0,100.0, 100.0,100.0, 100.0,100.0, 100.0,100.0, 100.0, 100.0);
        List<Integer> weightList = Arrays.asList(1,3,7,1,1,1,7,3,3,3,3,7,7,3,1,7,1,1,7,7);
                //,7, 7,7,7,7,7,7,7,7,7,7,7,7);

        DescriptiveStatistics ds = new DescriptiveStatistics();
        riskIndexList.forEach(ds::addValue);

        System.out.println("ds.getMax():" + ds.getMax());
        System.out.println("ds.getMin():"+ ds.getMin());
        System.out.println("ds.getGeometricMean():" + ds.getGeometricMean());;
        System.out.println("ds.getKurtosis():" + ds.getKurtosis());;
        System.out.println("ds.getQuadraticMean():"+ ds.getQuadraticMean());;
        System.out.println("ds.getQuadraticMean():"+ ds.getPopulationVariance());;
        System.out.println("ds.getWindowSize():"+ ds.getWindowSize());;
        System.out.println("ds.getSkewness():" + ds.getSkewness());
        System.out.println("size of risk index list -> "+ riskIndexList.size());
        System.out.println("size of weight  list -> "+ weightList.size());
        System.out.println("65 percentile -> " + ds.getPercentile(65));
        System.out.println("Median -> " + ds.getPercentile(50));
        System.out.println("Standard Deviation -> " + ds.getStandardDeviation());
        System.out.println("2-Sigma -> " + 2 * ds.getStandardDeviation());
        System.out.println("Mean using ds -> " + ds.getMean());
        double[] riskIndexes = riskIndexList.stream().mapToDouble(Double::doubleValue).toArray();
        double[] weights = weightList.stream().mapToDouble(Integer::doubleValue).toArray();
        System.out.println("riskIndexes -> " + Arrays.toString(riskIndexes));
        System.out.println("weights -> " + Arrays.toString(weights));


        //System.out.println("Mean of risk indexes-> "+ mean.evaluate(riskIndexes));
        //System.out.println("Weighted Mean of risk indexes-> "+ mean.evaluate(riskIndexes, weights));

    }
}
