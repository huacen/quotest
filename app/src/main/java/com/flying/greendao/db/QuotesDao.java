package com.flying.greendao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.flying.famous.quotes.db.Quotes;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "QUOTES".
*/
public class QuotesDao extends AbstractDao<Quotes, Long> {

    public static final String TABLENAME = "QUOTES";

    /**
     * Properties of entity Quotes.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Tid = new Property(1, Long.class, "tid", false, "TID");
        public final static Property Text = new Property(2, String.class, "text", false, "TEXT");
    }


    public QuotesDao(DaoConfig config) {
        super(config);
    }
    
    public QuotesDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"QUOTES\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TID\" INTEGER," + // 1: tid
                "\"TEXT\" TEXT);"); // 2: text
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"QUOTES\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Quotes entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long tid = entity.getTid();
        if (tid != null) {
            stmt.bindLong(2, tid);
        }
 
        String text = entity.getText();
        if (text != null) {
            stmt.bindString(3, text);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Quotes entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long tid = entity.getTid();
        if (tid != null) {
            stmt.bindLong(2, tid);
        }
 
        String text = entity.getText();
        if (text != null) {
            stmt.bindString(3, text);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Quotes readEntity(Cursor cursor, int offset) {
        Quotes entity = new Quotes( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // tid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // text
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Quotes entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTid(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setText(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Quotes entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Quotes entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Quotes entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
