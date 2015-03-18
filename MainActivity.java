package com.example.dgzl.gis_glass_helper_app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.os.Build;

import java.util.Set;

import static android.R.layout.simple_list_item_1;


public class MainActivity extends ActionBarActivity {

    private static final int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public void connectBT(View view){
        final int id = view.getId();
        ImageView image_view;
        switch (id) {
            case R.id.database_button:
                break;
            case R.id.bluetooth_button:

                /* CREATE BLUETOOTH ADAPTER */

                // the device's bluetooth radio adapter
                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter == null) {
                   // device does not support
                    // handle gracefully. TODO
                } else {
                    // does support
                    // show image for recognition

                    // all code in here? TODO
                }
                // checks if bluetooth is enabled
                if (!mBluetoothAdapter.isEnabled()) {
                    // ask to enable
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }


                /* DISCOVERY */
                /* RFCOMM */

                /* QUERY EXISTING PAIRS */
                final ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, android.R.id.text1);

//              listView.setAdapter(mArrayAdapter);

                Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
                // If there are paired devices
                if (pairedDevices.size() > 0) {
                    // Loop through paired devices
                    for (BluetoothDevice device : pairedDevices) {
                        // show image for recognition
                        image_view = (ImageView) findViewById(R.id.imageview1);
                        image_view.setVisibility(View.VISIBLE);
                        // Add the name and address to an array adapter to show in a ListView
                        mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                    }
                }

                final ArrayAdapter<BluetoothDevice> oArrayAdapter
                        = new ArrayAdapter<BluetoothDevice>(getApplicationContext(),
                        simple_list_item_1);

                mBluetoothAdapter.startDiscovery();
                BroadcastReceiver btReceiver = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        // When discovery finds a device
                        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                            // Get the BluetoothDevice object from the Intent
                            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                            // Add the name and address to an array adapter to show in a ListView
                            StringBuilder b = new StringBuilder();
                            b.append(device.getAddress());
                            String s = b.toString();

                            if (s.equals("00:06:66:44:E6:00")) {
                                oArrayAdapter.add(device);
                            }

                        }
                    }
                };

                /*
                /**/

                /***************** Device Discovery *******************************/
/*
                // Create a BroadcastReceiver for ACTION_FOUND
                final BroadcastReceiver mReceiver;
                mReceiver = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        // When discovery finds a device
                        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                            // Get the BluetoothDevice object from the Intent
                            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                            // Add the name and address to an array adapter to show in a ListView
                           mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                        }
                    }
                };

                // Register the BroadcastReceiver
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy

*/


                break;
            case R.id.other_button:
                break;
            case R.id.other_button_2:
                break;
            // even more buttons here
        }
    }

}
