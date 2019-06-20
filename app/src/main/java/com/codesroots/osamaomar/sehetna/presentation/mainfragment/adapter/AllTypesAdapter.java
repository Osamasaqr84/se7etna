package com.codesroots.osamaomar.sehetna.presentation.mainfragment.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.Entities.Categories;
import com.codesroots.osamaomar.sehetna.R;

import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.codesroots.osamaomar.sehetna.presentation.mainfragment.MainViewModel;
import com.codesroots.osamaomar.sehetna.presentation.Search.ResultFragment;

import java.util.List;


public class AllTypesAdapter extends RecyclerView.Adapter<AllTypesAdapter.ViewHolder> {


    FragmentManager fr;
    FragmentTransaction ft;

    private Context context;

    MainViewModel mainViewModel;
    List<Categories.Types> categoriesList;

    public AllTypesAdapter(FragmentActivity activity, List<Categories.Types> categories, MainViewModel mainViewModelC) {
        context = activity;
        mainViewModel = mainViewModelC;
        categoriesList = categories;
    }

    @Override
    public AllTypesAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_intypes, parent, false);
        return new AllTypesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

        viewHolder.name.setText(categoriesList.get(position).getName());

        Glide.with(context).load(categoriesList.get(position).getIcon()).dontAnimate().placeholder(R.drawable.group).into(viewHolder.type_img);

//        if (position == 0) {
//            viewHolder.type_img.setImageResource(R.drawable.group);
//        } else if (position == 1) {
//            viewHolder.type_img.setImageResource(R.drawable.clinics);
//        } else if (position == 2) {
//            viewHolder.type_img.setImageResource(R.drawable.pharma);
//        } else if (position == 3) {
//            viewHolder.type_img.setImageResource(R.drawable.doctors);
//        }

        viewHolder.mView.setOnClickListener((View v) -> {
            String catText = categoriesList.get(position).getName(), catType = categoriesList.get(position).getType();
            int typeID = categoriesList.get(position).getId();
            ((MainActivity)context).changeBNVisibility(0);
            ((MainActivity)context).changeSBVisibility(0);
            Fragment fragment = new ResultFragment();
            Bundle bundle = new Bundle();
            bundle.putString("catText", catText);
            bundle.putString("catType", catType);
            bundle.putInt("typeID", typeID);
            fragment.setArguments(bundle);
            fr = ((MainActivity) (v.getContext())).getSupportFragmentManager();
            ft = fr.beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_in_up);
            ft.replace(R.id.main_view, fragment, "result");
            ft.commit();
        });
    }


    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView name;
        ImageView type_img;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            type_img = mView.findViewById(R.id.img);
            name = mView.findViewById(R.id.name);
//
//            name =mView.findViewById(R.id.notification_text);
//            comment =mView.findViewById(R.id.comment);
//            time =mView.findViewById(R.id.time);
//            ratingBar =mView.findViewById(R.id.rates);
//            person_img =mView.findViewById(R.id.person_img);
        }


    }

}
