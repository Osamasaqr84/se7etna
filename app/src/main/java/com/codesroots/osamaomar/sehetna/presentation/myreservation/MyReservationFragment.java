package com.codesroots.osamaomar.sehetna.presentation.myreservation;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.presentation.details.CenterViewModel;
import com.codesroots.osamaomar.sehetna.presentation.details.CenterViewModelFactory;
import com.codesroots.osamaomar.sehetna.presentation.myreservation.adapter.ReservationAdapter;

public class MyReservationFragment extends Fragment {

    private CenterViewModel mViewModel;
    private RecyclerView recyclerView;
    private TextView notfound;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.my_reservation_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        notfound = view.findViewById(R.id.notfound);
        mViewModel = ViewModelProviders.of(this,getViewModelFactory()).get(CenterViewModel.class);
        mViewModel.getUserReservation(PreferenceHelper.getUserId());
        mViewModel.myReservationMutableLiveData.observe(this,myReservation ->
                {
                    if (myReservation.getData().size()>0)
                    recyclerView.setAdapter(new ReservationAdapter(getActivity(),myReservation.getData()));
                    else
                        notfound.setVisibility(View.VISIBLE);
                } );

        mViewModel.throwableMutableLiveData.observe(this,throwable ->
                        Toast.makeText(getContext(),throwable.getCause().toString(),Toast.LENGTH_SHORT).show()
                );

        return  view;
    }

    private CenterViewModelFactory getViewModelFactory() {
        return new CenterViewModelFactory(this.getActivity().getApplication());
    }


}
