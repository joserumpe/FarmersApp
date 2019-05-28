
package com.example.guzman.dcf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by GUZMAN on 3/26/2018.
 */
@SuppressWarnings("ALL")
public class DcfDatabaseHelper extends SQLiteOpenHelper {

    private static final String dbname = "dcf.db";
    private static final String tbname = "dcf_tb";
    private static final String tbArrival = "Arrival";
    private static final String tbRegion = "Region";
    private static final String tbPlant_Details = "PlantDetails";
    private static final String tbSeeds = "Seeds";
    private static final String tbDisease = "Disease";
    private static final String tbQuestionnaire = "questionaire";

    public static final String COL1 = "id";
    public static final String COL2 = "Fname";
    public static final String COL3 = "Lname";
    public static final String COL4 = "Email";
    public static final String COL5 = "Password";

    public static final String COL6 = "Country";
    public static final String COL7 = "County";
    public static final String COL8 = "Sub_County";
    public static final String COL9 = "Town";
    public static final String COL10 = "Location";
    public static final String COL11 = "Sub_Location";
    public static final String COL12 = "Village";

    public static final String COL13 = "Identification";
    public static final String COL14 = "Name";
    public static final String COL15 = "Cultivar";

    public static final String COL16 = "Ownseed";
    public static final String COL17 = "Comercial";
    public static final String COL18 = "Relatives";

    public static final String COL27 = "Date";
    public static final String COL28 = "Time";

    public static final String COL19 = "Description";
    public static final String COL20 = "Severity";
    public static final String COL21 = "Incidence";
    public static final String COL22 = "Gender";
    public static final String COL23 = "Age";
    public static final String COL24 = "Employment";
    public static final String COL25 = "Land_Size";
    public static final String COL26 = "Important Crops";
    public static final String COL29 = "Purpose";
    public static final String COL30 = "Extend";
    public static final String COL31 = "Training";
    public static final String COL32 = "Mixing";
    public static final String COL33 = "Rotate";
    public static final String COL34 = "Virus_Diseases";
//    public static final String COL25 = "Land_Size";
//    public static final String COL26 = "Important Crops";
//    public static final String COL27 = "Date";
//    public static final String COL28 = "Time";


    public DcfDatabaseHelper(Context context) {
        super(context, dbname, null, 1);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + tbname +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Fname TEXT ,  " +
                "Lname TEXT , " +
                "Email TEXT ,  " +
                "Password TEXT )");
        db.execSQL("CREATE TABLE " + tbArrival +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Date TEXT, " +
                "TIME TEXT )");
        db.execSQL("CREATE TABLE " + tbRegion +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Country TEXT , " +
                "County TEXT , " +
                "Sub_County TEXT , " +
                "Town TEXT , " +
                "Location TEXT , " +
                "Sub_Location TEXT , " +
                "Village TEXT )");
        db.execSQL("CREATE TABLE " + tbPlant_Details +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Identification TEXT , " +
                "Name TEXT , " +
                "Cultivar TEXT )");
        db.execSQL("CREATE TABLE " + tbSeeds +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Ownseed TEXT , " +
                "Comercial TEXT , " +
                "Relatives TEXT )");
        db.execSQL("CREATE TABLE " + tbDisease +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Description TEXT , " +
                "Severity TEXT , " +
                "Incidence TEXT )");
        db.execSQL("CREATE TABLE " + tbQuestionnaire +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Gender TEXT , " +
                "Age TEXT  , " +
                "Employment TEXT , " +
                "Land_Size TEXT , " +
                "Important_Crops TEXT, " +
                "Purpose TEXT, " +
                "Extend TEXT, " +
                "Training TEXT, " +
                "Mixing TEXT, " +
                "Rotate TEXT, " +
                "Virus_Diseases TEXT )");

//        public static final String COL29 = "Purpose";
//        public static final String COL30 = "Extend";
//        public static final String COL31 = "Training";
//        public static final String COL32 = "Mixing";
//        public static final String COL33 = "Rotate";
//        public static final String COL34 = "Virus_Diseases";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tbname);
    }

    public boolean InsertData(String fname, String lname, String email, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, fname);
        contentValues.put(COL3, lname);
        contentValues.put(COL4, email);
        contentValues.put(COL5, password);

        long result = database.insert(tbname, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }


    //update region
    public boolean InsertRegion(String cntry, String cnty, String subnty, String twn, String loc, String subloc, String vill) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, cntry);
        contentValues.put(COL7, cnty);
        contentValues.put(COL8, subnty);
        contentValues.put(COL9, twn);
        contentValues.put(COL10, loc);
        contentValues.put(COL11, loc);
        contentValues.put(COL12, vill);

        long result=db.insert(tbRegion, null, contentValues);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //update arrival
    public boolean insertArrival(String dte, String tym) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL27, dte);
        contentValues.put(COL28, tym);

        long result=db.insert(tbArrival, null, contentValues);

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //    Update plant Details
//String COL13="Identification";
////    String COL14="Name";
////    String COL15="Cultivar";
    public boolean insertPlantDetails(String identity, String nme, String cultiva) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL13, identity);
        contentValues.put(COL14, nme);
        contentValues.put(COL15, cultiva);

        long result=db.insert(tbPlant_Details, null, contentValues);

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Update Seeds
    public boolean insertSeeds(String ownstock ) {
//String COL16="Ownseed";
//    String COL17="Comercial";
//    String COL18="Relatives";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL16, ownstock);
//        contentValues.put(COL17, comm);
//        contentValues.put(COL18, relative);

        long result=db.insert(tbSeeds, null, contentValues);

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //    Update Disease
    public boolean insertDisease(String descript, String severity, String incidence ) {
//        String COL19="Description";
//        String COL20="Severity";
//        String COL21="Incidence";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL19, descript);
        contentValues.put(COL20, severity);
        contentValues.put(COL21, incidence);

        long result=db.insert(tbDisease, null, contentValues);

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //    String COL22="Gender";
//    String COL23="Age";
//    String COL24="Employment";
//    String COL25="Land_Size";
//    String COL26="Important Crops";
    public boolean UpdateQuestionnaire(String gendr, String age, String employ, String landsize, String imptncrops, String puposes, String income, String training, String mix, String rotate, String virus_diseases) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL22, gendr);
        contentValues.put(COL23, age);
        contentValues.put(COL24, employ);
        contentValues.put(COL25, landsize);
        contentValues.put(COL26, imptncrops);
        contentValues.put(COL29, gendr);
        contentValues.put(COL30, age);
        contentValues.put(COL31, employ);
        contentValues.put(COL32, landsize);
        contentValues.put(COL33, imptncrops);
//    contentValues.put(COL15, employ);
//    contentValues.put(COL15, employ);

        long result=db.insert(tbQuestionnaire, null, contentValues);

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor GetData(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM  " +tbname, null);

        return cursor;
    }

    public Integer DeleteData(String email) {
        //delete data
        SQLiteDatabase db = getWritableDatabase();
        int i = db.delete(tbname, "Email=?", new String[]{email});
        return i;

    }

}
