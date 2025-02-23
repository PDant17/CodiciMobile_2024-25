package it.unisa.di.approidlab.frammenticartelle;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cartella extends Fragment implements View.OnClickListener {
    int numeri[];

    public Cartella() {
        //Required empty public constructor
    }

    public void setNumeri(int[] numeri) {
        this.numeri = numeri;
        return;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cartella, container, false);
        ArrayList<View> allButtons;
        allButtons = (v.findViewById(R.id.cartellaTable)).getTouchables();
        int i=0;
        for (View element: allButtons) {
            Button b = (Button) element;
            if (b != null && numeri != null) {
                Log.d("DEBUG","Setting listener for button "+i);
                b.setOnClickListener(this);
                b.setText("" + numeri[i++]);
            }
        }
        return v;
    }

    public void onClick(View v) {
        Button b = (Button) v;
        Log.d("DEBUG", "Hello button " + b.getText().toString());
        Object tag = b.getTag();
        if (tag == null || tag.toString().equals("false")) {
            b.setTextColor(Color.GREEN);
            b.setTag("true");
        }
        else {
            b.setTextColor(Color.BLACK);
            b.setTag("false");
        }
    }


}
