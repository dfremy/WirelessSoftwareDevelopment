package edu.auburn.eng.csse.comp3710.rzd0024.midterm;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HaikuActivity extends ActionBarActivity implements HaikuCreator.OnButtonSelected {

    private final String HAIKU_TEXT = "haiku text";
    private final String HAIKU_CREATOR_FRAGMENT_TAG = "haiku creator fragment";
    private final String HAIKU_PREVIEW_FRAGMENT_TAG = "haiku preview fragment";

    private HaikuCreator haikuCreatorFragment;
    private HaikuPreview haikuPreviewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        if(savedInstanceState == null) {
            haikuCreatorFragment = new HaikuCreator();

            transaction.add(R.id.fragment, haikuCreatorFragment, HAIKU_CREATOR_FRAGMENT_TAG);
            transaction.commit();
        }
        else {
            haikuPreviewFragment = (HaikuPreview) fm.findFragmentByTag(HAIKU_PREVIEW_FRAGMENT_TAG);
            haikuCreatorFragment = (HaikuCreator) fm.findFragmentByTag(HAIKU_CREATOR_FRAGMENT_TAG);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_haiku, menu);
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
    public void onDisplaySelected(String[] phrases) {
        String text = "";

        for(int i=0; i<phrases.length; ++i) {
            if(i>0)
                text += "\n";

            text += phrases[i];
        }

        haikuPreviewFragment = new HaikuPreview();
        Bundle bundle = new Bundle();
        bundle.putString(HAIKU_TEXT, text);
        haikuPreviewFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment, haikuPreviewFragment, HAIKU_PREVIEW_FRAGMENT_TAG);
        transaction.addToBackStack(HAIKU_PREVIEW_FRAGMENT_TAG);
        transaction.commit();
    }
}
