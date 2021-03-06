package com.flying.famous.quotes.db.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.flying.famous.quotes.db.entity.Type;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TYPE".
*/
public class TypeDao extends AbstractDao<Type, Long> {

    public static final String TABLENAME = "TYPE";

    /**
     * Properties of entity Type.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property IconUrl = new Property(2, String.class, "iconUrl", false, "ICON_URL");
        public final static Property Bg = new Property(3, String.class, "bg", false, "BG");
    }


    public TypeDao(DaoConfig config) {
        super(config);
    }
    
    public TypeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TYPE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"ICON_URL\" TEXT," + // 2: iconUrl
                "\"BG\" TEXT);"); // 3: bg
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TYPE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Type entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
 
        String iconUrl = entity.getIconUrl();
        if (iconUrl != null) {
            stmt.bindString(3, iconUrl);
        }
 
        String bg = entity.getBg();
        if (bg != null) {
            stmt.bindString(4, bg);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Type entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
 
        String iconUrl = entity.getIconUrl();
        if (iconUrl != null) {
            stmt.bindString(3, iconUrl);
        }
 
        String bg = entity.getBg();
        if (bg != null) {
            stmt.bindString(4, bg);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Type readEntity(Cursor cursor, int offset) {
        Type entity = new Type( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // iconUrl
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // bg
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Type entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setIconUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setBg(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Type entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Type entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Type entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
