package com.example.challenge3;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAddition;
    private Button btnSubtraction;
    private Button btnMultiplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        btnAddition = findViewById(R.id.btnAddition);
        btnSubtraction = findViewById(R.id.btnSubtraction);
        btnMultiplication = findViewById(R.id.btnMultiplication);
    }

    private void setupClickListeners() {
        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAdditionGame();
            }
        });

        btnSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Có thể mở rộng sau
            }
        });

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Có thể mở rộng sau
            }
        });
    }

    private void startAdditionGame() {
        Intent intent = new Intent(this, AdditionGameActivity.class);
        startActivity(intent);
    }
}