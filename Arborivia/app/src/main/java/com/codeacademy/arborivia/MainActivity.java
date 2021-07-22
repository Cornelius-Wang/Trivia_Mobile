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

    // TODO 3-A: Declare View member variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO 2-G: Show app icon in ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

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