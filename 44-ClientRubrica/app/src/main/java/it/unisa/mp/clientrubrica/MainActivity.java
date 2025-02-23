package it.unisa.mp.clientrubrica;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		lv = (ListView) findViewById(R.id.listView);
	}

	public void getData(View v) {
		Log.d("DEBUG","button clicked");
		ContentResolver cr = getContentResolver();

		Log.d("DEBUG","URI="+ContactsContract.CommonDataKinds.Phone.CONTENT_URI);

		Cursor c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                				  ContactsContract.CommonDataKinds.Phone.NUMBER},
					null, null, null);

		List<String> contacts = new ArrayList<String>();
		if (c.moveToFirst()) {
			do {
				String str;
				str = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				str+= "  " + c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				contacts.add(str);
			} while (c.moveToNext());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, contacts);
		lv.setAdapter(adapter);
	}
}
