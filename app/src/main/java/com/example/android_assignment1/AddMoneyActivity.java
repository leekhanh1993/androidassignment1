package com.example.android_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddMoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        Button btnAddMoney = findViewById(R.id.btnAddMoney);

        Intent intent = getIntent();
        String message = (String) intent.getExtras().get("add_money");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        btnAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView edtAddMoney = findViewById(R.id.edtAddMoney);
                Intent intent = new Intent(AddMoneyActivity.this, MainActivity.class);
                String numMoney = "" + edtAddMoney.getText();
                intent.putExtra("num_money",  numMoney);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}