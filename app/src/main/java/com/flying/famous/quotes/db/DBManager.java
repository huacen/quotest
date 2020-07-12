package com.flying.famous.quotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.flying.famous.quotes.db.gen.DaoMaster;
import com.flying.famous.quotes.db.gen.DaoSession;
import com.flying.famous.quotes.db.gen.LikeDao;
import com.flying.famous.quotes.db.gen.QuotesDao;
import com.flying.famous.quotes.db.gen.TypeDao;

public class DBManager {
    private DaoSession daoSession;
    private static final String TABLENAME = "quotes.db";

    private static class Inner {
        private static final DBManager INSTANCE = new DBManager();
    }

    public static DBManager INSTANCE() {
        return Inner.INSTANCE;
    }

    private DBManager() {
    }

    public void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, TABLENAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public TypeDao getType() {
        return daoSession.getTypeDao();
    }

    public QuotesDao getQuotesDao() {
        return daoSession.getQuotesDao();
    }

    public LikeDao getLikeDao() {
        return daoSession.getLikeDao();
    }
}
