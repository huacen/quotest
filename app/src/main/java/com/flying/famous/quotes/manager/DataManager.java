package com.flying.famous.quotes.manager;

import com.flying.famous.quotes.MyApp;
import com.flying.famous.quotes.db.DBManager;
import com.flying.famous.quotes.db.entity.Quotes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class DataManager {
    private static final String TAG = "DataManager";
    private Map<String, LinkedList<Quotes>> map = new HashMap();
    private Map<String, Long> map2Id = new HashMap();


    private static class Inner {
        private static final DataManager INSTANCE = new DataManager();
    }

    public static DataManager getInstance() {
        return Inner.INSTANCE;
    }

    private DataManager() {
    }

    public void copyDataBaseIfNeed(boolean force) {
        String DATABASE_PATH = "/data/data/" + MyApp.context.getPackageName() + "/databases/";
        String outFileName = DATABASE_PATH + DBManager.TABLENAME;
        File out = new File(outFileName);
        if (out.exists() && !force) {
            return;
        }
        File file = new File(DATABASE_PATH);
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                f.delete();
            }
        }
        file.mkdirs();


        try {
            InputStream myInput = MyApp.context.getAssets().open(DBManager.TABLENAME);
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void syncInit() {
//        readExcel(new File("/sdcard/xls/111.xlsx"));
//        readExcel(new File("/sdcard/xls/222.xlsx"));
//        readExcel(new File("/sdcard/xls/333.xlsx"));
//        readExcel(new File("/sdcard/xls/444.xlsx"));
//        Log.i(TAG, "type size = " + map.keySet().size());
//        for (String key : map.keySet()) {
//            Log.i(TAG, "key  = " + key + "; size" + map.get(key).size());
//            DBManager.INSTANCE().getQuotesDao().insertOrReplaceInTx(map.get(key));
//        }
//
//    }

//
//    private void readExcel(File file) {
//        try {
//            Log.v(TAG, "readExcel:" + file.getName());
//            Log.v(TAG, "exists:" + file.exists());
//            // 创建输入流，读取Excel
//            InputStream is = new FileInputStream(file);
//            // jxl提供的Workbook类
//            XSSFWorkbook wb = new XSSFWorkbook(is);
//            XSSFSheet sheet = wb.getSheetAt(0);
//            // 每个页签创建一个Sheet对象
//            // sheet.getRows()返回该页的总行数
//
//            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//
//                try {
//                    XSSFRow row = sheet.getRow(i);
//                    String type = row.getCell(0).getStringCellValue();
//                    String txt = row.getCell(1).getStringCellValue();
//                    LinkedList<Quotes> list = map.get(type);
//                    if (list == null) {
//                        Type t = new Type();
//                        t.setBg(getRandColor());
//                        t.setName(type);
//                        long id = DBManager.INSTANCE().getType().insert(t);
//                        if (id > 0) {
//                            list = new LinkedList();
//                            map2Id.put(type, id);
//                            map.put(type, list);
//                        }
//                    }
//                    if (list != null) {
//                        Quotes quotes = new Quotes();
//                        quotes.setTid(map2Id.get(type));
//                        quotes.setText(txt);
//                        list.add(quotes);
//                    }
//                } catch (Exception e) {
////                    e.printStackTrace();
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     *
     * @return String
     */
    private String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }
}
