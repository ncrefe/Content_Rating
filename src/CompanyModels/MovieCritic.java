package CompanyModels;

import Interfaces.IMovie;

public class MovieCritic extends Critic implements Comparable {
    private IMovie currentMovie;

    public MovieCritic(int number, double opinion) {
        super(number, opinion);
    }

    public IMovie getCurrentMovie() {
        return currentMovie;
    }

    public void setCurrentMovie(IMovie watchMovie) {
        this.currentMovie = watchMovie;
    }

    //• Movie: average rating + ((duration - 150) * 0.01) - ((2021 - year) * 0.01) + (critic’s opinion)
    public double evaulate() {
        return currentMovie.getAverageRating() + ((currentMovie.getDuration() - 150) * 0.01) - ((2021 - currentMovie.getYear()) * 0.01) + getOpinion();
    }


    @Override
    public int compareTo(Object o) {
        if (o instanceof MovieCritic) {
            return Integer.compare(getCurrentMovie().getDuration(), ((MovieCritic) o).getCurrentMovie().getDuration());
        }
        return 1;
    }
}
