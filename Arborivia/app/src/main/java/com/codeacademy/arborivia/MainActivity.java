package com.codeacademy.arborivia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Initializing global variables
    int currentQuestionIndex = 0;
    int totalCorrect = 0;
    int totalQuestions = 0;
    // Add ArrayList member variable
    ArrayList<Question> questions;

    // Declaring View member variables

    ImageView questionImageView;
    TextView questionTextView;
    TextView questionsRemainingTextView;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // App icon in ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_main);

        // Assigning view member variables
        questionImageView = findViewById(R.id.iv_main_question_image);
        questionTextView = findViewById(R.id.tv_main_question_title);
        questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining_count);
        answer0Button = findViewById(R.id.btn_main_answer_0);
        answer1Button = findViewById(R.id.btn_main_answer_1);
        answer2Button = findViewById(R.id.btn_main_answer_2);
        answer3Button = findViewById(R.id.btn_main_answer_3);
        submitButton = findViewById(R.id.btn_main_submit_answer);


        // Setting onClickListener for each answer Button

        answer0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(0);
            }
        });

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(1);
            }
        });
        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(2);
            }
        });
        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(3);
            }
        });

        // TODO 5-A: set onClickListener for the submit answer Button

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSubmission();
            }
        });

        startNewGame();
    }

    public void displayQuestion(Question question) {
        questionImageView.setImageResource(question.imageId);
        questionTextView.setText(question.questionText);
        answer0Button.setText(question.answer0);
        answer1Button.setText(question.answer1);
        answer2Button.setText(question.answer2);
        answer3Button.setText(question.answer3);
    }

    public void displayQuestionsRemaining(int remainingQuestions){
        questionsRemainingTextView.setText(remainingQuestions + "");
    }

    public void onAnswerSelected(int answerSelected) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answerSelected;
        answer0Button.setText(currentQuestion.answer0);
        answer1Button.setText(currentQuestion.answer1);
        answer2Button.setText(currentQuestion.answer2);
        answer3Button.setText(currentQuestion.answer3);
        switch (answerSelected) {
            case 0:
                answer0Button.setText("✔" + currentQuestion.answer0);
                break;
            case 1:
                answer1Button.setText("✔" + currentQuestion.answer1);
                break;
            case 2:
                answer2Button.setText("✔" + currentQuestion.answer2);
                break;
            case 3:
                answer3Button.setText("✔" + currentQuestion.answer3);
                break;
        }
    }

    // Method to be called when starting a new game
    public void startNewGame() {
        Question question1 = new Question( R.drawable.img_quote_0, "Pretty good advice, and perhaps a scientist did say it… Who actually did?", "Albert Einstein", "Isaac Newton", "Rita Mae Brown", "Rosalind Franklin", 2);
        Question question2 = new Question(R.drawable.img_quote_1, "Was honest Abe honestly quoted? Who authored this pithy bit of wisdom?", "Edward Stieglitz", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 0);
        Question question3 = new Question(R.drawable.img_quote_2, "Easy advice to read, difficult advice to follow — who actually said it?", "Martin Luther King Jr", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 1);
        Question question4 = new Question(R.drawable.img_quote_3, "Insanely inspiring, insanely incorrect(maybe). Who is the true source of this inspiration?", "Nelson Mandela", "Harriet Tubman", "Mahatma Gandhi", "Nicholas Klein", 3);
        Question question5 = new Question(R.drawable.img_quote_4, "A peace worth striving for — who actually reminded us of this?", "Malala Yousafzai", "Martin Luther King Jr.", "Liu Xiaobo", "Dalai Lama", 3);
        Question question6 = new Question(R.drawable.img_quote_5, "Unfortunately, true — but did Marilyn Monroe convey it or did someone else?", "Laurel Thatcher Ulrich", "Eleanor Roosevelt", "Mahatma Gandhi", "Queen Victoria", 0);
        questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        totalCorrect = 0;
        totalQuestions = questions.size();
        Question firstQuestion = chooseNewQuestion();
        displayQuestionsRemaining(questions.size());
        displayQuestion(firstQuestion);
    }

    // Method of randomly choosing questions from the ArrayList in startNewGame()
    public Question chooseNewQuestion() {
        int newQuestionNumber = generateRandomNumber(questions.size());
        currentQuestionIndex = newQuestionNumber;
        return questions.get(currentQuestionIndex);
    }
    // Convenience method for calling curretly selected question object
    public Question getCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        return currentQuestion;
    }

    public void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
        if ( currentQuestion.isCorrect() ) {
            totalCorrect ++;
        }
        questions.remove(currentQuestion);
        displayQuestionsRemaining(questions.size());

        if (questions.size() == 0) {
            String gameOverMan = getGameOverMessage(totalCorrect, totalQuestions);

            // TODO 5-D: Show a popup instead
            System.out.println(gameOverMan);
            startNewGame();
        } else {
            chooseNewQuestion();
        }
        displayQuestion(getCurrentQuestion());
    }

    public int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    public String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }
    public static void main(String[] args) {}
}