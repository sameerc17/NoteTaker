package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes.R;

public class noteupd extends AppCompatActivity {

    dbhelper db=new dbhelper(this,null,null,1);
    Button button,button2;
    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upddel);
        button=(Button) findViewById(R.id.button);
        button2=(Button) findViewById(R.id.button2);
        t1=(TextView) findViewById(R.id.t1);
        t2=(TextView) findViewById(R.id.t2);
        Intent intent=getIntent();
        t1.setText(intent.getStringExtra("title"));
        t2.setText(intent.getStringExtra("desc"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.update(t1.getText().toString(),t2.getText().toString());
                Toast.makeText(noteupd.this,"Changes updated successfully",Toast.LENGTH_LONG).show();
                Intent i=new Intent(noteupd.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete(t1.getText().toString());
                Toast.makeText(noteupd.this,"Note deleted",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
