package jp.codedesign.android.artimehack;

import java.io.IOException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
	// The Android のデフォルトでのデータベースパス
	private static String DB_PATH = "/data/data/jp.codedesign.android.artime/";
//	private static String DB_PATH = "/data/data/com.adamrocker.android.input.simeji/";
	private static String DB_NAME = "writableJAJP.dic";
	private static String DB_NAME_ASSET = "writableJAJP2.dic";
	private SQLiteDatabase mDataBase;
	private final Context mContext;

	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.mContext = context;
	}

	/**
	 * asset に格納したデータベースをコピーするための空のデータベースを作成する
	 * 
	 **/
	public void createEmptyDataBase() throws IOException {
		boolean dbExist = checkDataBaseExists();
		if (dbExist) {
			// すでにデータベースは作成されている
		} else {
			// このメソッドを呼ぶことで、空のデータベースが
			// アプリのデフォルトシステムパスに作られる
			this.getReadableDatabase();
/*			try {
				// asset に格納したデータベースをコピーする
//				copyDataBaseFromAsset();
			} catch (IOException e) {
//				throw new Error("Error copying database");
			}
*/		}
	}

	/** * 再コピーを防止するために、すでにデータベースがあるかどうか判定する * * @return 存在している場合 {@code true} */
	private boolean checkDataBaseExists() {
		SQLiteDatabase checkDb = null;
		try {
			String dbPath = DB_PATH + DB_NAME;
			checkDb = SQLiteDatabase.openDatabase(dbPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			// データベースはまだ存在していない
		}
		if (checkDb != null) {
			checkDb.close();
		}
		return checkDb != null ? true : false;
	}

	public SQLiteDatabase openDataBase() throws Exception {
		// Open the database
		String myPath = DB_PATH + DB_NAME;
		try {
			mDataBase = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
			
		} catch (Exception e){
			throw e;
		}
		return mDataBase;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	@Override
	public synchronized void close() {
		if (mDataBase != null)
			mDataBase.close();
		super.close();
	}
}