package com.example.android_assignment1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    static final int ADJUST_MONEY_ACTIVITY_CODE = 1;
    static final int TRANSACTION_ACTIVITY_CODE = 2;
    static double totalMoney = 0;
    //Create a simple history list
    static ArrayList<History> listHistory = new ArrayList<>();
    //Create Adapter to pass user data
    static HistoryAdapter historyAdapter = new HistoryAdapter(listHistory);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create buttons
        Button btnAdjustMoneyWallet, btnCreateTran;
        btnAdjustMoneyWallet = findViewById(R.id.btnAdjustMoneyWallet);
        btnCreateTran = findViewById(R.id.btnCreateTran);

        //set list history
        RecyclerView rvHistory = findViewById(R.id.rvHistory);


        //Attach adapter to RecyclerView to populate items.
        rvHistory.setAdapter(historyAdapter);
        //set layout for items
        rvHistory.setLayoutManager(new LinearLayoutManager(this));


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
                    //get new history array list from Transaction Activity
                    ArrayList<History> new_history = bundle.getParcelableArrayList("new_history");
                    //calculate current balance
                    totalMoney += Double.parseDouble(new_history.get(0).getNumTran());
                    //set new current balance
                    txtTotalMoney.setText(String.valueOf(totalMoney));

                    //update history list
                    //record size list before making change
                    int curSize = historyAdapter.getItemCount();
                    //update current list history
                    listHistory.addAll(new_history);
                    //adapter notify
                    historyAdapter.notifyItemRangeInserted(curSize, new_history.size());

                }
            }
        }
    }
}
