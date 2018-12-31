package github.com.jeonjunho.expanditure.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE EXPANDITURE_BOOK (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "date DATE, " +
                        "item TEXT, " +
                        "price INTEGER," +
                        "category TEXT," +
                        "updateDateTime DATE," +
                        "createDateTime DATE);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String date, String item, Integer price, String category) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(
                "INSERT INTO EXPANDITURE_BOOK VALUES(" +
                        "null" +
                        ",'" + date + "'" +
                        ",'" + item + "'" +
                        ","  + price +
                        ",'" + category + "'" +
                        ","  + "null" +
                        ","  + "datetime('now')" +
                        ");"
        );
        db.close();
    }

    public void update(String item, int price) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE EXPANDITURE_BOOK SET price=" + price + " WHERE item='" + item + "';");
        db.close();
    }

    public void delete(String item) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM EXPANDITURE_BOOK WHERE item='" + item + "';");
        db.close();
    }

    public String showToday(String today) {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM EXPANDITURE_BOOK WHERE DATE ='" + today + "';", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(1) + " "
                    + cursor.getString(2) + "："
                    + cursor.getString(3) + "円 ["
                    + cursor.getString(4) + "]\n";
        }

        return result;
    }
}