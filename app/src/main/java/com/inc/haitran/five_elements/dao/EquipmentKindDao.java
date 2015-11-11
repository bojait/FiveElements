package com.inc.haitran.five_elements.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by apple on 9/8/15.
 */
public class EquipmentKindDao extends de.greenrobot.dao.AbstractDao<EquipmentKind,Long>{
    public static final String TABLENAME = "'equipment_kind_tbs'";
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "id");
        public final static Property Name_en = new Property(1, String.class, "name_en", false, "name_en");
        public final static Property Name_vn = new Property(2, String.class, "name_vn", false, "name_vn");
        public final static Property Creation_1_id = new Property(3, Long.class, "creation_1_id", false, "creation_1_id");
        public final static Property Creation_2_id = new Property(4, Long.class, "creation_2_id", false, "creation_2_id");
        public final static Property Cls = new Property(5, Long.class, "cls", false, "cls");
    };
    public EquipmentKindDao(DaoConfig config)
    {super(config);}
    public EquipmentKindDao(DaoConfig config, DaoSession daoSession) {
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
                "'name_en' INTEGER," + // 1: account_type_id
                "'name_vn' INTEGER," + // 2: contacts_id
                "'creation_1_id' INTEGER," + // 3: contacts_system_id
                "'creation_2_id' INTEGER," + // 4: account_type
                "'cls' INTEGER);"); // 5: account_name
    }
    /** @inheritdoc */
    @Override
    public EquipmentKind readEntity(Cursor cursor, int offset) {
        EquipmentKind entity = new EquipmentKind( //
                (cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0)),
        (cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1)),
        (cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2)),
        (cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3)),
        (cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4)),
        (cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5))
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
    protected void bindValues(SQLiteStatement stmt, EquipmentKind entity) {
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
        Long creation_1_id = entity.creation_1_id;
        if (creation_1_id != null) {
            stmt.bindLong(4, creation_1_id);
        }

        Long creation_2_id = entity.creation_2_id;
        if (creation_2_id != null) {
            stmt.bindLong(5, creation_2_id);
        }

        Long cls = entity.cls;
        if (cls != null) {
            stmt.bindLong(6, cls);
        }

    }
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, EquipmentKind entity, int offset) {
        entity.id = (cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.name_en = (cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.name_vn = (cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.creation_1_id = (cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.creation_2_id = (cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.cls = (cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(EquipmentKind entity, long rowId) {
        entity.id = rowId;
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(EquipmentKind entity) {
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
