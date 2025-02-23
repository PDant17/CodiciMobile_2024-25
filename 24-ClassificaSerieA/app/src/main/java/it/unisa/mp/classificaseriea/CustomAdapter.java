package it.unisa.mp.classificaseriea;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Squadra> {
    private int resource;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resourceId, List<Squadra> objects) {
            super(context, resourceId, objects);
            resource = resourceId;
            inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
    		if (v == null) {
    			Log.d("DEBUG","Inflating view");
    			v = inflater.inflate(R.layout.list_element, null);
    		}
    		
            Squadra sq = getItem(position);

            TextView nomeTextView;
            TextView puntiTextView;

            nomeTextView = (TextView) v.findViewById(R.id.nomeSquadra);
            puntiTextView = (TextView) v.findViewById(R.id.puntiSquadra);
            nomeTextView.setText(sq.getName());
            puntiTextView.setText(""+sq.getPunti());

            return v;
    }
}

