package Interfaces;

public interface IContent extends Comparable {
    public int getArrivalDay();

    public String getName();

    public int getDuration();

    public double getAverageRating();

    public String toString();

    public double getResultRate();

    public void setResultRate(double resultRate);

    public void finalResult();

    public int compareTo(Object o);
}
