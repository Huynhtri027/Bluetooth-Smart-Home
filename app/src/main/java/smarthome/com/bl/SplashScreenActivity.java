package smarthome.com.bl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by RAIHAN on 2/17/2017.
 */

public class SplashScreenActivity extends Activity {

    //Set Duration of the Splash Screen
    long Delay = 5000;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Remove the Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Get the view from splash_screen.xml
        setContentView(R.layout.splash_screen);

        //Create a Timer
        Timer runSplash = new Timer();

        //Task to do when the timer ends
        TimerTask showSplash = new TimerTask() {
            @Override
            public void run() {
                //Close SplashScrenActivity.class
                finish();

                //Start MainActivity.class
                Intent myIntent = new Intent(SplashScreenActivity.this, ConnectionManager.class);
                startActivity(myIntent);
            }
        };

        //Start the timer
        runSplash.schedule(showSplash, Delay);
    }
}
