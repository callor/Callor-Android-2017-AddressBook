package com.callor.hello.myaddrbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by callor on 2017-04-12.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "ADDR_BOOK";
    private static final int DB_VERSION = 1;

    // DB Helper 의 생성자
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // 모든 값을 외부로 부터 받을때 사용할 생성자
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    // CRUD
    public long insertDB(AddrTableVO vo){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBContract.AddrBook.COL_NAME,vo.getSname());
        values.put(DBContract.AddrBook.COL_BIRTH,vo.getSbirth());
        values.put(DBContract.AddrBook.COL_TEL,vo.getStel());

        long insertRow = db.insert(DBContract.AddrBook.TABLE_NAME,null,values);
        return insertRow;
    }

    public List<AddrTableVO> readFromDB(){
        List<AddrTableVO> dto = new ArrayList<AddrTableVO>();
        String sql = " SELECT " +
                        DBContract.AddrBook.COL_NAME + "," +
                        DBContract.AddrBook.COL_BIRTH + "," +
                        DBContract.AddrBook.COL_TEL  +
                        " FROM " + DBContract.AddrBook.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()) {
            do {

                AddrTableVO vo = new AddrTableVO();
                vo.setSname(cursor.getString(DBContract.AddrBook.COL_NAME_SQ));
                vo.setSbirth(cursor.getString(DBContract.AddrBook.COL_BIRTH_SQ)) ;
                vo.setStel(cursor.getString(DBContract.AddrBook.COL_TEL_SQ));

                dto.add(vo);

            } while (cursor.moveToNext()) ;
        }
        return dto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.AddrBook.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + DBContract.AddrBook.TABLE_NAME);
        onCreate(db);
    }
}
