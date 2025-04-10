package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    // Sample quiz data
    String[] questions = {"What is the capital of France?", "Which planet is known as the Red Planet?"};
    String[][] options = {
            {"Berlin", "Paris", "Madrid", "London"},
            {"Earth", "Venus", "Mars", "Jupiter"}
    };
    int[] correctAnswers = {1, 2}; // Paris, Mars
    int currentQuestion = 0;
    int score = 0;

    TextView tvQuestion;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;
    Button btnSubmit;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_apps);

        tvQuestion = findViewById(R.id.tvQuestion);
        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        btnSubmit = findViewById(R.id.btnSubmit);
        progressBar = findViewById(R.id.progressBar);

        loadQuestion();

        btnSubmit.setOnClickListener(view -> checkAnswer());
    }

    private void loadQuestion() {
        radioGroup.clearCheck();
        tvQuestion.setText(questions[currentQuestion]);
        rb1.setText(options[currentQuestion][0]);
        rb2.setText(options[currentQuestion][1]);
        rb3.setText(options[currentQuestion][2]);
        rb4.setText(options[currentQuestion][3]);
        progressBar.setProgress((int)(((float)(currentQuestion) / questions.length) * 100));
    }

    private void checkAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == -1) return; // No selection

        RadioButton selectedRadioButton = findViewById(selectedId);
        int selectedIndex = radioGroup.indexOfChild(selectedRadioButton);

        if (selectedIndex == correctAnswers[currentQuestion]) {
            selectedRadioButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            score++;
        } else {
            selectedRadioButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            RadioButton correct = (RadioButton) radioGroup.getChildAt(correctAnswers[currentQuestion]);
            correct.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        }

        btnSubmit.setEnabled(false);

        // Delay then go to next question
        btnSubmit.postDelayed(() -> {
            selectedRadioButton.setBackgroundColor(0);
            RadioButton correct = (RadioButton) radioGroup.getChildAt(correctAnswers[currentQuestion]);
            correct.setBackgroundColor(0);
            btnSubmit.setEnabled(true);
            currentQuestion++;

            if (currentQuestion < questions.length) {
                loadQuestion();
            } else {
                // Finish quiz
                // Here you'd use Intent to go to ResultActivity
            }

        }, 1500);
    }
}
