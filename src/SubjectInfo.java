import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SubjectInfo {
    private String subjectID;
    private String trialType;
    private int numDistractors;
    private int correctGuesses;
    private boolean hasGuessed;
    private int incorrectGuesses;
    private ArrayList<Guess> guessList;
    public Guess currentGuess;

    public int numGuesses;

    @Override
    public String toString() {
        return subjectID + ", " + trialType + ", " + numDistractors + ", " + correctGuesses + ", " + incorrectGuesses + "\n";
    }

    public SubjectInfo() {
        subjectID = "";
        trialType = "";
        numDistractors = 0;
        correctGuesses = 0;
        incorrectGuesses = 0;
        hasGuessed = false;
        guessList = new ArrayList<Guess>();
        currentGuess = new Guess();
        numGuesses = 0;
    }

    public void resetSubject(){
        subjectID = "";
        trialType = "";
        numDistractors = 0;
        correctGuesses = 0;
        incorrectGuesses = 0;
        hasGuessed = false;
        guessList.clear();
        currentGuess.resetGuess();
        numGuesses = 0;
    }

    public void saveSubjectInfo(){
        try{
            File file = new File("trial-results.txt");
            File file2 = new File("guess-records.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            FileWriter file2Writer = new FileWriter(file2, true);

            /*if(file.length() <= 0){
                fileWriter.write("subjectID, trialType, numDistractors, numCorrectGuesses, numIncorrectGuesses\n");
            }*/

            if(file.length() == 0){
                FileWriter title1Writer = new FileWriter(file);
                title1Writer.write("subjectID, trialType, numDistractors, numCorrectGuesses, numIncorrectGuesses\n");
                title1Writer.close();
            }
            /*if(file2.length() <= 0){
                file2Writer.write("subjectID, guessNum, correct, hadUnique, trialType, inspectionTime\n");
            }*/
            if(file2.length() == 0){
                FileWriter title2Writer = new FileWriter(file2);
                title2Writer.write("subjectID, guessNum, correct, hadUnique, trialType, inspectionTime\n");
                title2Writer.close();
            }
            fileWriter.write(this.toString());
            for(Guess guess: guessList){
                file2Writer.write(subjectID + ", " + guess.toString() + "\n");
            }
            fileWriter.close();
            file2Writer.close();
        }catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    public void incrementNumGuesses(){
        numGuesses += 1;
    }
    public void recordGuess(){
        currentGuess.setGuessNum(numGuesses);
        guessList.add(currentGuess);
        currentGuess = new Guess();
        currentGuess.resetGuess();
    }



    public boolean isHasGuessed() {
        return hasGuessed;
    }

    public void setHasGuessed(boolean hasGuessed) {
        this.hasGuessed = hasGuessed;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public void setCorrectGuesses(int correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public void setIncorrectGuesses(int incorrectGuesses) {
        this.incorrectGuesses = incorrectGuesses;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getTrialType() {
        return trialType;
    }

    public void setTrialType(String trialType) {
        this.trialType = trialType;
    }

    public int getNumDistractors() {
        return numDistractors;
    }

    public void setNumDistractors(int numDistractors) {
        this.numDistractors = numDistractors;
    }
}
