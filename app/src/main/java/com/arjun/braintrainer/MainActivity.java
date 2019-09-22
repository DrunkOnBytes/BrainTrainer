package com.arjun.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView question,time,score,message,finalScore,correctOrWrong;
    Button a,b,c,d,startButton,playAgain;
    Random rand;
    int q1,q2,ans,ansPos,correctAnswers,totalAnswers;
    int op[]=new int[4];

    public int randomExceptAns(){

        int temp=rand.nextInt(q1+q2+50);
        while(temp==ans)
            temp=rand.nextInt(q1+q2+50);
        return temp;

    }

    public void nextQuestion(){

        q1=rand.nextInt(100);
        q2=rand.nextInt(100);
        ans=q1+q2;
        ansPos=rand.nextInt(4);
        for(int i=0;i<4;i++){

            if(i==ansPos)
                op[i]=ans;
            else
                op[i]=randomExceptAns();

        }


        question.setText(Integer.toString(q1)+" + "+Integer.toString(q2));
        a.setText(Integer.toString(op[0]));
        b.setText(Integer.toString(op[1]));
        c.setText(Integer.toString(op[2]));
        d.setText(Integer.toString(op[3]));

    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        message.setVisibility(View.INVISIBLE);
        question.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);
        a.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
        finalScore.setVisibility(View.INVISIBLE);
        correctOrWrong.setText("");
        score.setText("0/0");

        correctAnswers=0;
        totalAnswers=0;

        new CountDownTimer(30000,1000){

            public void onTick(long millisecondsUntilDone){

                time.setText(Long.toString(millisecondsUntilDone/1000)+"s");
            }
            public void onFinish(){

                message.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                finalScore.setVisibility(View.VISIBLE);
                finalScore.setText("Score: "+Integer.toString(correctAnswers)+"/"+Integer.toString(totalAnswers));

                question.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);
                a.setVisibility(View.INVISIBLE);
                b.setVisibility(View.INVISIBLE);
                c.setVisibility(View.INVISIBLE);
                d.setVisibility(View.INVISIBLE);
                correctOrWrong.setVisibility(View.INVISIBLE);

            }
        }.start();

    }

    public void answer(View view){

        if(view.getTag().toString().equals(Integer.toString(ansPos))) {
            correctAnswers += 1;
            correctOrWrong.setText("Correct");
        }
        else
            correctOrWrong.setText("Wrong");
        totalAnswers+=1;
        score.setText(Integer.toString(correctAnswers)+"/"+Integer.toString(totalAnswers));

        nextQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question=(TextView)findViewById(R.id.question);
        score=(TextView)findViewById(R.id.scoreView);
        time=(TextView)findViewById(R.id.timeView);
        message=(TextView)findViewById(R.id.timeup);
        finalScore=(TextView)findViewById(R.id.finalScore);
        startButton=(Button)findViewById(R.id.start);
        correctOrWrong=(TextView)findViewById(R.id.message);
        playAgain=(Button)findViewById(R.id.playAgain);

        a=(Button)findViewById(R.id.option1);
        b=(Button)findViewById(R.id.option2);
        c=(Button)findViewById(R.id.option3);
        d=(Button)findViewById(R.id.option4);

        correctAnswers=0;
        totalAnswers=0;

        rand=new Random();

        nextQuestion();



    }
}
