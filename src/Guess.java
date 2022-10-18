public class Guess {
    private boolean correct;
    private boolean hadUnique;
    private String trialType;

    private int inspectionTime;

    private int guessNum;

    public Guess(boolean correct, boolean hadUnique, String trialType, int guessNum, int interval) {
        this.correct = correct;
        this.hadUnique = hadUnique;
        this.trialType = trialType;
        this.guessNum = guessNum;
        this.inspectionTime = interval;
    }

    public Guess(String trialType){
        this.trialType = trialType;
        this.correct = false;
        this.hadUnique = false;
        this.guessNum = 0;
    }

    public Guess(){
        this.trialType = "";
        this.correct = false;
        this.hadUnique = false;
        this.guessNum = 0;
    }

    public int getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(int inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public void setCorrectAndUnique(boolean correct, boolean hadUnique){
        this.correct = correct;
        this.hadUnique = hadUnique;
    }

    public void incrementGuessNum(){
        guessNum += 1;
    }

    public void resetGuess(){
        this.trialType = "";
        this.correct = false;
        this.hadUnique = false;
        this.guessNum = 0;
        this.inspectionTime = 125;
    }

    public void setGuess(String trialType, boolean correct, boolean hadUnique, int guessNum){
        this.correct = correct;
        this.hadUnique = hadUnique;
        this.trialType = trialType;
        this.guessNum = guessNum;
    }

    public void setGuessNum(int guessNum){
        this.guessNum = guessNum;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isHadUnique() {
        return hadUnique;
    }

    public void setHadUnique(boolean hadUnique) {
        this.hadUnique = hadUnique;
    }

    public String getTrialType() {
        return trialType;
    }

    public void setTrialType(String trialType) {
        this.trialType = trialType;
    }

    @Override
    public String toString() {
        return guessNum + ", " + correct + ", " + hadUnique + ", " + trialType + ", " + inspectionTime;
    }
}
