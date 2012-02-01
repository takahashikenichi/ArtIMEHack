package jp.codedesign.android.artimehack;

import java.io.IOException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
	// The Android �̃f�t�H���g�ł̃f�[�^�x�[�X�p�X
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
	 * asset �Ɋi�[�����f�[�^�x�[�X���R�s�[���邽�߂̋�̃f�[�^�x�[�X���쐬����
	 * 
	 **/
	public void createEmptyDataBase() throws IOException {
		boolean dbExist = checkDataBaseExists();
		if (dbExist) {
			// ���łɃf�[�^�x�[�X�͍쐬����Ă���
		} else {
			// ���̃��\�b�h���ĂԂ��ƂŁA��̃f�[�^�x�[�X��
			// �A�v���̃f�t�H���g�V�X�e���p�X�ɍ����
			this.getReadableDatabase();
/*			try {
				// asset �Ɋi�[�����f�[�^�x�[�X���R�s�[����
//				copyDataBaseFromAsset();
			} catch (IOException e) {
//				throw new Error("Error copying database");
			}
*/		}
	}

	/** * �ăR�s�[��h�~���邽�߂ɁA���łɃf�[�^�x�[�X�����邩�ǂ������肷�� * * @return ���݂��Ă���ꍇ {@code true} */
	private boolean checkDataBaseExists() {
		SQLiteDatabase checkDb = null;
		try {
			String dbPath = DB_PATH + DB_NAME;
			checkDb = SQLiteDatabase.openDatabase(dbPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			// �f�[�^�x�[�X�͂܂����݂��Ă��Ȃ�
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