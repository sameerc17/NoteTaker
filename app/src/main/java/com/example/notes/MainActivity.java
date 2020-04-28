package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String DATABASE_NAME="noteDB.db";
    private static final String TABLE_NAME="Notes";
    private static final String COL_1="NoteTitle";
    private static final String COL_2="NoteDesc";

    ListView listView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<String> data=new ArrayList<>();

        final dbhelper db=new dbhelper(this,DATABASE_NAME,null,1);
        Cursor cursor=db.getAll(TABLE_NAME);
        final int i1=cursor.getColumnIndex(COL_1);
        final int i2=cursor.getColumnIndex(COL_2);
        if(cursor.getCount()==0){
            data.add("NO ENTRY FOUND");
        }
        else {
            while (cursor.moveToNext()){
                data.add(cursor.getString(i1));
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str=data.get(i);
                Intent intent=new Intent(MainActivity.this,noteupd.class);
                intent.putExtra("title",str);
                Cursor cursor1=db.getAll(TABLE_NAME);
                while (cursor1.moveToNext()){
                    if(cursor1.getString(i1).equals(str)){
                        intent.putExtra("desc",cursor1.getString(i2));
                        break;
                    }
                }
                startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.button);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, noteadd.class);
                startActivity(intent);
            }
        });
//        Intent i=getIntent();

    }
}
