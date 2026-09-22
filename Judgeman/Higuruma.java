import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Higuruma{
    private int choice;
    private int points;
    ArrayList<String> accuseLines = new ArrayList<>();
    ArrayList<String> refuteLines = new ArrayList<>();
    ArrayList<String> agreeLines = new ArrayList<>();
    private final Random random = new Random();

    public Higuruma(){
        choice = 0;
        points = 0;
    }

    public void initVoiceLinesH(){
        Collections.addAll(accuseLines, "Your Guilt is clear.","The truth will set you free.","Just admit it.","You can end this right now.");
        Collections.addAll(refuteLines, "You couldn't be further from the truth.","You're Lying.","Don't make me laugh.");
        Collections.addAll(agreeLines, "Exactly.","Correct.","Finally, the Truth.","That's what I'm looking for.");
    }


    public int getPoints(){
        return points;
    }

    public void setChoice(int choice){
        this.choice = choice;
    }
    public int getChoice(){
        return choice;
    }
    public void modifyPoints(int increment){
        points += increment;
    }

    public void setPoints(int pointsNum){
        points = pointsNum;
    }

    public void resultCalc(Defendant accused){
        switch (getChoice()) {
            case 0 -> {
                switch (accused.getChoice()) {
                    case 0 -> modifyPoints(1);
                    case 1 -> modifyPoints(0);
                    case 2 -> modifyPoints(1);
                }
            }
            case 1 -> {
                switch (accused.getChoice()) {
                    case 0 -> modifyPoints(0);
                    case 1 -> modifyPoints(1);
                    case 2 -> modifyPoints(-1);
                }
            }
            case 2 -> {
                switch (accused.getChoice()) {
                    case 0 -> modifyPoints(-1);
                    case 1 -> modifyPoints(-2);
                    case 2 -> modifyPoints(3);
                }
            }
        }
    }

    public String voiceLineChooseH(){
        int VAChoice = random.nextInt(2);
        String voiceLine = "...";
        switch (getChoice()) {
            case 0 -> voiceLine = accuseLines.get(VAChoice); 
            case 1 -> voiceLine = refuteLines.get(VAChoice);
            case 2 -> voiceLine = agreeLines.get(VAChoice);
        }
        return voiceLine;
    }
}
