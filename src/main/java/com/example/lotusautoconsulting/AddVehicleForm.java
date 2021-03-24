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

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import android.os.Bundle;


public class AddVehicleForm extends AppCompatActivity {
    SQLiteDatabase db;
    Button btnInsert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle_form);
        btnInsert=(Button)findViewById(R.id.button3);
        try{
            db=openOrCreateDatabase("LotusAutoConsultingDB",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            db.execSQL("Create Table Vehicle(Reg_num text,Brand text,Variant text,Model number,Purchase_date date,Purchase_amount number,Insurance_expiry_date date)");
        }catch(SQLException e)
        {
            System.out.println(e);
        }
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText Reg_num=(EditText) findViewById(R.id.reg);
                EditText Brand=(EditText)findViewById(R.id.brand);
                EditText Variant=(EditText)findViewById(R.id.var);
                EditText Model=(EditText)findViewById(R.id.model);
                EditText Purchase_date=(EditText)findViewById(R.id.purdate);
                EditText Purchase_amount=(EditText)findViewById(R.id.puramount);
                EditText Insurance_expiry_date=(EditText)findViewById(R.id.insexp);
                ContentValues values=new ContentValues();
                values.put("Reg_num", Reg_num.getText().toString());
                values.put("Brand", Brand.getText().toString());
                values.put("Variant", Variant.getText().toString());
                values.put("Model", Model.getText().toString());
                values.put("Purchase_date", Purchase_date.getText().toString());
                values.put("Purchase_amount", Purchase_amount.getText().toString());
                values.put("Insurance_expiry_date", Insurance_expiry_date.getText().toString());
                if((db.insert("Vehicle", null, values))!=-1)
                {
                    Toast.makeText(AddVehicleForm.this, "Record Successfully Inserted", 2000).show();
                }
                else
                {
                    Toast.makeText(AddVehicleForm.this, "Insert Error", 2000).show();
                }
                Reg_num.setText("");
                Brand.setText("");
                Variant.setText("");
                Model.setText("");
                Purchase_date.setText("");
                Purchase_amount.setText("");
                Insurance_expiry_date.setText("");


                //Cursor c=db.rawQuery("SELECT * FROM ",null);
                //c.moveToFirst();
                //while(!c.isAfterLast())
                //{
                  //  Toast.makeText(MainActivity.this,c.getString(0)+ " "+c.getString(1),1000).show();
                    //c.moveToNext();
                //}
                //c.close();
            }
        });
    }
}
