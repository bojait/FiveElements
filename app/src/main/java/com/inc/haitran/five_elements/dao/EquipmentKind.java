package com.inc.haitran.five_elements.dao;

/**
 * Created by apple on 10/9/15.
 */
public class EquipmentKind {
    public Long id;
    public String name_en;
    public String name_vn;
    public Long creation_1_id;
    public Long creation_2_id;
    public Long cls;
    public EquipmentKind()
    {}
    public EquipmentKind(Long id, String name_en, String name_vn, Long creation_1_id, Long creation_2_id,Long cls)
    {
        this.id = id;
        this.name_en = name_en;
        this.name_vn = name_vn;
        this.creation_1_id = creation_1_id;
        this.creation_2_id = creation_2_id;
        this.cls = cls;
    }
    public EquipmentKind(EquipmentKind item)
    {
        updateInfo(item);
    }
    public void updateInfo(EquipmentKind item)
    {
        this.id = item.id;
        this.name_en = item.name_en;
        this.name_vn = item.name_vn;
        this.creation_1_id = item.creation_1_id;
        this.creation_2_id = item.creation_2_id;
        this.cls = item.cls;
    }
    public int getCreation_1_id()
    {
        if(creation_1_id == null)return  -1;
        return creation_1_id.intValue();
    }
    public void setCreation_1_id(int value)
    {
        creation_1_id = new Long(value);
    }
    public int getCreation_2_id()
    {
        if(creation_2_id == null)return  -1;
        return creation_2_id.intValue();
    }
    public void setCreation_2_id(int value)
    {
        creation_2_id = new Long(value);
    }
    public int getCls()
    {
        if(cls == null)return  -1;
        return cls.intValue();
    }
    public void setCls(int value)
    {
        cls = new Long(value);
    }
}
