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
        totalQuestions = 0;
        Question firstQuestion = chooseNewQuestion();
        // displayQuestion(firstQuestion);
        // displayQuestionsRemaining(questions.size());
    }

    // Method of randomly choosing questions from the ArrayList in startNewGame()
    public Question chooseNewQuestion() {
        int newQuestionNumber = generateRandomNumber(2);
        currentQuestionIndex = newQuestionNumber;
        return questions.get(currentQuestionIndex);
    }
    // Convenience method for calling curretly selected question object
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }
    // TODO #6 add onAnswerSubmission() here
    public void onAnswerSubmission() {
        if ( getCurrentQuestion().isCorrect() ) {
            totalCorrect ++;
        }
        questions.remove(currentQuestionIndex);
        // displayQuestionsRemaining(questions.size());
        if (questions.size() == 0) {
            System.out.println(getGameOverMessage(totalCorrect, totalQuestions));
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