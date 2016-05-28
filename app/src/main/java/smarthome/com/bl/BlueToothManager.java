package smarthome.com.bl;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Azad on 28-May-16.
 */
public class BlueToothManager {

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothSocket mmSocket;
    private BluetoothDevice mmDevice;


    private OutputStream mmOutputStream;
    private InputStream mmInputStream;

    private boolean isCOnnedted = false;


    public BlueToothManager() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // if no bluetooth module
        if(mBluetoothAdapter == null)isCOnnedted = false;
        // if ur bluetooths is turned off
        if (!mBluetoothAdapter.isEnabled()) isCOnnedted = false;
        //otherwise connected
        isCOnnedted = true;

    }



    public boolean isCOnnedted() {
        return isCOnnedted;
    }

    /**
     * Connect a specific BluetoothDevice from
     * a Set of paired BL devices
     * @param blname
     * @return
     *
     * @throws
     * NullPointerException if no paired devices
     * matches
     */

    public boolean connectMyBL(String blname){


        mmDevice = null;
        // Set of all pair devices
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
                Log.d("BLname",device.getName());
        //        Log.d("BLname",device.getAddress());
                if(device.getName().equals(blname))
                {
                    mmDevice = device;
                    return true;
                }
            }
        }
        if (mmDevice == null) throw  new NullPointerException("No device found");
        return false;
    }

    /**
     * Connect with a UUID code
     * @throws IOException
     */
    public void openConnection()throws IOException
    {
        // MY_UUID is the app's UUID string, also used by the server code
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //Standard SerialPortService ID

        mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);

        mmSocket.connect();
        mmOutputStream = mmSocket.getOutputStream();
        mmInputStream = mmSocket.getInputStream();

    }
    public OutputStream getOutputSream(){
        return mmOutputStream;
    }
    public InputStream getInputSream(){
        return mmInputStream;
    }
}
