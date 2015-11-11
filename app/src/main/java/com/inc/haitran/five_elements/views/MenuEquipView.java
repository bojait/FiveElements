package com.inc.haitran.five_elements.views;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import com.inc.haitran.five_elements.R;

/**
 * Created by apple on 9/15/15.
 */
public class MenuEquipView extends LinearLayout implements View.OnClickListener{
    private Context context;
    public float mWidth,mHeight;
    public float mWidthFram,mHeightFram;
    public float mBtnSize,mMarginLeft,mMarginAll;
    private Point[][] points0 = new Point[4][2];
    private Point[][] points1 = new Point[4][2];
    private ViewGroup[][] viewFram = new ViewGroup[2][4];
    private ViewGroup grTop,grDown;

    private final int timeAni = 200;
    private final int MaxBtn = 4;
    private OnClickListenerMenuResult onListener;
    public static final int MENU_VIEW = 0;
    public static final int MENU_EDIT = 1;
    public static final int MENU_CHANGE = 2;
    public static final int MENU_DELETE = 3;
    public MenuEquipView(Context context) {
        super(context);
        makeControllerView(context);
    }

    public MenuEquipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        makeControllerView(context);
    }

    public MenuEquipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        makeControllerView(context);
    }
    protected ViewGroup makeControllerView(Context context) {

        LinearLayout ln = new LinearLayout(getContext());
        ln.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        addView(ln);
        ViewGroup vg =  inst(context, ln);
        ln.addView(vg);

        return vg;
    }
    private ViewGroup inst(Context context,ViewGroup view)
    {
        ViewGroup v = (ViewGroup) LayoutInflater.from(context).inflate(
                R.layout.menu_equip_view_layout, view, false);
        this.context = context;
        mWidth =  context.getResources().getDimension(R.dimen.menu_equip_width);
        mHeight = context.getResources().getDimension(R.dimen.menu_equip_height);
        mWidthFram =  context.getResources().getDimension(R.dimen.menu_equip_btn_width);
        mHeightFram = context.getResources().getDimension(R.dimen.menu_equip_btn_height);
        mBtnSize = context.getResources().getDimension(R.dimen.menu_equip_btn_size);
        mMarginLeft = context.getResources().getDimension(R.dimen.menu_equip_btn_width_margin);
        mMarginAll = context.getResources().getDimension(R.dimen.menu_equip_margin);
        grTop = (ViewGroup)v.findViewById(R.id.gr_menu_top);
        grDown = (ViewGroup)v.findViewById(R.id.gr_menu_down);
        viewFram[0][0] = (ViewGroup)v.findViewById(R.id.fram_view_0);
        viewFram[1][0] = (ViewGroup)v.findViewById(R.id.fram_view_1);
        viewFram[0][1] = (ViewGroup)v.findViewById(R.id.fram_edit_0);
        viewFram[1][1] = (ViewGroup)v.findViewById(R.id.fram_edit_1);
        viewFram[0][2]  = (ViewGroup)v.findViewById(R.id.fram_change_0);
        viewFram[1][2] = (ViewGroup)v.findViewById(R.id.fram_change_1);
        viewFram[0][3] = (ViewGroup)v.findViewById(R.id.fram_delete_0);
        viewFram[1][3] = (ViewGroup)v.findViewById(R.id.fram_delete_1);


        setPointDown();
        setPointTop();
        visibleMenu();
        return v;
    }
    public void setOnListener(OnClickListenerMenuResult onListener)
    {
        this.onListener = onListener;
    }
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        // TODO Auto-generated method stub
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        float mWidthScreenTemp = MeasureSpec.getSize(widthMeasureSpec);
//        float mHeightScreenTemp = MeasureSpec.getSize(heightMeasureSpec);
//        if(mWidthScreenTemp != mWidth || mHeightScreenTemp !=mHeight)
//        {
//            mWidth = mWidthScreenTemp;
//            mHeight = mHeightScreenTemp;
//
//        }
//    }

    public void moveToPoint(Point point,float soHeight)
    {
        if(point == null)
        {
            stopAni();
            return;

        }
        ViewGroup viewGroup = null;
        if (point.y<soHeight/2) {
            grDown.setVisibility(VISIBLE);
            grTop.setVisibility(GONE);
            viewGroup = grDown;
            viewGroup.setY(point.y - mBtnSize);
            viewGroup.setX(point.x - (mWidth / 2));
            aniFramDown(true);
        }
        else
        {
            grDown.setVisibility(GONE);
            grTop.setVisibility(VISIBLE);
            viewGroup = grTop;

            viewGroup.setY(point.y - mHeight+mBtnSize);
            viewGroup.setX(point.x - (mWidth / 2));
            aniFramTop(true);
        }


    }
    public void visibleMenu()
    {
        grDown.setVisibility(GONE);
        grTop.setVisibility(GONE);
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.img_view_0:
            case R.id.img_view_1:
            case R.id.fram_view_0:
            case R.id.fram_view_1:
                stopAni();
                if( onListener != null)
                    onListener.onClick(MENU_VIEW);

                break;
            case R.id.img_edit_0:
            case R.id.img_edit_1:
            case R.id.fram_edit_0:
            case R.id.fram_edit_1:
                stopAni();
                if( onListener != null)
                    onListener.onClick(MENU_EDIT);

                break;
            case R.id.img_change_0:
            case R.id.img_change_1:
            case R.id.fram_change_0:
            case R.id.fram_change_1:
                stopAni();
                if( onListener != null)
                    onListener.onClick(MENU_CHANGE);
                break;
            case R.id.img_delete_0:
            case R.id.img_delete_1:
            case R.id.fram_delete_0:
            case R.id.fram_delete_1:
                stopAni();
                if(onListener != null)
                    onListener.onClick(MENU_DELETE);

                break;
        }
    }
    int count = 4;
    private void setPointDown()
    {
        float midWidth = mWidth/2;
        points0[0][0] = new Point((int)(midWidth - mWidthFram + mBtnSize/2 ),(int)(mBtnSize / 2));
        points0[0][1] = new Point((int)(mMarginAll),(int)(mBtnSize));

        points0[1][0] = new Point((int)(midWidth - mWidthFram + mBtnSize/2),(int)(mBtnSize / 2));
        points0[1][1] = new Point((int)(mMarginAll+ mMarginLeft),(int)(mHeight-mHeightFram -mMarginAll));

        points0[2][0] = new Point((int)(midWidth - mBtnSize/2 ),(int)(mBtnSize / 2));
        points0[2][1] = new Point((int)( mWidth - mWidthFram- mMarginLeft -mMarginAll),(int)(mHeight-mHeightFram -mMarginAll));

        points0[3][0] = new Point((int)(midWidth - mBtnSize/2),(int)(mBtnSize / 2));
        points0[3][1] = new Point((int)(mWidth - mWidthFram - mMarginAll),(int)(mBtnSize));

//        int i= 3;
        for(int i=0;i<MaxBtn;i++)
        {
            viewFram[0][i].setX(points0[i][0].x);
            viewFram[0][i].setY(points0[i][0].y);
            viewFram[0][i].setOnClickListener(this);
        }
    }
    public void aniFramDown(final boolean isShow)//down
    {
        count = 4;
        Animation.AnimationListener listener = new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                count--;
                if(count<=0)
                {
                    if(!isShow)
                        visibleMenu();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        int i = 0;
        for( i=0;i<MaxBtn;i++) {
            if (isShow)
                startAni(viewFram[0][i], points0[i][0], points0[i][1], listener);
            else
                startAni(viewFram[0][i], points0[i][1], points0[i][0], listener);
        }


    }
    private void setPointTop()
    {
        float midWidth = mWidth/2;
        points1[0][0] = new Point((int)(midWidth - mWidthFram + mBtnSize/2  ),(int)(mHeight - mHeightFram -mBtnSize/2));
        points1[0][1] = new Point((int)(mMarginAll),(int)(mHeight - mHeightFram -mBtnSize));

        points1[1][0] = new Point((int)(midWidth - mWidthFram + mBtnSize/2 ),(int)(mHeight - mHeightFram -mBtnSize/2));
        points1[1][1] = new Point((int)(mMarginAll+ mMarginLeft),(int)(mMarginAll));

        points1[2][0] = new Point((int)(midWidth - mBtnSize/2 ),(int)(mHeight - mHeightFram -mBtnSize/2));
        points1[2][1] = new Point((int)( mWidth - mWidthFram- mMarginLeft -mMarginAll),(int)(mMarginAll));

        points1[3][0] = new Point((int)(midWidth - mBtnSize/2),(int)(mHeight - mHeightFram -mBtnSize/2));
        points1[3][1] = new Point((int)(mWidth - mWidthFram -mMarginAll),(int)(mBtnSize));

//        int i= 3;
        for(int i=0;i<MaxBtn;i++)
        {
            viewFram[1][i].setX(points1[i][0].x);
            viewFram[1][i].setY(points1[i][0].y);
            viewFram[1][i].setOnClickListener(this);
        }
    }
    public void aniFramTop(final boolean isShow)//top
    {
        count = 4;
        Animation.AnimationListener listener = new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                count--;
                if(count<=0)
                {
                    if(!isShow)
                        visibleMenu();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
//        int i = 3;
        for(int i=0;i<MaxBtn;i++)
        {
            if (isShow)
                startAni(viewFram[1][i], points1[i][0], points1[i][1], listener);
            else
                startAni(viewFram[1][i], points1[i][1], points1[i][0], listener);
        }
    }
    public void stopAni()
    {
        if(grDown.getVisibility()==VISIBLE)
            aniFramDown(false);
        else
            aniFramTop(false);
    }
    public void startAni(final View v, final Point pStart, final Point pEnd , Animation.AnimationListener listener)
    {
        Animation anim=new Animation(){
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime,t);
                //0->pStart
                //1->pEnd
                //x ->?
                v.setX(pStart.x + (pEnd.x - pStart.x)*interpolatedTime);
                v.setY(pStart.y + (pEnd.y - pStart.y) * interpolatedTime);


            }};
        anim.setAnimationListener(listener);
        anim.setDuration(timeAni);
        v.startAnimation(anim);
    }
    public void setTextEidt()
    {

    }


    public static abstract interface OnClickListenerMenuResult {
        public abstract void onClick(int idMenu);
    }
}
