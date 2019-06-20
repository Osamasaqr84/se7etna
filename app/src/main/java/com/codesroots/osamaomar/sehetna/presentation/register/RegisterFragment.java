package com.codesroots.osamaomar.sehetna.presentation.register;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.osamaomar.sehetna.Entities.User;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.codesroots.osamaomar.sehetna.presentation.mainfragment.MainFragment;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import static android.app.Activity.RESULT_OK;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;

    TextView registerbtn;
    EditText username, password, repassword, mail;
    private ImageView profile;
    private User user = new User();
    private Uri profileuri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(RegisterViewModel.class);

        View view = inflater.inflate(R.layout.register_fragment, container, false);
        ((MainActivity) getActivity()).changeSBVisibility(0);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        repassword = view.findViewById(R.id.repassword);
        mail = view.findViewById(R.id.mail);
        profile = view.findViewById(R.id.img);
        profile.setOnClickListener(this::getProfileImage);
        registerbtn = view.findViewById(R.id.register);
        registerbtn.setOnClickListener(v -> {
            registerbtn.setText("انتظر ...");
            registerbtn.setEnabled(false);
            User user = new User();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.setRepassword(repassword.getText().toString());
            user.setEmail(mail.getText().toString());
            user.setUri(profileuri);
            user.setGroup("1");
            mViewModel.addUser(user);
        });

        mViewModel.errorMessage.observe(this, s ->
        {
            registerbtn.setText("تسجيل");
            registerbtn.setEnabled(true);
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        });


        mViewModel.registerMutableLiveData.observe(this, register ->
        {
            registerbtn.setText("تسجيل");
            registerbtn.setEnabled(true);
            if (register.isSuccess()) {
                PreferenceHelper.setUserId(register.getData().getId());
                // PreferenceHelper.setUserName(register.getData().getUsername());
                PreferenceHelper.setToken(register.getData().getToken());
                Toast.makeText(getActivity(), getText(R.string.registersuccess), Toast.LENGTH_SHORT).show();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MainFragment()).addToBackStack(null).commit();
            } else {
                Toast.makeText(getActivity(), getText(R.string.error_tryagani), Toast.LENGTH_SHORT).show();
            }
        });

        mViewModel.errorinRegister.observe(this, throwable ->
        {
            registerbtn.setText("تسجيل");
            registerbtn.setEnabled(true);
            Toast.makeText(getActivity(), getText(R.string.error_occure), Toast.LENGTH_SHORT).show();
        });
        return view;
    }

    public void getProfileImage(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 112);
        } else {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(getContext(), this);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                profileuri = result.getUri();
                profile.setImageURI(profileuri);////set the image after taking it
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        } else if (requestCode == 112)
            getProfileImage(null);
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
