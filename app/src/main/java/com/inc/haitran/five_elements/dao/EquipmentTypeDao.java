package com.inc.haitran.five_elements.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by apple on 9/8/15.
 */
public class EquipmentTypeDao extends de.greenrobot.dao.AbstractDao<EquipmentType,Long> {
    public static final String TABLENAME = "'equipment_type_tbs'";
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "id");
        public final static Property Name_en = new Property(1, String.class, "name_en", false, "name_en");
        public final static Property Name_vn = new Property(2, String.class, "name_vn", false, "name_vn");
        public final static Property Color = new Property(3, String.class, "color", false, "color");
    };
    public EquipmentTypeDao(DaoConfig config)
    {super(config);}
    public EquipmentTypeDao(DaoConfig config, DaoSession daoSession) {
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
                "'color' TEXT);"); // 5: account_name
    }
    /** @inheritdoc */
    @Override
    public EquipmentType readEntity(Cursor cursor, int offset) {
        EquipmentType entity = new EquipmentType( //
                (cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0)),
        (cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1)),
        (cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2)),
        (cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3))
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
    protected void bindValues(SQLiteStatement stmt, EquipmentType entity) {
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
        String color = entity.color;
        if (color != null) {
            stmt.bindString(4, color);
        }

    }
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, EquipmentType entity, int offset) {
        entity.id = (cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.name_en = (cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.name_vn = (cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.color = (cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));

    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(EquipmentType entity, long rowId) {
        entity.id = (rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(EquipmentType entity) {
        if(entity != null) {
            return entity.id;
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
