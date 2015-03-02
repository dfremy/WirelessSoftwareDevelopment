package edu.auburn.eng.csse.comp3710.rzd0024.challenge04;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements KeypadFragment.OnKeySelected{

    private final String RESULT_KEY = "result";
    private final String KEYPAD_KEY = "keypad";
    private final String RESULT_FRAGMENT_KEY = "result fragment";
    private final String KEYPAD_FRAGMENT_KEY = "keypad fragment";

    private KeypadFragment keypadFragment;
    private ResultFragment resultFragment;

    private int keypadFragmentValue, resultFragmentValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        if (savedInstanceState == null) {
            resultFragment = new ResultFragment();
            keypadFragment = new KeypadFragment();
            keypadFragmentValue = R.id.fragment2;
            resultFragmentValue = R.id.fragment1;
            transaction.add(resultFragmentValue, resultFragment, RESULT_KEY);
            transaction.add(keypadFragmentValue, keypadFragment, KEYPAD_KEY);
            transaction.commit();
        }
        else {
            keypadFragmentValue = savedInstanceState.getInt(KEYPAD_FRAGMENT_KEY);
            resultFragmentValue = savedInstanceState.getInt(RESULT_FRAGMENT_KEY);
            resultFragment = (ResultFragment) fm.findFragmentByTag(RESULT_KEY);
            keypadFragment = (KeypadFragment) fm.findFragmentByTag(KEYPAD_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(RESULT_FRAGMENT_KEY, resultFragmentValue);
        savedInstanceState.putInt(KEYPAD_FRAGMENT_KEY, keypadFragmentValue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClearClicked() {
        resultFragment.setText("");

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.remove(keypadFragment);
        transaction.remove(resultFragment);
        transaction.commit();

        try{
            fm.executePendingTransactions();
        } catch (Exception e){

        }

        int aux = resultFragmentValue;
        resultFragmentValue = keypadFragmentValue;
        keypadFragmentValue = aux;

        transaction = fm.beginTransaction();
        transaction.replace(resultFragmentValue, resultFragment, RESULT_KEY);
        transaction.replace(keypadFragmentValue, keypadFragment, KEYPAD_KEY);
        transaction.commit();
    }

    @Override
    public void on0to9Clicked(String value) {
        String s = resultFragment.getText() + value;
        if(s.length() <= 10)
            resultFragment.setText(String.valueOf(s));
    }

    @Override
    public void on42Clicked() {
        Long value = Long.parseLong("0" + resultFragment.getText()) + 42;
        String s = value.toString();
        if(s.length() <= 10)
            resultFragment.setText(String.valueOf(s));
    }
}