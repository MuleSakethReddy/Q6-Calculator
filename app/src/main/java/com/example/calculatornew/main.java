package com.example.calculatornew;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.udojava.evalex.Expression;

import java.util.HashMap;

public class main extends AppCompatActivity {
    String query = "", res="";
    TextView queryTv, resTv;
    HashMap<String, String> sm = new HashMap<String, String>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        queryTv = findViewById(R.id.query);
        resTv = findViewById(R.id.result);
        sm.put("X", "*");
        sm.put("÷","/");
        sm.put("√X","sqrt(");
        sm.put("1/x","^(-1)");
        sm.put("π","pi");
        sm.put("ln","log(");
        sm.put("lg","2.3026*log(");
        sm.put("sin","sin(");
        sm.put("cos","cos(");
        sm.put("tan","tan(");
        sm.put("X!","!");
    }

    public void calculate(View v)
    {
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        if(buttonText.equals("AC")){
            query = "";
            res = "";
            queryTv.setText("0");
            resTv.setText("0");
            return;
        }
        else if(sm.containsKey(buttonText))
        {
            query = query + sm.get(buttonText);
        }
        else
        {
            query = query + b.getText().toString();
        }

        queryTv.setText(query);
    }

    public void backButton(View v)
    {
        if(query.length() > 0) {
            query = query.substring(0, query.length() - 1);
        }
        queryTv.setText(query);
    }

    public void result(View v)
    {
        Button b = (Button)v;
        res = evaluate(query);
        resTv.setText(res);
    }

    String evaluate(String query)
    {
        try{
            Expression expression=new Expression(query); //This Library Evaluate It
            String res = expression.eval().toString();
            return "="+res;
        } catch(Exception e) {
            return "Error";
        }
    }
}
