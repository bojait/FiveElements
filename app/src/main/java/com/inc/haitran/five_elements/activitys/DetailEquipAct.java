package com.inc.haitran.five_elements.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.inc.haitran.five_elements.R;
import com.inc.haitran.five_elements.db.Equipment;
import com.inc.haitran.five_elements.utils.DataManager;
import com.inc.haitran.five_elements.utils.MyData;
import com.inc.haitran.five_elements.views.DetailEquipView;
import com.inc.haitran.five_elements.views.HeaderView;

/**
 * Created by apple on 9/10/15.
 */
public class DetailEquipAct extends Activity  implements View.OnClickListener{
    private Equipment equipment;
    private DetailEquipView detail_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_equip_layout);

        Bundle bl = getIntent().getExtras();
        if(bl != null && bl.containsKey(DataManager.key_equip_id))
        {
            int equipId = bl.getInt(DataManager.key_equip_id);
            equipment = MyData.getInst().myEquips[equipId];
            if(equipment == null)
                finish();
        }
        else
            finish();

        detail_view = (DetailEquipView)findViewById(R.id.detail_view);
        HeaderView header = (HeaderView) findViewById(R.id.gr_header);
        header.setTitle(R.string.header_title_detail);
        header.setButtonOk(R.string.header_txt_save, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(DetailEquipAct.this,EditEquipAct.class);
                intent.putExtra(DataManager.key_equip_id,equipment.getKindId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
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

    @Override
    public void onStart()
    {
        super.onStart();
        if(equipment != null)
        {
            detail_view.showInforEquip(equipment);
        }
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {

        }
    }


}
