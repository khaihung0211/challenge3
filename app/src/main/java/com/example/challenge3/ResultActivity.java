package com.example.challenge3;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvFinalScore;
    private TextView tvCongratulations;
    private Button btnPlayAgain;
    private Button btnExit;

    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get final score from intent
        finalScore = getIntent().getIntExtra("final_score", 0);

        initViews();
        setupClickListeners();
        displayResults();
    }

    private void initViews() {
        tvFinalScore = findViewById(R.id.tvFinalScore);
        tvCongratulations = findViewById(R.id.tvCongratulations);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnExit = findViewById(R.id.btnExit);
    }

    private void setupClickListeners() {
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitGame();
            }
        });
    }

    private void displayResults() {
        tvFinalScore.setText("Final Score: " + finalScore);

        // Set congratulatory message based on score
        String congratsMessage;
        if (finalScore >= 100) {
            congratsMessage = "Excellent! You're a math genius!";
        } else if (finalScore >= 50) {
            congratsMessage = "Great job! Well done!";
        } else if (finalScore >= 20) {
            congratsMessage = "Good effort! Keep practicing!";
        } else {
            congratsMessage = "Nice try! Practice makes perfect!";
        }

        tvCongratulations.setText(congratsMessage);
    }

    private void playAgain() {
        // Restart the addition game
        Intent intent = new Intent(this, AdditionGameActivity.class);
        startActivity(intent);
        finish();
    }

    private void exitGame() {
        // Go back to main menu
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitGame();
    }
}