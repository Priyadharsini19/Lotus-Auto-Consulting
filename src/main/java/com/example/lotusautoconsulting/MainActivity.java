package com.example.lotusautoconsulting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button button;
    Button btnCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCheck=(Button)findViewById(R.id.button);
        try{
            db=openOrCreateDatabase("LotusAutoConsultingDB",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            //db.execSQL("Create Table Vehicle(Reg_num text,Brand text,Variant text,Model text,Purchase_date date,Purchase_amount number,Insurance_expiry_date date)");
        }catch(SQLException e)
        {
            System.out.println(e);
        }
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText search=(EditText) findViewById(R.id.searchtext);
                String s = search.getText().toString();
                Cursor c1=db.rawQuery("SELECT * FROM Vehicle Where Reg_num='"+s+"'",null);
                Cursor c2=db.rawQuery("SELECT * FROM Vehicle Where Brand='"+s+"'",null);
                Cursor c3=db.rawQuery("SELECT * FROM Vehicle Where Variant='"+s+"'",null);
                Cursor c4=db.rawQuery("SELECT * FROM Vehicle Where Model='"+s+"'",null);
                //System.out.println("No.of rows:"+c1.getCount());
                if(c1.getCount()>0||c2.getCount()>0||c3.getCount()>0||c4.getCount()>0)
                    addAnotherListener();
                else
                    addListener();
                c1.close();
                c2.close();
                c3.close();
                c4.close();
            }
        });

    }

    void addListener() {
        // Do something in response to button
        final Context context = this;

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, AddVehicle.class);
                startActivity(intent);
                //EditText editText = (EditText) findViewById(R.id.editText);
                //String message = editText.getText().toString();
                //intent.putExtra(EXTRA_MESSAGE, message);
            }
        });
    }
    void addAnotherListener() {
        // Do something in response to button
        final Context context = this;

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, Vehicleinfo.class);
                EditText editText = (EditText) findViewById(R.id.searchtext);
                String message = editText.getText().toString();
                intent.putExtra("message_key", message);
                startActivity(intent);
            }
        });
    }
}








        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText ename=(EditText) findViewById(R.id.name);
                EditText epass=(EditText)findViewById(R.id.pass);
                String name1 = ename.toString();
                System.out.println(name1);
                String pass1 = epass.toString();
                Cursor c=db.rawQuery("SELECT * FROM User where name = '"+name1+"'",null);
                c.moveToFirst();
                while(!c.isAfterLast())
                {
                    if(name1.equals("priya"))
                        Toast.makeText(MainActivity.this, "User present", 2000).show();
                    else
                        Toast.makeText(MainActivity.this, "User not present", 2000).show();
                    c.moveToNext();
                }


                c.close();
            }
        });
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        db.close();
        super.onStop();
    }
}*/
