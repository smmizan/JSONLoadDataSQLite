package com.smmizan.jsondownloadoffline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;

    Button SaveButtonInSQLite, ShowSQLiteDataInListView;

    String HttpJSonURL = "http://yoursite/products.php";

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SaveButtonInSQLite = (Button)findViewById(R.id.button);

        ShowSQLiteDataInListView = (Button)findViewById(R.id.button2);

        SaveButtonInSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDataBaseBuild();

                SQLiteTableBuild();

                DeletePreviousData();

                new StoreJSonDataInToSQLiteClass(MainActivity.this).execute();

            }
        });

        ShowSQLiteDataInListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
                startActivity(intent);

            }
        });


    }

    private class StoreJSonDataInToSQLiteClass extends AsyncTask<Void, Void, Void> {

        public Context context;

        String FinalJSonResult;

        public StoreJSonDataInToSQLiteClass(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("LOADING");
            progressDialog.setMessage("Please Wait");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServiceClass httpServiceClass = new HttpServiceClass(HttpJSonURL);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult = httpServiceClass.getResponse();

                    if (FinalJSonResult != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult);
                            JSONObject jsonObject;

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempSubjectName = jsonObject.getString("products_name");

                                String tempSubjectFullForm = jsonObject.getString("products_code");

                                String tempProductsType = jsonObject.getString("products_type");

                                String tempProductsPackSize = jsonObject.getString("products_pack_size");
                                String tempProductsQuantity = jsonObject.getString("quantity");
                                String tempProductsPrice = jsonObject.getString("products_price");

                                String SQLiteDataBaseQueryHolder = "INSERT INTO "+ SQLiteDBHelper.TABLE_NAME+" (productsName,productsCode,productsType,productsPackSize,productsQuantity,productsPrice) VALUES('"+tempSubjectName+"', '"+tempSubjectFullForm+"', '"+tempProductsType+"', '"+tempProductsPackSize+"', '"+tempProductsQuantity+"', '"+tempProductsPrice+"');";

                                sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);

                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            sqLiteDatabase.close();

            progressDialog.dismiss();

            Toast.makeText(MainActivity.this,"Load Done", Toast.LENGTH_LONG).show();

        }
    }


    public void SQLiteDataBaseBuild(){

        sqLiteDatabase = openOrCreateDatabase(SQLiteDBHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ SQLiteDBHelper.TABLE_NAME+"("+ SQLiteDBHelper.COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+ SQLiteDBHelper.COL_Name +" VARCHAR, "+ SQLiteDBHelper.Col_Code +" VARCHAR, "+ SQLiteDBHelper.column_products_type+" VARCHAR, "+ SQLiteDBHelper.COL_Pack_Size+" VARCHAR, "+ SQLiteDBHelper.COL_Quantity+" VARCHAR, "+ SQLiteDBHelper.COL_Price+" VARCHAR);");

    }

    public void DeletePreviousData(){

        sqLiteDatabase.execSQL("DELETE FROM "+ SQLiteDBHelper.TABLE_NAME+"");

    }
}
