package com.example.fulgencio.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG= MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
         Log.v(LOG_TAG,"onCreate happening");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            Intent settingsIntent = new Intent(this,SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(LOG_TAG,"onPause happening");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(LOG_TAG,"onResume happening");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(LOG_TAG,"onStop happening");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(LOG_TAG,"onStart happening");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG,"onDestroy happening");

    }

    /**
     * A placeholder fragment containing a simple view.
     */
}
