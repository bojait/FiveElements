package com.inc.haitran.five_elements.db;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 10/9/15.
 */
public class EquipmentType extends com.inc.haitran.five_elements.dao.EquipmentType implements IFunctionDB {
    public EquipmentType()
    {

    }
    public EquipmentType(Long id, String name_en ,String name_vn,String color)
    {
        super(id,name_en,name_vn,color);
    }
    public EquipmentType(com.inc.haitran.five_elements.dao.EquipmentType item)
    {
        super(item);
    }
    public boolean equals(EquipmentType e)
    {
        if(e == null)return  false;
        if(id != e.id) return  false;
        return true;
    }
    public String toString()
    {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("name_en", name_en);
            jsonObject.put("name_vn", name_vn);
            jsonObject.put("color", color);
            return jsonObject.toString();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return "";
    }
    public static EquipmentType getInstall(JSONObject jsonObject)
    {
        EquipmentType data =  new EquipmentType();
        try
        {
            if(jsonObject.has("id"))
                data.id = jsonObject.getLong("id");
            if(jsonObject.has("name_en"))
                data.name_en = jsonObject.getString("name_en");
            if(jsonObject.has("name_vn"))
                data.name_vn = jsonObject.getString("name_vn");
            if(jsonObject.has("color"))
                data.color = jsonObject.getString("color");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Long insert(Context context) {
        return null;
    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}

