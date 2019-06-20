package com.codesroots.osamaomar.sehetna.presentation.details.infofragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.osamaomar.sehetna.R;

import java.util.Locale;

public class InfoFragment extends Fragment {

    private InfoViewModel mViewModel;
    TextView place, state, state2, hour, website, phone;
    ImageView ivPhone, location, ivWebsite;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);

        place = view.findViewById(R.id.place);
        state = view.findViewById(R.id.state);
        state2 = view.findViewById(R.id.state2);
        hour = view.findViewById(R.id.hour);
        website = view.findViewById(R.id.website);
        phone = view.findViewById(R.id.mail);

        ivPhone = view.findViewById(R.id.removeListR4);
        location = view.findViewById(R.id.removeListR);
        ivWebsite = view.findViewById(R.id.removeListR3);

        if (getArguments() != null) {
            place.setText(getArguments().getString("adress"));
            hour.setText(getArguments().getString("hour"));
            website.setText(getArguments().getString("website"));
            phone.setText(getArguments().getString("phone"));

            if (getArguments().getBoolean("state")) {
                state.setText("مفتوح");
                if (! hour.getText().toString().equals("")) state2.setText("-يغلق");

            } else {
                state.setText("مغلق");
                state.setTextColor(getContext().getResources().getColor(R.color.red));
               if (! hour.getText().toString().equals("")) state2.setText("-يفتح");

            }

        }

        ivPhone.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone.getText().toString()));
            getContext().startActivity(intent);
        });
        location.setOnClickListener(v->{

           double lat  =  getArguments().getDouble("lat");
            double  lng  =  getArguments().getDouble("lng");
            String name  =  getArguments().getString("name");
            String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", lat,
                    lng, name );

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage("com.google.android.apps.maps");
            getContext().startActivity(intent);

        });
        ivWebsite.setOnClickListener(v->{
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website.getText().toString()));
                startActivity(browserIntent);
            }
            catch (Exception e)
            {
                Toast.makeText(getContext(),getText(R.string.error_occure),Toast.LENGTH_SHORT).show();
            }
        });
        return view;   }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(com.codesroots.osamaomar.sehetna.presentation.details.infofragment.InfoViewModel.class);
        // TODO: Use the ViewModel
    }

}
