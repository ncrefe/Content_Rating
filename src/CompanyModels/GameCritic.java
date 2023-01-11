package CompanyModels;

import Interfaces.IGame;

public class GameCritic extends Critic implements Comparable {
    private IGame currentGame;
    private double timeLeft;

    public GameCritic(int number, double opinion) {
        super(number, opinion);
    }

    public void setCurrentGame(IGame game) {
        this.currentGame = game;

        switch (currentGame.getClass().getSimpleName()) {
            case "IndefiniteGame" -> this.timeLeft = 4;
            case "CasualGame" -> this.timeLeft = currentGame.getDuration() * 3;
            default -> this.timeLeft = game.getDuration();
        }
    }

    public IGame getCurrentGame() {
        return this.currentGame;
    }

    public void decreaseTimeByOneHour() {
        this.timeLeft -= 1;
    }

    public double getTimeLeft() {
        return this.timeLeft;
    }

    public boolean isDone() {
        return this.timeLeft == 1;
    }


    public double evaluate() {
        return switch (currentGame.getClass().getSimpleName()) {
            case "IndefiniteGame" -> currentGame.getAverageRating() + ((10 - currentGame.getDuration()) * 0.25) + getOpinion();
            case "StoryGame" -> currentGame.getAverageRating() + ((currentGame.getDuration()) * 0.25) + getOpinion();
            case "CasualGame" -> currentGame.getAverageRating() + ((currentGame.getDuration() - 3) * 3) + getOpinion();
            default -> -1;
        };
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof GameCritic) {
            return Integer.compare(getCurrentGame().getDuration(), ((GameCritic) o).getCurrentGame().getDuration());
        }
        return 1;
    }
}
