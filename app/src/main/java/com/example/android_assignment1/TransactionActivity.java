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

import java.util.ArrayList;

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

                //get result from validation transaction field
                ArrayList<String> validationResult = validationFieldTransaction(rdbExpense,
                        rdbIncome, totalMoney, numTransaction);
                if(validationResult.get(0).equals("true")){ //check transaction number can not be empty
                    if (rdbExpense.isChecked()){
                            intentOut.putExtra("num_tran", String.format("%s%s", "-",
                                    numTransaction));
                            setResult(RESULT_OK, intentOut);
                            finish();
                    }
                    if (rdbIncome.isChecked()){
                        intentOut.putExtra("num_tran", numTransaction);
                        setResult(RESULT_OK, intentOut);
                        finish();
                    }

                }else{
                    Toast toast = Toast.makeText(TransactionActivity.this,
                            validationResult.get(1), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.show();
                }

            }
        });
    }

    private ArrayList<String> validationFieldTransaction(RadioButton rdbExpense,
                                                        RadioButton rdbIncome,
                                                         String totalMoney,
                                                         String numTransaction){
        ArrayList<String> result = new ArrayList<>();
        //check transaction money
        if(numTransaction.isEmpty()){
            result.add("false");
            result.add("Transaction Money can not be blanked!!!");
            return result;
        }

        //check radio button
        if(!rdbExpense.isChecked() && !rdbIncome.isChecked()){
            result.add("false");
            result.add("Please choose type of your transaction!!!");
            return result;
        }

        //check transaction number to make sure that it can can not greater
        // than current balance
        if(rdbExpense.isChecked() &&
                Double.parseDouble(totalMoney) < Double.parseDouble(numTransaction)){
            result.add("false");
            result.add("Adjusted money can not be greater than current balance!!!");
        }

        //set result true if all fields are filled
        result.add("true");
        return result;
    }
}