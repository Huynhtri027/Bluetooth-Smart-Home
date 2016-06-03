package smarthome.com.bl;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Azad on 28-May-16.
 */
public class ListenForData implements Runnable {

    private String data;
    private boolean stopWorker = false;
    private Handler handler = new Handler();
    private Updator updatorUI;
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
     * @param updator
     */

    public void setUIComponent(Updator updator){
        updatorUI = updator;
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

                    handler.post((Runnable) updatorUI);
                }
            }
            catch (IOException e){
                stopWorker = true;
            }

        }
        Looper.loop();
    }
}
