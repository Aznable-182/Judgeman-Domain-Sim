public class Crime{
    private final String crimeName;
    private final int maxSentence;

    public Crime(String name, int sentence){
        crimeName = name;
        maxSentence = sentence;
    }

    public String getCrimeName(){
        return crimeName;
    }
    public int getMaxSentence(){
        return maxSentence;
    }

    public String getMaxSentenceString(){
        int maximum = getMaxSentence();
        String maxString = "null";
        switch (maximum){
            case 1 -> maxString = "Guilty: Confiscation";
            case 2 -> maxString = "Guilty: Death Sentence";
        }
        return maxString;
    }

}