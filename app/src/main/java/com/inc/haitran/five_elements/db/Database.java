package com.inc.haitran.five_elements.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.inc.haitran.five_elements.dao.DaoMaster;
import com.inc.haitran.five_elements.dao.DaoSession;
import com.inc.haitran.five_elements.dao.ElementKindDao;
import com.inc.haitran.five_elements.dao.EquipmentDao;
import com.inc.haitran.five_elements.dao.EquipmentKindDao;
import com.inc.haitran.five_elements.dao.EquipmentTypeDao;
import com.inc.haitran.five_elements.dao.PropertiesKindDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;

/**
 * Created by apple on 9/8/15.
 */
public class Database {
    private static Database mInst;
    private SQLiteDatabase db;

    private final String DB_NAME = "five_db.sqlite";
    private DaoMaster daoMaster;
    private DaoSession daoSession;



    private static final String strElementsKindTbs = "elements_kind_tbs";
    private static final String strEquipmentsKindTbs = "equipments_kind_tbs";
    private static final String strPropertiesKindTbs = "properties_kind_tbs";
    private static final String strEquipTypeTbs = "equipments_type_tbs";

    private Map<Integer,ElementKind> elementsKindTbs;
    private Map<Integer,EquipmentKind> equipmentsKindTbs;
    private Map<Integer,PropertiesKind> propertiesKindTbs;
    private Map<Integer,EquipmentType> equipTypeTbs;
    public ElementKind getElementsKindTbs(Long index)
    {
        if(index == null )return  null;
         return getElementsKindTbs(index.intValue());

    }
    public ElementKind getElementsKindTbs(int index)
    {
        if(elementsKindTbs.containsKey(index))
            return elementsKindTbs.get(index);
        return null;
    }
    public Map<Integer,ElementKind> getAllElementsKindTbs()
    {
        return elementsKindTbs;
    }
    public EquipmentKind getEquipmentsKindTbs(Long index)
    {
        if(index == null )return  null;
        return getEquipmentsKindTbs(index.intValue());

    }
    public EquipmentKind getEquipmentsKindTbs(int index)
    {
        if(equipmentsKindTbs.containsKey(index))
            return equipmentsKindTbs.get(index);
        return null;
    }
    public Map<Integer,EquipmentKind> getAllEquipmentsKindTbs()
    {
        return equipmentsKindTbs;
    }
    public PropertiesKind getPropertiesKindTbs(Long index)
    {
        if(index == null )return  null;
        return getPropertiesKindTbs(index.intValue());

    }
    public PropertiesKind getPropertiesKindTbs(int index)
    {
        if(propertiesKindTbs.containsKey(index))
            return propertiesKindTbs.get(index);
        return null;
    }
    public Map<Integer,PropertiesKind> getAllPropertiesKindTbs()
    {
        return propertiesKindTbs;
    }
    public EquipmentType getEquipTypeTbs(Long index)
    {
        if(index == null )return  null;
        return getEquipTypeTbs(index.intValue());

    }
    public EquipmentType getEquipTypeTbs(int index)
    {
        if(equipTypeTbs.containsKey(index))
            return equipTypeTbs.get(index);
        return null;
    }
    public Map<Integer,EquipmentType> getAllEquipTypeTbs()
    {
        return equipTypeTbs;
    }
    public static Database getInst()
    {
        return mInst;
    }
    public static Database getInstall(Context context)
    {
        if(mInst == null)
            mInst = new Database(context);
        return mInst;
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
    public Database()
    {}
    public Database(Context context)
    {
        // check file and copy database
//        File pathFolder = new File(context.getApplicationInfo().dataDir + "/databases/");
//        if(!pathFolder.exists())
//            pathFolder.mkdirs();
//        File outFile = context.getDatabasePath(DB_NAME);
//        if(!outFile.exists())
//        {
//            try {
//                InputStream in = context.getAssets().open(DB_NAME);
//                OutputStream out = new FileOutputStream(outFile);
//                byte[] buffer = new byte[1024];
//                int read;
//                while((read = in.read(buffer)) != -1){
//                    out.write(buffer, 0, read);
//                }
//                in.close();
//                in = null;
//                out.flush();
//                out.close();
//                out = null;
//            }
//            catch (IOException e)
//            {
//                Log.e("tag", "Error :" + DB_NAME, e);
//                e.printStackTrace();
//            }
//        }
//        CopyFileExternalStorage(context,outFile.getPath(),"five_db_new.sql");
        try {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME,
                    null);
            db = helper.getReadableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        elementsKindTbs = new HashMap<>();
        for (ElementKind element : getAllElementKind(context)) {
            elementsKindTbs.put(element.id.intValue(), element);
        }


        equipmentsKindTbs =  new HashMap<>();
        for (EquipmentKind element : getAllEquipmentKind(context)) {
            equipmentsKindTbs.put(element.id.intValue(), element);
        }

        propertiesKindTbs =  new HashMap<>();
        for (PropertiesKind element : getAllPropertiesKind(context)) {
            propertiesKindTbs.put(element.id.intValue(), element);
        }


        equipTypeTbs =  new HashMap<>();
        for (EquipmentType element : getAllEquipmentType(context)) {
            equipTypeTbs.put(element.id.intValue(), element);
        }

    }
    public ArrayList<ElementKind> getAllElementKind(Context context)
    {
        QueryBuilder<com.inc.haitran.five_elements.dao.ElementKind> qEquipt = getDaoSession()
                .getElementKindDao().queryBuilder();
        qEquipt.where(ElementKindDao.Properties.Id.isNotNull());
        List<com.inc.haitran.five_elements.dao.ElementKind> listContactTmp = qEquipt.list();

        ArrayList<ElementKind> listElementKind = new ArrayList<ElementKind>();
        for (com.inc.haitran.five_elements.dao.ElementKind contactTmp : listContactTmp) {
            ElementKind contact = new ElementKind(contactTmp);
            listElementKind.add(contact);
        }
        return listElementKind;
    }
    public ArrayList<EquipmentKind> getAllEquipmentKind(Context context)
    {
        QueryBuilder<com.inc.haitran.five_elements.dao.EquipmentKind> qEquipt = getDaoSession()
                .getEquipmentKindDao().queryBuilder();
        qEquipt.where(EquipmentKindDao.Properties.Id.isNotNull());
        List<com.inc.haitran.five_elements.dao.EquipmentKind> listContactTmp = qEquipt.list();

        ArrayList<EquipmentKind> listEquiptKind = new ArrayList<EquipmentKind>();
        for (com.inc.haitran.five_elements.dao.EquipmentKind contactTmp : listContactTmp) {
            EquipmentKind contact = new EquipmentKind(contactTmp);
            listEquiptKind.add(contact);
        }
        return listEquiptKind;
    }
    public ArrayList<EquipmentType> getAllEquipmentType(Context context)
    {
        QueryBuilder<com.inc.haitran.five_elements.dao.EquipmentType> qEquipt = getDaoSession()
                .getEquipmentTypeDao().queryBuilder();
        qEquipt.where(EquipmentTypeDao.Properties.Id.isNotNull());
        List<com.inc.haitran.five_elements.dao.EquipmentType> listContactTmp = qEquipt.list();

        ArrayList<EquipmentType> listEquiptType = new ArrayList<EquipmentType>();
        for (com.inc.haitran.five_elements.dao.EquipmentType contactTmp : listContactTmp) {
            EquipmentType contact = new EquipmentType(contactTmp);
            listEquiptType.add(contact);
        }
        return listEquiptType;
    }
    public ArrayList<PropertiesKind> getAllPropertiesKind(Context context)
    {
        QueryBuilder<com.inc.haitran.five_elements.dao.PropertiesKind> qPro = getDaoSession()
                .getPropertiesKindDao().queryBuilder();
        qPro.where(PropertiesKindDao.Properties.Id.isNotNull());
        List<com.inc.haitran.five_elements.dao.PropertiesKind> listContactTmp = qPro.list();

        ArrayList<PropertiesKind> listContact = new ArrayList<PropertiesKind>();
        for (com.inc.haitran.five_elements.dao.PropertiesKind contactTmp : listContactTmp) {
            PropertiesKind contact = new PropertiesKind(contactTmp);
            listContact.add(contact);
        }
        return listContact;
    }
    public ArrayList<Equipment> getAllEquipment(Context context)
    {
        QueryBuilder<com.inc.haitran.five_elements.dao.Equipment> qPro = getDaoSession()
                .getEquipmentDao().queryBuilder();
        qPro.where(EquipmentDao.Properties.Id.isNotNull());
        List<com.inc.haitran.five_elements.dao.Equipment> listContactTmp = qPro.list();

        ArrayList<Equipment> listEquipment = new ArrayList<Equipment>();
        for (com.inc.haitran.five_elements.dao.Equipment contactTmp : listContactTmp) {
            Equipment contact = new Equipment(contactTmp);
            listEquipment.add(contact);
        }
        return listEquipment;
    }

    public void CopyFileExternalStorage(Context context,String path,String nameOut)
    {
        try {
            InputStream in = new FileInputStream(path);
            File outFile = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath()+"/Five_Element");
            if(!outFile.exists())
                outFile.mkdirs();

            File wFile = new File(outFile.getPath()+"/"+nameOut);
//            if(!wFile.exists())
//                wFile.mkdirs();
            OutputStream out = new FileOutputStream(wFile);
            byte[] buffer = new byte[1024];
            int read;
            while((read = in.read(buffer)) != -1){
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void getQuery()
    {
        getDaoSession().getEquipmentDao().queryBuilder().where(new WhereCondition.StringCondition("")).build();
    }
}
