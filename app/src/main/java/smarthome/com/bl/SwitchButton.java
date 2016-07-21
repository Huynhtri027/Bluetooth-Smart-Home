package smarthome.com.bl;
import android.util.Log;
import android.widget.CompoundButton;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Azad on 21-Jul-16.
 */

class SwitchButton implements CompoundButton.OnCheckedChangeListener {

    private OutputStream outputStream;
    public SwitchButton(OutputStream outputStream){
        this.outputStream = outputStream;
    }
    public void setBl(OutputStream outputStream){
        this.outputStream = outputStream;
    }
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        try {

            String msg ="";
            if (isChecked) {
                // The toggle is enabled
                msg = buttonView.getTag().toString();


            } else {
                msg = buttonView.getTag().toString();
            }
            msg += "\n";
            Log.d("DRY", msg);
            outputStream.write(msg.getBytes());

        } catch (IOException ex) {
            Log.d("aaaaa","a");
        }
        catch (NullPointerException ex) {
            Log.d("null","a");
        }
    }
}
