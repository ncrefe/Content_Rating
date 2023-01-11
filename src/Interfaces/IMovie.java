package Interfaces;

import CompanyModels.MovieCritic;

public interface IMovie extends IContent {
    public int getYear();

    public int compareTo(Object o);
}
