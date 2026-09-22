import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Defendant{
    private int grade;
    private Crime crime;
    private int evidence;
    private int choice;
    ArrayList<String> confessionLines = new ArrayList<>();
    ArrayList<String> lieLines = new ArrayList<>();
    private final Random random = new Random();

    public void initCrime(){
        int crimeNum = random.nextInt(12);
        switch (crimeNum){
            case 0 -> crime = new Crime("Petty Theft", 1);
            case 1 -> crime = new Crime("Fraud", 1);
            case 2 -> crime = new Crime("Underage Drinking", 1);
            case 3 -> crime = new Crime("Violence with injury", 1);
            case 4 -> crime = new Crime("Criminal Damage Offences", 1);
            case 5 -> crime = new Crime("Manslaughter", 2);
            case 6 -> crime = new Crime("Firearms Offence", 1);
            case 7 -> crime = new Crime("Underage Gambling", 1);
            case 8 -> crime = new Crime("Computer Misuse", 1);
            case 9 -> crime = new Crime("Illicit Drug Trafficking", 2);
            case 10 -> crime = new Crime("Drunk Driving", 1);
            case 11 -> crime = new Crime("Murder", 2);
            case 12 -> crime = new Crime("Mass Murder", 2);

        }
    }

    public void initGrade(){
        int gradeInt = random.nextInt(19);
        if (gradeInt <= 1){
            grade = 5;
        }
        else if (gradeInt <= 5) {
            grade = 4;
        }
        else if (gradeInt <= 10){
            grade = 3;
        }
        else if (gradeInt <= 14){
            grade = 2;
        }
        else if (gradeInt <= 17){
            grade = 1;
        }
        else if (gradeInt <= 19){
            grade = 0;
        }
    }
    public void initEvidence(){
        int evidenceInt = random.nextInt(19);
        if (evidenceInt <= 2){
            evidence = 0;
        }
        else if (evidenceInt <= 9){
            evidence = 1;
        }
        else if (evidenceInt <= 19) {
            evidence = 2;
        }
    }

    public int getGrade(){
        return grade;
    }
    public int getEvidence(){
        return evidence;
    }
    public Crime getCrime(){
        return crime;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public void initVoiceLinesD(){
        Collections.addAll(confessionLines, "Fine, it was me.","You got me...","Let's hurry up and end this.","Yeah... I did that, I'm not lying nor tryna deny it.");
        Collections.addAll(lieLines, "I didn't do that.","I would Never.","You won't trip me up.","What a Ridiculous Claim.");
    }

    public String voiceLineChooseD(){
        int VAChoice = random.nextInt(2);
        String voiceLine = "...";
        switch (getChoice()) {
            case 0 -> voiceLine = "..."; 
            case 1 -> voiceLine = lieLines.get(VAChoice);
            case 2 -> voiceLine = confessionLines.get(VAChoice);
        }
        return voiceLine;
    }
}