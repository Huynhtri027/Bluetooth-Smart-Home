package smarthome.com.bl;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Azad on 28-May-16.
 */
public class ListenForData implements Runnable {

    private String data;
    private boolean stopWorker = false;
    private Handler handler = new Handler();
    private UpdatorUI updatorUI;
    private ReadFromSocket readFromSocket;

    /**
     * Listen for data.
     *
     * @param mmInputStream
     */
    public ListenForData(InputStream mmInputStream) {
        readFromSocket = new ReadFromSocket(mmInputStream);
    }

    /**
     * Pass The TextView to display the changed
     * data
     * @param myLabel
     */
    public void getTextArea(TextView myLabel){
        updatorUI = new UpdatorUI(myLabel);
    }
    @Override
    public void run()
    {
        Looper.prepare();
        while(!Thread.currentThread().isInterrupted() && !stopWorker)
        {
            try {
                if (readFromSocket.isDataAvailable()>0) {
                    data = readFromSocket.readSocketData();
                    updatorUI.setData(data);
                    handler.post(updatorUI);
                }
            }
            catch (IOException e){
                stopWorker = true;
            }

        }
        Looper.loop();
    }
}
