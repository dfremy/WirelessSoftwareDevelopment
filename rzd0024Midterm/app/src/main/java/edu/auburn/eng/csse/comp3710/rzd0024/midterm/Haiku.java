package edu.auburn.eng.csse.comp3710.rzd0024.midterm;

/**
 * Created by Rémy on 01/03/15.
 */
public class Haiku {

    private String[] phrases = new String[3];
    private int[] syllables = new int[3];

    public Haiku() {
        phrases[0] = "";
        phrases[1] = "";
        phrases[2] = "";

        syllables[0] = 5;
        syllables[1] = 7;
        syllables[2] = 5;
    }

    public Haiku(Haiku haiku) {
        this.phrases = haiku.phrases.clone();
        this.syllables = haiku.syllables.clone();
    }

    public int getNextSyllablesQuantity() {
        int quantity = 0;

        for(int i=2; i>=0; --i)
            if(syllables[i]!=0)
                quantity = syllables[i];

        return quantity;
    }

    public String[] getPhrases() {
        return phrases;
    }

    public void addText(String text, int syllablesQuantity) {
        int i;
        for(i=0; i<3; ++i)
            if(syllables[i] > 0)
                break;
        if(i<3) {
            syllables[i] -= syllablesQuantity;
            phrases[i] += text + " ";
        }
    }
}
