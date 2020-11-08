package com.example.android_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AdjustmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjustment);

        Intent intentIn = getIntent();
        String totalMoney = intentIn.getExtras().getString("total_money");
        final TextView edtAdjustment = findViewById(R.id.edtAdjustment);
        //set total money for Text View Adjustment
        edtAdjustment.setText(totalMoney);
        ImageButton btnSave = findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOut = new Intent(AdjustmentActivity.this, MainActivity.class);
                String adjustMoney = edtAdjustment.getText().toString();
                if (!adjustMoney.isEmpty()){
                    intentOut.putExtra("adjust_money",  adjustMoney);
                    setResult(RESULT_OK, intentOut);
                    finish();
                }else{
                    Toast toast = Toast.makeText(AdjustmentActivity.this,
                            "Adjusted money can not be blank!!!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }

            }
        });
    }
}