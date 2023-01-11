package ItemModels;

import Interfaces.IGame;


public class StoryGame extends Content implements IGame {
    //• Story Game: arrival day, 2, name, story duration(hour), average rating

    public StoryGame(int arrivalDay, int number, String name, int duration, double averageRating) {
        super(arrivalDay, number, name, duration, averageRating);
    }
}
