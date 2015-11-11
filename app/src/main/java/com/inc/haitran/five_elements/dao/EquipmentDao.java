package com.inc.haitran.five_elements.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by apple on 10/9/15.
 */
public class EquipmentDao extends de.greenrobot.dao.AbstractDao<Equipment,Long> {
    public static final String TABLENAME = "'equipment_tbs'";
    public static class Properties {
        public final static Property Id = new Property(0, Integer.class, "id", true, "id");
        public final static Property Name = new Property(1, String.class, "name", false, "name");
        public final static Property Equipment_kind_id = new Property(2, Integer.class, "equipment_kind_id", false, "equipment_kind_id");
        public final static Property Equipment_type_id = new Property(3, Integer.class, "equipment_type_id", false, "equipment_type_id");
        public final static Property Element_id = new Property(4, Integer.class, "element_id", false, "element_id");
        public final static Property Properties_id_0 = new Property(5, Integer.class, "properties_id_0", false, "properties_id_0");
        public final static Property Value_0 = new Property(6, Integer.class, "value_0", false, "value_0");
        public final static Property Properties_id_1 = new Property(7, Integer.class, "properties_id_1", false, "properties_id_1");
        public final static Property Value_1 = new Property(8, Integer.class, "value_1", false, "value_1");
        public final static Property Properties_id_2 = new Property(9, Integer.class, "properties_id_2", false, "properties_id_2");
        public final static Property Value_2 = new Property(10, Integer.class, "value_2", false, "value_2");
        public final static Property Properties_id_3 = new Property(11, Integer.class, "properties_id_3", false, "properties_id_3");
        public final static Property Value_3 = new Property(12, Integer.class, "value_3", false, "value_3");
        public final static Property Properties_id_4 = new Property(13, Integer.class, "properties_id_4", false, "properties_id_4");
        public final static Property Value_4 = new Property(14, Integer.class, "value_4", false, "value_4");
        public final static Property Properties_id_5 = new Property(15, Integer.class, "properties_id_5", false, "properties_id_5");
        public final static Property Value_5 = new Property(16, Integer.class, "value_5", false, "value_5");
        public final static Property Active = new Property(17, Integer.class, "active", false, "active");
    };
    public EquipmentDao(DaoConfig config)
    {super(config);}
    public EquipmentDao(DaoConfig config, DaoSession daoSession) {
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
                "`name` TEXT,\n" +
                "`equipment_kind_id` INTEGER ,\n" +
                "`equipment_type_id` INTEGER ,\n" +
                "`element_id` INTEGER ,\n" +
                "`properties_id_0` INTEGER ,\n" +
                "`value_0` INTEGER ,\n" +
                "`properties_id_1` INTEGER ,\n" +
                "`value_1` INTEGER ,\n" +
                "`properties_id_2` INTEGER ,\n" +
                "`value_2` INTEGER ,\n" +
                "`properties_id_3` INTEGER ,\n" +
                "`value_3` INTEGER ,\n" +
                "`properties_id_4` INTEGER ,\n" +
                "`value_4` INTEGER ,\n" +
                "`properties_id_5` INTEGER ,\n" +
                "`value_5` INTEGER ,\n" +
                "`active` INTEGER );"); // 5: account_name
    }
    /** @inheritdoc */
    @Override
    public Equipment readEntity(Cursor cursor, int offset) {
        Equipment entity = new Equipment( //
        (cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0)),
        (cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1)),
        (cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2)),
        (cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3)),
        (cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4)),
        (cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5)),
        (cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6)),
        (cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7)),
        (cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8)),
        (cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9)),
        (cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10)),
        (cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11)),
        (cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12)),
        (cursor.isNull(offset + 13) ? null : cursor.getLong(offset + 13)),
        (cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14)),
        (cursor.isNull(offset + 15) ? null : cursor.getLong(offset + 15)),
        (cursor.isNull(offset + 16) ? null : cursor.getLong(offset + 16)),
        (cursor.isNull(offset + 17) ? null : cursor.getLong(offset + 17))
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
    protected void bindValues(SQLiteStatement stmt, Equipment entity) {
        stmt.clearBindings();

        Long id = entity.id;
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String name_en = entity.name;
        if (name_en != null) {
            stmt.bindString(2, name_en);
        }

        Long equipment_kind_id = entity.equipment_kind_id;
        if (equipment_kind_id != null) {
            stmt.bindLong(3, equipment_kind_id);
        }
        Long equipment_type_id = entity.equipment_type_id;
        if (equipment_type_id != null) {
            stmt.bindLong(4, equipment_type_id);
        }

        Long element_id = entity.element_id;
        if (element_id != null) {
            stmt.bindLong(5, element_id);
        }

        Long properties_id_0 = entity.properties_id_0;
        if (properties_id_0 != null) {
            stmt.bindLong(6, properties_id_0);
        }
        Long value_0 = entity.value_0;
        if (value_0 != null) {
            stmt.bindLong(7, value_0);
        }



        Long properties_id_1 = entity.properties_id_1;
        if (properties_id_1 != null) {
            stmt.bindLong(8, properties_id_1);
        }
        Long value_1 = entity.value_1;
        if (value_1 != null) {
            stmt.bindLong(9, value_1);
        }

        Long properties_id_2 = entity.properties_id_2;
        if (properties_id_2 != null) {
            stmt.bindLong(10, properties_id_2);
        }
        Long value_2 = entity.value_2;
        if (value_2 != null) {
            stmt.bindLong(11, value_2);
        }

        Long properties_id_3 = entity.properties_id_3;
        if (properties_id_3 != null) {
            stmt.bindLong(12, properties_id_3);
        }
        Long value_3 = entity.value_3;
        if (value_3 != null) {
            stmt.bindLong(13, value_3);
        }

        Long properties_id_4 = entity.properties_id_4;
        if (properties_id_4 != null) {
            stmt.bindLong(14, properties_id_4);
        }
        Long value_4 = entity.value_4;
        if (value_4 != null) {
            stmt.bindLong(15, value_4);
        }

        Long properties_id_5 = entity.properties_id_5;
        if (properties_id_5 != null) {
            stmt.bindLong(16, properties_id_5);
        }
        Long value_5 = entity.value_5;
        if (value_5 != null) {
            stmt.bindLong(17, value_5);
        }
        Long active = entity.active;
        if (active != null) {
            stmt.bindLong(18, active);
        }
    }
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Equipment entity, int offset) {
        entity.id = (cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.name = (cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.equipment_kind_id = (cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.equipment_type_id = (cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.element_id = (cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.properties_id_0 = (cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.value_0 = (cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.properties_id_1 = (cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
        entity.value_1 = (cursor.isNull(offset + 8) ? null : cursor.getLong(offset +8));
        entity.properties_id_2 = (cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.value_2 = (cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.properties_id_3 = (cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11));
        entity.value_3 = (cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12));
        entity.properties_id_4 = (cursor.isNull(offset + 13) ? null : cursor.getLong(offset + 13));
        entity.value_4 = (cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14));
        entity.properties_id_5 = (cursor.isNull(offset + 15) ? null : cursor.getLong(offset + 15));
        entity.value_5 = (cursor.isNull(offset + 16) ? null : cursor.getLong(offset + 16));
        entity.active = (cursor.isNull(offset + 17) ? null : cursor.getLong(offset + 17));


    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Equipment entity, long rowId) {
        entity.id = rowId;
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(Equipment entity) {
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
