package com.example.fitbit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.fitbit.Chatbot.chat_main_activity;
import com.example.fitbit.Diet.dietMainActivity;
import com.example.fitbit.Excercise.Excercise_main;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    /*  private Button logout;*/
    CircleImageView picClick;
    BottomNavigationView btm;
    ImageView reminder,excercise,diet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //new code

       Toolbar toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);


        nav=findViewById(R.id.navmenu);
        btm=findViewById(R.id.bottomNavigation);
        drawerLayout=findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // latest change
        btm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menuHome: startActivity(new Intent(Home.this,Home.class));
                        break;
                    case R.id.fav: startActivity(new Intent(Home.this,StepCount.class));
                        break;
                    case R.id.menuAdd: startActivity(new Intent(Home.this, chat_main_activity.class));
                        break;
                    /*case R.id.menu_report: Toast.makeText(getApplicationContext(),"report is clicked",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_reminder: Toast.makeText(getApplicationContext(),"reminder is clicked",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;*/
                }
                return true;
            }
        });
        //change end here
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.menu_home: Toast.makeText(getApplicationContext(),"Home is clicked",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_settings: Toast.makeText(getApplicationContext(),"settings is clicked",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_profile: Toast.makeText(getApplicationContext(),"Profile is clicked",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_report: Toast.makeText(getApplicationContext(),"report is clicked",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_reminder: Toast.makeText(getApplicationContext(),"reminder is clicked",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

                return true;
            }
        });

        picClick=nav.getHeaderView(0).findViewById(R.id.profile_image);
        //redirect to reminder's activity
        reminder=findViewById(R.id.createReminder);
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Reminder.class));
            }
        });
        excercise=findViewById(R.id.Show_Excercise_activity);
        excercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Excercise_main.class));
            }
        });
        diet=findViewById(R.id.showDiet);
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, dietMainActivity.class));
            }
        });
        /*logout=findViewById(R.id.Signout);
    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Home.this,MainActivity.class));*/
          /*  AppBarConfiguration appBarConfiguration =
                    new AppBarConfiguration.Builder(navController.getGraph())
                            .setDrawerLayout(drawerLayout)
                            .build();*/

        /*}
    });

*/
        user= FirebaseAuth.getInstance().getCurrentUser();
        userId=user.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("User").child(userId);
        mAuth= FirebaseAuth.getInstance();
        //profile pic on menu
        picClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile=new Intent(Home.this,profile_update.class);
                startActivity(profile);
            }
        });
    getUserInfo();



        Log.d(userId, "onCreate: ");
        /*final TextView greetingTextView=findViewById(R.id.greetings);
         */
        //Inflater

        final TextView nameTextView=nav.getHeaderView(0).findViewById(R.id.menuUserName);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name=snapshot.child("uName").getValue().toString();
                //String email=snapshot.child("email").getValue().toString();

                Log.d(name, "onDataChange: ");
                //Log.d(email, "onDataChange: ");
                //Log.d(String.valueOf(emailT), "onDataChange: ");
                try{
                    Log.d("I am in try", "onDataChange: ");
                    nameTextView.setText(name);

                }
                catch (NullPointerException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "Something wrong happened", Toast.LENGTH_LONG).show();

            }
        });



/*  final TextView emailTextView=findViewById(R.id.emailAddress);
    final TextView dobTextView=findViewById(R.id.dob);
*/
  /*
        reference.child(userId);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
               // Log.d(userProfile.uName, "onDataChange: ");
                if (userProfile != null) {
                    String uName = "Hi"+ userProfile.uName;
                    Log.d(uName, "onDataChange: ");
                String email=userProfile.email;
                String dob=userProfile.dob;
                            greetingTextView.setText("Welcome"+uName);

                    Log.d(uName, "onCreate: ");
                    try {
                        nameTextView.setText("Hi"+uName);
                    }
                    catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }
            emailTextView.setText(email);
               dobTextView.setText(dob);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "Something wrong happened", Toast.LENGTH_LONG).show();
            }
        });*/
    }

  private void getUserInfo() {
        reference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()&&snapshot.getChildrenCount()>0)
                {
                    if(snapshot.hasChild("image"))
                    {
                        String image=snapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(picClick);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

   /* public void MyFunction(View view) {
        startActivity(new Intent(Home.this,Reminder.class));
    }*/
}
