package com.ezetap.rborawar.ezetaptest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    EditText editText1, editText2;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPackageInstalled("com.ezetap.rborawar2.ezetap", getPackageManager())) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ezetap.rborawar2.ezetap");
                    if (launchIntent != null) {
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        launchIntent.putExtra("number1", Integer.parseInt(editText1.getText().toString()));
                        launchIntent.putExtra("number2", Integer.parseInt(editText2.getText().toString()));
                        launchIntent.putExtra("action", "add");
                        startActivity(launchIntent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Please Installed the Other App for Calculation",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPackageInstalled("com.ezetap.rborawar2.ezetap", getPackageManager())) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ezetap.rborawar2.ezetap");
                    if (launchIntent != null) {
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        launchIntent.putExtra("number1", Integer.parseInt(editText1.getText().toString()));
                        launchIntent.putExtra("number2", Integer.parseInt(editText2.getText().toString()));
                        launchIntent.putExtra("action", "subtract");
                        startActivity(launchIntent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Please Installed the Other App for Calculation",Toast.LENGTH_LONG).show();
                }
            }
        });

        updateResult();
    }

    public void init() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        textViewResult = (TextView) findViewById(R.id.resultTextView);
    }

    public void updateResult() {
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            int result = bundle.getInt("result");
            textViewResult.setText(""+result);
        }
    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {

        boolean found = true;

        try {

            packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {

            found = false;
        }

        return found;
    }
}
