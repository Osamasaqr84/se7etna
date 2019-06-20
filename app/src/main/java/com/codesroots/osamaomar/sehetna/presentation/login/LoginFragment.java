package com.codesroots.osamaomar.sehetna.presentation.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.osamaomar.sehetna.Entities.User;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.codesroots.osamaomar.sehetna.presentation.mainfragment.MainFragment;
import com.codesroots.osamaomar.sehetna.presentation.register.RegisterFragment;
import com.codesroots.osamaomar.sehetna.presentation.register.RegisterViewModel;
import com.codesroots.osamaomar.sehetna.presentation.register.RegisterViewModelFactory;

public class LoginFragment extends Fragment {

    private RegisterViewModel mViewModel;
    TextView loginbtn;
    EditText username, password;
    private User user = new User();

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    TextView gotoregister;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(RegisterViewModel.class);
        ((MainActivity) getActivity()).changeSBVisibility(0);
        loginbtn = view.findViewById(R.id.login);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        loginbtn.setOnClickListener(v -> {
            loginbtn.setText(getText(R.string.wait));
            loginbtn.setEnabled(false);
            User user = new User();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            mViewModel.userLogin(user);
        });

        mViewModel.errorMessage.observe(this, s -> Toast.makeText(getActivity(), s + " ", Toast.LENGTH_SHORT).show());
        mViewModel.loginResponseMutableLiveData.observe(this, response ->
        {
            if (response.isSuccess()) {
                PreferenceHelper.setUserId(response.getData().getUserid());
                PreferenceHelper.setUserName(response.getData().getUsername());
                PreferenceHelper.setToken(response.getData().getToken());
                PreferenceHelper.setCurrentPhoto(response.getData().getPhoto());
                Toast.makeText(getActivity(), getText(R.string.hello) + " " + response.getData().getUsername(), Toast.LENGTH_SHORT).show();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MainFragment()).addToBackStack(null).commit();
            } else {
                Toast.makeText(getActivity(), getText(R.string.error_tryagani), Toast.LENGTH_SHORT).show();
                loginbtn.setText(getText(R.string.login));
                loginbtn.setEnabled(true);
            }
        });

        mViewModel.errorinRegister.observe(this, throwable ->
        {
            loginbtn.setText(getText(R.string.login));
            loginbtn.setEnabled(true);
            if (throwable.toString().contains("HTTP 401 Unauthorized"))
                Toast.makeText(getActivity(), "اسم المستخدم او كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), getText(R.string.error_occure), Toast.LENGTH_SHORT).show();
        });


        gotoregister = view.findViewById(R.id.gotoregister);
        gotoregister.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).addToBackStack(null).commit());
        return view;
    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new RegisterViewModelFactory(getActivity().getApplication());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity) getActivity()).changeSBVisibility(1);
    }
}
