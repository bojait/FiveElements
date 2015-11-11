package com.inc.haitran.five_elements.db;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 10/9/15.
 */
public class EquipmentKind  extends com.inc.haitran.five_elements.dao.EquipmentKind implements IFunctionDB{
    public EquipmentKind() {
        super();
    }
    public EquipmentKind(Long id, String name_en, String name_vn, Long creation_1_id, Long creation_2_id, Long cls) {
        super(id, name_en, name_vn, creation_1_id, creation_2_id, cls);
    }

    public EquipmentKind(com.inc.haitran.five_elements.dao.EquipmentKind item) {
        super(item);
    }


    public boolean equals(EquipmentKind e)
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
            jsonObject.put("creation_1_id", getCreation_1_id());
            jsonObject.put("creation_2_id", getCreation_2_id());
            jsonObject.put("cls", getCls());
            return jsonObject.toString();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return "";
    }
    public static EquipmentKind getInstall(JSONObject jsonObject)
    {
        EquipmentKind data =  new EquipmentKind();
        try
        {
            if(jsonObject.has("id"))
                data.id = jsonObject.getLong("id");
            if(jsonObject.has("name_en"))
                data.name_en = jsonObject.getString("name_en");
            if(jsonObject.has("name_vn"))
                data.name_vn = jsonObject.getString("name_vn");
            if(jsonObject.has("creation_1_id"))
                data.setCreation_1_id(jsonObject.getInt("creation_1_id"));
            if(jsonObject.has("creation_2_id"))
                data.setCreation_2_id(jsonObject.getInt("creation_2_id"));
            if(jsonObject.has("cls"))
                data.setCls(jsonObject.getInt("cls"));
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
