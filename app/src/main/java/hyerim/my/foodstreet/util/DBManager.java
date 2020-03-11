package hyerim.my.foodstreet.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Html;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import hyerim.my.foodstreet.Object.ItemObject;

public class DBManager extends SQLiteOpenHelper {

    public DBManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE itemtable (" +
                "title TEXT, " +
                "link TEXT, " +
                "telephone TEXT, " +
                "roadAddress TEXT, " +
                "category TEXT, " +
                "mapx INTEGER, " +
                "mapy INTEGER, " +
                "PRIMARY KEY(link)" +
                ");"
        );
    }

    public long insertItem(ItemObject itemObject){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", String.valueOf(Html.fromHtml(itemObject.title)));
//        contentValues.put("link",itemObject.link);
        contentValues.put("telephone",itemObject.telephone);
        contentValues.put("roadAddress",itemObject.roadAddress);
        contentValues.put("category",itemObject.category);
        contentValues.put("mapx",itemObject.mapx);
        contentValues.put("mapy",itemObject.mapy);

        long ret = getWritableDatabase().insert("itemtable",null,contentValues);
//        Log.i(TAG, "insertItem: "+ ret+" | " + new Gson().toJson(contentValues));
        return ret;
    }

    public long deleteItem(String title){
       return getWritableDatabase().delete("itemtable","title=?",new String[]{title});
    }

//    public long readItem(ItemObject itemObject){
//        ContentValues contentValues = new ContentValues();
//        contentValues.get("title");
//        contentValues.get("link");
//        contentValues.get("telephone");
//        contentValues.get("roadAddress");
//        contentValues.get("category");
//        contentValues.get("mapx");
//        contentValues.get("mapy");
//
//        long ret = getReadableDatabase().
//    }

    public long updateItem(ItemObject itemObject){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",itemObject.title);
        contentValues.put("link",itemObject.link);
        contentValues.put("telephone",itemObject.telephone);
        contentValues.put("roadAddress",itemObject.roadAddress);
        contentValues.put("category",itemObject.category);
        contentValues.put("mapx",itemObject.mapx);
        contentValues.put("mapy",itemObject.mapy);

        long ret = getWritableDatabase().update("itemtable",contentValues, "link=? or title=?", new String[]{itemObject.link, itemObject.title});
//        Log.i(TAG, "insertItem: "+ ret+" | " + new Gson().toJson(contentValues));
        return ret;
    }

    public ArrayList<ItemObject> itemObjects(){
        ArrayList<ItemObject> itemObjects = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query("itemtable",null,null,null, null,null,null);
        while (cursor.moveToNext()) {
            ItemObject itemObject = new ItemObject();
            itemObject.title=cursor.getString(cursor.getColumnIndex("title"));
            itemObject.link = cursor.getString(cursor.getColumnIndex("link"));
            itemObject.telephone = cursor.getString(cursor.getColumnIndex("telephone"));
            itemObject.roadAddress = cursor.getString(cursor.getColumnIndex("roadAddress"));
            itemObject.category = cursor.getString(cursor.getColumnIndex("category"));
            itemObject.mapx = cursor.getInt(cursor.getColumnIndex("mapx"));
            itemObject.mapy = cursor.getInt(cursor.getColumnIndex("mapy"));
            itemObjects.add(itemObject);
        }
        return  itemObjects;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
