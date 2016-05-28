package smarthome.com.bl;

import android.widget.TextView;

/**
 * Created by Azad on 28-May-16.
 */

/**
 * This class to communicate with UI to background
 * data (InputSteam). Update data from backGround
 * to UI (TextView) by using Handler
 */
public class UpdatorUI implements Runnable {

    private TextView myLabel; // Update the data to this
    private String data; // Some data

    /**
     * Take TextView
     * @param myLabel
     */
    public UpdatorUI(TextView myLabel) {
        this.myLabel = myLabel;
    }

    /**
     *
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        myLabel.setText(data);
    }
}
