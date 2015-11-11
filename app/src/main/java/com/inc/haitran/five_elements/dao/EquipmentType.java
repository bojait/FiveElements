package com.inc.haitran.five_elements.dao;

/**
 * Created by apple on 10/9/15.
 */
public class EquipmentType {
    public Long id;
    public String name_en;
    public String name_vn;
    public String color;
    public EquipmentType()
    {

    }
    public EquipmentType(Long id, String name_en ,String name_vn,String color)
    {
        this.id = id;
        this.name_en = name_en;
        this.name_vn = name_vn;
        this.color = color;
    }
    public EquipmentType(EquipmentType item)
    {
        updateInfo(item);
    }
    public void updateInfo(EquipmentType item)
    {
        this.id = item.id;
        this.name_en = item.name_en;
        this.name_vn = item.name_vn;
        this.color = item.color;
    }
}
