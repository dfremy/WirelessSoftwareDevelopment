package edu.auburn.eng.csse.comp3710.rzd0024.midterm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Rémy on 01/03/15.
 */
public class HaikuPreview extends Fragment {
    private TextView haikuTextView;
    private final String HAIKU_TEXT = "haiku text";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_preview, parent, false);

        haikuTextView = (TextView) root.findViewById(R.id.haiku_preview_textview);

        String text = getArguments().getString(HAIKU_TEXT);
        haikuTextView.setText(text);

        return root;
    }
}
