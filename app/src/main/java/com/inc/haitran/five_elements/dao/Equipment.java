package com.inc.haitran.five_elements.dao;

/**
 * Created by apple on 10/9/15.
 */
public class Equipment {
    public Long id;
    public String name;
    public Long equipment_kind_id;
    public Long equipment_type_id;
    public Long element_id;
    public Long properties_id_0;
    public Long value_0;
    public Long properties_id_1;
    public Long value_1;
    public Long properties_id_2;
    public Long value_2;
    public Long properties_id_3;
    public Long value_3;
    public Long properties_id_4;
    public Long value_4;
    public Long properties_id_5;
    public Long value_5;
    public Long active;
    public Equipment(){};
    public Equipment(Long id, String name, Long equipment_kind, Long equipment_type, Long element_id
            , Long properties_id_0, Long value_0, Long properties_id_1, Long value_1
            ,Long properties_id_2, Long value_2 , Long properties_id_3, Long value_3
            , Long properties_id_4, Long value_4, Long properties_id_5, Long value_5,Long active)
    {
        this.id = id;
        this.name = name;
        this.equipment_kind_id = equipment_kind;
        this.equipment_type_id = equipment_type;
        this.element_id = element_id;

        this.properties_id_0 = properties_id_0;
        this.value_0 = value_0;
        this.properties_id_1 = properties_id_1;
        this.value_1 = value_1;
        this.properties_id_2 = properties_id_2;
        this.value_2 = value_2;

        this.properties_id_3 = properties_id_3;
        this.value_3 = value_3;
        this.properties_id_4 = properties_id_4;
        this.value_4 = value_4;
        this.properties_id_5 = properties_id_5;
        this.value_5 = value_5;
        this.active = active;
    }
    public Equipment(Equipment item)
    {
        updateInfo(item);
    }
    public void updateInfo(Equipment item)
    {
        this.id = item.id;
        this.name = item.name;
        this.equipment_kind_id = item.equipment_kind_id;
        this.equipment_type_id = item.equipment_type_id;
        this.element_id = item.element_id;

        this.properties_id_0 = item.properties_id_0;
        this.value_0 = item.value_0;
        this.properties_id_1 = item.properties_id_1;
        this.value_1 = item.value_1;
        this.properties_id_2 = item.properties_id_2;
        this.value_2 = item.value_2;

        this.properties_id_3 = item.properties_id_3;
        this.value_3 = item.value_3;
        this.properties_id_4 = item.properties_id_4;
        this.value_4 = item.value_4;
        this.properties_id_5 = item.properties_id_5;
        this.value_5 = item.value_5;

        this.active =item.active;
    }
    public int id()
    {
        if(id == null) return -1;
        return id.intValue();
    }
    public boolean getActive()
    {
        if(active == null || active.intValue() == 0) return  false;
        return true;
    }
    public void setActive(boolean active)
    {
        this.active = new Long(active?1:0);
    }
}
