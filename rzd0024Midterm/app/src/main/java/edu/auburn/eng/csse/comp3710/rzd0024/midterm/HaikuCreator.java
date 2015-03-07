package edu.auburn.eng.csse.comp3710.rzd0024.midterm;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Rémy on 03/03/15.
 */
public class HaikuCreator extends Fragment {

    private final String HAIKU_HISTORY_TAG = "haiku history";
    private Stack<Haiku> haikuHistory = new Stack<>();
    private Haiku haiku;
    private TextView haikuTextView;
    private Button deleteButton, addButton, startOverButton, displayButton;
    private Activity activity;
    private Words words;
    private Spinner spinner;
    private RadioGroup radioGroup;
    ArrayAdapter<String> adapter;
    ArrayList<Integer> possibleSyllables;

    Button.OnClickListener listenDelete = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            haikuHistory.pop();
            haiku = new Haiku(haikuHistory.peek());

            if(haikuHistory.size() == 1)
                deleteButton.setVisibility(View.INVISIBLE);

            addButton.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);

            refresh();
        }
    };

    Button.OnClickListener listenAdd = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(haiku.getNextSyllablesQuantity()>0) {
                int position = spinner.getSelectedItemPosition();

                haiku.addText(adapter.getItem(position), possibleSyllables.get(position));
                haikuHistory.push(new Haiku(haiku));

                deleteButton.setVisibility(View.VISIBLE);
                startOverButton.setVisibility(View.VISIBLE);
                displayButton.setVisibility(View.VISIBLE);

                refresh();
            }
        }
    };

    Button.OnClickListener listenStartOver = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            haikuHistory.clear();
            haiku = new Haiku();
            haikuHistory.push(new Haiku(haiku));
            initialStateVisibility();
        }
    };

    Button.OnClickListener listenDisplay = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((OnButtonSelected) activity).onDisplaySelected(haiku.getPhrases());
        }
    };

    RadioGroup.OnCheckedChangeListener listenWordsRadioGroup = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if(checkedId == -1) {
                spinner.setVisibility(View.INVISIBLE);
                addButton.setVisibility(View.INVISIBLE);
                startOverButton.setVisibility(View.INVISIBLE);
                displayButton.setVisibility(View.INVISIBLE);
            }
            else {
                spinner.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.VISIBLE);
                startOverButton.setVisibility(View.VISIBLE);
                displayButton.setVisibility(View.VISIBLE);
            }

            refresh();
        }
    };

    AdapterView.OnItemSelectedListener listenSpinnerItemSelected = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            addButton.setText("ADD\n'" + adapter.getItem(position).toUpperCase() + "'\nTO THE HAIKU");
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_haiku, parent, false);

        spinner = (Spinner) root.findViewById(R.id.words_spinner);
        radioGroup = (RadioGroup) root.findViewById(R.id.words_radio_group);
        deleteButton = (Button) root.findViewById(R.id.delete_last_word_button);
        addButton = (Button) root.findViewById(R.id.add_button);
        startOverButton = (Button) root.findViewById(R.id.start_over_button);
        displayButton = (Button) root.findViewById(R.id.display_button);
        haikuTextView = (TextView) root.findViewById(R.id.haiku_textview);

        deleteButton.setOnClickListener(listenDelete);
        addButton.setOnClickListener(listenAdd);
        startOverButton.setOnClickListener(listenStartOver);
        displayButton.setOnClickListener(listenDisplay);
        radioGroup.setOnCheckedChangeListener(listenWordsRadioGroup);
        spinner.setOnItemSelectedListener(listenSpinnerItemSelected);

        if(savedInstanceState != null) {
            haikuHistory = (Stack<Haiku>) savedInstanceState.getSerializable(HAIKU_HISTORY_TAG);
            haiku = haikuHistory.peek();
            words = new Words(activity);
        }

        if(haikuHistory.empty()) {
            words = new Words(activity);
            haiku = new Haiku();
            haikuHistory.push(new Haiku(haiku));
            initialStateVisibility();
        }
        else if(haikuHistory.size() == 1)
            deleteButton.setVisibility(View.INVISIBLE);

        refresh();

        return root;
    }

    public void refresh() {
        populateSpinner();
        setHaikuTextView();
    }

    private void initialStateVisibility() {
        deleteButton.setVisibility(View.INVISIBLE);
        addButton.setVisibility(View.INVISIBLE);
        spinner.setVisibility(View.INVISIBLE);
        startOverButton.setVisibility(View.INVISIBLE);
        displayButton.setVisibility(View.INVISIBLE);

        radioGroup.clearCheck();
    }

    private ArrayList<String> getPossibleWords() {
        int syllables = haiku.getNextSyllablesQuantity();
        ArrayList<String> possibleWords = new ArrayList<>();

        switch(radioGroup.getCheckedRadioButtonId()){
            case R.id.nouns_radio_button:
                possibleWords = words.getNounsWithNorLessSyllables(syllables);
                break;

            case R.id.verbs_radio_button:
                possibleWords = words.getVerbsWithNorLessSyllables(syllables);
                break;

            case R.id.adjectives_radio_button:
                possibleWords = words.getAdjectivesWithNorLessSyllables(syllables);
                break;

            case R.id.other_radio_button:
                possibleWords = words.getOtherWithNorLessSyllables(syllables);
                break;
        }

        return possibleWords;
    }

    public void setHaikuTextView() {
        String haikuPhrase = "";
        String[] phrases = haiku.getPhrases();

        for(int i=0; i<phrases.length; ++i) {
            if(i>0)
                haikuPhrase += "\n";
            haikuPhrase += Integer.toString(i+1) +") " + phrases[i];
        }
        haikuTextView.setText(haikuPhrase);
    }

    private void populateSpinner() {
        ArrayList<String> possibleWords = getPossibleWords();
        ArrayList<String> possibleW = new ArrayList<>();
        ArrayList<Integer> possibleI = new ArrayList<>();

        for(String word : possibleWords) {
            possibleW.add(word.substring(1));
            possibleI.add(Integer.parseInt(word.substring(0,1)));
        }

        adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, possibleW);
        possibleSyllables = possibleI;

        if(adapter.isEmpty()) {
            spinner.setVisibility(View.INVISIBLE);
            addButton.setVisibility(View.INVISIBLE);
        }

        spinner.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(HAIKU_HISTORY_TAG, haikuHistory);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public interface OnButtonSelected {
        public void onDisplaySelected(String[] phrases);
    }
}