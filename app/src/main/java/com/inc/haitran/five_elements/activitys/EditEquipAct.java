package com.inc.haitran.five_elements.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.inc.haitran.five_elements.R;
import com.inc.haitran.five_elements.adapter.DataAdapter;
import com.inc.haitran.five_elements.adapter.DataArrayAdapter;
import com.inc.haitran.five_elements.db.Database;
import com.inc.haitran.five_elements.db.ElementKind;
import com.inc.haitran.five_elements.db.Equipment;
import com.inc.haitran.five_elements.db.EquipmentType;
import com.inc.haitran.five_elements.db.Properties;
import com.inc.haitran.five_elements.db.PropertiesKind;
import com.inc.haitran.five_elements.dbview.EnumDB;
import com.inc.haitran.five_elements.utils.DataManager;
import com.inc.haitran.five_elements.utils.MyData;
import com.inc.haitran.five_elements.views.AlertView;
import com.inc.haitran.five_elements.views.CusEditText;
import com.inc.haitran.five_elements.views.EquipsView;
import com.inc.haitran.five_elements.views.HeaderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 9/10/15.
 */
public class EditEquipAct extends Activity  implements View.OnClickListener{
    private Equipment equipment;
    private TextView txtEquipKind,txtEquipType,txtElementKind,txtEquipName;
    private TextView[] txtProperties;
    private CusEditText[] txtPropertiesValue;
    private ViewGroup gr_properties;
    private ImageView imgAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_equip_layout);

        Bundle bl = getIntent().getExtras();
        if(bl != null && bl.containsKey(DataManager.key_equip_id))
        {
            int equipId = bl.getInt(DataManager.key_equip_id);
            equipment = MyData.getInst().myEquips[equipId];
            if(equipment == null)
            {
                equipment =  new Equipment();
                equipment.setEquipmentsKind(equipId);
            }
        }
        else
            finish();
        txtEquipKind = (TextView) findViewById(R.id.txt_equip_kind);
        txtElementKind = (TextView) findViewById(R.id.txt_element_kind);
        txtEquipType = (TextView) findViewById(R.id.txt_equip_type);
        txtEquipName = (TextView) findViewById(R.id.txt_equip_name);
        gr_properties = (ViewGroup) findViewById(R.id.gr_properties);
        imgAvatar = (ImageView) findViewById(R.id.img_avatar);

        txtProperties =  new TextView[Equipment.MAX_ROW_PROPERTIES];
        txtProperties[0] = (TextView)findViewById(R.id.txt_properties_kind_s1);
        txtProperties[1] = (TextView)findViewById(R.id.txt_properties_kind_h1);
        txtProperties[2] = (TextView)findViewById(R.id.txt_properties_kind_s2);
        txtProperties[3] = (TextView)findViewById(R.id.txt_properties_kind_h2);
        txtProperties[4] = (TextView)findViewById(R.id.txt_properties_kind_s3);
        txtProperties[5] = (TextView)findViewById(R.id.txt_properties_kind_h3);

        txtPropertiesValue =  new CusEditText[Equipment.MAX_ROW_PROPERTIES];
        txtPropertiesValue[0] = (CusEditText)findViewById(R.id.txt_value_s1);
        txtPropertiesValue[1] = (CusEditText)findViewById(R.id.txt_value_h1);
        txtPropertiesValue[2] = (CusEditText)findViewById(R.id.txt_value_s2);
        txtPropertiesValue[3] = (CusEditText)findViewById(R.id.txt_value_h2);
        txtPropertiesValue[4] = (CusEditText)findViewById(R.id.txt_value_s3);
        txtPropertiesValue[5] = (CusEditText)findViewById(R.id.txt_value_h3);
        HeaderView header = (HeaderView) findViewById(R.id.gr_header);
        header.setTitle(R.string.header_title_edit);
        header.setButtonOk(R.string.header_txt_save, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                saveEquip();
            }
        });
        header.setButtonBackImage(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        finish();
                    }
                });

    }
    public void saveEquip()
    {
        for(int i=0;i<equipment.getAllValueEquip().length;i++) {
            if(equipment.elementsKind() == null || equipment.equipmentsKind() == null)
                equipment.setValueEquip(i,null);
            else if(equipment.getValueEquip(i) != null)
            {
                Properties p = equipment.getValueEquip(i);
                int value = 0;
                try
                {
                    value = Integer.parseInt(txtPropertiesValue[i].getText().toString());
                    p.setValue(value);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                equipment.setPropertiesValueEquip(i, p);
            }
        }
        equipment.equipName = txtEquipName.getText().toString();
        Log.d("Edit Equip", "Equip info:" + equipment.toString());
        MyData.getInst().addEquipMyData(equipment);
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @Override
    public void onStart()
    {
        super.onStart();
        if(equipment != null)
        {
            if(equipment.equipmentsKind() != null)
                txtEquipKind.setText(equipment.equipmentsKind().name_vn);
            int resColor = Color.WHITE;
            if(equipment.equipType() != null) {
                resColor = Color.parseColor(equipment.equipType().color);

            }
            txtEquipKind.setTextColor(resColor);
            txtEquipName.setTextColor(resColor);
            txtEquipName.setText(equipment.equipName);

            if(equipment.elementsKind() != null)
            {
                txtElementKind.setText(equipment.elementsKind().name_vn);
                txtElementKind.setTextColor(Color.parseColor(equipment.elementsKind().color));
            }
            if(equipment.equipType() != null)
            {
                txtEquipType.setText(equipment.equipType().name_vn);
                txtEquipType.setTextColor(Color.parseColor(equipment.equipType().color));
            }
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
            checkProperties();
        }
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_element_kind:
                showPopupChooseElement();
                break;
            case R.id.btn_equip_type:
                showPopupChooseEquipType();
                break;
            case R.id.btn_properties_kind_s1:
                showPopupChooseProperties(0);
                break;
            case R.id.btn_properties_kind_h1:
                showPopupChooseProperties(1);
                break;
            case R.id.btn_properties_kind_s2:
                showPopupChooseProperties(2);
                break;
            case R.id.btn_properties_kind_h2:
                showPopupChooseProperties(3);
                break;
            case R.id.btn_properties_kind_s3:
                showPopupChooseProperties(4);
                break;
            case R.id.btn_properties_kind_h3:
                showPopupChooseProperties(5);
                break;

        }
    }
    private void showPopupChooseElement()
    {
        final ArrayList<DataAdapter> listData = new ArrayList<DataAdapter>();
        for (Map.Entry<Integer,ElementKind> entry : Database.getInst().getAllElementsKindTbs().entrySet()) {
            ElementKind value=entry.getValue();
            if(value != null)
                listData.add(new DataAdapter(value.id.intValue(),value.name_vn));

        }
        int idSelect = -1;
        if(equipment != null) {
            if(equipment.elementsKind() != null )
                idSelect = equipment.elementsKind().id.intValue();
            showPopupChoose(listData, idSelect, new EquipsView.OnClickListenerResult() {
                @Override
                public void onClick(int iEquip) {
                    ElementKind value = (ElementKind) Database.getInst().getElementsKindTbs(iEquip);
                    equipment.element_id = value.id;
                    if (value != null) {
                        if(value.id != EnumDB.none) {
                            txtElementKind.setText(value.name_vn);
                            txtElementKind.setTextColor(Color.parseColor(value.color));
                            checkProperties();

                        }
                        else
                        {
                            txtElementKind.setText(R.string.edit_properties_default);
                            txtElementKind.setTextColor(getResources().getColor(R.color.edit_text_default));
                            gr_properties.setVisibility(View.GONE);
                        }
                    }
                }
                @Override
                public void onResultPoint(int id,Point p)
                {}
            });
        }
    }
    private void showPopupChooseEquipType()
    {
        final ArrayList<DataAdapter> listData = new ArrayList<DataAdapter>();
        for (Map.Entry<Integer,EquipmentType> entry : Database.getInst().getAllEquipTypeTbs().entrySet()) {
            EquipmentType value=entry.getValue();
            if(value != null)
                listData.add(new DataAdapter(value.id.intValue(),value.name_vn));

        }
        int idSelect = -1;
        if(equipment != null) {
            if(equipment.equipType() != null )
                idSelect = equipment.equipType().id.intValue();
            showPopupChoose(listData, idSelect, new EquipsView.OnClickListenerResult() {
                @Override
                public void onClick(int iEquip) {
                    EquipmentType value = (EquipmentType) Database.getInst().getEquipTypeTbs(iEquip);
                    equipment.setEquipType( value);
                    if (value != null) {
                        if(value.id != EnumDB.none) {
                            txtEquipType.setText(value.name_vn);
                            txtEquipType.setTextColor(Color.parseColor(value.color));
                            for(CusEditText editText : txtPropertiesValue)
                                editText.resColorOn = Color.parseColor(value.color);
                        }
                        else
                        {
                            txtEquipType.setText(R.string.edit_properties_default);
                            txtEquipType.setTextColor(getResources().getColor(R.color.edit_text_default));
                            for(CusEditText editText : txtPropertiesValue)
                                editText.resColorOn = getResources().getColor(R.color.edit_text_default);
                        }
                        checkProperties();

                    }
                }
                @Override
                public void onResultPoint(int id,Point p)
                {}
            });
        }
    }
    private void showPopupChooseProperties(final int row)
    {
        boolean isHide = (row%2 !=0?true:false);
        final ArrayList<DataAdapter> listData = new ArrayList<DataAdapter>();
        for (Map.Entry<Integer,PropertiesKind> entry : Database.getInst().getAllPropertiesKindTbs().entrySet()) {
            PropertiesKind value=entry.getValue();
            if(value != null) {
                boolean iFlag = false;
                for(Properties p : equipment.getAllValueEquip())
                {
                    if(p != null && p.getPropertiesKind() == value) {
                        iFlag = true;
                        break;
                    }
                }
                if(iFlag) continue;
                if(equipment.equipType().id == EnumDB.equip_type_gold || value.id == EnumDB.none)
                    listData.add(new DataAdapter(value.id.intValue(), value.name_vn));
                else
                {
                    if(equipment.equipType().id == EnumDB.equip_type_blue && value.hide() == isHide)
                    {
                        if(value.hide()) {
                            if (value.checkInEquip(equipment.equipmentsKind().id.intValue()) &&
                                    value.checkElement(equipment.elementsKind().id.intValue()))
                                listData.add(new DataAdapter(value.id.intValue(), value.name_vn));
                        }
                        else {
                            if (value.checkInEquip(equipment.equipmentsKind().id.intValue()))
                                listData.add(new DataAdapter(value.id.intValue(), value.name_vn));
                        }
                    }
                }

            }

        }
        int idSelect = -1;
        if(equipment != null && listData.size() != 0) {
            if(equipment.getValueEquip(row) != null  )
                idSelect = equipment.getValueEquip(row).getId().intValue();
            showPopupChoose(listData, idSelect, new EquipsView.OnClickListenerResult() {
                @Override
                public void onClick(int iEquip) {
                    PropertiesKind value = (PropertiesKind) Database.getInst().getPropertiesKindTbs(iEquip);

                    equipment.setValueEquip(row, value.id);
                    if (value != null) {
                        if (value.id() != EnumDB.none) {
                            txtProperties[row].setText(value.name_vn);
                            txtProperties[row].setTextColor(Color.parseColor(equipment.equipType().color));
                        } else {
                            txtProperties[row].setText(R.string.edit_properties_default);
                            txtProperties[row].setTextColor(getResources().getColor(R.color.edit_text_default));
                        }

                    }
                }
                @Override
                public void onResultPoint(int id,Point p)
                {}
            });
        }
    }
    public void checkProperties()
    {
        if(equipment == null || equipment.equipType() == null || equipment.elementsKind() == null) return;
        if((equipment.equipType().id.intValue() == EnumDB.equip_type_blue || equipment.equipType().id.intValue() == EnumDB.equip_type_gold)
                && (equipment.elementsKind() != null && equipment.elementsKind().id.intValue() != EnumDB.none)) {
            gr_properties.setVisibility(View.VISIBLE);
            for(int i= 0;i<Equipment.MAX_ROW_PROPERTIES;i++)
            {
                txtProperties[i].setTextColor(Color.parseColor( equipment.equipType().color));
                txtPropertiesValue[i].setTextColor(Color.parseColor( equipment.equipType().color));
                txtPropertiesValue[i].resColorOn = Color.parseColor(equipment.equipType().color);
                Properties pro = equipment.getValueEquip(i);
                if(pro != null && pro.getId() != null)
                {
                    txtProperties[i].setText(pro.getPropertiesKind().name_vn);
                    txtPropertiesValue[i].setText(String.valueOf( pro.getValue()));

                }
            }
        }
        else
            gr_properties.setVisibility(View.GONE);

    }
    private void showPopupChoose(final List<DataAdapter> listData, final int indexSelect,final EquipsView.OnClickListenerResult eventResult)
    {
        ListView listView = new ListView(this);
        DataArrayAdapter adapter = new DataArrayAdapter(this,R.layout.data_choose_layout,listData);
        adapter.indexChoose = indexSelect;
        final AlertView mAlert = new AlertView(this);
        mAlert.setView(listView);
        mAlert.setNegativeButton(R.string.alert_tag_cancel,
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        mAlert.hide();
                    }
                });
        mAlert.show();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final DataAdapter item = (DataAdapter) parent
                        .getItemAtPosition(position);

             if(eventResult != null && item != null)
                 eventResult.onClick(item.id);
                mAlert.hide();

            }

        });
    }
}
