package com.example.lotusautoconsulting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class Vehicleinfo extends AppCompatActivity {
    TextView receiver_msg,reg_num,brand,variant,model,pur_date,pur_amount,ins_exp;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicleinfo);
        receiver_msg = (TextView) findViewById(R.id.vehicle_num);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        receiver_msg.setText(str);
        try{
            db=openOrCreateDatabase("LotusAutoConsultingDB",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            //db.execSQL("Create Table Vehicle(Reg_num text,Brand text,Variant text,Model text,Purchase_date date,Purchase_amount number,Insurance_expiry_date date)");
        }catch(SQLException e)
        {
            System.out.println(e);
        }
        Cursor c1=db.rawQuery("SELECT * FROM Vehicle Where Reg_num='"+str+"'",null);
        reg_num = (TextView) findViewById(R.id.reg_no);
        brand = (TextView) findViewById(R.id.brand);
        variant = (TextView) findViewById(R.id.var);
        model = (TextView) findViewById(R.id.mod);
        pur_date = (TextView) findViewById(R.id.pur_d);
        pur_amount = (TextView) findViewById(R.id.pur_a);
        ins_exp = (TextView) findViewById(R.id.ins_d);
        c1.moveToFirst();
        while(!c1.isAfterLast())
        {
            String reg = c1.getString(0);
            String b = c1.getString(1);
            String v = c1.getString(2);
            String m = c1.getString(3);
            String pd = c1.getString(4);
            String pa = c1.getString(5);
            String ins = c1.getString(6);
            //Toast.makeText(MainActivity.this,c.getString(0)+ " "+c.getString(1),1000).show();
            reg_num.setText(reg);
            brand.setText(b);
            variant.setText(v);
            model.setText(m);
            pur_date.setText(pd);
            pur_amount.setText(pa);
            ins_exp.setText(ins);

            c1.moveToNext();
        }


    }
}
