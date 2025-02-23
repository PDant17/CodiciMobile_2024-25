package it.unisa.di.mp.socketjsoup;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {
    private final String TAG = "DEBUG";
    private String URL = "http://www.legaseriea.it/it/serie-a/classifica";
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

            //Estraiamo i nomi delle squadre nell'ordine della classifica
            //<img  src="/uploads/default/attachments/squadre/squadre_m/1/images/logo/763/original/loghi400x400juventus_black.png" width="30"  height="30" title="JUVENTUS" />
            Elements elements = doc.select("[src]");
            //doc contiene tutti le linee [src], sono tante

            int i=0;
            for (Element element : elements) {
                //con il grep su width=30 si "salta" alle [src] che corrispondono alla classifica
                if (element.toString().contains("width=\"30\"")) {
                    String input = element.toString();
                    Log.d(TAG,"input="+input);
                    String nome = input.substring(input.indexOf("title=")+7,input.length()-4);
                    Log.d(TAG,"nome="+ nome);
                    nomi.add(nome);
                    i++;
                    if (i>=20) break; //prendiamo solo i primi 20 che sono la classifica
                }
            }

            //Estraiamo i punteggi nell'ordine della classifica
            Elements punteggi = doc.getElementsByClass("blue");
            //Con la classe "blue" si ottengo le linee con i punteggi, che sono del tipo <td class="blue">83</td>
            //in realtà ci sono anche altre line che contengono "blue", ma se saltiamo le prime 2 e prendiamo le
            //successive 20 otteniamo i punteggi della classifica

            i = 0;
            for (Element punteggio : punteggi) {
                i++;
                String input = punteggio.toString();
                Log.d(TAG,"input="+input);
                if (i<=2) continue; //Salta i primi 2
                String pt = input.substring(17,19);
                //Se il punteggio è < 10 il secondo carattere non è una cifra, bisogna toglierlo
                if (!Character.isDigit(pt.charAt(1))) {
                    pt = pt.substring(0,1);
                }
                Log.d(TAG,"pt=" +pt);
                punti.add(pt);
                if (i>=22) break;
            }

            //Ottenuti nomi delle squadre e punteggi, in ordine, aggiorniamo l'array adapter del listview
            i = 0;

            String data_squadre = "";
            String data_punti = "";

            Log.d(TAG, "Size of nomi=" + nomi.size() + "   Size of punti=" + punti.size());

            for (i = 0; i < nomi.size(); i++) {
                Log.d(TAG, "Getting element at index i=" + i);
                Log.d(TAG, "  nome=" + nomi.get(i));
                Log.d(TAG, "  punti=" + punti.get(i));
                data_squadre += nomi.get(i) + "\n";
                data_punti += punti.get(i) + "\n";
                Squadra sq = new Squadra(nomi.get(i), Integer.parseInt(punti.get(i)));
                customAdapter.add(sq);
            }
            customAdapter.notifyDataSetChanged();

        }
    }

}