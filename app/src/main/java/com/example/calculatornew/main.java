package com.example.calculatornew;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.udojava.evalex.Expression;

import java.util.HashMap;

public class main extends AppCompatActivity {
    String q = "", res="";
    TextView qTv, resTv;
    HashMap<String, String> sm = new HashMap<String, String>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        qTv = findViewById(R.id.q);
        resTv = findViewById(R.id.result);
        sm.put("sin","sin(");
        sm.put("cos","cos(");
        sm.put("tan","tan(");
        sm.put("X", "*");
        sm.put("÷","/");
        sm.put("π","pi");
        sm.put("ln","log(");
        sm.put("√X","sqrt(");
        sm.put("1/x","^(-1)");
        sm.put("lg","2.3026*log(");
        sm.put("X!","!");
    }

    public void calculate(View v)
    {
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        if(buttonText.equals("AC")){
            q = "";
            res = "";
            qTv.setText("0");
            resTv.setText("0");
            return;
        }
        else if(sm.containsKey(buttonText))
        {
            q = q + sm.get(buttonText);
        }
        else
        {
            q = q + b.getText().toString();
        }

        qTv.setText(q);
    }

    public void bp(View v)
    {
        if(q.length() > 0) {
            q = q.substring(0, q.length() - 1);
        }
        qTv.setText(q);
    }

    public void r(View v)
    {
        Button b = (Button)v;
        res = ev(q);
        resTv.setText(res);
    }

    String ev(String q)
    {
        try{
            Expression e=new Expression(q); 
            String res = e.eval().toString();
            return "="+res;
        } catch(Exception e) {
            return "Error";
        }
    }
}
