package CompanyModels;


public abstract class Critic {
    private final int number;
    private final double opinion;

    public Critic(int number, double opinion) {
        this.number = number;
        this.opinion = opinion;
    }

    public int getNumber() {
        return number;
    }

    public double getOpinion() {
        return opinion;
    }


}
