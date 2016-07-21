package smarthome.com.bl;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Azad on 28-May-16.
 */

/**
 * ReadFromSocket That is a Boilerplate class
 * to process all InputStream
 */
public class ReadFromSocket {

    private InputStream mmInputStream;
    byte[] readBuffer = new byte[100];
    final byte delimiter = 10; //This is the ASCII code for a newline character
    int readBufferPosition = 0; // init from 0

    /**
     * Take Bluetooth InputStream
     *
     * @param mmInputStream
     */
    public ReadFromSocket(InputStream mmInputStream) {
        this.mmInputStream = mmInputStream;
    }

    public int isDataAvailable()throws IOException{
        int bytesAvailable = mmInputStream.available();

        return bytesAvailable;
    }

    /**
     * Method to read from  InputStream and
     * process it in String
     *
     * data must be delimited by new line
     *
     * @return data
     * @throws IOException
     */
    public String readSocketData() throws IOException{

        int bytesAvailable = isDataAvailable();

        if(bytesAvailable > 0)
        {
            byte[] packetBytes = new byte[bytesAvailable];
            mmInputStream.read(packetBytes);
            for(int i=0;i<bytesAvailable;i++)
            {
                byte b = packetBytes[i];
                if(b == delimiter)
                {
                    byte[] encodedBytes = new byte[readBufferPosition];
                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                    final String data = new String(encodedBytes, "US-ASCII");
                    readBufferPosition = 0;
                    return data;
                }
                else
                {
                    readBuffer[readBufferPosition++] = b;
                }
            }
        }
        return "";
    }
}
