package smarthome.com.bl;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.OutputStream;

import smarthome.com.bl.databinding.ConnectionBinding;
import smarthome.util.Messages;

public class ConnectionManager extends BaseFragment implements View.OnClickListener {
    OutputStream outputStream;
    SwitchButton switchButton = new SwitchButton(outputStream);


    private ConnectionBinding binding;
    private BlueToothManager blueToothManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blueToothManager = new BlueToothManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.connection, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.switch1.setTag("1");
        binding.switch2.setTag("2");
        binding.switch3.setTag("3");

        binding.switch1.setOnCheckedChangeListener(switchButton);
        binding.switch2.setOnCheckedChangeListener(switchButton);
        binding.switch3.setOnCheckedChangeListener(switchButton);
        binding.connect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.connect) {
            try {

                boolean x = blueToothManager.connectMyBL(Messages.BLUETOOTH_DEVICE_NAME);

                Log.d("Connected", String.valueOf(x));
                blueToothManager.openConnection();
                outputStream = blueToothManager.getOutputSream();

                if (outputStream != null) {
                    binding.connect.setText("Connected");
                    binding.connect.setEnabled(false);
                }
                switchButton.setBl(outputStream);
                outputStream.write("6199\n".getBytes());

            } catch (IOException ex) {
                Log.d("My Ex", ex.getMessage());
            } catch (NullPointerException ex) {
                Log.d("My Null", ex.getMessage());
            }
        }
    }
}
