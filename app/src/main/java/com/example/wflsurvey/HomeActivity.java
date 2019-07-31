package com.example.wflsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class HomeActivity extends AppCompatActivity {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();
    private TextView mQuestionNoView;
    private TextView mQuestionView;
    private Button mChoice1;
    private Button mChoice2;
    private Button mChoice3;
    private Button mChoice4;


    private int mQuestionNoValue = 1;
    private int mQuestionValue = 0;
    String options="" ;
    String json;
    Reference ref = new Reference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mQuestionNoView = (TextView)findViewById(R.id.number);
        mQuestionView = (TextView)findViewById(R.id.Question);
        mChoice1 = (Button)findViewById(R.id.choice1);
        mChoice2 = (Button)findViewById(R.id.choice2);
        mChoice3 = (Button)findViewById(R.id.choice3);
        mChoice4 = (Button)findViewById(R.id.choice4);

        updateQuestion();

        mChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options+="mChoice1.getText()"+",";
                mQuestionNoValue++;
                if(mQuestionNoValue==5)
                {
                   /* mChoice1.setVisibility(v.INVISIBLE);
                    submit.setVisibility(v.VISIBLE);
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(HomeActivity.this,LastActivity.class);
                            startActivity(i);
                        }
                    }); */
                   ref.value=options;
                    Gson gson = new Gson();
                    //convert java object to JSON format
                    json = gson.toJson(ref);
                    sendData();
                }
                else
                    {
                    updateQuestionNo(mQuestionNoValue);
                    updateQuestion();
                }
            }
        });
        mChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options+="mChoice1.getText()"+",";
                mQuestionNoValue++;
                if(mQuestionNoValue==5)
                {
                    ref.value=options;
                    Gson gson = new Gson();
                    //convert java object to JSON format
                    json = gson.toJson(ref);
                    sendData();
                }
                else {
                    updateQuestionNo(mQuestionNoValue);
                    updateQuestion();
                }
            }
        });
        mChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options+="mChoice1.getText()"+",";
                mQuestionNoValue++;
                if(mQuestionNoValue==5)
                {
                    ref.value=options;
                    Gson gson = new Gson();
                    //convert java object to JSON format
                    json = gson.toJson(ref);
                    sendData();
                }
                else {
                    updateQuestionNo(mQuestionNoValue);
                    updateQuestion();
                }
            }
        });
        mChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options+="mChoice1.getText()"+",";
                mQuestionNoValue++;
                if(mQuestionNoValue==5)
                {
                    ref.value=options;
                    Gson gson = new Gson();
                    //convert java object to JSON format
                    json = gson.toJson(ref);
                    sendData();
                }
                else {
                    updateQuestionNo(mQuestionNoValue);
                    updateQuestion();
                }
            }
        });

    }
    private void updateQuestion()
    {
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionValue));
        mChoice1.setText(mQuestionLibrary.getChoice1(mQuestionValue));
        mChoice2.setText(mQuestionLibrary.getChoice2(mQuestionValue));
        mChoice3.setText(mQuestionLibrary.getChoice3(mQuestionValue));
        mChoice4.setText(mQuestionLibrary.getChoice4(mQuestionValue));
        mQuestionValue++;

    }
    private void updateQuestionNo(int number)
    {
      mQuestionNoView.setText(""+mQuestionNoValue);
    }
    private void sendData()
    {
        try {
            GetText();
        } catch (Exception ex) {
            mQuestionView.setText("url exception");
        }

    }

    public void GetText() throws UnsupportedEncodingException {
        String text = "";
        BufferedReader reader = null;

        // Send data
        try {

            // Defined URL  where to send data
            URL url = new URL("http://124.123.48.80:8080");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json);
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        } catch (Exception ex) {

        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }

        // Show response on activity
        //this text has to be passed to a new activity where we will display the options they selected activity
        mQuestionView.setText(text);
    }



    }

