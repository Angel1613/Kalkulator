package com.example.angel.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView ekran;
    private String wyswietlenie = "";
    private String currentOperator = "";
    private String wynik = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ekran= (TextView)findViewById(R.id.textView);
        ekran.setText(wyswietlenie);
    }


    private void updateEkran(){
        ekran.setText(wyswietlenie);
    }

    public void onClickNumer(View v) {
        if(wynik != ""){
            clear();
            updateEkran();
        }
        Button b = (Button) v;
        wyswietlenie += b.getText();
        updateEkran();

    }

    private boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case '*':
            case '/': return true;
            default: return false;
        }
    }

    public void onClickOperator(View v) {
        Button b = (Button)v;
        if(wynik!=""){
            wyswietlenie = wynik;
            wynik = "";
        }

        if(currentOperator !=""){
            if(isOperator(wyswietlenie.charAt(wyswietlenie.length()-1))){

                wyswietlenie.replace(wyswietlenie.charAt(wyswietlenie.length()-1), b.getText().charAt(0));
            }
            else {
                getWynik();
                wyswietlenie = wynik;
                wynik = "";

            }
            currentOperator=b.getText().toString();
        }


        wyswietlenie+=b.getText();
        currentOperator = b.getText().toString();
        updateEkran();
    }

    private void clear() {
        wyswietlenie = "";
        currentOperator = "";
        wynik="";
    }

    public void onClickClear(View v) {
        clear();
        updateEkran();

    }

    private double operate(String a, String b, String op) {

        switch(op) {
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case"-": return Double.valueOf(a) - Double.valueOf(b);
            case "*": return Double.valueOf(a) * Double.valueOf(b);

            case "/": try {
                return Double.valueOf(a) / Double.valueOf(b);
            }catch(Exception e){

                Log.d("Calc",e.getMessage());
            }

            default: return -1;
        }
    }

    private boolean getWynik(){
        String[] operation = wyswietlenie.split(Pattern.quote(currentOperator));

        if(operation.length < 2) return false;
        wynik = String.valueOf(operate(operation[0],operation[1],currentOperator));
        return true;
    }

    public void onClickEqual(View v) {


        if(!getWynik()) return;
        ekran.setText(wyswietlenie+"\n"+String.valueOf(wynik));

    }
}
