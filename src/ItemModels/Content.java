package ItemModels;

import Interfaces.IContent;


public abstract class Content implements IContent {
    private final int arrivalDay;
    private final int number;
    private final String name;
    private final int duration; // Movie:minutes , Game:Hours
    private final double averageRating;
    private double resultRate;


    public Content(int arrivalDay, int number, String name, int duration, double averageRating) {
        this.arrivalDay = arrivalDay;
        this.number = number;
        this.name = name;
        this.duration = duration;
        this.averageRating = averageRating;
    }

    public int getArrivalDay() {
        return arrivalDay;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public double getResultRate() {
        if (resultRate >= 100) {
            return 100;
        } else {
            return resultRate;

        }
    }

    public void setResultRate(double resultRate) {
        this.resultRate = resultRate;
    }

    public void finalResult() {
        System.out.println(getName() + ", " + (int) getResultRate());  // name, rating
    }

    @Override
    public String toString() {
        return "Content{" +
                "arrivalDay=" + arrivalDay +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", averageRating=" + averageRating +
                '}';
    }

    public int compareTo(Object o) {
        if (o instanceof Content) {
            return getName().compareTo(((Content) o).getName());
        }
        return 1;
    }
}

