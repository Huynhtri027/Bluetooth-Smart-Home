package smarthome.com.bl;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStream;

public class ConnectionManager extends Activity
{
    TextView myLabel;
    Button connectButton ;
    Button closeButton ;
    OutputStream outputStream ;
    ListenForData listenForData;
    int i =0;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);
        connectButton = (Button)findViewById(R.id.connect);
        closeButton = (Button)findViewById(R.id.closebutton);
        final BlueToothManager blueToothManager = new BlueToothManager();

         myLabel = (TextView)findViewById(R.id.myLabel);

        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {

                    boolean x = blueToothManager.connectMyBL("HC-05");

                    Log.d("Connected", String.valueOf(x));
                    blueToothManager.openConnection();
                    outputStream = blueToothManager.getOutputSream();

                    listenForData = new ListenForData(blueToothManager.getInputSream());
                    listenForData.getTextArea(myLabel);

                    Thread mythred = new Thread(listenForData);

                    mythred.start();

                } catch (IOException ex) {
                    Log.d("My Ex" , ex.getMessage());
                }
                catch (NullPointerException ex)
                {
                    Log.d("My Null" , ex.getMessage());
                }
            }
        });

        //Close button
        closeButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try
                {
                    String msg = "Hello " + String.valueOf(i);
                    msg += "\n";
                    i++;
                    outputStream.write(msg.getBytes());

                }
                catch (IOException ex) { }
            }
        });
    }
}
