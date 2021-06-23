package com.example.fitbit.Excercise;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.fitbit.R;

import java.util.ArrayList;

public class Excercise_main extends AppCompatActivity {
    ListView listView;
    ListViewAdapter listViewAdapter;
    String []title;
    String []Description;
    int []icon;
    ArrayList<Model> arrayList=new ArrayList<Model>();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_main);

        title=new String[]{"ABS Crunch","Barbell Triceps","Barbell Curl","Bench Press","Chin up","DeadLift","Hammer Curl","Incline Bench Press","Leg Curl","Plank","Pull Ups","Push Ups","Squat"};
        Description=new String[]{"Click to view Details","Click to view Details","Click to view Details","Click to view Details","Click to view Details","Click to view Details","Click to view Details","Click to view Details","Click to view Details","Click to view Details","Click to view Details","Click to view Details","Click to view Details",};
        icon=new int[]{R.drawable.abcrunch,R.drawable.barbelltricep,R.drawable.barbellcurl,R.drawable.benchpress,R.drawable.chinup,R.drawable.deadlift,R.drawable.hammercurl,R.drawable.inclinebenchpress,R.drawable.legpress,R.drawable.plank,R.drawable.pullup,R.drawable.pushup,R.drawable.squat};
        listView=findViewById(R.id.Excercise_list_view);
        for (int i=0;i<title.length;i++){
            Model model=new Model(title[i],Description[i],icon[i]);
            arrayList.add(model);
        }
        //pass adapter to list view adapter
        listViewAdapter=new ListViewAdapter(this,arrayList);
        //bind adapter to list view
        listView.setAdapter(listViewAdapter);
        //toolbar=findViewById(R.id.ExcerciseToolbar);
        //setActionBar(toolbar);
        //toolbar.showOverflowMenu();

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.excercise_menu,menu);
        MenuItem myActionMenuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    listViewAdapter.filter("");
                    listView.clearTextFilter();

                }
                else{
                    listViewAdapter.filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_setting){
            //take to settings activity
            return true;
        }
         return super.onOptionsItemSelected(item);
    }
}
