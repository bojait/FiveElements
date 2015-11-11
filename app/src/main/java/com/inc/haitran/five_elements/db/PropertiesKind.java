package com.inc.haitran.five_elements.db;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by apple on 10/9/15.
 */
public class PropertiesKind extends com.inc.haitran.five_elements.dao.PropertiesKind implements IFunctionDB {
    private ArrayList<Integer> listEquip;
    public PropertiesKind()
    {
        listEquip = new ArrayList<>();
    }

    public PropertiesKind(com.inc.haitran.five_elements.dao.PropertiesKind item)
    {
        super(item);
    }
    public boolean equals(PropertiesKind e)
    {
        if(e == null)return  false;
        if(id != e.id) return  false;
        return true;
    }
    public boolean checkInEquip(int idEquip)
    {
        if(listEquip == null || listEquip.size() <= 0 || idEquip == -1) return true;
        for(Integer i : listEquip)
        {
            if(idEquip == i)
                return true;
        }
        return false;
    }
    public boolean checkElement(int id)
    {

        if(element_id == -1 || element_id == id) return true;
        return false;
    }
    public String toScore()
    {
        if(score == 0) return " %";
        if(score >= 0) return " điểm";
        return "";
    }
    public String toString()
    {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("name_en", name_en);
            jsonObject.put("name_vn", name_vn);
            jsonObject.put("equip_id", equip_id);
            jsonObject.put("element_id", element_id);
            jsonObject.put("hide", hide);
            jsonObject.put("score", score);
            return jsonObject.toString();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return "";
    }
    public boolean hide()
    {
        if(hide == null) return false;
        if(hide.intValue() == 0) return  false;
        return true;

    }
    public static PropertiesKind getInstall(JSONObject jsonObject)
    {
        PropertiesKind data =  new PropertiesKind();
        try
        {
            if(jsonObject.has("id"))
                data.id = jsonObject.getLong("id");
            if(jsonObject.has("name_vn"))
                data.name_vn = jsonObject.getString("name_vn");
            if(jsonObject.has("equip_id")) {
                data.equip_id = jsonObject.getString("equip_id");
                if(!TextUtils.isEmpty(data.equip_id)) {
                    String[] arrEquip = data.equip_id.split(",");
                    for (String s : arrEquip) {
                        try {
                            data.listEquip.add(Integer.parseInt(s));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if(jsonObject.has("element_id"))
                data.element_id = jsonObject.getLong("element_id");
            if(jsonObject.has("hide"))
                data.hide = jsonObject.getLong("hide");
            if(jsonObject.has("score"))
                data.score = jsonObject.getLong("score");
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
