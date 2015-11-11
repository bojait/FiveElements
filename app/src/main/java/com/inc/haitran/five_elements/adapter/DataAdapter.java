package com.inc.haitran.five_elements.adapter;

/**
 * Created by apple on 9/11/15.
 */
public class DataAdapter {
    public int id;
    public String name;
    public int value = 0;
    public boolean isShowValue = false;
    public DataAdapter(int id,String name)
    {
        this.id = id;
        this.name = name;
        isShowValue = false;
    }
    public DataAdapter(int id,String name,int value)
    {
        this.id = id;
        this.name = name;
        this.value = value;
        isShowValue = true;
    }
}
