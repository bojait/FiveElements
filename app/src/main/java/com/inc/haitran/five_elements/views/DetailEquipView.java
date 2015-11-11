package com.inc.haitran.five_elements.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inc.haitran.five_elements.R;
import com.inc.haitran.five_elements.db.ElementKind;
import com.inc.haitran.five_elements.db.Equipment;
import com.inc.haitran.five_elements.db.Properties;
import com.inc.haitran.five_elements.dbview.EnumDB;
import com.inc.haitran.five_elements.utils.MyData;

/**
 * Created by apple on 9/15/15.
 */
public class DetailEquipView extends LinearLayout {
    private Context context;
    private Equipment equipment;
    private TextView txtEquipKind,txtEquipType,txtElementKind,txtEquipName;
    private TextView[] txtProperties;

    private ImageView imgAvatar;
    public DetailEquipView(Context context) {
        super(context);
        makeControllerView(context);
    }

    public DetailEquipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        makeControllerView(context);
    }

    public DetailEquipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        makeControllerView(context);
    }
    protected ViewGroup makeControllerView(Context context) {

        LinearLayout ln = new LinearLayout(getContext());
        ln.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        addView(ln);
        ViewGroup vg =  inst(context, ln);
        ln.addView(vg);

        return vg;
    }
    private ViewGroup inst(Context context,ViewGroup view)
    {
        ViewGroup v = (ViewGroup) LayoutInflater.from(context).inflate(
                R.layout.detail_equip_view_layout, view, false);
        this.context = context;
        txtEquipKind = (TextView) v.findViewById(R.id.txt_equip_kind);
        txtEquipName = (TextView) v.findViewById(R.id.txt_equip_name);
        txtElementKind = (TextView) v.findViewById(R.id.txt_element_kind);
        txtEquipType = (TextView) v.findViewById(R.id.txt_equip_type);
        imgAvatar = (ImageView) v.findViewById(R.id.img_avatar);

        txtProperties =  new TextView[Equipment.MAX_ROW_PROPERTIES];
        txtProperties[0] = (TextView)v.findViewById(R.id.txt_properties_kind_s1);
        txtProperties[1] = (TextView)v.findViewById(R.id.txt_properties_kind_h1);
        txtProperties[2] = (TextView)v.findViewById(R.id.txt_properties_kind_s2);
        txtProperties[3] = (TextView)v.findViewById(R.id.txt_properties_kind_h2);
        txtProperties[4] = (TextView)v.findViewById(R.id.txt_properties_kind_s3);
        txtProperties[5] = (TextView)v.findViewById(R.id.txt_properties_kind_h3);
        return v;
    }
    public void showInforEquip(Equipment equipment)
    {
        if(equipment == null) return;
        txtEquipKind.setText(txtEquipKind.getText().toString()+ equipment.equipmentsKind().name_vn);
        switch (equipment.equipmentsKind().id.intValue())
        {
            case EnumDB.equip_kind_weapons:
                imgAvatar.setImageResource(R.drawable.weapons);
                break;
            case EnumDB.equip_kind_hat:
                imgAvatar.setImageResource(R.drawable.hat);
                break;
            case EnumDB.equip_kind_glove:
                imgAvatar.setImageResource(R.drawable.glove);
                break;
            case EnumDB.equip_kind_shirt:
                imgAvatar.setImageResource(R.drawable.shirt);
                break;
            case EnumDB.equip_kind_belt:
                imgAvatar.setImageResource(R.drawable.belt);
                break;
            case EnumDB.equip_kind_shoes:
                imgAvatar.setImageResource(R.drawable.shoes);
                break;
            case EnumDB.equip_kind_rings_down:
                imgAvatar.setImageResource(R.drawable.rings_down);
                break;
            case EnumDB.equip_kind_ring_up:
                imgAvatar.setImageResource(R.drawable.ring_up);
                break;
            case EnumDB.equip_kind_pearl:
                imgAvatar.setImageResource(R.drawable.pearl);
                break;
            case EnumDB.equip_kind_chain:
                imgAvatar.setImageResource(R.drawable.chain);
                break;
        }
        int resColor = Color.WHITE;
        int resColorHide = Color.GRAY;
        if(equipment.equipType() != null) {
            resColor = Color.parseColor(equipment.equipType().color);
            if(equipment.equipType().id == EnumDB.equip_type_blue)
                resColorHide = Color.parseColor("#0d3d64");
            else if(equipment.equipType().id == EnumDB.equip_type_gold)
                resColorHide = Color.parseColor("#817834");
        }
        txtEquipKind.setTextColor(resColor);
        txtEquipName.setTextColor(resColor);
        txtEquipName.setText(equipment.equipName);

        if(equipment.elementsKind() != null)
        {
            txtElementKind.setText(txtElementKind.getText().toString()+equipment.elementsKind().name_vn);
            txtElementKind.setTextColor(Color.parseColor(equipment.elementsKind().color));
        }

        boolean[] bProperties =  new boolean[Equipment.MAX_ROW_PROPERTIES];
        bProperties[0] = bProperties[2] = bProperties[4] = true;
        ElementKind heroElement = MyData.getInst().myHeroElementKind;
        ElementKind equipElement = equipment.elementsKind();
        int flag = 0;
        if( heroElement!= null && equipElement != null
                && heroElement.id != EnumDB.none
                && equipElement.id != EnumDB.none
                && heroElement.creation_id == equipElement.id)
        {
            bProperties[1] = true;
            flag = 1;
        }

        if(equipment.checkEquipCreation1())
        {
            if(flag == 1)
                bProperties[3] = true;
            else
                bProperties[1] = true;
            flag++;
        }
        if(equipment.checkEquipCreation2())
        {
            if(flag == 2)
                bProperties[5] = true;
            else if(flag == 1)
                bProperties[3] = true;
            else
                bProperties[1] = true;
        }
        for(int i = 0;i<Equipment.MAX_ROW_PROPERTIES ;i++)
        {
            Properties pro = equipment.getValueEquip(i);
            if(pro != null && pro.getId() != EnumDB.none)
            {
                if(bProperties[i])
                    txtProperties[i].setTextColor(resColor);
                else
                    txtProperties[i].setTextColor(resColorHide);

                String strValue = pro.getPropertiesKind().name_vn+" : "+(pro.getValue()>0?"+":"")+pro.getValue() +pro.getPropertiesKind().toScore();
                txtProperties[i].setText(strValue);
            }
            else {
//                    txtProperties[i].setVisibility(View.GONE);
                txtProperties[i].setText("Chưa chọn thuộc tính...");
            }
        }

    }
}
