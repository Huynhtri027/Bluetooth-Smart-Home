package smarthome.com.bl;

import android.widget.Switch;

/**
 * Created by Azad on 03-Jun-16.
 */
public class SwitchUpdate implements Updator, Runnable {

    private Switch aSwitch;
    boolean state;
    private String data; // Some data



    public SwitchUpdate(Switch aSwitch) {
        this.aSwitch = aSwitch;
    }

    private boolean getstate(){
        return true;
    }


    /**
     *
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void update() {

        if (data.compareTo("0") == 0)
            aSwitch.setChecked(false);
        else aSwitch.setChecked(true);
    }
    @Override
    public void run() {
            update();
    }
}
