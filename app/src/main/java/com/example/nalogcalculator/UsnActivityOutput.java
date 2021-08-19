package com.example.nalogcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UsnActivityOutput extends AppCompatActivity implements View.OnClickListener {

    Button btnBack;
    TextView viewIncome;
    TextView viewTax;
    TextView viewInsure;
    float insure = 40874;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usn_output);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBack = (Button) findViewById(R.id.btnBack);
        viewIncome = (TextView) findViewById(R.id.viewIncome);
        viewTax = (TextView) findViewById(R.id.viewTax);
        viewInsure = (TextView) findViewById(R.id.viewInsure);

        btnBack.setOnClickListener(this);


        final Bundle arguments = getIntent().getExtras();
        String txtQuarter1 = arguments.getString("inputQuarter1");
        String txtQuarter2 = arguments.getString("inputQuarter2");
        String txtQuarter3 = arguments.getString("inputQuarter3");
        String txtQuarter4 = arguments.getString("inputQuarter4");

        float Quarter1 = Float.parseFloat(txtQuarter1);
        float Quarter2 = Float.parseFloat(txtQuarter2);
        float Quarter3 = Float.parseFloat(txtQuarter3);
        float Quarter4 = Float.parseFloat(txtQuarter4);

        float sumIncome = Quarter1 + Quarter2 + Quarter3 + Quarter4;
        float sumLarge = (Quarter1 + Quarter2 + Quarter3 + Quarter4)/100*1;
        float sumTax = (Quarter1 + Quarter2 + Quarter3 + Quarter4)/100*6;

        if (sumIncome > 300000) {
            float insureSum =  insure + sumLarge;
            viewInsure.setText(viewInsure.getText().toString() + " " + insureSum);
        }
        else {
            viewInsure.setText(viewInsure.getText().toString() + " " + insure);
        }

        viewIncome.setText(viewIncome.getText().toString() + " " + sumIncome);
        viewTax.setText(viewTax.getText().toString() + " " + sumTax);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
        }

    }
}

