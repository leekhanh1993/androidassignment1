package com.example.android_assignment1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int ADJUST_MONEY_ACTIVITY_CODE = 1;
    static final int TRANSACTION_ACTIVITY_CODE = 2;
    static double totalMoney = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create buttons
        Button btnAdjustMoneyWallet, btnCreateTran;
        btnAdjustMoneyWallet = findViewById(R.id.btnAdjustMoneyWallet);
        btnCreateTran = findViewById(R.id.btnCreateTran);

        //create listener for buttons

        btnAdjustMoneyWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        AdjustmentActivity.class);
                intent.putExtra("total_money", String.valueOf(totalMoney));
                startActivityForResult(intent, ADJUST_MONEY_ACTIVITY_CODE);
            }
        });

        btnCreateTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        TransactionActivity.class);
                intent.putExtra("total_money", String.valueOf(totalMoney));
                startActivityForResult(intent, TRANSACTION_ACTIVITY_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView txtTotalMoney = findViewById(R.id.txtTotalMoney);
        if (requestCode == ADJUST_MONEY_ACTIVITY_CODE){
            if (resultCode==RESULT_OK){
                Bundle bundle = data.getExtras();
                if (bundle != null){
                    totalMoney = Double.parseDouble((String)bundle.get("adjust_money"));
                    txtTotalMoney.setText(String.valueOf(totalMoney));
                }
            }
        }else{
            if (resultCode==RESULT_OK){
                Bundle bundle = data.getExtras();
                if (bundle != null){
                    totalMoney += Double.parseDouble((String)bundle.get("num_tran"));
                    txtTotalMoney.setText(String.valueOf(totalMoney));
                }
            }
        }
    }
}
