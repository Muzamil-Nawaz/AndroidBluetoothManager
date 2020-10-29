package com.example.bluetoothmanagerlab5task1;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter bluetoothAdapter;
    Button startBluetooth,stopBluetooth,listDevices;
    private Set<BluetoothDevice> pairedDevices;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBluetooth = (Button) findViewById(R.id.startBluetooth);
        stopBluetooth = (Button) findViewById(R.id.stopBluetooth);
        listDevices = (Button) findViewById(R.id.listDevices);
        listView = (ListView)findViewById(R.id.listView);

        stopBluetooth.setEnabled(false);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


        pairedDevices = bluetoothAdapter.getBondedDevices();


    }
    public void onStart(View v){
        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOn,0);
        Toast.makeText(getApplicationContext(),"Bluetooth is on now.",Toast.LENGTH_SHORT).show();
        startBluetooth.setEnabled(false);
        stopBluetooth.setEnabled(true);
        pairedDevices = bluetoothAdapter.getBondedDevices();
    }
    public void onStop(View v){
        bluetoothAdapter.disable();
        Toast.makeText(getApplicationContext(),"Bluetooth is off now.",Toast.LENGTH_SHORT).show();
        startBluetooth.setEnabled(true);
        stopBluetooth.setEnabled(false);
    }
    public void listDevices(View v){
        ArrayList<String> devices  = new ArrayList<>();
        Iterator iterator = pairedDevices.iterator();

        while (iterator.hasNext()){
            devices.add(iterator.next().toString());
            System.out.println(devices);
        }
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,devices);

        listView.setAdapter(adapter);
    }
}
