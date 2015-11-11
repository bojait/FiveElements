package com.inc.haitran.five_elements.db;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apple on 9/9/15.
 */
public class Properties {
    private Long id;
    private Long value;
    public void setId(int id)
    {
        this.id = new Long(id);
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getId()
    {
        if(id == null) id =  new Long(-1);
        return id;
    }
    public Properties()
    {}
    public Properties(Long id,Long value)
    {
        this.id = id;
        this.value = value;
    }
    public void setPropertiesKind(PropertiesKind propertiesKind)
    {
        this.id = propertiesKind.id;
    }
    public PropertiesKind getPropertiesKind()
    {
        return Database.getInst().getPropertiesKindTbs(id);
    }
    public Long getLongValue()
    {
        return value;
    }
    public int getValue()
    {
        if(value == null) return 0;
        return value.intValue();
    }
    public void setValue(Long value)
    {
        this.value = value;
    }
    public void setValue(int value)
    {
        this.value = new Long(value);
    }
    public static Properties getInstall(JSONObject jsObj)
    {
        Properties p = new Properties();
        try
        {
            p.id = jsObj.getLong("id");
            p.value = jsObj.getLong("value");

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return p;
    }
    public JSONObject toJson()
    {
        JSONObject jsObj = new JSONObject();
        try
        {
            jsObj.put("id",id);
            jsObj.put("value",value);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsObj;
    }
    public static JSONObject toJson(Properties pro)
    {
        if(pro != null)return pro.toJson();
        JSONObject jsObj = new JSONObject();
        try
        {
            jsObj.put("id",-1);
            jsObj.put("value",0);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsObj;
    }
}
