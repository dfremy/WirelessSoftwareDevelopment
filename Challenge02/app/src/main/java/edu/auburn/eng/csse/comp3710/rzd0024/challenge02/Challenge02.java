package edu.auburn.eng.csse.comp3710.rzd0024.challenge02;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Challenge02 extends ActionBarActivity {

    final String ORIENTATION_CHANGES_QT = "orientation_changes";
    final String ORIENTATION_KEY = "orientation";

    private int orientationChangesQt = 0;
    private int orientation = 0;

    private TextView challenge02TextView;
    private TelephonyManager telephonyManager;

    PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if(state == TelephonyManager.CALL_STATE_RINGING) {
                orientationChangesQt = 0;
                challenge02TextView.setText("Orientation changes: " + Integer.toString(orientationChangesQt));
                //call = true;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_challenge02, menu);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("challenge02", "onCreate");
        setContentView(R.layout.activity_challenge02);

        if(savedInstanceState != null) {
            orientationChangesQt = savedInstanceState.getInt(ORIENTATION_CHANGES_QT);
            orientation = savedInstanceState.getInt(ORIENTATION_KEY);
        }
        else {
            orientationChangesQt = 0;
            orientation = getResources().getConfiguration().orientation;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("challenge02", "onSavedInstanceState");

        savedInstanceState.putInt(ORIENTATION_CHANGES_QT, orientationChangesQt);
        savedInstanceState.putInt(ORIENTATION_KEY, orientation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("challenge02", "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("challenge02", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("challenge02", "onResume");
        if(orientation != getResources().getConfiguration().orientation) {
            orientationChangesQt++;
            orientation = getResources().getConfiguration().orientation;
        }

        challenge02TextView = (TextView) findViewById(R.id.textView_orientation);
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        challenge02TextView.setText("Orientation changes: " + Integer.toString(orientationChangesQt));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("challenge02", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("challenge02", "onDestroy");
    }

}
