package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CALL_PHONE = 0;
    private EditText phoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);

        Button btn = findViewById(R.id.zerobtn);
        btn.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View view) {
                String number = "+";
                phoneNumberEditText.append(number);
                return true;
            }
        });
    }

    public void onNumberClick(View view) {
        String number = ((Button) view).getText().toString();
        phoneNumberEditText.append(number);
    }

    public void onDeleteClick(View view) {
        String phoneNumber = phoneNumberEditText.getText().toString();
        if (!phoneNumber.isEmpty()) {//0, phoneNumber.length() - 1
            phoneNumberEditText.setText(phoneNumber.substring(0,phoneNumber.length()-1));
        }
    }

    public void onCallClick(View view) {
        String phoneNumber = "tel:" + phoneNumberEditText.getText().toString();


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);

        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(phoneNumber));//
            startActivity(intent);
// else block means user has already accepted.And make your phone call here.

        }
    }
}