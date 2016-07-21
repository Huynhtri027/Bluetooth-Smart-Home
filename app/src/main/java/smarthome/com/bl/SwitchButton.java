package smarthome.com.bl;

import android.content.Context;
import android.widget.CompoundButton;

/**
 * Created by Azad on 21-Jul-16.
 */

public class SwitchButton implements CompoundButton.OnCheckedChangeListener {

    String myObject;
    public SwitchButton(String yourObject){
        this.myObject = yourObject;
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (myObject == whatever){
//            ....
//        }
    }
}
