package com.inc.haitran.five_elements.dao;

/**
 * Created by apple on 10/9/15.
 */
public class PropertiesKind {
    public Long id;
    public String name_en;
    public String name_vn;
    public String equip_id;
    public Long element_id;
    public Long hide;
    public Long score;
    public PropertiesKind()
    {

    }
    public PropertiesKind(   Long id,String name_en, String name_vn,String equip_id,
                             Long element_id, Long hide ,Long score)
    {
        this.id = id;
        this.name_en = name_en;
        this.name_vn = name_vn;
        this.equip_id = equip_id;
        this.element_id = element_id;
        this.hide = hide;
        this.score = score;
    }
    public PropertiesKind(PropertiesKind item)
    {
        updateInfo(item);
    }
    public void updateInfo(PropertiesKind item)
    {
        this.id = item.id;
        this.name_en = item.name_en;
        this.name_vn = item.name_vn;
        this.equip_id = item.equip_id;
        this.element_id = item.element_id;
        this.hide = item.hide;
        this.score = item.score;
    }
    public int id()
    {
        if(id == null) return -1;
        return  id.intValue();
    }

}
