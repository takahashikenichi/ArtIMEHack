package jp.codedesign.android.artimehack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ArtIMEHackActivity extends Activity {

	private DataBaseHelper mDbHelper;
	private SQLiteDatabase db;

	protected ListAdapter adapter;
	protected ListView candidateList;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        ListView lv = new ListView(this);
        setContentView(lv);

		if(setDatabase()) {

			Cursor c = findData();
			c.moveToFirst();
	        List<HashMap<String, Object>> myData = new ArrayList<HashMap<String, Object>>();
	        Map<String, Object> map;

			for (int i = 0; i < c.getCount(); i++) {
		        map = new HashMap<String, Object>();
		        map.put("rowid", c.getString(0));
		        map.put("stroke", c.getString(1));
		        map.put("candidate", c.getString(2));
		        myData.add((HashMap<String, Object>) map);
				c.moveToNext();

			}
			c.close();
		
	        SimpleAdapter adapter = new SimpleAdapter(
	        		this,
	        		myData,
	                R.layout.list_row,
	                new String[] { "stroke", "candidate" },
	                new int[] { R.id.stroke, R.id.candidate }
	        		);
	        lv.setAdapter(adapter);
		}
	}

	@Override
	public void onDestroy() {
		if(db != null) {
			db.close();			
		}
		super.onDestroy();
	}

	private boolean setDatabase() {
		mDbHelper = new DataBaseHelper(this);
		try {
			mDbHelper.createEmptyDataBase();
			db = mDbHelper.openDataBase();
			return true;
		} catch (Exception e) {
			Toast.makeText(this, R.string.Caution, Toast.LENGTH_LONG).show();
			return false;
		}
	}

	private static final String[] COLUMNS = { "rowid", "stroke", "candidate",
			"posLeft" };

	private Cursor findData() {
		Cursor cursor = db.query("dic", COLUMNS, null, null, null, null,
				"rowid");
		return cursor;
	}

}