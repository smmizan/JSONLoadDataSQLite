package com.smmizan.jsondownloadoffline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {

    SQLiteDBHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView myListView;
    ArrayList<String> ID_Array;
    ArrayList<String> Products_Name;
    ArrayList<String> Products_Code;
    ArrayList<String> Products_Type;
    ArrayList<String> Products_Pack;
    ArrayList<String> Products_Quantity;
    ArrayList<String> Products_Price;
    ArrayList<String> listviewItem = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);


        myListView = (ListView) findViewById(R.id.listView1);

        ID_Array = new ArrayList<String>();

        Products_Name = new ArrayList<String>();

        Products_Code = new ArrayList<String>();

        Products_Type = new ArrayList<String>();

        Products_Pack = new ArrayList<String>();
        Products_Quantity = new ArrayList<String>();
        Products_Price = new ArrayList<String>();

        sqLiteHelper = new SQLiteDBHelper(this);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Toast.makeText(ShowDataActivity.this, listviewItem.get(position).toString(), Toast.LENGTH_LONG).show();

                String product = Products_Name.get(position);
                String product2 = Products_Code.get(position);
                String product3 = Products_Type.get(position);

                String product4 = Products_Pack.get(position);
                String product5 = Products_Quantity.get(position);
                String product6 = Products_Price.get(position);

                //Object dataParsing = LISTVIEW.getItemAtPosition(position);
                Intent intent = new Intent(ShowDataActivity.this,DetailsActivity.class);
                intent.putExtra("Products_Name",product);
                intent.putExtra("Products_Code",product2);
                intent.putExtra("products_type",product3);

                intent.putExtra("products_pack_size",product4);
                intent.putExtra("quantity",product5);
                intent.putExtra("products_price",product6);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ SQLiteDBHelper.TABLE_NAME+"", null);

        ID_Array.clear();
        Products_Name.clear();
        Products_Code.clear();
        Products_Type.clear();

        Products_Pack.clear();
        Products_Quantity.clear();
        Products_Price.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COL_ID)));

                //Inserting Column Name into Array to Use at ListView Click Listener Method.
                listviewItem.add(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COL_Name)));

                Products_Name.add(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COL_Name)));

                Products_Code.add(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.Col_Code)));

                Products_Type.add(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.column_products_type)));

                Products_Pack.add(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COL_Pack_Size)));
                Products_Quantity.add(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COL_Quantity)));
                Products_Price.add(cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COL_Price)));


            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(ShowDataActivity.this,

                ID_Array,
                Products_Name,
                Products_Code,
                Products_Type,
                Products_Pack,
                Products_Quantity,
                Products_Price
        );

        myListView.setAdapter(listAdapter);

        cursor.close();
    }
}
