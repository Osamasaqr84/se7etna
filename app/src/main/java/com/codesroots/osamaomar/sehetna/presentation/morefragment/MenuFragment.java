package com.codesroots.osamaomar.sehetna.presentation.morefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.codesroots.osamaomar.sehetna.presentation.login.LoginFragment;
import com.codesroots.osamaomar.sehetna.presentation.mainfragment.MainFragment;
import com.codesroots.osamaomar.sehetna.presentation.myreservation.MyReservationFragment;


public class MenuFragment extends Fragment {

    private MenuViewModel mViewModel;
    private TextView aboutapp, login,logout,myreservation;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        ((MainActivity)getActivity()).changeSBVisibility(0);
        //mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MenuViewModel.class);
        aboutapp = view.findViewById(R.id.aboutapp);
        login = view.findViewById(R.id.login);
        logout = view.findViewById(R.id.logout);
        myreservation = view.findViewById(R.id.myreservation);
        login.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment()).addToBackStack(null).commit());
        myreservation.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MyReservationFragment()).addToBackStack(null).commit());
        if (PreferenceHelper.getUserId()>0)
            login.setVisibility(View.GONE);
        else
            logout.setVisibility(View.GONE);

        logout.setOnClickListener(v -> {
            PreferenceHelper.setUserId(0);
            FragmentManager fm = getActivity().getSupportFragmentManager();
            for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MainFragment()).addToBackStack(null).commit();

            Toast.makeText(getActivity(),getText(R.string.youlogout),Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity)getActivity()).changeSBVisibility(1);
    }
}
