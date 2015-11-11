package com.inc.haitran.five_elements.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inc.haitran.five_elements.R;
import com.inc.haitran.five_elements.db.Database;
import com.inc.haitran.five_elements.db.PropertiesKind;

import java.util.List;


/**
 * Created by HaiTran on 7/21/2015.
 */
public class DataArrayAdapter extends ArrayAdapter<DataAdapter> {

    Context mContext;
    int idLayout;
    public int indexChoose = -1;
    public DataArrayAdapter(Context context, int textViewResourceId,
                         List<DataAdapter> objects) {
        super(context, textViewResourceId, objects);

        mContext = context;
        idLayout = textViewResourceId;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Log.d("EditCard", "list tag:" + position);
        View view = convertView;
        final DataAdapter notice = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(idLayout, null);
            final DataViewHolder viewHolder = new DataViewHolder();
            viewHolder.setInst(view);
            viewHolder.setData(notice);

            view.setTag(viewHolder);
        }
        else {
            final DataViewHolder holder = (DataViewHolder) view.getTag();
            holder.setData(notice);
        }
        return view;
    }
    class DataViewHolder {
        TextView txtTitle;
        TextView txtValue;
        ImageView imgCheck;
        public void setInst(View v) {

            txtTitle = (TextView) v.findViewById(R.id.txt_title);
            txtValue = (TextView) v.findViewById(R.id.txt_value);
            imgCheck = (ImageView) v.findViewById(R.id.img_check);
        }
        public void setData(DataAdapter data)
        {
            txtTitle.setText(data.name);
            if(!data.isShowValue) {
                if (data.id == indexChoose)
                    imgCheck.setVisibility(View.VISIBLE);
                else
                    imgCheck.setVisibility(View.GONE);
                txtValue.setVisibility(View.GONE);
            }
            else
            {
                imgCheck.setVisibility(View.GONE);
                txtValue.setVisibility(View.VISIBLE);
                PropertiesKind pro = Database.getInst().getPropertiesKindTbs(data.id);
                if(pro != null) {
                    String strValue = (data.value > 0 ? "+" : "") + data.value + pro.toScore();
                    txtValue.setText(strValue);
                }
            }
        }

    }
}