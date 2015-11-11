package com.inc.haitran.five_elements.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.inc.haitran.five_elements.R;
import com.inc.haitran.five_elements.adapter.DataAdapter;
import com.inc.haitran.five_elements.adapter.DataArrayAdapter;
import com.inc.haitran.five_elements.db.Database;
import com.inc.haitran.five_elements.db.ElementKind;
import com.inc.haitran.five_elements.db.Equipment;
import com.inc.haitran.five_elements.db.Properties;
import com.inc.haitran.five_elements.db.PropertiesKind;
import com.inc.haitran.five_elements.dbview.EnumDB;
import com.inc.haitran.five_elements.utils.Constanst;
import com.inc.haitran.five_elements.utils.DataManager;
import com.inc.haitran.five_elements.utils.MyData;
import com.inc.haitran.five_elements.views.AlertView;
import com.inc.haitran.five_elements.views.DetailEquipView;
import com.inc.haitran.five_elements.views.EquipsView;
import com.inc.haitran.five_elements.views.HeaderView;
import com.inc.haitran.five_elements.views.MenuEquipView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends Activity implements View.OnClickListener {

    private EquipsView equipsView;
    private TextView txtElementKind;
    private MenuEquipView menuEquip;
    private int idEquip = EnumDB.none;
    private ListView listViewProperties;
    private  DataArrayAdapter adapter;
    private List<DataAdapter> listData = new ArrayList<DataAdapter>();
    private Map<Long,DataAdapter> mapPro = new HashMap<>();
    public CheckBox chAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        DataManager.getInstall(this);
        setContentView(R.layout.activity_main);

        HeaderView header = (HeaderView) findViewById(R.id.gr_header);
        header.setTitle(R.string.header_title_main);

        chAll = (CheckBox)findViewById(R.id.chAll);
        chAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showProperties();
            }
        });
        listViewProperties = (ListView)findViewById(R.id.listPro);
        txtElementKind = (TextView) findViewById(R.id.txt_element_kind);
        menuEquip = (MenuEquipView) findViewById(R.id.menu_equip);
        menuEquip.setOnListener(new MenuEquipView.OnClickListenerMenuResult() {
            @Override
            public void onClick(int idMenu) {
                if (idEquip == EnumDB.none) return;
                switch (idMenu) {
                    case MenuEquipView.MENU_VIEW:
                        showDetailEquip();
                        break;
                    case MenuEquipView.MENU_EDIT:
                        Intent intent = new Intent(getBaseContext(), EditEquipAct.class);
                        intent.putExtra(DataManager.key_equip_id, idEquip);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        break;
                    case MenuEquipView.MENU_CHANGE:
//                        Equipment equipment = null;
//                        Log.d("MainACt", equipment.toString());
                        showProperties();
                        break;
                    case MenuEquipView.MENU_DELETE:
                        if(MyData.getInst().myEquips[idEquip] != null) {
                            MyData.getInst().myEquips[idEquip].setActive(false);
                            MyData.getInst().myEquips[idEquip].update(getBaseContext());
                        }
                        MyData.getInst().myEquips[idEquip] = null;
                        if (equipsView != null)
                            equipsView.invalidate();
                        showProperties();
                        break;
                }
            }
        });
        equipsView = (EquipsView)findViewById(R.id.equip_view);

        equipsView.setClickListenerEquip(new EquipsView.OnClickListenerResult() {
            @Override
            public void onClick(int iEquip) {
//                Log.d(this.getClass().getSimpleName(), "you Sellect :" + iEquip );
//                showInformationEquip(iEquip);
            }

            @Override
            public void onResultPoint(int iEquip, Point p) {

                idEquip = iEquip;
//                equipsView.invalidate();
                if(idEquip >= 0 && MyData.getInst().myEquips[iEquip] == null)
                {
                    if (menuEquip != null)
                        menuEquip.stopAni();
                    Intent intent = new Intent(getBaseContext(), EditEquipAct.class);
                    intent.putExtra(DataManager.key_equip_id, idEquip);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
                else if (menuEquip != null)
                    menuEquip.moveToPoint(p, equipsView.mHeightScreen);
            }
        });

        if (MyData.getInst().myHeroElementKind  != null) {
            if(MyData.getInst().myHeroElementKind .id != EnumDB.none) {
                txtElementKind.setText(MyData.getInst().myHeroElementKind .name_vn);
                txtElementKind.setTextColor(Color.parseColor(MyData.getInst().myHeroElementKind .color));
            }
            else
            {
                txtElementKind.setText(R.string.edit_properties_default);
                txtElementKind.setTextColor(getResources().getColor(R.color.edit_text_default));

            }
        }
    }

    private void showDetailEquip()
    {
        Equipment equipment = MyData.getInst().myEquips[idEquip];
        LinearLayout ln = new LinearLayout(this);
        ln.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        DetailEquipView detailView = new DetailEquipView(this);
        ln.addView(detailView);
        detailView.showInforEquip(equipment);
        final AlertView alertView = AlertView.showAlert(this, ln, R.string.alert_tag_close);
        View.OnClickListener onClickGoup = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertView.hide();

            }
        };

    }
    @Override
    public void onResume()
    {
        super.onResume();
        if(equipsView != null)
            equipsView.invalidate();
        showProperties();
    }
    public void showProperties()
    {

        listData.clear();
        mapPro.clear();
        if(adapter == null) {
            adapter = new DataArrayAdapter(this, R.layout.data_choose_layout, listData);
            listViewProperties.setAdapter(adapter);
        }
        // add thuoc tinh bang dau
        addMap(Database.getInst().getPropertiesKindTbs(1),0);// + Sinh Luc
        addMap(Database.getInst().getPropertiesKindTbs(3),0);// + Noi Luc
        addMap(Database.getInst().getPropertiesKindTbs(5),0);// + The Luc

        addMap(Database.getInst().getPropertiesKindTbs(7), 0);// + Suc Manh
        addMap(Database.getInst().getPropertiesKindTbs(9), 0);// + Sinh Khi
        addMap(Database.getInst().getPropertiesKindTbs(8), 0);// + Than Phap
        addMap(Database.getInst().getPropertiesKindTbs(10), 0);// + Noi Cong

        addMap(Database.getInst().getPropertiesKindTbs(11),0);// + PTVL
        addMap(Database.getInst().getPropertiesKindTbs(15),0);// + Khang Bang
        addMap(Database.getInst().getPropertiesKindTbs(14),0);// + Khang Loi
        addMap(Database.getInst().getPropertiesKindTbs(13), 0);// + Khang Hoa
        addMap(Database.getInst().getPropertiesKindTbs(12), 0);// + Khang Doc

        addMap(Database.getInst().getPropertiesKindTbs(19), 0);// + Phuc Hoi
        addMap(Database.getInst().getPropertiesKindTbs(16),0);// + Lam Cham
        addMap(Database.getInst().getPropertiesKindTbs(18),0);// + Choang
        addMap(Database.getInst().getPropertiesKindTbs(17),0);// + Thoi Gian trung doc


        for(Equipment e : MyData.getInst().myEquips)
        {
            if(e != null)
            {
                boolean[] bProperties =  new boolean[Equipment.MAX_ROW_PROPERTIES];
                bProperties[0] = bProperties[2] = bProperties[4] = true;
                ElementKind heroElement = MyData.getInst().myHeroElementKind;
                ElementKind equipElement = e.elementsKind();
                int flag = 0;
                if(!chAll.isChecked()) {
                    if (heroElement != null && equipElement != null
                            && heroElement.id != EnumDB.none
                            && equipElement.id != EnumDB.none
                            && heroElement.creation_id == equipElement.id) {
                        bProperties[1] = true;
                        flag = 1;
                    }

                    if (e.checkEquipCreation1()) {
                        if (flag == 1)
                            bProperties[3] = true;
                        else
                            bProperties[1] = true;
                        flag++;
                    }
                    if (e.checkEquipCreation2()) {
                        if (flag == 2)
                            bProperties[5] = true;
                        else if (flag == 1)
                            bProperties[3] = true;
                        else
                            bProperties[1] = true;
                    }
                }
                for(int i = 0;i<Equipment.MAX_ROW_PROPERTIES ;i++)
                {
                    Properties p = e.getValueEquip(i);
                    if(p == null || p.getId() == EnumDB.none) continue;
                    if(!chAll.isChecked() && !bProperties[i]) continue;
                    PropertiesKind pro = p.getPropertiesKind();
                    if(pro.id != 0)// khac khang tat ca
                        addMap(pro,p.getValue());
                    else
                    {
                        // neu la khang tat ca
                        addMap(Database.getInst().getPropertiesKindTbs(11),p.getValue());// + PTVL
                        addMap(Database.getInst().getPropertiesKindTbs(15),p.getValue());// + PTVL
                        addMap(Database.getInst().getPropertiesKindTbs(14),p.getValue());// + PTVL
                        addMap(Database.getInst().getPropertiesKindTbs(13),p.getValue());// + PTVL
                        addMap(Database.getInst().getPropertiesKindTbs(12),p.getValue());// + PTVL
                    }

                }
            }
        }
        adapter.notifyDataSetChanged();
        listViewProperties.getLayoutParams().height = (int)(listData.size()*getResources().getDimension(R.dimen.main_height_pro));
    }
    public void addMap(PropertiesKind pro,int value)
    {
        if(pro == null) return;
        if(!mapPro.containsKey(pro.id)) {
            DataAdapter dataAdapter = new DataAdapter(pro.id.intValue(), pro.name_vn, value);
            mapPro.put(pro.id,dataAdapter);
            listData.add(dataAdapter);
        }
        else
        {
            DataAdapter dataAdapter = mapPro.get(pro.id);
            dataAdapter.value += value;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_element_kind:
                showPopupChooseElement();
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

         if(MyData.getInst().myHeroElementKind != null )
                idSelect = MyData.getInst().myHeroElementKind.id.intValue();
        showPopupChoose(listData, idSelect, new EquipsView.OnClickListenerResult() {
            @Override
            public void onClick(int iEquip) {
                ElementKind value = (ElementKind) Database.getInst().getAllElementsKindTbs().get(iEquip);
                MyData.getInst().myHeroElementKind = value;
                if (value != null) {
                    if(value.id != EnumDB.none) {
                        txtElementKind.setText(value.name_vn);
                        txtElementKind.setTextColor(Color.parseColor(value.color));
                        Constanst.setInt(MainActivity.this, "element_hero", (value != null ? value.id.intValue() : -1));
                    }
                    else
                    {
                        txtElementKind.setText(R.string.edit_properties_default);
                        txtElementKind.setTextColor(getResources().getColor(R.color.edit_text_default));

                    }
                }}
            @Override
            public void onResultPoint(int id,Point p)
            {}
        });

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
