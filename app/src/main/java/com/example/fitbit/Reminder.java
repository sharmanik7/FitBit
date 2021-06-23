package com.example.fitbit;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Reminder extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private FloatingActionButton mAddReminderButton;
    private Toolbar mToolbar;
    AlarmCursorAdapter mCursorAdapter;
    AlarmReminderDbHelper alarmReminderDbHelper = new AlarmReminderDbHelper(this);
    ListView reminderListView;
    ProgressDialog prgDialog;
    TextView reminderText;
    private String alarmTitle="";
    private static final int VEHICLE_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //mToolbar.setTitle("Fit360");


        reminderListView = (ListView) findViewById(R.id.list);
        reminderText=findViewById(R.id.reminderText);
        try {
            View emptyView = findViewById(R.id.empty_view);
            reminderListView.setEmptyView(emptyView);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        mCursorAdapter = new AlarmCursorAdapter(this, null);

        reminderListView.setAdapter(mCursorAdapter);

        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(Reminder.this, AddReminderActivity.class);

                Uri currentVehicleUri = ContentUris.withAppendedId(AlarmReminderContract.AlarmReminderEntry.CONTENT_URI, id);

                // Set the URI on the data field of the intent
                intent.setData(currentVehicleUri);

                startActivity(intent);

            }
        });


        mAddReminderButton =  findViewById(R.id.fab);

        mAddReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(v.getContext(), AddReminderActivity.class);
                    //intent.setFlags(FLAG_GRANT_READ_URI_PERMISSION);
                //startActivity(intent);
                addReminderTitle();
            }
        });
        getLoaderManager().initLoader(VEHICLE_LOADER, null, this);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                AlarmReminderContract.AlarmReminderEntry._ID,
                AlarmReminderContract.AlarmReminderEntry.KEY_TITLE,
                AlarmReminderContract.AlarmReminderEntry.KEY_DATE,
                AlarmReminderContract.AlarmReminderEntry.KEY_TIME,
                AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT,
                AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO,
                AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE,
                AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE

        };

        return new CursorLoader(this,   // Parent activity context
                AlarmReminderContract.AlarmReminderEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
        if(cursor.getCount()>0)
            reminderText.setVisibility(View.VISIBLE);
        else
            reminderText.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);

    }
    public void restartLoader(){
        //add loader code

        getLoaderManager().restartLoader(VEHICLE_LOADER,null,this);
    }
    public void addReminderTitle(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Set Reminder Title");
        final EditText input=new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Ok",((dialog, which) -> {
            if(input.getText().toString().isEmpty()){
                return;
            }
            alarmTitle=input.getText().toString();
            ContentValues values=new ContentValues();
            values.put(AlarmReminderContract.AlarmReminderEntry.KEY_TITLE,alarmTitle);

            values.put(AlarmReminderContract.AlarmReminderEntry.KEY_DATE,"");
            values.put(AlarmReminderContract.AlarmReminderEntry.KEY_TIME,"");
            values.put(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT,"");
            values.put(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO,"");
            values.put(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE,"");
            values.put(AlarmReminderContract.AlarmReminderEntry.KEY_TITLE,"");
            values.put(AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE,"");
            Uri newUri=getContentResolver().insert(AlarmReminderContract.AlarmReminderEntry.CONTENT_URI,values);
            restartLoader();
            if(newUri==null)
            {
                Toast.makeText(getApplicationContext(),"Setting Reminder Title Failed",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Title set Successfully",Toast.LENGTH_SHORT).show();
            }
        }));
        builder.setNegativeButton("Cancel",(dialog, which) -> {
           dialog.cancel();
        });
        builder.show();
    }

}