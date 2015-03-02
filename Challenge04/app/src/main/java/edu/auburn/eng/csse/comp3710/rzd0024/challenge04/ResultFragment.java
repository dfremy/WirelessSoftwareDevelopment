package edu.auburn.eng.csse.comp3710.rzd0024.challenge04;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Rémy on 28/02/15.
 */
public class ResultFragment extends Fragment {

    private final String RESULT_TEXT_KEY = "result_text";

    private EditText resultEditText;

    public void setText(String text) {
        resultEditText.setText(text);
    }

    public String getText() {
        return resultEditText.getText().toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_result, parent, false);

        resultEditText = (EditText) root.findViewById(R.id.resultTextView);

        if (savedInstanceState != null)
            resultEditText.setText(savedInstanceState.getString(RESULT_TEXT_KEY));

        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(RESULT_TEXT_KEY, resultEditText.getText().toString());
    }
}
