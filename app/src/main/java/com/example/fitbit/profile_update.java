package com.example.fitbit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile_update extends AppCompatActivity {

    CircleImageView profileImageView;
    Button closeButton,saveButton;
    TextView profileChangeId;

    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    Uri imageUrl;
    String myUrl="";
    StorageTask uploadTask;
    StorageReference storageProfilePicRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        mAuth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("User");
        storageProfilePicRef= FirebaseStorage.getInstance().getReference().child("Profile pic");
        profileImageView=findViewById(R.id.profile_pic);
        closeButton=findViewById(R.id.btnclose);
        saveButton=findViewById(R.id.btnsave);
        profileChangeId=findViewById(R.id.change_profile);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile_update.this,Home.class));
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfileImage();
            }
        });
        profileChangeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setAspectRatio(1,1).start(profile_update.this);
            }
        });
        getUserInfo();
    }

    private void getUserInfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()&&snapshot.getChildrenCount()>0)
                {
                    if(snapshot.hasChild("image"))
                    {
                        String image=snapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileImageView);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data!=null)
        {
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            imageUrl=result.getUri();
            profileImageView.setImageURI(imageUrl);
        }
        else{
            Toast.makeText(this,"error please try again",Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadProfileImage() {
        if(imageUrl!=null)
        {
            final StorageReference filere=storageProfilePicRef
                    .child(mAuth.getCurrentUser().getUid()+".jpg");
            uploadTask=filere.putFile(imageUrl);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return filere.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful())
                    {
                        Uri downloadUri= task.getResult();
                        myUrl=downloadUri.toString();
                        HashMap<String,Object> userMap=new HashMap<>();
                        userMap.put("image",myUrl);
                        databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);
                    }
                }
            });
        }
        else{
            Toast.makeText(this,"Image Not Selected",Toast.LENGTH_SHORT).show();
        }
    }
}


