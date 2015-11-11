package com.inc.haitran.five_elements.db;

import android.content.Context;

import com.inc.haitran.five_elements.dbview.EnumDB;
import com.inc.haitran.five_elements.utils.MyData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 10/9/15.
 */
public class Equipment extends com.inc.haitran.five_elements.dao.Equipment implements IFunctionDB {
    public static final int MAX_ROW_PROPERTIES = 6;
    public ElementKind elementsKind(){
        return Database.getInst().getElementsKindTbs(element_id);
    };
    public EquipmentKind equipmentsKind(){
        return Database.getInst().getEquipmentsKindTbs(equipment_kind_id);
    };

    @Override
    public Long insert(Context context) {
        return Database.getInstall(context).getDaoSession().getEquipmentDao().insert(this);
    }

    @Override
    public void update(Context context) {
        Database.getInstall(context).getDaoSession().getEquipmentDao().update(this);
    }

    @Override
    public void delete(Context context) {

    }

    public EquipmentType equipType ()
    {
        return Database.getInst().getEquipTypeTbs(equipment_type_id);
    }
    private Properties[] mValueEquip;
    public Equipment() {
        super();
    }

    public Equipment(com.inc.haitran.five_elements.dao.Equipment item) {
        super(item);
    }
    public Properties[] getAllValueEquip()
    {
        if(mValueEquip == null) {
            mValueEquip = new Properties[MAX_ROW_PROPERTIES];
            if(properties_id_0 != null)
            mValueEquip[0] = new Properties(properties_id_0,value_0);
            if(properties_id_1 != null)
            mValueEquip[1] = new Properties(properties_id_1,value_1);
            if(properties_id_2 != null)
            mValueEquip[2] = new Properties(properties_id_2,value_2);
            if(properties_id_3 != null)
            mValueEquip[3] = new Properties(properties_id_3,value_3);
            if(properties_id_4 != null)
            mValueEquip[4] = new Properties(properties_id_4,value_4);
            if(properties_id_5 != null)
            mValueEquip[5] = new Properties(properties_id_5,value_5);
        }
        return mValueEquip;
    }
    public Properties getValueEquip(int index)
    {
        if(mValueEquip == null) {
            getAllValueEquip();
        }

        return  mValueEquip[index];
    }
    public void setValueEquip(int index,Long id)
    {
        if(mValueEquip == null) {
            getAllValueEquip();
        }
        if(mValueEquip[index] == null)
            mValueEquip[index] = new Properties();
        mValueEquip[index].setId(id);
        switch (index) {
            case 0:

                this.properties_id_0 = id;
                mValueEquip[index].setValue(value_0);

                break;
            case 1:
                this.properties_id_1 = id;
                mValueEquip[index].setValue(value_1);
                break;
            case 2:
                this.properties_id_2 = id;
                mValueEquip[index].setValue(value_2);
                break;
            case 3:
                this.properties_id_3 = id;
                mValueEquip[index].setValue(value_3);
                break;
            case 4:
                this.properties_id_4 = id;
                mValueEquip[index].setValue(value_4);
                break;
            case 5:
                this.properties_id_5 = id;
                mValueEquip[index].setValue(value_5);
                break;
        }
    }
    public void setValueEquip(int index,Long id, int value)
    {
        setValueEquip(index,id,new Long(value));
    }
    public void setValueEquip(int index,Long id, Long value)
    {
        if(mValueEquip == null) {
            getAllValueEquip();
        }
        if(mValueEquip[index] == null) mValueEquip[index] = new Properties(id,value);
        switch (index)
        {
            case 0:
                this.properties_id_0 = id;
                this.value_0 = value;

                break;
            case 1:
                this.properties_id_1 = id;
                this.value_1 = value;
                break;
            case 2:
                this.properties_id_2 = id;
                this.value_2 = value;
                break;
            case 3:
                this.properties_id_3 = id;
                this.value_3 = value;
                break;
            case 4:
                this.properties_id_4 = id;
                this.value_4 = value;
                break;
            case 5:
                this.properties_id_5 = id;
                this.value_5 = value;
                break;

        }
    }
    public void setPropertiesValueEquip(int index,Properties properties)
    {
        if(mValueEquip == null) {
            getAllValueEquip();
        }
        mValueEquip[index] = properties;
        switch (index)
        {
            case 0:
                if(properties != null) {
                    this.properties_id_0 = properties.getId();
                    this.value_0 = properties.getLongValue();
                }
                else
                {
                    this.properties_id_0 = null;
                    this.value_0 = null;
                }
                break;
            case 1:
                if(properties != null) {
                    this.properties_id_1 = properties.getId();
                    this.value_1 = properties.getLongValue();
                }
                else
                {
                    this.properties_id_1 = null;
                    this.value_1 = null;
                }
                break;
            case 2:
                if(properties != null) {
                    this.properties_id_2 = properties.getId();
                    this.value_2 = properties.getLongValue();
                }
                else
                {
                    this.properties_id_2 = null;
                    this.value_2 = null;
                }
                break;
            case 3:
                if(properties != null) {
                    this.properties_id_3 = properties.getId();
                    this.value_3 = properties.getLongValue();
                }
                else
                {
                    this.properties_id_3 = null;
                    this.value_3 = null;
                }
                break;
            case 4:
                if(properties != null) {
                    this.properties_id_4 = properties.getId();
                    this.value_4 = properties.getLongValue();
                }
                else
                {
                    this.properties_id_4 = null;
                    this.value_4 = null;
                }
                break;
            case 5:
                if(properties != null) {
                    this.properties_id_5 = properties.getId();
                    this.value_5 = properties.getLongValue();
                }
                else
                {
                    this.properties_id_5 = null;
                    this.value_5 = null;
                }
                break;

        }
    }
    public void setValueEquip(Properties[] valueEquip)
    {
        this.mValueEquip = valueEquip;
    }
    public String equipName;
    public int minLevel = 0;
    public int minStrong = 0;
    public int minTP = 0;
    public Long getKindId()
    {
        return equipment_kind_id;
    }

    public EnumDB.EElementKind getElementKind()
    {
        if(equipment_kind_id == null) return EnumDB.EElementKind.none;
        return EnumDB.EElementKind.fromInteger( equipment_kind_id.intValue());
    }
    public int getElementKindId()
    {
        if(element_id == null) return -1;
        return element_id.intValue();
    }
    public void setEquipmentsKind(EnumDB.EEquipmentKind eKind)
    {
        setEquipmentsKind(eKind.getValue());
    }
    public void setEquipmentsKind(int eKind)
    {
        equipment_kind_id = new Long(eKind) ;
    }
    public void setEquipmentsKind(EquipmentKind eKind)
    {
        equipment_kind_id = eKind.id;
    }
    public void setElementsKind(int eKind)
    {
        element_id = new Long(eKind);
    }
    public void setElementsKind(EnumDB.EElementKind eKind)
    {
        setElementsKind(eKind.getValue());
    }
    public void setElementsKind(ElementKind eKind)
    {
        element_id = eKind.id;
    }

    public void setEquipType(int eKind)
    {
        equipment_type_id = new Long(eKind);
    }
    public void setEquipType(EnumDB.EElementKind eKind)
    {
        setElementsKind(eKind.getValue());
    }
    public void setEquipType(EquipmentType eKind)
    {
        equipment_type_id = eKind.id;
    }
    public boolean checkEquipCreation1()
    {
        if(equipment_kind_id == null) return false;
        return checkEquipCreation(MyData.getInst().myEquips[equipmentsKind().getCreation_1_id()]);
    }
    public boolean checkEquipCreation2()
    {
        if(equipment_kind_id == null) return false;
        return checkEquipCreation(MyData.getInst().myEquips[equipmentsKind().getCreation_2_id()]);
    }
    public boolean checkEquipCreation(Equipment equipment)
    {
        if (equipment == null) return false;
        ElementKind eKind = equipment.elementsKind();
        return (eKind.creation_id == element_id);

    }

    public static Equipment getInstall(JSONObject jsObj)
    {
        Equipment equipment = new Equipment();
        try {
            equipment.id = jsObj.getLong("id");
            Long equipment_kind_id = jsObj.getLong("equipments_kind");
            if(equipment_kind_id != EnumDB.none)
                equipment.equipment_kind_id = equipment_kind_id;
            Long elements_id = jsObj.getLong("elements_kind");
            if(elements_id != EnumDB.none)
                equipment.element_id = elements_id;
            Long equipment_type_id = jsObj.getLong("equip_type");
            if(equipment_type_id != EnumDB.none)
                equipment.equipment_type_id = equipment_type_id;
            if(jsObj.has("equip_name"))
                equipment.equipName = jsObj.getString("equip_name");
            JSONArray jsArrProperties = jsObj.getJSONArray("value_equip");
            Properties[] valueEquip = new Properties[MAX_ROW_PROPERTIES];
            for(int i=0;i<jsArrProperties.length();i++)
            {
                JSONObject jsPro = jsArrProperties.getJSONObject(i);
                if(jsPro != null)
                    valueEquip[i] = Properties.getInstall(jsPro);

            }
            equipment.setValueEquip(valueEquip);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return equipment;
    }
    public String toString()
    {
        return toJson().toString();
    }
    public JSONObject toJson()
    {
        JSONObject jsObj = new JSONObject();
        try
        {
            jsObj.put("id",id);

            jsObj.put("equipments_kind",(equipment_kind_id != null?equipment_kind_id:-1));
            jsObj.put("elements_kind",(element_id != null?element_id:-1));
            jsObj.put("equip_type",(equipment_type_id != null?equipment_type_id:-1));
            jsObj.put("equip_name",equipName);
            JSONArray jsArrProperties = new JSONArray();
            for(int i=0;i<getAllValueEquip().length;i++)
            {
                Properties p = getValueEquip(i);
                jsArrProperties.put(Properties.toJson(p));


            }
            jsObj.put("value_equip",jsArrProperties);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsObj;
    }
}
