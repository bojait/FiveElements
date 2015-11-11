package com.inc.haitran.five_elements.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.inc.haitran.five_elements.R;
import com.inc.haitran.five_elements.db.Database;
import com.inc.haitran.five_elements.db.Equipment;
import com.inc.haitran.five_elements.db.EquipmentKind;
import com.inc.haitran.five_elements.dbview.EnumDB;
import com.inc.haitran.five_elements.utils.DataManager;
import com.inc.haitran.five_elements.utils.MyData;


/**
 * Created by apple on 9/9/15.
 */
public class EquipsView extends View implements View.OnTouchListener{
    private Point[] points;
    private Point[][][] pointArrows;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int widthLine = 5;
    private float widthLineArrow = 20;
    private float radius    = 20;
    public float mWidthScreen,mHeightScreen;
    private int indexSelect = -1;
    private double timeTouch = 0;
    private double timeDel = 0.3f;
    private OnClickListenerResult onEventEquip;

    public EquipsView(Context context) {
        super(context);
        install(context);
    }

    public EquipsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        install(context);
    }

    public EquipsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        install(context);
    }
    private void install(Context context)
    {
        points = new Point[DataManager.MAX_EQUIP];
        pointArrows = new Point[DataManager.MAX_EQUIP][DataManager.MAX_EQUIP][3];
        mWidthScreen =  getHeight();
        mWidthScreen = getWidth();
        radius = context.getResources().getDimension(R.dimen.view_draw_radius);
        widthLineArrow = radius*0.75f;
        this.setOnTouchListener(this);

    }
    public void setClickListenerEquip(OnClickListenerResult event)
    {
        onEventEquip = event;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        float mWidthScreenTemp = MeasureSpec.getSize(widthMeasureSpec);
        float mHeightScreenTemp = MeasureSpec.getSize(heightMeasureSpec);
        if(mWidthScreenTemp != mWidthScreen || mHeightScreenTemp !=mHeightScreen)
        {
            mWidthScreen = mWidthScreenTemp;
            mHeightScreen = mHeightScreenTemp;
            setInstallPoint();
        }
    }
    private void setInstallPoint()
    {


        Point pointMid = new Point((int)(mWidthScreen/2),(int)(mHeightScreen/2));
        float mRadius = Math.min(pointMid.x,pointMid.y) - radius*1.5f;

        for(int i=0;i<points.length;i++) {
          switch (i)
          {
              case EnumDB.equip_kind_weapons:
                  points[i] = findPointIn5(pointMid,0,mRadius);
                  break;
              case EnumDB.equip_kind_hat:
                  points[i] = findPointIn5(pointMid,0,mRadius/2);
                  break;
              case EnumDB.equip_kind_glove:
                  points[i] = findPointIn5(pointMid,3,mRadius);
                  break;
              case EnumDB.equip_kind_shirt:
                  points[i] = findPointIn5(pointMid,1,mRadius/2);
                  break;
              case EnumDB.equip_kind_belt:
                  points[i] = findPointIn5(pointMid,2,mRadius/2);
                  break;
              case EnumDB.equip_kind_shoes:
                  points[i] = findPointIn5(pointMid,4,mRadius/2);
                  break;
              case EnumDB.equip_kind_rings_down:
                  points[i] = findPointIn5(pointMid,2,mRadius);
                  break;
              case EnumDB.equip_kind_ring_up:
                  points[i] = findPointIn5(pointMid,4,mRadius);
                  break;
              case EnumDB.equip_kind_pearl:
                  points[i] = findPointIn5(pointMid,3,mRadius/2);
                  break;
              case EnumDB.equip_kind_chain:
                  points[i] = findPointIn5(pointMid,1,mRadius);
                  break;
          }

        }
        if(Database.getInst() != null) {
            for (int i = 0; i < points.length; i++) {
                EquipmentKind e = Database.getInst().getEquipmentsKindTbs(i);
                if (e != null) {
                    setPointsArrow(e.getCreation_1_id(), i);
                    setPointsArrow(e.getCreation_2_id(), i);

                }

            }
        }
    }
    private void setPointsArrow(int iStart,int iEnd)
    {
        Point pHead = new Point();
        Point pLeft = new Point();
        Point pRight = new Point();
        Point pN = new Point(points[iEnd].x - points[iStart].x,points[iEnd].y - points[iStart].y) ;
        double dStartAndEnd = Math.sqrt((pN.x*pN.x)+(pN.y*pN.y));
        // phuong A(X-a)+B(Y-b)= 0;
        // x = a + pN.x * t;
        // y = b + pN.y * t;
        // (1-t)^2 = tH^2

        double tH = Math.sqrt((radius*radius)/(pN.x*pN.x + pN.y*pN.y));
        // t = 1- sqrt(tH);
        double t = 1.0-tH;
        pHead.x =(int)(points[iStart].x + pN.x*t);
        pHead.y =(int)(points[iStart].y + pN.y*t);
        double dHeadAndStart = Math.sqrt ((pHead.x-points[iStart].x)*(pHead.x-points[iStart].x)-(pHead.y-points[iStart].y)*(pHead.y-points[iStart].y));
        if(dStartAndEnd<dHeadAndStart)
        {
            pHead.x =(int)(points[iStart].x - pN.x*t);
            pHead.y =(int)(points[iStart].y - pN.y*t);
        }
        tH = Math.sqrt(((radius+widthLineArrow)*(radius+widthLineArrow))/(pN.x*pN.x + pN.y*pN.y));
        t = 1.0-tH;
        int midX =(int)(points[iStart].x + pN.x*t);
        int midY =(int)(points[iStart].y + pN.y*t);
        double dMidAndStart = Math.sqrt ((midX-points[iStart].x)*(midX-points[iStart].x)-(midY-points[iStart].y)*(midY-points[iStart].y));
        if(dStartAndEnd<dMidAndStart)
        {
            midX =(int)(points[iStart].x - pN.x*t);
            midY =(int)(points[iStart].y - pN.y*t);
        }
        pN.x = -1* (pHead.y - midY);
        pN.y = pHead.x - midX;

        float wArrow = widthLineArrow*1.25f;
        tH = Math.sqrt((wArrow*wArrow)/(pN.x*pN.x + pN.y*pN.y));
        // t = 1- sqrt(tH);
        t = 1.0-tH;
        pLeft.x =(int)(midX + pN.x*t);
        pLeft.y =(int)(midY + pN.y*t);

        t = 1.0-tH;
        pRight.x =(int)(midX - pN.x*t);
        pRight.y =(int)(midY - pN.y*t);

        pointArrows[iStart][iEnd][0] = pHead;
        pointArrows[iStart][iEnd][1] = pLeft;
        pointArrows[iStart][iEnd][2] = pRight;

    }
    private Point findPointIn5(Point pMid,int index,float mRadius)
    {
        if(mRadius <= 0) return null;
        float angle = getAngle(index);
        double aSinA = Math.sin(Math.toRadians(angle));
        int y = (int)(aSinA*mRadius);
        double aCosA = Math.cos(Math.toRadians(angle));
        int x = (int)(aCosA*mRadius);
        return new Point(pMid.x + x,pMid.y - y);
    }
    private float getAngle(int index)
    {
        switch (index)
        {
            case 0 :
                return 18;
            case 1:
                return 90;
            case 2:
                return 162;//90+72;
            case 3:
                return 234;//162+72;
            case 4:
                return 306;//234+72;

        }
        return  0;
    }
    private boolean checkButtonTouch(int x,int y,int index)
    {
        if(index <0) return false;
        Point pCenter = points[index];
        float dis = (x - pCenter.x)*(x - pCenter.x) + (y - pCenter.y)*(y - pCenter.y);
        if(dis < radius*radius)
            return true;
        return false;
    }
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                for(int i=0;i<points.length;i++) {
                    if(checkButtonTouch(x,y,i))
                    {
                        indexSelect = i;
                        timeTouch = System.currentTimeMillis();
                        Log.d(this.getClass().getSimpleName(),"index Sellect :"+indexSelect);
                        invalidate();
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!checkButtonTouch(x,y,indexSelect)) {
                    indexSelect = -1;
                }
                break;
            case MotionEvent.ACTION_UP:

                boolean iResult = false;
                if(indexSelect != -1 && onEventEquip != null) {
                    if (System.currentTimeMillis()-timeTouch < timeDel*1000) {
                        onEventEquip.onClick(indexSelect);
                        onEventEquip.onResultPoint(indexSelect,points[indexSelect]);
                        iResult = true;
                    }
                }
                if(!iResult)
                {
                    onEventEquip.onResultPoint(indexSelect,null);
                }
                timeTouch = 0;
                indexSelect = -1;
                invalidate();
                break;

        }
        return true;
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Draw line
        if(DataManager.getInst() != null && MyData.getInst().myEquips != null && points!= null )
        {
            for(int i=0;i<points.length;i++) {

                EquipmentKind e = Database.getInst().getEquipmentsKindTbs(i);
                if(e != null) {
                    EquipmentKind e1 = Database.getInst().getEquipmentsKindTbs(e.getCreation_1_id());
                    if(e1.getCls() == 0)
                        drawArrow(canvas, Color.parseColor("#52db4a"), e.getCreation_1_id(),i,
                                MyData.getInst().checkCreationEquip(e.getCreation_1_id(),i));
                    else
                        drawArrow(canvas, Color.parseColor("#1882d6"), e.getCreation_1_id(),i,
                                MyData.getInst().checkCreationEquip(e.getCreation_1_id(),i));
                    drawArrow(canvas, Color.parseColor("#ce9600"), e.getCreation_2_id(),i,
                            MyData.getInst().checkCreationEquip(e.getCreation_2_id(),i));
                }

            }
        }
        //Draw point
        if( points!= null )
        {
            for(int i=0;i<points.length;i++) {
                if(points[i] == null) continue;

                String textName = "Trang Bi";
                if(Database.getInst() != null ) {
                    EquipmentKind e = Database.getInst().getEquipmentsKindTbs(i);
                    if(e != null)
                        textName = e.name_vn;
                }
                drawPoint(canvas, i, textName) ;

            }

        }
    }
    private void drawPoint(Canvas canvas,int index,String text)
    {
        Point point = points[index];
        int color = Color.BLACK;
        int colorText = Color.WHITE;
        if(MyData.getInst() != null && MyData.getInst().myEquips != null)
        {

            Equipment e = MyData.getInst().myEquips[index];
            if(e != null && e.elementsKind() != null) {
                color = Color.parseColor(e.elementsKind().color);
                colorText = Color.BLACK;
            }
        }

        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(0);
        canvas.drawCircle(point.x, point.y, radius, paint);
        if(indexSelect == index ) {
            paint.setColor(Color.parseColor("#a1ae7e"));
            paint.setStrokeWidth(widthLine+5);
        }
        else {
            paint.setColor(Color.parseColor("#b4b4b5"));
            paint.setStrokeWidth(widthLine);
        }
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(point.x, point.y, radius, paint);


        if(text != null) {
            paint.setColor(colorText);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(getContext().getResources().getDimension(R.dimen.view_font));
//            canvas.drawText(text, point.x - radius, point.y, paint);
            // the display area.
            RectF areaRect = new RectF(point.x-radius, point.y-radius,point.x +  radius,point.y+  radius);

            String[] sText = text.split(" ");
            for(int i = 0;i<sText.length ;i++) {
                String t = sText[i];
                RectF bounds = new RectF(areaRect);
// measure text width
                bounds.right = paint.measureText(t, 0, t.length());
// measure text height
                bounds.bottom = paint.descent() - paint.ascent();

                bounds.left += (areaRect.width() - bounds.right) / 2.0f;
                bounds.top += (areaRect.height() - bounds.bottom) / 2.0f;

                float y = bounds.top - paint.ascent()+(sText.length ==1?0:10);
                y += (i-(sText.length-1))*25 ;
                canvas.drawText(t, bounds.left, y, paint);
            }
        }
    }
    private void drawArrow(Canvas canvas,int color,int iStart,int iEnd,boolean isCreation)
    {
        if(isCreation)
            paint.setColor(color);
        else
            paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.moveTo(pointArrows[iStart][iEnd][0].x, pointArrows[iStart][iEnd][0].y);
        path.lineTo(pointArrows[iStart][iEnd][1].x, pointArrows[iStart][iEnd][1].y);
        path.lineTo(pointArrows[iStart][iEnd][2].x, pointArrows[iStart][iEnd][2].y);
        path.lineTo(pointArrows[iStart][iEnd][0].x, pointArrows[iStart][iEnd][0].y);
        path.close();
        canvas.drawPath(path, paint);

        if(isCreation)
            paint.setColor(color);
        else
            paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(widthLine);
        canvas.drawLine(points[iStart].x,points[iStart].y,points[iEnd].x,points[iEnd].y,paint);



    }
    public static abstract interface OnClickListenerResult {
        public abstract void onClick(int iEquip);
        public abstract void onResultPoint(int iEquip,Point point);
    }

}
