package com.azhar.kamus.database;

import android.provider.BaseColumns;

/**
 * Created by Azhar Rivaldi on 31-03-2020.
 */

public class DatabaseContract {

    static String TABLE_ENGLISH = "tb_inggris";
    static String TABLE_INDONESIA = "tb_indonesia";

    static final class KamusColumns implements BaseColumns {

        static String KATA = "kata";
        static String DESKRIPSI = "deskripsi";

    }
}
