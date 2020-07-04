package com.flying.famous.quotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.flying.greendao.db.DaoMaster;
import com.flying.greendao.db.DaoSession;
import com.flying.greendao.db.LikeDao;
import com.flying.greendao.db.QuotesDao;
import com.flying.greendao.db.TypeDao;

public class DBManager {
    private DaoSession daoSession;

    private static class Inner {
        private static final DBManager INSTANCE = new DBManager();
    }

    public static DBManager INSTANCE() {
        return Inner.INSTANCE;
    }

    private DBManager() {
    }

    public void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "quotes.db");
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
