package com.roish.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ActionActivity extends Activity {

    Button b1;
    Intent mIntent;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        mIntent = new Intent(this, LogicActivity.class);
        value = getIntent().getExtras().getString("key");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



    public void clickPlus(View v)
    {
        //value= Beginner/Advanced/Professional
        mIntent.putExtra("key", value+"Plus");
        startActivity(mIntent);
    }
    public void clickMinus(View v)
    {
        mIntent.putExtra("key", value+"Minus");
        startActivity(mIntent);
    }
    public void clickMulti(View v)
    {
        mIntent.putExtra("key", value+"Multi");
        startActivity(mIntent);
    }
    public void clickDivision(View v)
    {
        mIntent.putExtra("key", value+"Division");
        startActivity(mIntent);
    }

}
