package com.example.fitbit.Excercise;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitbit.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater layoutInflater;
    List<Model> modelList;
    ArrayList<Model> arrayList;

    public ListViewAdapter(Context Context, List<Model> modelList) {
        mContext = Context;
        this.modelList = modelList;
        layoutInflater=LayoutInflater.from(mContext);
        this.arrayList=new ArrayList<Model>();
        this.arrayList.addAll(modelList);
    }
    public class ViewHolder{
        TextView mTitleTv,mDescTv;
        ImageView mIconTv;
    }
    @Override
    public int getCount() {
        return modelList.size();
    }
    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.row,null);
            holder.mTitleTv=convertView.findViewById(R.id.mainTitle);
            holder.mDescTv=convertView.findViewById(R.id.mainDesc);
            holder.mIconTv=convertView.findViewById(R.id.mainIcon);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }
        //set the title in textviews
        holder.mTitleTv.setText(modelList.get(position).getTitle());
        holder.mDescTv.setText(modelList.get(position).getDesc());
        //set the image icon
        holder.mIconTv.setImageResource(modelList.get(position).getIcon());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code later
                if (modelList.get(position).getTitle().equals("ABS Crunch")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","ABS Crunch");
                    mContext.startActivity(intent);
                }
                if (modelList.get(position).getTitle().equals("Barbell Triceps")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","Barbell Triceps");
                    mContext.startActivity(intent);
                }
                if (modelList.get(position).getTitle().equals("Barbell Curl")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","Barbell Curl");
                    mContext.startActivity(intent);
                }
                if (modelList.get(position).getTitle().equals("Bench Press")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","Bench Press");
                    mContext.startActivity(intent);
                }
                if (modelList.get(position).getTitle().equals("Chin up")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","Chin up");
                    mContext.startActivity(intent);
                }
                if (modelList.get(position).getTitle().equals("DeadLift")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","DeadLift");
                    mContext.startActivity(intent);
                }
                if (modelList.get(position).getTitle().equals("Hammer Curl")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","Hammer Curl");
                    mContext.startActivity(intent);
                }
                if (modelList.get(position).getTitle().equals("Incline Bench Press")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","Incline Bench Press");
                    mContext.startActivity(intent);
                }
                if (modelList.get(position).getTitle().equals("Leg Curl")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","Leg Curl");
                    mContext.startActivity(intent);
                }
                if (modelList.get(position).getTitle().equals("Plank")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","Plank");
                    mContext.startActivity(intent);
                }
                if (modelList.get(position).getTitle().equals("Pull Ups")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","Pull Ups");
                    mContext.startActivity(intent);
                }
                if (modelList.get(position).getTitle().equals("Push Ups")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","Pull Ups");
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("Squat")){
                    Intent intent=new Intent(mContext,MyExcerciseActivity.class);
                    intent.putExtra("actionBarTitle","Pull Ups");
                    mContext.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    //filter function

    public void filter(String charText){
        charText=charText.toLowerCase(Locale.getDefault());
        modelList.clear();
        if(charText.length()==0){
            modelList.addAll(arrayList);
        }
        else {
            for (Model model:arrayList){
                if (model.getTitle().toLowerCase(Locale.getDefault()).contains(charText)){
                        modelList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
