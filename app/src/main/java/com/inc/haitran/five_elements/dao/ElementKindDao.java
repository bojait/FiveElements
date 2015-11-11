package com.inc.haitran.five_elements.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by apple on 9/8/15.
 */
public class ElementKindDao  extends de.greenrobot.dao.AbstractDao<ElementKind,Long>{
    public static final String TABLENAME = "'element_kind_tbs'";
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "id");
        public final static Property Name_en = new Property(1, String.class, "name_en", false, "name_en");
        public final static Property Name_vn = new Property(2, String.class, "name_vn", false, "name_vn");
        public final static Property Creation_id = new Property(3, Long.class, "creation_id", false, "creation_id");
        public final static Property Destruction_id = new Property(4, Long.class, "destruction_id", false, "destruction_id");
        public final static Property Color = new Property(5, String.class, "color", false, "color");
    };
    public ElementKindDao(DaoConfig config)
    {super(config);}
    public ElementKindDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }
    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }


    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + TABLENAME+" (" + //
                "'id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'name_en' TEXT," + // 1: account_type_id
                "'name_vn' TEXT," + // 2: contacts_id
                "'creation_id' INTEGER," + // 3: contacts_system_id
                "'destruction_id' INTEGER," + // 4: account_type
                "'color' TEXT);"); // 5: account_name
    }
    /** @inheritdoc */
    @Override
    public ElementKind readEntity(Cursor cursor, int offset) {
        ElementKind entity = new ElementKind( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name en
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name vn
                cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // creation_id
                cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // destruction_id
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // color
        );
        return entity;
    }
    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + TABLENAME;
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ElementKind entity) {
        stmt.clearBindings();

        Long id = entity.id;
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String name_en = entity.name_en;
        if (name_en != null) {
            stmt.bindString(2, name_en);
        }

        String name_vn = entity.name_vn;
        if (name_vn != null) {
            stmt.bindString(3, name_vn);
        }
        Long creation_id = entity.creation_id;
        if (creation_id != null) {
            stmt.bindLong(4, creation_id);
        }

        Long destruction_id = entity.destruction_id;
        if (destruction_id != null) {
            stmt.bindLong(5, destruction_id);
        }

        String color = entity.color;
        if (color != null) {
            stmt.bindString(6, color);
        }

    }
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ElementKind entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName_en(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName_vn(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCreation_id(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setDestruction_id(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setColor(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ElementKind entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(ElementKind entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }


}
