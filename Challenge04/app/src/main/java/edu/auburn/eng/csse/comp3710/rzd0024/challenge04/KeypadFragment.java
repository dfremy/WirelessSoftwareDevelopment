package edu.auburn.eng.csse.comp3710.rzd0024.challenge04;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Rémy on 28/02/15.
 */
public class KeypadFragment extends Fragment {

    private Button num1_button, num2_button,num3_button;
    private Button num4_button,num5_button,num6_button;
    private Button num7_button,num8_button,num9_button;
    private Button num0_button, c_button, num42_button;
    private Activity activity;

    Button.OnClickListener listen0to9 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            ((OnKeySelected) activity).on0to9Clicked(button.getText().toString());
        }
    };

    Button.OnClickListener listen42 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((OnKeySelected) activity).on42Clicked();
        }
    };

    Button.OnClickListener listenc = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((OnKeySelected) activity).onClearClicked();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_keypad, parent, false);

        num1_button = (Button) root.findViewById(R.id.num1_button);
        num2_button = (Button) root.findViewById(R.id.num2_button);
        num3_button = (Button) root.findViewById(R.id.num3_button);
        num4_button = (Button) root.findViewById(R.id.num4_button);
        num5_button = (Button) root.findViewById(R.id.num5_button);
        num6_button = (Button) root.findViewById(R.id.num6_button);
        num7_button = (Button) root.findViewById(R.id.num7_button);
        num8_button = (Button) root.findViewById(R.id.num8_button);
        num9_button = (Button) root.findViewById(R.id.num9_button);
        num0_button = (Button) root.findViewById(R.id.num0_button);
        num42_button = (Button) root.findViewById(R.id.num42_button);
        c_button = (Button) root.findViewById(R.id.c_button);

        num1_button.setOnClickListener(listen0to9);
        num2_button.setOnClickListener(listen0to9);
        num3_button.setOnClickListener(listen0to9);
        num4_button.setOnClickListener(listen0to9);
        num5_button.setOnClickListener(listen0to9);
        num6_button.setOnClickListener(listen0to9);
        num7_button.setOnClickListener(listen0to9);
        num8_button.setOnClickListener(listen0to9);
        num9_button.setOnClickListener(listen0to9);
        num0_button.setOnClickListener(listen0to9);
        num42_button.setOnClickListener(listen42);
        c_button.setOnClickListener(listenc);

        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public interface OnKeySelected {
        public void onClearClicked();
        public void on0to9Clicked(String value);
        public void on42Clicked();
    }
}
