package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SAM on 22/04/2018.
 */

public class SQLliteUser extends SQLiteOpenHelper {
    //private static final String TAG = SQLiteHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String TAG = SQLliteUser.class.getSimpleName();

    // Database Name
    private static final String DATABASE_NAME = "android_db";

    // Login table name
    private static final String TABLE_USER = "users";
    // Login Table Columns names
    private static final String KEY_IDU = "iduc";
    private static final String KEY_TOKEN = "token";

    public static String getKeyIdu() {
        return KEY_IDU;
    }
    public static String getKeyToken() {
        return KEY_TOKEN;
    }
   /* public void setKeyToken(String KEY_TOKEN){SQLliteUser.KEY_TOKEN=KEY_TOKEN;}
    public String setKeyIdu(String KEY_IDU){return KEY_IDU;}*/
    public SQLliteUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_TOKEN + " TEXT ," + KEY_IDU+ " TEXT PRIMARY KEY "+")";
        System.out.println(CREATE_LOGIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_LOGIN_TABLE);


        Log.d(TAG, "Database tables created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void addUser(String token, String idu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
      //  values.put(KEY_NOM, nom); // Name
        values.put(KEY_TOKEN, token); // token
        values.put(KEY_IDU, idu); // id user crypte
// Insert the new row, returning the primary key value of the new row
        long id = db.insert(TABLE_USER, null, values);

        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /*
     * Getting user data from database
     */
    public ArrayList<HashMap<String, String>> getUserDetails() {
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap<String, String>> array_list = new ArrayList<HashMap<String, String>>();

        //hp = new HashMap();
       Cursor res =  db.rawQuery( selectQuery, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            HashMap<String,String>  hashmap = new HashMap<String, String>();
            hashmap.put("idu", res.getString(res.getColumnIndex(KEY_IDU)));
            hashmap.put("Tocken", res.getString(res.getColumnIndex(KEY_TOKEN)));
           // hashmap.put("image", res.getString(res.getColumnIndex(image)));


            array_list.add(hashmap);
            res.moveToNext();
        }
        return array_list;

    }
    // HashMap<String, String> user = new HashMap<String, String>();
       /*   String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

          users uqu=new users();
  user.put("nom", cursor.getString(1));

        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());
     //
        return user;*/
}


