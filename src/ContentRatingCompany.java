import CompanyModels.Company;
import FileIO.FileProcessor;
import Interfaces.ICompany;
import Interfaces.IGame;
import Interfaces.IMovie;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ContentRatingCompany {
    public static void main(String[] args) throws FileNotFoundException {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.scan();

        ArrayList<IMovie> movies = fileProcessor.getMovies();
        ArrayList<IGame> games = fileProcessor.getGames();

        ICompany company = new Company(games,movies);
        company.work();
    }
}
