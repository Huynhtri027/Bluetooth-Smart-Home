package smarthome.com.bl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.OutputStream;

public class ConnectionManager extends Activity
{
    Button connectButton ;
    Button closeButton ;
    OutputStream outputStream ;
    // Helpfull to get response from BL remote device
    // ListenForData listenForData;
    Switch toggle1, toggle2, toggle3 ;
    SwitchButton switchButton = new SwitchButton(outputStream);
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);
        connectButton = (Button)findViewById(R.id.connect);
        toggle1 =(Switch) findViewById(R.id.switch1);
        toggle2 =(Switch) findViewById(R.id.switch2);
        toggle3 =(Switch) findViewById(R.id.switch3);
        final BlueToothManager blueToothManager = new BlueToothManager();
        final Updator updator = new SwitchUpdate(toggle1);

        toggle1.setTag("1");
        toggle2.setTag("2");
        toggle3.setTag("3");

        toggle1.setOnCheckedChangeListener(switchButton);
        toggle2.setOnCheckedChangeListener(switchButton);
        toggle3.setOnCheckedChangeListener(switchButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {

                    boolean x = blueToothManager.connectMyBL("HC-05");

                    Log.d("Connected", String.valueOf(x));
                    blueToothManager.openConnection();
                    outputStream = blueToothManager.getOutputSream();

                    if (outputStream != null){
                        connectButton.setText("Connected");
                        connectButton.setEnabled(false);
                    }
                    switchButton.setBl(outputStream);
                    outputStream.write("6666\n".getBytes());
//                    listenForData = new ListenForData(blueToothManager.getInputSream());
//                    listenForData.setUIComponent(updator);
//
//                    Thread mythred = new Thread(listenForData);
//
//                    mythred.start();

                } catch (IOException ex) {
                    Log.d("My Ex" , ex.getMessage());
                }
                catch (NullPointerException ex)
                {
                    Log.d("My Null" , ex.getMessage());
                }
            }
        });


    }
}
