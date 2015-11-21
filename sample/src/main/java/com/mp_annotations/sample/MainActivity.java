package com.mp_annotations.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mp_annotations.AnalyticsManager;
import com.mp_annotations.annotation.TrackEvent;

/**
 * Created by ajaybhatt on 11/10/15.
 */
public class MainActivity extends AppCompatActivity {

    @TrackEvent(event = "ActivityOpen")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            onLoginButtonClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @TrackEvent(event = "Setting Open")
    private void onLoginButtonClicked() {
        AnalyticsManager.getInstance().trackEvent("Login Button");
        doStuff();
    }

    @TrackEvent(event = "Do Stuff")
    private void doStuff() {
    }
}