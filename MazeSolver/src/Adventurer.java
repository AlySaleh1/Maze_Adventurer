public class Adventurer {

    private int stepsTaken;
    private String secretKey;

    public Adventurer(int stepsTaken, String secretKey) {
        this.stepsTaken = stepsTaken;
        this.secretKey = secretKey;
    }


    public void setStepsTaken(int stepsTaken) {
        this.stepsTaken = stepsTaken;
    }

    public int getStepsTaken() {
        return stepsTaken;
    }


    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }


}
