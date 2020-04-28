package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class noteadd extends AppCompatActivity {

    private static final String DATABASE_NAME="noteDB.db";
    private static final String TABLE_NAME="Notes";

    dbhelper db=new dbhelper(this,DATABASE_NAME,null,1);
    Button button;
    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noter);
        button=(Button) findViewById(R.id.button);
        t1=(TextView) findViewById(R.id.t1);
        t2=(TextView) findViewById(R.id.t2);
        Intent intent=getIntent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.add(new notes(t1.getText().toString(),t2.getText().toString()));
                Toast.makeText(noteadd.this,"Note added successfully",Toast.LENGTH_LONG).show();
                Intent i=new Intent(noteadd.this,MainActivity.class);
                finish();
            }
        });
    }
}
