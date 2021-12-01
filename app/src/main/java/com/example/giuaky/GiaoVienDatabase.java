package com.example.giuaky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GiaoVienDatabase extends SQLiteOpenHelper {
    private static String DB_NAME = "QLCB.db";
    private static int DB_VERSION = 1;


    //Define table GiaoVien
    private static final String TB_GIAOVIEN = "tbGiaoVien";
    private static final String COL_GIAOVIEN_MA = "giaovien_ma";
    private static final String COL_GIAOVIEN_MK = "giaovien_mk";
    private static final String COL_GIAOVIEN_TEN = "giaovien_ten";
    private static final String COL_GIAOVIEN_HINHANH= "giaovien_hinhanh";

    //Define table MonHoc
    private static final String TB_MONHOC = "tbMonHoc";
    private static final String COL_MONHOC_MA = "monhoc_ma";
    private static final String COL_MONHOC_TEN = "monhoc_ten";
    private static final String COL_MONHOC_HINHANH = "monhoc_hinhanh";

    //Define table PhieuChamBai
    private static final String TB_PHIEUCHAMBAI = "tbPhieuChamBai";
    private static final String COL_PCB_MA = "phieuchambai_ma";
    private static final String COL_PCB_NGAY = "phieuchambai_ngaygiao";
    private static final String COL_PCB_MAGV = "phieuchambai_magiaovien";
    private static final String COL_PCB_MAMH = "phieuchambai_mamonhoc";
    private static final String COL_PCB_SLBAI = "phieuchambai_soluongbai";
    private static final String COL_PCB_BAIDACHAM = "phieuchambai_baidacham";
    private static final String COL_PCB_BAICHUACHAM = "phieuchambai_baichuacham";


    //Define table CTThongTinChamBai
    private static final String TB_CTTTCHAMBAI = "tbCTThongTinChamBai";
    private static final String COL_CTTT_ID = "cttt_id";
    private static final String COL_CTTT_SOPHIEU = "cttt_sophieu";
    private static final String COL_CTTT_HINHANH = "cttt_hinhanh";
    private static final String COL_CTTT_MAMH = "cttt_mamh";
    private static final String COL_CTTT_BAISO = "cttt_baiso";
    private static final String COL_CTTT_DIEM = "cttt_diem";



    public GiaoVienDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_GIAOVIEN);
        db.execSQL("DROP TABLE IF EXISTS " + TB_MONHOC);
        db.execSQL("DROP TABLE IF EXISTS " + TB_PHIEUCHAMBAI);
        db.execSQL("DROP TABLE IF EXISTS " + TB_CTTTCHAMBAI);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Execute script
        db.execSQL("CREATE TABLE " + TB_GIAOVIEN + "(" +
                COL_GIAOVIEN_MA + " TEXT PRIMARY KEY NOT NULL, " +COL_GIAOVIEN_MK + " TEXT NOT NULL, "
                + COL_GIAOVIEN_TEN + " TEXT, "
                + COL_GIAOVIEN_HINHANH + " BLOG)");
        db.execSQL( "CREATE TABLE " + TB_MONHOC + "(" +
                COL_MONHOC_MA + " TEXT PRIMARY KEY NOT NULL, " + COL_MONHOC_TEN + " TEXT, "
                + COL_MONHOC_HINHANH + " BLOG)");
        db.execSQL( "CREATE TABLE " + TB_PHIEUCHAMBAI + "(" +
                COL_PCB_MA + " TEXT PRIMARY KEY NOT NULL, " + COL_PCB_NGAY+ " TEXT, "
                + COL_PCB_MAGV + " TEXT," + COL_PCB_MAMH+ " TEXT, "
                + COL_PCB_SLBAI + " TEXT,"+ COL_PCB_BAIDACHAM + " INTEGER,"+ COL_PCB_BAICHUACHAM + " INTEGER,"
                +"FOREIGN KEY ("+COL_PCB_MAGV+") REFERENCES "+TB_GIAOVIEN+"("+COL_GIAOVIEN_MA+"),"
                +"FOREIGN KEY ("+COL_PCB_MAMH+") REFERENCES "+TB_MONHOC+"("+COL_MONHOC_MA+"))");
        db.execSQL( "CREATE TABLE " + TB_CTTTCHAMBAI +
                "(" + COL_CTTT_ID+ " INTEGER PRIMARY KEY" + " AUTOINCREMENT NOT NULL, "
                + COL_CTTT_MAMH + " TEXT ,"+ COL_CTTT_HINHANH + " BLOG ," +COL_CTTT_SOPHIEU+ " TEXT, "+ COL_CTTT_BAISO + " TEXT,"
                + COL_CTTT_DIEM + " TEXT,"
                +"FOREIGN KEY ("+COL_CTTT_MAMH+") REFERENCES "+TB_MONHOC+"("+COL_MONHOC_MA+"),"
                +"FOREIGN KEY ("+COL_CTTT_SOPHIEU+") REFERENCES "+TB_PHIEUCHAMBAI+"("+COL_PCB_MA+"))");
    }

    public void getGiaoVien(ArrayList<GiaoVien> giaoViens) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_GIAOVIEN, new String[]{COL_GIAOVIEN_MA,COL_GIAOVIEN_MK, COL_GIAOVIEN_TEN,
                        COL_GIAOVIEN_HINHANH}, null, null, null, null,
                null);
        if (cursor.moveToFirst()) {
            do {
                GiaoVien gv = new GiaoVien();
                gv.setMa(cursor.getString(cursor.getColumnIndex(COL_GIAOVIEN_MA)));
                gv.setMk(cursor.getString(cursor.getColumnIndex(COL_GIAOVIEN_MK)));
                gv.setTen(cursor.getString(cursor.getColumnIndex(COL_GIAOVIEN_TEN)));
                gv.setHinhanh(cursor.getBlob(cursor.getColumnIndex(COL_GIAOVIEN_HINHANH)));
                giaoViens.add(gv);
            } while (cursor.moveToNext());
        }
    }

    public void getMonHoc(ArrayList<MonHoc> monHocs) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_MONHOC, new String[]{COL_MONHOC_MA, COL_MONHOC_TEN,
                        COL_MONHOC_HINHANH}, null, null, null, null,
                null);
        if (cursor.moveToFirst()) {
            do {
                MonHoc mh = new MonHoc();
                mh.setMa(cursor.getString(cursor.getColumnIndex(COL_MONHOC_MA)));
                mh.setTen(cursor.getString(cursor.getColumnIndex(COL_MONHOC_TEN)));
                mh.setHinhanh(cursor.getBlob(cursor.getColumnIndex(COL_MONHOC_HINHANH)));
                monHocs.add(mh);
            } while (cursor.moveToNext());
        }
    }

    public void getAllData(ArrayList<GiaoVien> giaoViens) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TB_GIAOVIEN;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                GiaoVien gv = new GiaoVien();
                gv.setMa(cursor.getString(cursor.getColumnIndex(COL_GIAOVIEN_MA)));
                gv.setMk(cursor.getString(cursor.getColumnIndex(COL_GIAOVIEN_MK)));
                gv.setTen(cursor.getString(cursor.getColumnIndex(COL_GIAOVIEN_TEN)));
                gv.setHinhanh(cursor.getBlob(cursor.getColumnIndex(COL_GIAOVIEN_HINHANH)));
                giaoViens.add(gv);
            } while (cursor.moveToNext());
        }
    }

    public void getAllMaGV(ArrayList<String> giaoViens) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select giaovien_ma from " + TB_GIAOVIEN;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String gv = cursor.getString(cursor.getColumnIndex(COL_GIAOVIEN_MA));
                giaoViens.add(gv);
            } while (cursor.moveToNext());
        }
    }

    public void getAllPassGV(ArrayList<String> giaoViens) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select giaovien_mk from " + TB_GIAOVIEN;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String gv = cursor.getString(cursor.getColumnIndex(COL_GIAOVIEN_MK));
                giaoViens.add(gv);
            } while (cursor.moveToNext());
        }
    }



    public String getTenGV(String magv) {
        String gv = new String();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select giaovien_ten from " + TB_GIAOVIEN+" where giaovien_ma ='"+magv+"'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
            gv = cursor.getString(cursor.getColumnIndex(COL_GIAOVIEN_TEN));
            } while (cursor.moveToNext());
        }
        return gv;
    }


    public String getSoluongbai(String sophieu, String magv) {
        String gv = new String();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select phieuchambai_soluongbai from " + TB_PHIEUCHAMBAI+" where (phieuchambai_ma ='"+sophieu+"' and phieuchambai_magiaovien ='"+magv+"')";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                gv = cursor.getString(cursor.getColumnIndex(COL_PCB_SLBAI));
            } while (cursor.moveToNext());
        }
        return gv;
    }



    public String getTenMH(String mamh) {
        String mh = new String();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select monhoc_ten from " + TB_MONHOC+" where monhoc_ma ='"+mamh+"'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                mh = cursor.getString(cursor.getColumnIndex(COL_MONHOC_TEN));
            } while (cursor.moveToNext());
        }
        return mh;
    }

    public void getAllDatamh(ArrayList<MonHoc> monHocs) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TB_MONHOC ;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                MonHoc mh = new MonHoc();
                mh.setMa(cursor.getString(cursor.getColumnIndex(COL_MONHOC_MA)));
                mh.setTen(cursor.getString(cursor.getColumnIndex(COL_MONHOC_TEN)));
                mh.setHinhanh(cursor.getBlob(cursor.getColumnIndex(COL_MONHOC_HINHANH)));
                monHocs.add(mh);
            } while (cursor.moveToNext());
        }
    }

    public void getAllDataPCB(ArrayList<PhieuChamBai> phieuChamBaiss) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TB_PHIEUCHAMBAI ;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                PhieuChamBai mh = new PhieuChamBai();
                mh.setMa(cursor.getString(cursor.getColumnIndex(COL_PCB_MA)));
                mh.setNgaygb(cursor.getString(cursor.getColumnIndex(COL_PCB_NGAY)));
                mh.setMagv(cursor.getString(cursor.getColumnIndex(COL_PCB_MAGV)));
                mh.setMamh(cursor.getString(cursor.getColumnIndex(COL_PCB_MAMH)));
                mh.setSoluongbai(cursor.getString(cursor.getColumnIndex(COL_PCB_SLBAI)));
                mh.setBaidacham(cursor.getInt(cursor.getColumnIndex(COL_PCB_BAIDACHAM)));
                mh.setBaichuacham(cursor.getInt(cursor.getColumnIndex(COL_PCB_BAICHUACHAM)));
                phieuChamBaiss.add(mh);
            } while (cursor.moveToNext());
        }
    }

    public void save(GiaoVien gv) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_GIAOVIEN_MA, gv.getMa());
        values.put(COL_GIAOVIEN_MK, gv.getMk());
        values.put(COL_GIAOVIEN_TEN, gv.getTen());
        values.put(COL_GIAOVIEN_HINHANH, gv.getHinhanh());
        db.insert(TB_GIAOVIEN, null, values);
        db.close();
    }

    public void delete(String maGV) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM tbGiaoVien WHERE giaovien_ma = '"+ maGV+"'";
        db.execSQL(query);
    }


    public void savemh(MonHoc mh) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MONHOC_MA, mh.getMa());
        values.put(COL_MONHOC_TEN, mh.getTen());
        values.put(COL_MONHOC_HINHANH, mh.getHinhanh());
        db.insert(TB_MONHOC, null, values);
        db.close();
    }

    public void deletemh(String maMH) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM tbMonHoc WHERE monhoc_ma = '"+ maMH+"'";
        db.execSQL(query);
    }

    public void getAllMaMH(ArrayList<String> mh) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select monhoc_ma from " + TB_MONHOC;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String gv = cursor.getString(cursor.getColumnIndex(COL_MONHOC_MA));
                mh.add(gv);
            } while (cursor.moveToNext());
        }
    }

    public void savePCB(PhieuChamBai pcb) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PCB_MA, pcb.getMa());
        values.put(COL_PCB_NGAY, String.valueOf(pcb.getNgaygb()));
        values.put(COL_PCB_MAGV, pcb.getMagv());
        values.put(COL_PCB_MAMH, pcb.getMamh());
        values.put(COL_PCB_SLBAI, pcb.getSoluongbai());
        values.put(COL_PCB_BAIDACHAM, pcb.getBaidacham());
        values.put(COL_PCB_BAICHUACHAM, pcb.getBaichuacham());
        db.insert(TB_PHIEUCHAMBAI, null, values);
        db.close();
    }

    public void deletePCB(String maPhieu) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM tbPhieuChamBai WHERE phieuchambai_ma = '"+ maPhieu+"'";
        db.execSQL(query);
    }


    public void getSLphieu(ArrayList<String> giaoViens, String magv) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select phieuchambai_ma FROM tbPhieuChamBai WHERE phieuchambai_magiaovien = '"+ magv+"'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String mh = cursor.getString(cursor.getColumnIndex(COL_PCB_MA));
                giaoViens.add(mh);
            } while (cursor.moveToNext());
        }
    }

    public void getAllDataCB(ArrayList<ChamBai> giaoViens, String magv) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select phieuchambai_ma, phieuchambai_mamonhoc,phieuchambai_soluongbai,phieuchambai_baidacham,phieuchambai_baichuacham from tbPhieuChamBai WHERE phieuchambai_magiaovien = '"+ magv+"'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ChamBai gv = new ChamBai();
                gv.setSophieu(cursor.getString(cursor.getColumnIndex(COL_PCB_MA)));
                gv.setMamh(cursor.getString(cursor.getColumnIndex(COL_PCB_MAMH)));
                gv.setSoluongbai(cursor.getString(cursor.getColumnIndex(COL_PCB_SLBAI)));
                gv.setBaidacham(cursor.getInt(cursor.getColumnIndex(COL_PCB_BAIDACHAM)));
                gv.setBaichuacham(cursor.getInt(cursor.getColumnIndex(COL_PCB_BAICHUACHAM)));
                giaoViens.add(gv);
            } while (cursor.moveToNext());
        }
    }

    public void updateDiemPhieuChamBai(PhieuChamBai ct) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Update " + TB_PHIEUCHAMBAI + " set ";
        sql += COL_PCB_BAIDACHAM + " = '" + ct.getBaidacham() + "' ,";
        sql += COL_PCB_BAICHUACHAM + " = '" + ct.getBaichuacham() + "' ";
        sql += " where "+ COL_PCB_MA +" = '" + ct.getMa() +"'";
        db.execSQL(sql);
    }

    public void updateChamBai(ChiTietTTCB ct) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Update " + TB_CTTTCHAMBAI + " set ";
        sql += COL_CTTT_DIEM + " = '" + ct.getDiem() + "' ";
        sql += " where ("+ COL_CTTT_MAMH +" = '" + ct.getMamh() +"' and cttt_sophieu = '"+ ct.getSophieu()+"' and cttt_baiso = '"+ct.getBaiso() +"')";
        db.execSQL(sql);
    }

    public void getAllDataChamBai(ArrayList<ChiTietTTCB> ct, String sophieu, String mamh) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TB_CTTTCHAMBAI +" WHERE ( cttt_sophieu = '"+ sophieu+"' and cttt_mamh = '"+mamh+"')";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ChiTietTTCB gv = new ChiTietTTCB();
                gv.setMamh(cursor.getString(cursor.getColumnIndex(COL_CTTT_MAMH)));
                gv.setHinhanh(cursor.getBlob(cursor.getColumnIndex(COL_CTTT_HINHANH)));
                gv.setSophieu(cursor.getString(cursor.getColumnIndex(COL_CTTT_SOPHIEU)));
                gv.setBaiso(cursor.getString(cursor.getColumnIndex(COL_CTTT_BAISO)));
                gv.setDiem(cursor.getString(cursor.getColumnIndex(COL_CTTT_DIEM)));
                ct.add(gv);
            } while (cursor.moveToNext());
        }
    }
    public void deleteChamBai(String maPhieu, String mamh) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TB_CTTTCHAMBAI +" WHERE ( cttt_sophieu = '"+ maPhieu+"' and cttt_mamh = '"+mamh+"')";
        db.execSQL(query);
    }

    public int getBaiDC(String maphieu) {
        int mh = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select phieuchambai_baidacham from " + TB_PHIEUCHAMBAI+" where phieuchambai_ma ='"+maphieu+"'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                mh = cursor.getInt(cursor.getColumnIndex(COL_PCB_BAIDACHAM));
            } while (cursor.moveToNext());
        }
        return mh;
    }

    public int getBaiCC(String maphieu) {
        int mh = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select phieuchambai_baichuacham from " + TB_PHIEUCHAMBAI+" where phieuchambai_ma ='"+maphieu+"'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                mh = cursor.getInt(cursor.getColumnIndex(COL_PCB_BAICHUACHAM));
            } while (cursor.moveToNext());
        }
        return mh;
    }


    public void getAllMagv(ArrayList<String> giaoViens, String magv) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select phieuchambai_ma from " + TB_PHIEUCHAMBAI+" where phieuchambai_magiaovien ='"+magv+"'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String gv = cursor.getString(cursor.getColumnIndex(COL_PCB_MAGV));
                giaoViens.add(gv);
            } while (cursor.moveToNext());
        }
    }


    public void InsertChambai(String mamh,String mahs , String diem) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO tbCTThongTinChamBai" +
                "(cttt_mamh,cttt_mahs,cttt_diem) values ('" +mamh+"','"+mahs+"','"+diem+"')";
        db.execSQL(sql);
    }

    public void XoaBaiTrung(String mamh) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM tbCTThongTinChamBai WHERE ( cttt_mamh = '"+ mamh+"')";
        db.execSQL(query);
    }

    public void saveCT(ChiTietTTCB gv) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CTTT_MAMH, gv.getMamh());
        values.put(COL_CTTT_HINHANH, gv.getHinhanh());
        values.put(COL_CTTT_SOPHIEU, gv.getSophieu());
        values.put(COL_CTTT_BAISO, gv.getBaiso());
        values.put(COL_CTTT_DIEM, gv.getDiem());
        db.insert(TB_CTTTCHAMBAI, null, values);
        db.close();
    }
    public void saveCTct(String mamh, byte hinhanh, String sophieu, String baiso, String diem) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CTTT_MAMH, mamh);
        values.put(COL_CTTT_HINHANH, hinhanh);
        values.put(COL_CTTT_SOPHIEU, sophieu);
        values.put(COL_CTTT_BAISO, baiso);
        values.put(COL_CTTT_DIEM, diem);
        db.insert(TB_CTTTCHAMBAI, null, values);
        db.close();
    }

}
