package com.example.fulgencio.sunshine.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fulgencio on 19/02/2015.
 */

public  class ForecastFragment extends Fragment {

    public ForecastFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ArrayList<String> stLabels = new ArrayList<String>(Arrays.asList(
                "Today - Horrible -3",
                "Tomorrow - Catastrophic -4",
                "Saturday - Could be worse -2", "Sunday - No Sun, ironically 0",
                "Monday - Blue sky, just that  -3",
                "Tuesday - Snowfall!   -1",
                "Wednesday - Winter is staying -5"
        ));

        ArrayAdapter<String> adapterForecast = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast,
                R.id.list_item_forecast_textview, stLabels);
        ListView showWeatherList = (ListView)rootView.findViewById(R.id.listView_forecast);
        showWeatherList.setAdapter(adapterForecast);

        new FetchWeatherTask().execute();

        return rootView;
    }

    public class FetchWeatherTask extends AsyncTask<Void,Void, Void> {

        private final String LOG_TAG= FetchWeatherTask.class.getSimpleName();
        protected Void doInBackground(Void... params) {
            //copied snippet code
            // These two need to be declared outside the try/catch
// so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;
            try {
// Construct the URL for the OpenWeatherMap query
// Possible parameters are available at OWM's forecast API page, at
// http://openweathermap.org/API#forecast
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");
// Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
// Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
// Nothing to do.
                    forecastJsonStr = null;
                    //return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
// Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
// But it does make debugging a *lot* easier if you print out the completed
// buffer for debugging.
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
// Stream was empty. No point in parsing.
                   // forecastJsonStr = null;
                    return null;
                }
                forecastJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
// If the code didn't successfully get the weather data, there's no point in attempting
// to parse it.
                //forecastJsonStr = null;
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
            //end of copied snippet code
            //return forecastJsonStr;
            return null;

        }

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        //MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.forecastfragment,menu);
        return;
    }

    //public boolean onOptionsItemSelected(MenuItem item){

    //}
}
