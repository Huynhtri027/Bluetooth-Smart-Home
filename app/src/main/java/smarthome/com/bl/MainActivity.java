package smarthome.com.bl;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        tab2.setIndicator("Connection");
        tab2.setContent(new Intent(this,ConnectionManager.class));

        tabHost.addTab(tab2);

    }
}
