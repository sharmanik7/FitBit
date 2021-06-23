package com.example.fitbit.Diet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitbit.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dietAdapter extends FirebaseRecyclerAdapter<DietModel, dietAdapter.myViewHolder> {



    public dietAdapter(@NonNull FirebaseRecyclerOptions<DietModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull DietModel model) {
        /*new changes
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String   currentUserId=user.getUid();
        final String key=getRef(position).getKey();
        end here*/
        holder.name.setText(model.getName());
        holder.calories.setText("Calorie Count: "+(int) model.getCalories());
        holder.fats.setText("Fats: "+(int)model.getFats());
        holder.fibre.setText("Fibre: "+(int)model.getFibre());
        holder.carbohydrates.setText("Carbohydrates: "+(int)model.getCarbohydrates());
        holder.proteins.setText("Proteins: "+(int)model.getProteins());
        Glide.with(holder.imageView.getContext()).load(model.getImage()).into(holder.imageView);
//new changes
        String itemName=getItem(position).getName();
        float getCalorie=getItem(position).getCalories();
        float getProtein=getItem(position).getProteins();
        float getFats=getItem(position).getFats();
        float getCarbo=getItem(position).getCarbohydrates();
        float getFibre=getItem(position).getFibre();
        /*holder.favouriteChecker(key);
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.favrtChecker=true;

                holder.fvrt_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(holder.favrtChecker.equals(true)){
                            if (snapshot.child(key).hasChild(currentUserId)){
                                holder.fvrt_ref.child(key).child(currentUserId).removeValue();
                                holder.favrtChecker=false;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });*/
        //end here
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_single_row,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        ImageButton addBtn;
        TextView name,calories,fats,proteins,fibre,carbohydrates;
        ImageButton fvrt;
        DatabaseReference fvrt_ref;
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        Boolean favrtChecker=false;

        public myViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.dietFood);
            name=itemView.findViewById(R.id.foodName);
            calories=itemView.findViewById(R.id.calories);
            fats=itemView.findViewById(R.id.fats);
            proteins=itemView.findViewById(R.id.proteins);
            fibre=itemView.findViewById(R.id.fibre);
            carbohydrates=itemView.findViewById(R.id.carbohydrate);

        }

        /*public void favouriteChecker(String k) {

        }*/
    }
}
