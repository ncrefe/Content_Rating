package ItemModels;


import Interfaces.IMovie;

public class Movie extends Content implements IMovie {
    //    • Movie: arrival day, 0, name, year, duration(min), average rating
    private final int year;

    public Movie(int arrivalDay, int number, String name, int year, int duration, double averageRating) {
        super(arrivalDay, number, name, duration, averageRating);
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void finalResult() {
        System.out.print(getName() + "(" + getYear() + "), " + String.format("%.2g%n", getResultRate()).replace(",","."));
    }
}
