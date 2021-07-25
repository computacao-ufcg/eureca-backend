package br.edu.ufcg.computacao.eureca.backend.core.models;

import java.util.Collection;

public class MetricStatistics {
    private double min;
    private double firstQuartile;
    private double median;
    private double thirdQuartile;
    private double max;
    private double average;
    private int count;

    public MetricStatistics(double min, double firstQuartile, double median, double thirdQuartile, double max,
                            double average, int count) {
        this.min = min;
        this.firstQuartile = firstQuartile;
        this.median = median;
        this.thirdQuartile = thirdQuartile;
        this.max = max;
        this.average = average;
        this.count = count;
    }

    public MetricStatistics(int min, int max, int count) {
        this.min = min;
        this.max = max;
        this.count = count;
    }

    public MetricStatistics() {
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getFirstQuartile() {
        return firstQuartile;
    }

    public void setFirstQuartile(double firstQuartile) {
        this.firstQuartile = firstQuartile;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getThirdQuartile() {
        return thirdQuartile;
    }

    public void setThirdQuartile(double thirdQuartile) {
        this.thirdQuartile = thirdQuartile;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MetricStatistics computeStatistics(Collection<Double> sample) {
        double min;
        double firstQuartile;
        double median;
        double thirdQuartile;
        double max;
        int count;
        double accumulated = 0;

        Double[] sampleArray = (Double[]) sample.toArray();
        count = sampleArray.length;
        min = sampleArray[0];
        max = sampleArray[count-1];

        for (int i = 0; i < count; i++) {
            accumulated += sampleArray[i];
        }
        if (count >= 4) {
            int quartilSize = count / 4;
            firstQuartile = sampleArray[(quartilSize == 0 ? 0 : quartilSize - 1)];
            if (count % 2 == 0) {
                median = (double) (sampleArray[2 * quartilSize] + sampleArray[2 * quartilSize + 1]) / 2;
            } else {
                median = (double) sampleArray[2 * quartilSize];
            }
            thirdQuartile = sampleArray[3 * quartilSize - 1];
        } else {
            firstQuartile = -1;
            median = -1;
            thirdQuartile = -1;
        }
        return new MetricStatistics(min, firstQuartile, median, thirdQuartile, max, accumulated/count, count);
    }
}
