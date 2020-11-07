package com.example.android_assignment1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int ADD_MONEY_ACTIVITY_CODE = 100;
    static final int TRANSACTION_ACTIVITY_CODE = 101;
    static double totalMoney = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create buttons
        Button btnAddMoneyWallet, btnCreateTran;
        btnAddMoneyWallet = findViewById(R.id.btnAddMoneyWallet);
        btnCreateTran = findViewById(R.id.btnCreateTran);

        //create listener for buttons

        btnAddMoneyWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMoneyActivity.class);
                intent.putExtra("add_money", "Main to AddMoney");
                startActivityForResult(intent, ADD_MONEY_ACTIVITY_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MONEY_ACTIVITY_CODE){
            if (resultCode==RESULT_OK){
                TextView txtTotalMoney = findViewById(R.id.txtTotalMoney);
                Bundle bundle = data.getExtras();
                if (bundle != null){
                    totalMoney += Double.parseDouble((String)bundle.get("num_money"));
                    txtTotalMoney.setText(String.valueOf(totalMoney));
                }
            }
        }
    }
}
