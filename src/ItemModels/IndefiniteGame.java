package ItemModels;

import Interfaces.IGame;

public class IndefiniteGame extends Content implements IGame {
    // • Indefinite Game: arrival day, 1, name, minimum evaluation time(hour), average rating

    public IndefiniteGame(int arrivalDay, int number, String name, int duration, double averageRating) {
        super(arrivalDay, number, name, duration, averageRating);
    }


}
