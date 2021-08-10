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
        Question question7 = new Question(R.drawable.img_quote_6, "Here’s the truth, Will Smith did say this, but in which movie?", "Independence Day", "Bad Boys", "Men In Black", "The Pursuit of Happyness", 2);
        Question question8 = new Question(R.drawable.img_quote_7, "Which TV funny gal actually quipped this 1-liner?", "Ellen Degeneres", "Amy Poehler", "Betty White", "Tina Fay", 3);
        Question question9 = new Question(R.drawable.img_quote_8, "This mayor won’t get my vote — but did he actually give this piece of advice? And if not, who did?", "Forrest Gump, Forrest Gump", "Dorry, Finding Nemo", "Esther Williams", "The Mayor, Jaws", 1);
        Question question10 = new Question(R.drawable.img_quote_9, "Her heart will go on, but whose heart is it?", "Whitney Houston", "Diana Ross", "Celine Dion", "Mariah Carey", 0);
        Question question11 = new Question(R.drawable.img_quote_10, "He’s the king of something alright — to whom does this self-titling line belong to?", "Tony Montana, Scarface", "Joker, The Dark Knight", "Lex Luthor, Batman v Superman", "Jack, Titanic", 3);
        Question question12 = new Question(R.drawable.img_quote_11, "Is “Grey” synonymous for“wise”? If so, maybe Gandalf did preach this advice. If not, who did?", "Yoda, Star Wars", "Gandalf The Grey, Lord of the Rings", "Dumbledore, Harry Potter", "Uncle Ben, Spider-Man", 0);
        Question question13 = new Question(R.drawable.img_quote_12, "Houston, we have a problem with this quote — which space-traveler does this catch-phrase actually belong to?", "Han Solo, Star Wars", "Captain Kirk, Star Trek", "Buzz Lightyear, Toy Story", "Jim Lovell, Apollo 13", 2);



        questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
        questions.add(question11);
        questions.add(question12);
        questions.add(question13);

        while (questions.size() > 6) {
            int q = generateRandomNumber(questions.size());
            questions.remove(q);
        }

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
    // Call the question displayed for player
    public Question getCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        return currentQuestion;
    }

    // Submit answer and change to next question

    public void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.playerAnswer == -1) {
            AlertDialog.Builder selectAnAnswerBuilder = new AlertDialog.Builder(MainActivity.this);
            selectAnAnswerBuilder.setCancelable(true);
            selectAnAnswerBuilder.setTitle("Please choose an answer.");
            selectAnAnswerBuilder.setMessage("Click outside the box to select an answer.");
            selectAnAnswerBuilder.create().show();
            return ;
        } else if ( currentQuestion.isCorrect() ) {
            totalCorrect ++;
        }
        questions.remove(currentQuestion);
        displayQuestionsRemaining(questions.size());

        if (questions.size() == 0) {
            String gameOverMan = getGameOverMessage(totalCorrect, totalQuestions);

            // Game over message using an Alert
                AlertDialog.Builder gameOverDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
                gameOverDialogueBuilder.setCancelable(false);
                gameOverDialogueBuilder.setTitle("Game Over!");
                gameOverDialogueBuilder.setMessage(gameOverMan);
                gameOverDialogueBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startNewGame();
                    }
                });
                gameOverDialogueBuilder.create().show();
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