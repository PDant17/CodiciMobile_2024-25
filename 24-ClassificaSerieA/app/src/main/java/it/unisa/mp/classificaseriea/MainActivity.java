package it.unisa.mp.classificaseriea;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private final String TAG = "DEBUG";
    private String URL = "https://www.repubblica.it/sport/dirette/calcio/serie-a-2022/classifica/";
    private EditText textToSend;
    private ListView listViewClassifica;
    private String strToSend;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.layout);

        //Bind widgets
        textToSend = (EditText) findViewById(R.id.editTextToSend);
        textToSend.setText(URL);
        listViewClassifica = (ListView) findViewById(R.id.listViewClassifica);

        customAdapter = new CustomAdapter(this, R.layout.list_element, new ArrayList<Squadra>());

        listViewClassifica.setAdapter(customAdapter);

    }

    public void sendButtonClicked(View v) {
        strToSend = textToSend.getEditableText().toString();
        String str = "Send button has been clicked.\n" +
                "Sending: " + strToSend;
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

        NetworkTask nt = new NetworkTask();
        customAdapter.clear();
        customAdapter.notifyDataSetChanged();
        nt.execute(strToSend);
    }


    public void clearButtonClicked(View v) {
        customAdapter.clear();
        customAdapter.notifyDataSetChanged();
    }

    class NetworkTask extends AsyncTask<String, Integer, Document> {

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute()");
        }

        @Override
        protected Document doInBackground(String... values) {
            Log.d(TAG, "doInBackground: values[0]=URL=" + values[0]);

            try {
                Document doc = Jsoup.connect(values[0]).get();
                return doc;

            } catch (IOException exception) {
                exception.printStackTrace();
                Log.d(TAG,"doInBackground exception: "+exception.getMessage());
            }

            return null;
        }


        @Override
        protected void onPostExecute(Document doc) {

            ArrayList<String> nomi = new ArrayList();
            ArrayList<String> punti = new ArrayList();

            if (doc == null) {
                Log.d(TAG,"doc is null");
                Toast.makeText(getApplicationContext(),"Null doc", Toast.LENGTH_LONG).show();
                return;
            }

            //Log.d(TAG, "onPostExecute(): received doc="+doc.toString());
            Log.d(TAG,"onPostExecute: got doc LEN="+doc.toString().length());

            //Estraiamo le righe della tablle della classifica

            Elements elements = doc.select("tbody > tr.as21-classifica-table-tr");

            //Log.d(TAG,"elements size="+elements.size());

            int i=0;
            for (Element element : elements) {
                //Log.d(TAG, "Element: "+element);

                //Per ogni riga estriamo il nome della squadra

                //String team = element.select("td.as21-classifica-table-cell-name > div > a").text();
                //Log.d(TAG,"squadra="+team);

                //e i punti. Il td in realtà contiene anche il nome della squadra oltre ai punti
                //Quindi il nome lo prendiamo da qui

                String points = element.select("td.as21-classifica-table-right-border").text();
                //Log.d(TAG,"punti="+points);

                //Contiene anche il nome della squadra oltre ai punti
                String[] parts;
                parts = points.split(" ");

                //Nome squadra e punti
                Log.d(TAG,"Parts="+parts[0]+" "+parts[1]);

                Squadra sq = new Squadra(parts[0], Integer.parseInt(parts[1]));
                customAdapter.add(sq);
            }
            customAdapter.notifyDataSetChanged();
        }
    }

}