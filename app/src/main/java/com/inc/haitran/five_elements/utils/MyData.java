package com.inc.haitran.five_elements.utils;

import android.content.Context;

import com.inc.haitran.five_elements.db.Database;
import com.inc.haitran.five_elements.db.ElementKind;
import com.inc.haitran.five_elements.db.Equipment;
import com.inc.haitran.five_elements.db.Properties;
import com.inc.haitran.five_elements.dbview.EnumDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 9/9/15.
 */
public class MyData {
    private static MyData mInst;
    public static int maxID = 0;
    public Context context;
    public ElementKind myHeroElementKind;
    public Equipment[] myEquips;
    public Map<Long,Properties> myProperties;
    public Map<Long,Equipment> mapEquip;
    public static MyData getInst()
    {
        return mInst;
    }
    public static void getInstall(Context context)
    {
        if(mInst == null)
            mInst = new MyData(context);
    }
    public MyData(Context context)
    {
        this.context = context;
        myEquips = new Equipment[DataManager.MAX_EQUIP];
        myProperties = new HashMap<>();
        mapEquip =  new HashMap<>();
        readData();
    }

    public boolean checkCreationEquip(int creation_id,int equip_id)
    {
        if(myEquips[creation_id] == null) return  false;
        Equipment creation_equipment = myEquips[creation_id];
        if(creation_equipment.getElementKind() == null) return  false;

        if(myEquips[equip_id] == null) return  false;
        Equipment equipment = myEquips[equip_id];
        if(equipment.getElementKind() == null) return  false;

        return equipment.checkEquipCreation(creation_equipment);

    }
    public void removeEquip(int idEquip)
    {
        if(idEquip == EnumDB.none) return;
        myEquips[idEquip] = null;
        saveData();
    }
    public void removeEquip(Equipment equipment)
    {
        if(equipment == null) return;
        removeEquip(equipment.id.intValue());
    }
    public void addEquipMyData(Equipment equipment)
    {
//        if(mapEquip.containsKey(maxID))
//            maxID++;
        if(equipment == null) return;
        Long id =null;
        if(equipment.id() == EnumDB.none)
        {
//            saveData();
            equipment.setActive(true);
            id = equipment.insert(context);
            equipment.id = id;
            Equipment eOld = myEquips[equipment.equipment_kind_id.intValue()];
            if( eOld!= null)
            {
                eOld.setActive(false);
                eOld.update(context);
            }
            myEquips[equipment.equipment_kind_id.intValue()] = equipment;
            return;
        }
        else
        {
            equipment.update(context);
        }

        if(equipment.id != null && !mapEquip.containsKey(equipment.id))
            mapEquip.put(equipment.id,equipment);

    }
    public void saveData()
    {
        try {
            JSONObject jsObj = new JSONObject();
            jsObj.put("maxId", maxID);
//            jsObj.put("element_hero",(myHeroElementKind != null?myHeroElementKind.id:-1));
            JSONArray jsArrMyEquip = new JSONArray();
            for (Map.Entry<Long,Equipment> entry : mapEquip.entrySet()) {
                Equipment value=entry.getValue();
                if(value != null)
                    jsArrMyEquip.put(value.toJson());

            }
            jsObj.put("equips",jsArrMyEquip);
            JSONArray jsArrEquipChoose = new JSONArray();
            for(Equipment e:myEquips)
            {
                JSONObject jsEquip = new JSONObject();
                jsEquip.put("equip_id",(e!=null?e.id:-1));
                jsArrEquipChoose.put(jsEquip);
            }
            jsObj.put("equip_choose",jsArrEquipChoose);

            DataManager.getInst().onWriteData(jsObj.toString());


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        Constanst.setInt(context,"element_hero", (myHeroElementKind != null ? myHeroElementKind.id.intValue() : -1));
    }
    public void readData()
    {
//        String strData = DataManager.getInst().onReadData();
        ArrayList<Equipment> listEquipment = Database.getInstall(context).getAllEquipment(context);
        for (Equipment e : listEquipment) {
            mapEquip.put(e.id,e);
            if(e.getActive() && e.equipment_kind_id != null)
                myEquips[e.equipment_kind_id.intValue()] = e;
        }
//        if(!TextUtils.isEmpty(strData))
//        {
//            try
//            {
//                JSONObject jsObj = new JSONObject(strData);
//                maxID = jsObj.getInt("maxId");
//                JSONArray jsArrMyEquip = jsObj.getJSONArray("equips");
//                for(int i=0;i<jsArrMyEquip.length();i++)
//                {
//                    JSONObject jsEquip = jsArrMyEquip.getJSONObject(i);
//                    Equipment equipment = Equipment.getInstall(jsEquip);
//                    if(equipment != null && equipment.id != -1)
//                        mapEquip.put(equipment.id,equipment);
//
//                }
//                JSONArray jsArrEquipChoose = jsObj.getJSONArray("equip_choose");
//                for(int i=0;i<jsArrEquipChoose.length();i++)
//                {
//                    JSONObject jsEquip = jsArrEquipChoose.getJSONObject(i);
//                    int equip_id = jsEquip.getInt("equip_id");
//                    if(equip_id!= -1)
//                        myEquips[i]=mapEquip.get(equip_id);
//
//                }
//
//
//
//            }catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
//
//        }
        int hero_element_id = Constanst.getInt(context, "element_hero", EnumDB.none);
        if (hero_element_id != EnumDB.none)
            myHeroElementKind = Database.getInst().getElementsKindTbs(hero_element_id);
    }
}
