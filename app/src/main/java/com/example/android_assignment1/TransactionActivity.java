package com.example.android_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        Button btnCreateTran = findViewById(R.id.btnCreateTran);

        btnCreateTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rdbExpense = findViewById(R.id.rdbExpense);
                RadioButton rdbIncome = findViewById(R.id.rdbIncome);
                TextView edtTransaction = findViewById(R.id.edtTransaction);
                Intent intentIn = getIntent();
                String totalMoney = intentIn.getExtras().getString("total_money");
                Intent intentOut = new Intent(TransactionActivity.this,
                        MainActivity.class);
                String numTransaction = edtTransaction.getText().toString();
                if (!numTransaction.isEmpty()){ //check transaction number can not be empty
                    if (rdbExpense.isChecked()){
                        //check transaction number to make sure that it can can not greater
                        // than current balance
                        if(Double.parseDouble(totalMoney) >= Double.parseDouble(numTransaction)){
                            intentOut.putExtra("num_tran", String.format("%s%s", "-",
                                    numTransaction));
                            setResult(RESULT_OK, intentOut);
                            finish();
                        }else{
                            Toast toast = Toast.makeText(TransactionActivity.this,
                                    "Adjusted money can not greater than current balance",
                                    Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0,0);
                            toast.show();
                        }

                    }
                    if (rdbIncome.isChecked()){
                        intentOut.putExtra("num_tran", numTransaction);
                        setResult(RESULT_OK, intentOut);
                        finish();
                    }

                }else{
                    Toast toast = Toast.makeText(TransactionActivity.this,
                            "Transaction Money can not be blanked!!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.show();
                }

            }
        });
    }
}