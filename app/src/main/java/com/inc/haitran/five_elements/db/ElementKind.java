package com.inc.haitran.five_elements.db;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 10/9/15.
 */
public class ElementKind extends com.inc.haitran.five_elements.dao.ElementKind implements IFunctionDB{
    public ElementKind()
    {}
    public ElementKind(com.inc.haitran.five_elements.dao.ElementKind item)
    {
        super(item);
    }
    public boolean equals(ElementKind e)
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
            jsonObject.put("creation_id", creation_id);
            jsonObject.put("destruction_id", destruction_id);
            jsonObject.put("color", color);
            return jsonObject.toString();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return "";
    }
    public static ElementKind getInstall(JSONObject jsonObject)
    {
        if (jsonObject == null) {
            return null;
        }

        Gson gson = new GsonBuilder().serializeNulls().create();
        com.inc.haitran.five_elements.dao.ElementKind elementKind = gson.fromJson(
                jsonObject.toString(), com.inc.haitran.five_elements.dao.ElementKind.class);

        ElementKind data = new ElementKind(elementKind);

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
