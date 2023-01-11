package CompanyModels;


import Interfaces.ICompany;
import Interfaces.IGame;
import Interfaces.IMovie;
import java.util.*;

public class Company implements ICompany {
    private final Double[] opinionsOfMovieCritics = {0.1, -0.2, 0.3};
    private final Double[] opinionsOfGameCritics = {5.0, 9.0, -3.0, 2.0, -7.0};
    private int day = 1;

    // We made a company design as discussed in our homework. Company employees wait in line (FIFO) in the
    // "movieCritics,gameCritics" queues to get jobs. The content they will receive is waiting in the stacks "games,movies" (LIFO).
    // The critic, who is assigned a job during the day, enters "evaluationProcessMovies, evaluationProcessGames",
    // which we can think of as an office, while doing this job. If he completes his work and there is content in stack,
    // he re-enters the queue and takes the job and starts it. The evaluated "games,movies" is kept in "evaluatedMovies,evaluatedGames".

    // Movies are treated in the same way as for games in the assignment. He/She who finishes the job first from the critics
    // goes directly to the queue . In other words, the one who evaluates the short film takes its place first.

    // List of contents in File
    private ArrayList<IGame> allGames;
    private ArrayList<IMovie> allMovies;

    // Queue for evaluating the contents
    private Queue<MovieCritic> movieCritics = new LinkedList<>();
    private Queue<GameCritic> gameCritics = new LinkedList<>();

    // Stack holds the unevaluated contents
    private Stack<IGame> games = new Stack<>();
    private Stack<IMovie> movies = new Stack<>();

    // Arraylist shows the order of unfinished contents
    private ArrayList<MovieCritic> evaluationProcessMovies = new ArrayList<>();
    private ArrayList<GameCritic> evaluationProcessGames = new ArrayList<>();

    // ArrayList for printing final ratings (Evaluated Contents)
    private ArrayList<IMovie> evaluatedMovies = new ArrayList<>();
    private ArrayList<IGame> evaluatedGames = new ArrayList<>();

    public Company(ArrayList<IGame> allGames, ArrayList<IMovie> allMovies) {
        this.allGames = allGames;
        this.allMovies = allMovies;
    }

    private void putMoviesToStack() {
        for (IMovie movie : allMovies) {
            if (movie.getArrivalDay() == day) {
                movies.push(movie);
            }
        }
    }

    private void putGamesToStack() {
        for (IGame game : allGames) {
            if (game.getArrivalDay() == day) {
                games.push(game);
            }
        }
    }

    private void addMovieCritics(int numberOfMovieCritics) {
        for (int i = 0; i < numberOfMovieCritics; i++) {
            movieCritics.add(new MovieCritic(i + 1, opinionsOfMovieCritics[i]));
        }
    }

    private void addGameCritics(int numberOfGameCritics) {
        for (int i = 0; i < numberOfGameCritics; i++) {
            gameCritics.add(new GameCritic(i + 1, opinionsOfGameCritics[i]));
        }
    }

    private void workOnMovies() {
        for (MovieCritic tempMovieCritic : evaluationProcessMovies) {
            tempMovieCritic.getCurrentMovie().setResultRate(tempMovieCritic.evaulate());
            evaluatedMovies.add(tempMovieCritic.getCurrentMovie());
            System.out.println(tempMovieCritic.getNumber() + ". movie critic evaluated (" + tempMovieCritic.getCurrentMovie().getName() + ")");
            movieCritics.add(tempMovieCritic);
        }
        evaluationProcessMovies.clear();
    }

    private void workOnGames() {
        ArrayList<GameCritic> tempGameCriticList = new ArrayList();

        for (GameCritic tempGameCritic : evaluationProcessGames) {
            tempGameCritic.decreaseTimeByOneHour();

            if (tempGameCritic.isDone()) {
                tempGameCritic.getCurrentGame().setResultRate(tempGameCritic.evaluate());
                evaluatedGames.add(tempGameCritic.getCurrentGame());
                System.out.println(tempGameCritic.getNumber() + ". game critic evaluated (" + tempGameCritic.getCurrentGame().getName() + ")");
                gameCritics.add(tempGameCritic);

                if (!gameCritics.isEmpty() && !games.isEmpty()) {
                    GameCritic newGameCritic = gameCritics.remove();
                    newGameCritic.setCurrentGame(games.pop());
                    tempGameCriticList.add(newGameCritic);
                    System.out.println(newGameCritic.getNumber() + ". game critic works on (" + newGameCritic.getCurrentGame().getName() + ")");
                }
            } else {
                tempGameCriticList.add(tempGameCritic);
            }
        }

        evaluationProcessGames = tempGameCriticList;
    }

    // For one day work
    private void movieCriticsWork() {
        int size = movieCritics.size();
        for (int i = 0; i < size; i++) {
            if (!movies.empty()) {
                MovieCritic tempMovieCritic = movieCritics.remove();
                tempMovieCritic.setCurrentMovie(movies.pop());
                evaluationProcessMovies.add(tempMovieCritic);
            } else {
                break;
            }
        }
        if (!evaluationProcessMovies.isEmpty()) {
            Collections.sort(evaluationProcessMovies);
            workOnMovies();
        }
    }

    // For one day work
    private void gameCriticsWork() {
        int size = gameCritics.size();
        for (int i = 0; i < size; i++) {
            if (!games.empty()) {
                GameCritic tempGameCritic = gameCritics.remove();
                tempGameCritic.setCurrentGame(games.pop());
                evaluationProcessGames.add(tempGameCritic);
                System.out.println(tempGameCritic.getNumber() + ". game critic works on (" + tempGameCritic.getCurrentGame().getName() + ")");
            } else {
                break;
            }
        }

        if (!evaluationProcessGames.isEmpty()) {
            Collections.sort(evaluationProcessGames);
            workOnGames();
        }
    }

    private void printRatesOfMovies() {
        Collections.sort(evaluatedMovies);
        for (IMovie movie : evaluatedMovies) {
            movie.finalResult();
        }
    }

    private void printRatesOfGames() {
        Collections.sort(evaluatedGames);
        for (IGame game : evaluatedGames) {
            game.finalResult();
        }
    }

    public void work() {
        addGameCritics(5);
        addMovieCritics(3);

        for (int day = 1; day < 6; day++) {
            this.day = day;
            System.out.println(day + ".day:");
            putGamesToStack();
            putMoviesToStack();
            movieCriticsWork();

            for (int hour = 1; hour < 9; hour++) {
                gameCriticsWork();
            }
        }

        System.out.println("\n\n");
        System.out.println("Ratings:");
        printRatesOfMovies();
        System.out.println();
        printRatesOfGames();
    }
}
