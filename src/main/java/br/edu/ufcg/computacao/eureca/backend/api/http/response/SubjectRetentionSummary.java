package br.edu.ufcg.computacao.eureca.backend.api.http.response;

public class SubjectRetentionSummary {
    private int subjectCount;
    private int minimum;
    private int maximum;
    private double firstQuartile;
    private double median;
    private double thirdQuartile;
    private double average;

    public SubjectRetentionSummary(int subjectCount, int minimum, int maximum,
                                   double firstQuartile, double median, double thirdQuartile, double average) {
        this.subjectCount = subjectCount;
        this.minimum = minimum;
        this.maximum = maximum;
        this.firstQuartile = firstQuartile;
        this.median = median;
        this.thirdQuartile = thirdQuartile;
        this.average = average;
    }

    public int getSubjectCount() {
        return subjectCount;
    }

    public void setSubjectCount(int subjectCount) {
        this.subjectCount = subjectCount;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
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

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
