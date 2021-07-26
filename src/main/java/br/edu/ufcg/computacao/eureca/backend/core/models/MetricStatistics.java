package br.edu.ufcg.computacao.eureca.backend.core.models;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

public class MetricStatistics {
    private double min;
    private double firstQuartile;
    private double median;
    private double thirdQuartile;
    private double max;
    private double average;
    private int sampleSize;

    public MetricStatistics(double min, double firstQuartile, double median, double thirdQuartile, double max,
                            double average, int sampleSize) {
        this.min = min;
        this.firstQuartile = firstQuartile;
        this.median = median;
        this.thirdQuartile = thirdQuartile;
        this.max = max;
        this.average = average;
        this.sampleSize = sampleSize;
    }

    public MetricStatistics(int min, int max, int sampleSize) {
        this.min = min;
        this.max = max;
        this.sampleSize = sampleSize;
    }

    public MetricStatistics(@NotNull List<Double> sample) {
        this.min = 0;
        this.firstQuartile = 0;
        this.median = 0;
        this.thirdQuartile = 0;
        this.max = 0;
        this.sampleSize = sample.size();

        Double[] sampleArray = new Double[this.sampleSize];
        Collections.sort(sample);
        int i = 0;
        double accumulated = 0;
        for (Double item: sample) {
            sampleArray[i++] = item;
            accumulated += item;
        }
        this.min = sampleArray[0];
        this.max = sampleArray[sampleSize-1];

        if (this.sampleSize >= 4) {
            int quartilSize = this.sampleSize / 4;
            this.firstQuartile = sampleArray[(quartilSize == 0 ? 0 : quartilSize - 1)];
            if (this.sampleSize % 2 == 0) {
                this.median = (sampleArray[2 * quartilSize] + sampleArray[2 * quartilSize + 1]) / 2;
            } else {
                this.median = sampleArray[2 * quartilSize];
            }
            this.thirdQuartile = sampleArray[3 * quartilSize - 1];
        } else {
            this.firstQuartile = -1;
            this.median = -1;
            this.thirdQuartile = -1;
        }
        this.average = (sampleSize == 0 ? -1 : accumulated/this.sampleSize);
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

    public int getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }
}
