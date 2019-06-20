package com.codesroots.osamaomar.sehetna.presentation.details.directionfragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.ViewsUtils;

import java.util.Locale;
import java.util.Objects;

public class DirectionFragment extends Fragment {

    private DirectionViewModel mViewModel;

    boolean isvisible =false;

    public static DirectionFragment newInstance() {
        return new DirectionFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isvisible){

            String  name="";
            double lat ,lng;
            if (getArguments() != null){
                lat  =  getArguments().getDouble("lat");
                lng  =  getArguments().getDouble("lng");
                name  =  getArguments().getString("name");
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", lat,
                        lng, name );
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                getContext().startActivity(intent);

            }else {lat =0 ; lng =0 ;}

        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

   View view  =      inflater.inflate(R.layout.direction_fragment, container, false);
  return view ;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&& isResumed()) {
       isvisible  = isVisibleToUser ;


            String  name="";
            double lat ,lng;
            if (getArguments() != null){
                lat  =  getArguments().getDouble("lat");
                lng  =  getArguments().getDouble("lng");
                name  =  getArguments().getString("name");
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", lat,
                        lng, name );
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                Objects.requireNonNull(ViewsUtils.getActivity(getContext())).startActivity(intent);
                     //  startActivity(intent);
        }/*else{
            isvisible  =false;
        }*/
    }


}

    @Override
    public void onResume() {
        super.onResume();
    }
}
