package com.roish.helloandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


public class LogicActivity extends Activity {

    public final static int TIME_BE_PM=5000; //time beginner plus/minus
    public final static int TIME_BE_MD=10000;
    public final static int TIME_AD_PM=10000;
    public final static int TIME_AD_MD=150000;
    public final static int TIME_PR_PM=200000;
    public final static int TIME_PR_MD=400000;


    TextView text;
    TextView timerText;
    TextView successesText;
    EditText edit;
    String userNum,value;
    int result,numOfResult,numOfEdit;
    boolean beginner=false,advanced=false,professional=false;
    CountDownTimer countDownTimer;
    InputMethodManager inputMethodManager;
    int numOfSuccesses=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic);


        text=(TextView)findViewById(R.id.textView);
        edit=(EditText)findViewById(R.id.editText);
        timerText=(TextView) findViewById(R.id.timmerText);
        successesText=(TextView) findViewById(R.id.successesText);


      /*  this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(edit.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }*/

        successesText.setText("Successes: "+Integer.toString(numOfSuccesses));
        value = getIntent().getExtras().getString("key");
        //value= BeginnerPlus/AdvancedPlus/ProfessionalPlus..
        if(value.contains("Professional"))
            professional=true;
        else if(value.contains("Advanced"))
            advanced=true;
        else if(value.contains("Beginner"))
            beginner=true;

        if (value.contains("Plus"))
            plus();
        else if(value.contains("Minus"))
            minus();
        else if(value.contains("Multi"))
            miulti();
        else if(value.contains("Division"))
            division();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void plus()
    {
        int firstNum,secondNum;
        firstNum=randomNum();
        secondNum=randomNum();
        result=firstNum+secondNum;
        text.setText(firstNum+" + "+secondNum );

        numOfEdit=0;
        numOfResult=Integer.toString(result).length();
        if(value.contains("Professional"))
            runTimer(TIME_PR_PM);
        else if(value.contains("Advanced"))
            runTimer(TIME_AD_PM);
        else if(value.contains("Beginner"))
            runTimer(TIME_BE_PM);


        checkEditText();

    }

    public void minus()
    {
        int firstNum,secondNum;
        firstNum=randomNum();
        secondNum=randomNum();
        while(secondNum>firstNum)
        {
            firstNum=randomNum();
            secondNum=randomNum();
        }
        result=firstNum-secondNum;
        text.setText(firstNum+" - "+secondNum );

        numOfEdit=0;
        numOfResult=Integer.toString(result).length();

        if(value.contains("Professional"))
            runTimer(TIME_PR_PM);
        else if(value.contains("Advanced"))
            runTimer(TIME_AD_PM);
        else if(value.contains("Beginner"))
            runTimer(TIME_BE_PM);

        checkEditText();
    }

    public void miulti()
    {
        int firstNum,secondNum;
        firstNum=randomNum();
        secondNum=randomNum();
        result=firstNum*secondNum;
        text.setText(firstNum+" * "+secondNum );

        numOfEdit=0;
        numOfResult=Integer.toString(result).length();

        if(value.contains("Professional"))
            runTimer(TIME_PR_MD);
        else if(value.contains("Advanced"))
            runTimer(TIME_AD_MD);
        else if(value.contains("Beginner"))
            runTimer(TIME_BE_MD);

        checkEditText();

    }

    public void division()
    {
        int firstNum,secondNum,multiNuum;
        firstNum=randomNum();
        secondNum=randomNum();
        multiNuum=firstNum*secondNum;
        result=multiNuum/firstNum;
        text.setText(multiNuum+" / "+firstNum);
        numOfEdit=0;
        numOfResult=Integer.toString(result).length();

        if(value.contains("Professional"))
            runTimer(TIME_PR_MD);
        else if(value.contains("Advanced"))
            runTimer(TIME_AD_MD);
        else if(value.contains("Beginner"))
            runTimer(TIME_BE_MD);

        checkEditText();

    }

    public int randomNum()
    {
        int randNum=1;
        while(randNum<=1)
            if(beginner)
                randNum=(int)(Math.random()*10);
            else if(advanced)
                randNum=(int)(Math.random()*100);
            else if(professional)
                randNum=(int)(Math.random()*1000);
        return randNum;
    }

    public void checkEditText()
    {

        edit.addTextChangedListener(new TextWatcher()
        {

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}


            public void afterTextChanged(Editable s)
            {

                numOfEdit=edit.getText().length();
                userNum=edit.getText().toString();
                if(numOfEdit==numOfResult)
                {


                    if(Integer.toString(result).equals(userNum))
                    {
                        numOfSuccesses++;
                        successesText.setText("Successes: "+Integer.toString(numOfSuccesses));
                        edit.setText("");

                        if(value.contains("Plus")) {
                            countDownTimer.cancel();
                            plus();
                        }
                        else if(value.contains("Minus")) {
                            countDownTimer.cancel();
                            minus();
                        }
                        else if(value.contains("Multi")) {
                            countDownTimer.cancel();
                            miulti();
                        }
                        else if(value.contains("Division")) {
                            countDownTimer.cancel();
                            division();
                        }
                    }
                }


            }
        });
    }

    public void runTimer(int time) {
        countDownTimer= new CountDownTimer(time+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerText.setTextColor(Color.BLACK);
                if (millisUntilFinished / 1000 >= 10)
                    timerText.setText("00:" + millisUntilFinished / 1000);
                else
                    timerText.setText("00:0" + millisUntilFinished / 1000);

            }

            public void onFinish() {
                timerText.setTextColor(Color.RED);
                timerText.setText("TIME IS UP ");
                numOfSuccesses=0;
                successesText.setText("Successes: "+Integer.toString(numOfSuccesses));
            }

        }.start();

    }


    public void one_btn(View v) {
        edit.setText(edit.getText()+"1");
        edit.setSelection(edit.getText().length());
    }
    public void two_btn(View v) {
        edit.setText(edit.getText()+"2");
        edit.setSelection(edit.getText().length());
    }
    public void three_btn(View v) {
        edit.setText(edit.getText()+"3");
        edit.setSelection(edit.getText().length());
    }
    public void four_btn(View v) {
        edit.setText(edit.getText()+"4");
        edit.setSelection(edit.getText().length());
    }
    public void five_btn(View v) {
        edit.setText(edit.getText()+"5");
        edit.setSelection(edit.getText().length());
    }
    public void six_btn(View v) {
        edit.setText(edit.getText()+"6");
        edit.setSelection(edit.getText().length());
    }
    public void seven_btn(View v) {
        edit.setText(edit.getText()+"7");
        edit.setSelection(edit.getText().length());
    }
    public void eight_btn(View v) {
        edit.setText(edit.getText()+"8");
        edit.setSelection(edit.getText().length());
    }
    public void nine_btn(View v) {
        edit.setText(edit.getText()+"9");
        edit.setSelection(edit.getText().length());
    }
    public void zero_btn(View v) {
        edit.setText(edit.getText()+"0");
        edit.setSelection(edit.getText().length());
    }

    public void delete_btn(View v) {
        String text = edit.getText().toString();
        if(edit.length()!=0
                ) {
            edit.setText(text.substring(0, text.length() - 1));
            edit.setSelection(edit.getText().length());
        }
    }






}
