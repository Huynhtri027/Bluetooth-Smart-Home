package smarthome.com.bl;
import android.util.Log;
import android.widget.CompoundButton;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Azad on 21-Jul-16.
 *
 * This Class is responsible for obeying DRY principle
 * for Switch (toggle switch)
 */

class SwitchButton implements CompoundButton.OnCheckedChangeListener {

    private OutputStream outputStream; // OutputStream for Bluetooth

    /**
     *
     * @param outputStream  OutputStream for Bluetooth
     */
    public SwitchButton(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    /**
     *
     * @param outputStream OutputStream for Bluetooth
     */
    public void setBl(OutputStream outputStream){
        this.outputStream = outputStream;
    }
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        try {

            String msg ="";
            if (isChecked) { // The toggle is enabled

                msg = buttonView.getTag().toString();

                //The toggle is disabled
            } else {
                // vice versa
                msg = buttonView.getTag().toString();
            }
            msg += "\n";
            Log.d("DRY", msg);
            // sent signal to OutputStream

            outputStream.write(msg.getBytes());

        } catch (IOException ex) {
            Log.d("aaaaa","a");
        }
        catch (NullPointerException ex) {
            Log.d("null","a");
        }
    }
}
