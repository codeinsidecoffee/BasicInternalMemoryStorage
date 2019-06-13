package com.mrlonewolfer.basicinternalmemorystorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL =1 ;
    private static final String FIlE_NAME = "SaveMe";
    Button btnWrite,btnRead;
    TextView txtResult;
    EditText edtMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnWrite=findViewById(R.id.btnWrite);
        btnRead=findViewById(R.id.btnRead);
        txtResult=findViewById(R.id.txtResult);
        edtMsg=findViewById(R.id.edtMsg);
        btnWrite.setOnClickListener(this);
        btnRead.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnWrite){

            writeDataToInternalMemory();
        }
        if(v.getId()==R.id.btnRead){
            readDataFromInternalMemory();
        }

    }

    private void readDataFromInternalMemory() {


        try {
            FileInputStream fileInputStream=openFileInput(FIlE_NAME);
            byte b[]=new byte[fileInputStream.available()];
            fileInputStream.read(b);
            String msg=new String(b);
            txtResult.setText(msg);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeDataToInternalMemory() {
        String msg=edtMsg.getText().toString();

        try {
            FileOutputStream fileOutputStream=openFileOutput(FIlE_NAME,MODE_PRIVATE);
            fileOutputStream.write(msg.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Your Message Written Successfully.", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        edtMsg.setText("");
    }
}
