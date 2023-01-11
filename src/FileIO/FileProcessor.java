package FileIO;

import Interfaces.IGame;
import Interfaces.IMovie;
import ItemModels.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor {
    private final ArrayList<IGame> games = new ArrayList();
    private final ArrayList<IMovie> movies = new ArrayList();

    public void scan() throws FileNotFoundException {
        Scanner scanner;

        scanner = new Scanner(new File("src/Assets/contents.csv"));

        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");

            switch (Integer.parseInt(data[1])) {
                case 0:
                    Movie movie = new Movie(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2],
                            Integer.parseInt(data[3]), Integer.parseInt(data[4]), Double.parseDouble(data[5]));
                    movies.add(movie);
                    break;
                case 1:
                    IndefiniteGame indefiniteGame = new IndefiniteGame(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2],
                            Integer.parseInt(data[3]), Double.parseDouble(data[4]));
                    games.add(indefiniteGame);
                    break;
                case 2:
                    //int arrivalDay, int number, String name, int duration, double averageRating
                    StoryGame storyGame = new StoryGame(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2],
                            Integer.parseInt(data[3]), Double.parseDouble(data[4]));
                    games.add(storyGame);
                    break;
                case 3:
                    CasualGame casualGame = new CasualGame(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2],
                            Integer.parseInt(data[3]), Double.parseDouble(data[4]));
                    games.add(casualGame);
                    break;
            }
        }
    }

    public ArrayList<IMovie> getMovies() {
        ArrayList<IMovie> movieStack = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            for (IMovie movie : movies) {
                if (movie.getArrivalDay() == i && !movieStack.contains(movie)) {
                    movieStack.add(movie);
                }

            }
        }
        return movieStack;
    }

    public ArrayList<IGame> getGames() {
        ArrayList<IGame> gameStack = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            for (IGame game : games) {
                if (game.getArrivalDay() == i && !gameStack.contains(game)) {
                    gameStack.add(game);
                }
            }
        }
        return gameStack;
    }
}