import java.util.Random;
import java.util.Scanner;

public class Menu{
    Scanner sc = new Scanner(System.in);
    private final Random random = new Random();
    private final Defendant accused = new Defendant();
    private final Higuruma user = new Higuruma();

    public void initAll(){
        accused.initCrime();
        accused.initEvidence();
        accused.initGrade();
        accused.initVoiceLinesD();
        user.initVoiceLinesH();
    }

    public void initialmenu(){
        initAll();
        System.out.println("Welcome to the Judgeman Domain Simulator.");
        pause(500);
        System.out.println("Enter 1 to start the sim, Enter 2 to quit.");
        int firstMenuChoice = sc.nextInt();
        if (firstMenuChoice == 1){
            System.out.println("||Domain Expansion||");
            pause(1000);
            System.out.println("||Deadly Sentencing||");
            pause(1000);
            battleMenu();
        }
        else{
            System.exit(0);
        }
    }

    public Defendant getDefendant(){
        return accused;
    }
    public Higuruma getUser(){
        return user;
    }

    public String getGradeString(){
        int gradeInt = getDefendant().getGrade();
        String gradeString;
        switch (gradeInt){
            case 0 -> gradeString = "Special Grade";
            case 1 -> gradeString = "First Grade";
            case 2 -> gradeString = "Second Grade";
            case 3 -> gradeString = "Third Grade";
            case 4 -> gradeString = "Fourth Grade";
            case 5 -> gradeString = "A Non Sorcerer";
            default -> gradeString = "Undefined";
        }
        return gradeString;
    }

    public String getEvidenceString(){
        int evidenceInt = getDefendant().getEvidence();
        String evidenceString;
        switch (evidenceInt){
            case 0 -> evidenceString = "Damning";
            case 1 -> evidenceString = "Relevant";
            case 2 -> evidenceString = "Irrelevant";
            default -> evidenceString = "Undefined";
        }
        return evidenceString;
    }

    public void battleMenu(){
        System.out.println("The defendant is " +(getGradeString())+ " and is accused of " +(getDefendant().getCrime()).getCrimeName()+ ".");
        pause(500);
        while (user.getPoints() < 3 && user.getPoints() > -3){
            battleLoop();
        }
        if (user.getPoints() >= 3){
            runBattleCalc(0);
        }
        else{
            runBattleCalc(1);
        }
    }

    public void pause(int timeFrame){
        try {
            Thread.sleep(timeFrame);
        } catch (InterruptedException ex) {
            System.getLogger(Menu.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void battleLoop(){
        int trialChoice = 0;
        if (user.getPoints() <= -1){
            System.out.println("If you'd like to, you can consult the evidence and force a certain decision");
            pause(500);
            System.out.println("0 to consult the evidence and 1 to continue the trial.");
    
            trialChoice = sc.nextInt();
            if (trialChoice == 0){
                evidenceCheck();
            }
        }
        else if (user.getPoints() > -1 || trialChoice == 1){
            accused.setChoice(random.nextInt(3));
            System.out.println("You can Accuse (0), Refute (1), or Agree (2).");
            pause(500);
            System.out.println("What do you do?");
            int userChoice = sc.nextInt();
            getUser().setChoice(userChoice);
            user.resultCalc(accused);
            resultSummary();
        }
    }

    public void evidenceCheck(){
        System.out.print("The Evidence is... ");
        pause(750);
        System.out.println(getEvidenceString());
        switch(getDefendant().getEvidence()){
            case 0 -> user.setPoints(3);
            case 1 -> user.setPoints(1);
            case 2 -> user.setPoints(-3);
        }
    }

    public String formatPoints(){
        String pointString = "";
        switch(user.getPoints()){
            case -3 -> pointString = "[ | | | | | ]";
            case -2 -> pointString = "[#| | | | | ]";
            case -1 -> pointString = "[#|#| | | | ]";
            case 0 -> pointString = "[#|#|#| | | ]";
            case 1 -> pointString = "[#|#|#|#| | ]";
            case 2 -> pointString = "[#|#|#|#|#| ]";
            case 3 -> pointString = "[#|#|#|#|#|#]";
        }
        return pointString;
    }

    public void resultSummary(){
        String defendantVL = accused.voiceLineChooseD();
        String higurumaVL = user.voiceLineChooseH();
        System.out.println(defendantVL);
        pause(750);
        System.out.println(higurumaVL);
        pause(500);
        System.out.println(formatPoints());
        pause(500);
    }

    public void runBattleCalc(int chance){
        if (chance == 1){
            int winChance = 0;
            System.out.print("Verdict... ");
            pause(750);
            System.out.println("Not Guilty");
            pause(500);
            switch (accused.getGrade()){
                case 5 -> winChance = 19;
                case 4 -> winChance = 19;
                case 3 -> winChance = 17;
                case 2 -> winChance = 14;
                case 1 -> winChance = 12;
                case 0 -> winChance = 9;
            }
            if (random.nextInt(19) < winChance){
                System.out.print("Have you ever killed someone who pissed you off? ...");
                pause(750);
                System.out.println("Feels better than you think.");
            }
            else{
                System.out.print("Even in curses... ");
                System.out.println("There can be judgement...");
                pause(500);
                retrial();
            }

        }
        else{
            String maxSentence = accused.getCrime().getMaxSentenceString();
            System.out.print("Verdict... ");
            pause(750);
            System.out.println(maxSentence);
            pause(500);
            int winChance = 0;
            if (accused.getCrime().getMaxSentence() == 1){
                switch (accused.getGrade()){
                    case 5 -> winChance = 19;
                    case 4 -> winChance = 19;
                    case 3 -> winChance = 18;
                    case 2 -> winChance = 16;
                    case 1 -> winChance = 14;
                    case 0 -> winChance = 11;
                }
            }
            else{
                switch (accused.getGrade()){
                    case 5 -> winChance = 19;
                    case 4 -> winChance = 19;
                    case 3 -> winChance = 19;
                    case 2 -> winChance = 18;
                    case 1 -> winChance = 17;
                    case 0 -> winChance = 13;
                }
            }
            if (random.nextInt(19) < winChance){
                System.out.print("Have you ever killed someone who pissed you off? ... ");
                pause(750);
                System.out.println("Feels better than you think.");
            }
            else{
                System.out.println("Even in curses... There can be judgement...");
                pause(500);
                retrial();
            }

        }
    }

    public void retrial(){
        System.out.println("Everyone come back... ");
        pause(1000);
        System.out.println("We're having a retrial.");
        pause(1000);
        user.setPoints(0);
        accused.initEvidence();
        accused.initCrime();
        battleMenu();
    }
}