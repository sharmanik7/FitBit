package com.example.fitbit.Excercise;
import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitbit.R;
public class MyExcerciseActivity extends AppCompatActivity {
TextView textView,sets,reps,weight;
VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_excercise);
        ActionBar actionBar=getSupportActionBar();
    textView=findViewById(R.id.VideoTitle);
    sets=findViewById(R.id.sets);
    reps=findViewById(R.id.reps);
    weight=findViewById(R.id.weight);
    videoView=findViewById(R.id.videoView);
    //retrieve data from precious activity when it is clicked
        Intent intent=getIntent();
        String title=intent.getStringExtra("actionBarTitle");
        actionBar.setTitle(title);
        MediaController mediaController=new MediaController(this);
        if(title.equals("ABS Crunch")){
            sets.setText("3-4");
            reps.setText("25");
            weight.setText("0lbs");
            textView.setText(title);
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.absvideo);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
        }
        if(title.equals("Barbell Triceps")){
            sets.setText("4-5");
            reps.setText("15");
            weight.setText("25lbs");
            textView.setText(title);
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.barbelltricep);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();

        }
        if(title.equals("Barbell Curl")){
            sets.setText("1-2");
            reps.setText("12");
            weight.setText("80lbs");
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.barbellcurl);
            textView.setText(title);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
        }

        if(title.equals("Bench Press")){
            sets.setText("1-3");
            reps.setText("15");
            weight.setText("80lbs");
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.benchpress);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
            textView.setText(title);

        }


        if(title.equals("Chin up")){
            sets.setText("1-4");
            reps.setText("10");
            weight.setText("50lbs");
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.chinups);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
            textView.setText(title);

        }
        if(title.equals("DeadLift")){
            sets.setText("1-2");
            reps.setText("8-10");
            weight.setText("80lbs");
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.deadlift);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
            textView.setText(title);

        }
        if(title.equals("Hammer Curl")){
            sets.setText("1-2");
            reps.setText("8-10");
            weight.setText("80lbs");
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.hammercurl);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
            textView.setText(title);

        }
        if(title.equals("Incline Bench Press")){
            sets.setText("6-7");
            reps.setText("8");
            weight.setText("135lbs-230lbs");
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.inclinebarbellbenchpress);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
            textView.setText(title);

        }

        if(title.equals("Leg Curl")){
            sets.setText("1-3");
            reps.setText("10");
            weight.setText("360lbs");
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.legcurl);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
            textView.setText(title);

        }
        if(title.equals("Plank")){
            sets.setText("3-4");
            reps.setText("30-45sec");
            weight.setText("0");
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.planks);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
            textView.setText(title);

        }
        if(title.equals("Squat")){
            sets.setText("3-5");
            reps.setText("10-20");
            weight.setText("0");
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.squats);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
            textView.setText(title);

        }


        if(title.equals("Pull Ups")){
            sets.setText("1-5");
            reps.setText("3-4");
            weight.setText("0");
            videoView.setVideoPath("android.resource://"+ getPackageName()+ "/"+ R.raw.pullups);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.start();
            textView.setText(title);

        }

    }
}
