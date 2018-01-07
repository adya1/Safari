package com.apkglobal.safari.ImplicitIntent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.apkglobal.safari.R;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    Button btn_call, btn_email, btn_share, btn_web,btn_callrun; int pid=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn_call = (Button) findViewById(R.id.btn_call);
        btn_email = (Button) findViewById(R.id.btn_email);
        btn_share = (Button) findViewById(R.id.btn_share);
        btn_web = (Button) findViewById(R.id.btn_web);
        btn_callrun=(Button)findViewById(R.id.btn_callrun);
        btn_call.setOnClickListener(this);
        btn_share.setOnClickListener(this);
        btn_email.setOnClickListener(this);
        btn_web.setOnClickListener(this);
        btn_callrun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_call:
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:8076460247"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(call);
                break;
            case R.id.btn_email:
                Intent email=new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"adya.jha98@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT,"Hello");
                email.putExtra(Intent.EXTRA_TEXT,"how r u?");
                email.setType("email/rfc822");
                startActivity(Intent.createChooser(email,"send email"));
                break;
            case R.id.btn_share:
                Intent share=new Intent(Intent.ACTION_SEND);

                share.putExtra(Intent.EXTRA_TEXT,"Download the App"+"com.apkglobal.safari");
                share.setType("text/plain");
                startActivity(Intent.createChooser(share,"share App via"));
                break;
            case R.id.btn_web:
                Intent web = new Intent(Intent.ACTION_VIEW);
                web.setData(Uri.parse("http://www.google.com/"));
                startActivity(web);
                break;
            case R.id.btn_callrun:
                callatruntime();
                break;
        }
    }
    private  void callatruntime() {
        //check manifest permission and mobile sdk>=23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, pid);
        } else {
            //open call function
            Intent call = new Intent(Intent.ACTION_CALL);
            call.setData(Uri.parse("tel:8076460247"));
            startActivity(call);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == pid) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permissio is granted

                callatruntime();
            } else {
                Toast.makeText(getApplicationContext(), "not granted", Toast.LENGTH_LONG).show();

            }
        }
    }


}


