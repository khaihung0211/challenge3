package com.example.challenge3;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class AdditionGameActivity extends AppCompatActivity {

    private TextView tvScore;
    private TextView tvLife;
    private TextView tvTimer;
    private TextView tvQuestion;
    private EditText etAnswer;
    private Button btnOK;
    private Button btnNextQuestion;

    private int score = 0;
    private int life = 3;
    private int timer = 60;
    private int currentAnswer;
    private Random random;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_game);

        random = new Random();
        initViews();
        setupClickListeners();
        startTimer();
        generateNewQuestion();
    }

    private void initViews() {
        tvScore = findViewById(R.id.tvScore);
        tvLife = findViewById(R.id.tvLife);
        tvTimer = findViewById(R.id.tvTimer);
        tvQuestion = findViewById(R.id.tvQuestion);
        etAnswer = findViewById(R.id.etAnswer);
        btnOK = findViewById(R.id.btnOK);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);
    }

    private void setupClickListeners() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        // Button Next Question chỉ để trưng, không làm gì
        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Không làm gì, chỉ để trưng như yêu cầu
                Toast.makeText(AdditionGameActivity.this, "This button is for display only", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer = (int) (millisUntilFinished / 1000);
                updateTimer();
            }

            @Override
            public void onFinish() {
                timer = 0;
                updateTimer();
                endGame();
            }
        };
        countDownTimer.start();
    }

    private void generateNewQuestion() {
        int num1 = random.nextInt(10) + 1; // 1-10
        int num2 = random.nextInt(10) + 1; // 1-10
        currentAnswer = num1 + num2;

        tvQuestion.setText(num1 + " + " + num2 + " = ?");
        etAnswer.setText("");
    }

    private void checkAnswer() {
        String answerText = etAnswer.getText().toString().trim();

        if (answerText.isEmpty()) {
            Toast.makeText(this, "Please enter an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int userAnswer = Integer.parseInt(answerText);

            if (userAnswer == currentAnswer) {
                // Correct answer
                score += 10;
                updateScore();
                Toast.makeText(this, "Correct! +10 points", Toast.LENGTH_SHORT).show();
                generateNewQuestion();
            } else {
                // Wrong answer
                life--;
                updateLife();
                Toast.makeText(this, "Wrong! The answer was " + currentAnswer, Toast.LENGTH_SHORT).show();

                if (life <= 0) {
                    endGame();
                } else {
                    generateNewQuestion();
                }
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateScore() {
        tvScore.setText("Score: " + score);
    }

    private void updateLife() {
        tvLife.setText("Life: " + life);
    }

    private void updateTimer() {
        tvTimer.setText("Timer: " + timer);
    }

    private void endGame() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("final_score", score);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        finish();
    }
}