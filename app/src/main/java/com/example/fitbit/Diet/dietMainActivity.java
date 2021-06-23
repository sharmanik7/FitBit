package com.example.fitbit.Diet;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitbit.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class dietMainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    dietAdapter dietAdapter;
    //new changes
    //DatabaseReference fvrtref,fvrt_item;
    //String currentUserId;
    //FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar=getSupportActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_main);
        recyclerView=findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        actionBar.setTitle("Diet");
        FirebaseRecyclerOptions <DietModel> options=new FirebaseRecyclerOptions.Builder<DietModel>().setQuery(FirebaseDatabase.getInstance().getReference().child("Food"),DietModel.class).build();
        //new changes
        /*user= FirebaseAuth.getInstance().getCurrentUser();
        currentUserId=user.getUid();
        fvrtref=FirebaseDatabase.getInstance().getReference("favorites");
        fvrt_item=FirebaseDatabase.getInstance().getReference("favoriteList").child(currentUserId);*/
        dietAdapter=new dietAdapter(options);
        recyclerView.setAdapter(dietAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        dietAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dietAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.diet_menu,menu);
        MenuItem item=menu.findItem(R.id.searchBar);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    public void processSearch(String s){
        s=s.toUpperCase();
        FirebaseRecyclerOptions <DietModel> options=new FirebaseRecyclerOptions.Builder<DietModel>().setQuery(FirebaseDatabase.getInstance().getReference().child("Food").orderByChild("Name").startAt(s).endAt(s+"\uf8ff"),DietModel.class).build();
           dietAdapter=new dietAdapter(options);
           dietAdapter.startListening();
           recyclerView.setAdapter(dietAdapter);
    }
}
