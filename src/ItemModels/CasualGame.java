package ItemModels;


import Interfaces.IGame;

public class CasualGame extends Content implements IGame {
    // • Casual Game: arrival day, 3, name, match duration(hour), average rating

    public CasualGame(int arrivalDay, int number, String name, int matchDuration, double averageRating) {
        super(arrivalDay, number, name, matchDuration, averageRating);
    }

}
