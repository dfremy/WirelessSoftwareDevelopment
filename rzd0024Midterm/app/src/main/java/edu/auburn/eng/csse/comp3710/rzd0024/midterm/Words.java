package edu.auburn.eng.csse.comp3710.rzd0024.midterm;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Rémy on 01/03/15.
 */
public class Words {
    private ArrayAdapter<CharSequence> nouns, verbs, other, adjectives;

    public Words(Activity activity) {
        nouns = ArrayAdapter.createFromResource(activity, R.array.nouns, android.R.layout.simple_spinner_dropdown_item);
        verbs = ArrayAdapter.createFromResource(activity, R.array.verbs, android.R.layout.simple_spinner_dropdown_item);
        other = ArrayAdapter.createFromResource(activity, R.array.other, android.R.layout.simple_spinner_dropdown_item);
        adjectives = ArrayAdapter.createFromResource(activity, R.array.adjectives, android.R.layout.simple_spinner_dropdown_item);
    }

    public ArrayList<String> getNounsWithNorLessSyllables(int n) {
        return getWordsWithNorLessSyllables(nouns, n);
    }

    public ArrayList<String> getVerbsWithNorLessSyllables(int n) {
        return getWordsWithNorLessSyllables(verbs, n);
    }

    public ArrayList<String> getOtherWithNorLessSyllables(int n) {
        return getWordsWithNorLessSyllables(other, n);
    }

    public ArrayList<String> getAdjectivesWithNorLessSyllables(int n) {
        return getWordsWithNorLessSyllables(adjectives, n);
    }

    public ArrayList<String> getWordsWithNorLessSyllables(ArrayAdapter<CharSequence> adapter, int n) {
        ArrayList<String> words = new ArrayList<>();

        for(int i=0; i<adapter.getCount(); ++i) {
            if(n >= Integer.parseInt(String.valueOf(adapter.getItem(i).charAt(0))))
                words.add(adapter.getItem(i).toString());
        }

        return words;
    }

}
