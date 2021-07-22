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

public class MainActivity {

    // Initializing global variables
    int currentQuestionIndex = 0;
    int totalCorrect = 0;
    int totalQuestions = 0;
    // Add ArrayList member variable
    ArrayList<Question> questions;

    // TODO 3-A: Declare View member variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO 2-G: Show app icon in ActionBar

        setContentView(R.layout.activity_main);

        // TODO 3-B: assign View member variables

        // TODO 4-E: set onClickListener for each answer Button

        // TODO 5-A: set onClickListener for the submit answer Button

        startNewGame();
    }

    // TODO 3-F: displayQuestion(Question question) {...}

    // TODO 3-C: displayQuestionsRemaining(int questionRemaining) {...}

    // TODO 4-A: onAnswerSelected(int answerSelected) {...}


    // Method to be called when starting a new game
    public void startNewGame() {
        Question question1 = new Question(1, "Which American Task Force(s) took part in the Battle of Midway?", "Task Force 6", "Task Forces 1 & 3", "Task Force 33", "Task Forces 16 & 17", 3);
        Question question2 = new Question(2, "What was the name of the Japanese Fleet in the Battle of Midway?", "Kantai Kessen", "Kido Butai", "Kawasaki Kombu", "Kageyama's Crescent'", 1);
        Question question3 = new Question(3, "Who won the Battle of Midway", "The Americans", "The Japanese", "Draw", "The Chinese", 0);
        questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        totalCorrect = 0;
        totalQuestions = questions.size();
        Question firstQuestion = chooseNewQuestion();
        // TODO 3-D.ii: Uncomment the line below after implementing displayQuestionsRemaining(int)
        // displayQuestionsRemaining(questions.size());

        // TODO 3-H.ii: Uncomment after implementing displayQuestion(Question)
        // displayQuestion(firstQuestion);
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
        // TODO 3-D.i: Uncomment the line below after implementing displayQuestionsRemaining(int)
        // displayQuestionsRemaining(questions.size());

        if (questions.size() == 0) {
            String gameOverMan = getGameOverMessage(totalCorrect, totalQuestions);

            // TODO 5-D: Show a popup instead
            System.out.println(gameOverMan);
            startNewGame();
        } else {
            chooseNewQuestion();
        }
        // TODO: uncomment after implementing displayQuestion()
        // displayQuestion(getCurrentQuestion());
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