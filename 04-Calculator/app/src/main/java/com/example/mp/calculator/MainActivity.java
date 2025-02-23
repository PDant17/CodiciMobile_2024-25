package com.example.mp.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView mDisplay;
    private TextView mSecondaryDisplay;
    private TextView mOperatorDisplay;
    private TextView mMemoryDisplay;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    private Button mButton0;
    private Button mButtonPlus;
    private Button mButtonMinus;
    private Button mButtonMultiplication;
    private Button mButtonDivision;
    private Button mButtonDot;
    private Button mButtonEqual;
    private Button mButtonClear;
    private Button mButtonMemoryClear;
    private Button mButtonMemorySet;
    private Button mButtonMemoryRecall;

    private Boolean equalJustPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDisplay = (TextView)findViewById(R.id.calcdisplay);
        mSecondaryDisplay = (TextView)findViewById(R.id.secondarydisplay);
        mOperatorDisplay = (TextView)findViewById(R.id.operatordisplay);
        mMemoryDisplay = (TextView)findViewById(R.id.memorydisplay);

        mButton1 = (Button)this.findViewById(R.id.Button1);
        mButton2 = (Button)this.findViewById(R.id.Button2);
        mButton3 = (Button)this.findViewById(R.id.Button3);
        mButton4 = (Button)this.findViewById(R.id.Button4);
        mButton5 = (Button)this.findViewById(R.id.Button5);
        mButton6 = (Button)this.findViewById(R.id.Button6);
        mButton7 = (Button)this.findViewById(R.id.Button7);
        mButton8 = (Button)this.findViewById(R.id.Button8);
        mButton9 = (Button)this.findViewById(R.id.Button9);
        mButton0 = (Button)this.findViewById(R.id.Button0);
        mButtonPlus =           (Button)this.findViewById(R.id.ButtonPlus);
        mButtonMinus =          (Button)this.findViewById(R.id.ButtonMinus);
        mButtonMultiplication = (Button)this.findViewById(R.id.ButtonMultiplication);
        mButtonDivision =       (Button)this.findViewById(R.id.ButtonDivision);
        mButtonDot =            (Button)this.findViewById(R.id.ButtonDot);
        mButtonEqual =          (Button)this.findViewById(R.id.ButtonEqual);
        mButtonClear =          (Button)this.findViewById(R.id.ButtonClear);
        mButtonMemoryClear =    (Button)this.findViewById(R.id.ButtonMemoryClear);
        mButtonMemorySet =      (Button)this.findViewById(R.id.ButtonMemorySet);
        mButtonMemoryRecall =   (Button)this.findViewById(R.id.ButtonMemoryRecall);

        mButton1.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { updateDisplay('1');} });
        mButton2.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { updateDisplay('2');} });
        mButton3.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { updateDisplay('3');} });
        mButton4.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { updateDisplay('4');} });
        mButton5.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { updateDisplay('5');} });
        mButton6.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { updateDisplay('6');} });
        mButton7.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { updateDisplay('7');} });
        mButton8.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { updateDisplay('8');} });
        mButton9.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { updateDisplay('9');} });
        mButton0.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { updateDisplay('0');} });

        mButtonDot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mDisplay.getText().toString().contains(".")) {
                    //do nothing
                } else {
                    mDisplay.setText(mDisplay.getText().toString()+".");
                }
            }
        });

        mButtonPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSecondaryDisplay.setText(mDisplay.getText().toString());
                mOperatorDisplay.setText("+");
                mDisplay.setText("");
            }
        });

        mButtonMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSecondaryDisplay.setText(mDisplay.getText().toString());
                mOperatorDisplay.setText("-");
                mDisplay.setText("");
            }
        });

        mButtonMultiplication.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSecondaryDisplay.setText(mDisplay.getText().toString());
                mOperatorDisplay.setText("*");
                mDisplay.setText("");
            }
        });

        mButtonDivision.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSecondaryDisplay.setText(mDisplay.getText().toString());
                mOperatorDisplay.setText("/");
                mDisplay.setText("");
            }
        });

        mButtonEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float op1 = Float.parseFloat(mSecondaryDisplay.getText().toString());

                float op2;
                String str = mDisplay.getText().toString();
                if (str.length()==0)
                    { op2=0; }
                else
                    { op2 = Float.parseFloat(str); }

                float res = 0;
                switch (mOperatorDisplay.getText().toString().charAt(0)) {
                    case '+': res = op1 + op2; break;
                    case '-': res = op1 - op2; break;
                    case '*': res = op1 * op2; break;
                    case '/': res = op1 / op2; break;
                    default: //should nevere be here!
                }
                mSecondaryDisplay.setText("");
                mOperatorDisplay.setText("");
                mDisplay.setText(Float.toString(res));
                equalJustPressed = true;
            }
        });

        mButtonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSecondaryDisplay.setText("");
                mOperatorDisplay.setText("");
                mDisplay.setText("");
            }
        });

        mButtonMemorySet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mMemoryDisplay.setText(mDisplay.getText().toString());
            }
        });

        mButtonMemoryRecall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDisplay.setText(mMemoryDisplay.getText().toString());
            }
        });

        mButtonMemoryClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mMemoryDisplay.setText("");
            }
        });


    }

    private void updateDisplay(char digit) {
        if (equalJustPressed) {
            mDisplay.setText("");
            equalJustPressed = false;
        }
        mDisplay.setText(mDisplay.getText().toString()+digit);
    }
}
