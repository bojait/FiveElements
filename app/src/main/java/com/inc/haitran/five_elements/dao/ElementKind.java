package com.inc.haitran.five_elements.dao;

/**
 * Created by apple on 10/9/15.
 */
public class ElementKind {
    public Long id;
    public String name_en;
    public String name_vn;
    public Long creation_id;
    public Long destruction_id;
    public String color;
    public ElementKind()
    {}
    public ElementKind(Long id,String name_en,String name_vn,Long creation_id,Long destruction_id,String color)
    {
        this.id = id;
        this.name_en = name_en;
        this.name_vn = name_vn;
        this.creation_id = creation_id;
        this.destruction_id = destruction_id;
        this.color = color;
    }
    public ElementKind(ElementKind item)
    {
        UpdateInfo(item);
    }
    public void UpdateInfo(ElementKind item)
    {
        this.id = item.id;
        this.name_en = item.name_en;
        this.name_vn = item.name_vn;
        this.creation_id = item.creation_id;
        this.destruction_id = item.destruction_id;
        this.color = item.color;
    }
    public Long getId(){return this.id;}
    public void setId(Long id){this.id = id;}

    public String getName_en(){return this.name_en;}
    public void setName_en(String name_en){this.name_en = name_en;}

    public String getName_vn(){return this.name_vn;}
    public void setName_vn(String name_vn){this.name_vn = name_vn;}

    public Long getCreation_id(){return this.creation_id;}
    public void setCreation_id(Long creation_id){this.creation_id = creation_id;}

    public Long getDestruction_id(){return this.destruction_id;}
    public void setDestruction_id(Long destruction_id){this.destruction_id = destruction_id;}

    public String getColor(){return this.color;}
    public void setColor(String color){this.color = color;}
}
