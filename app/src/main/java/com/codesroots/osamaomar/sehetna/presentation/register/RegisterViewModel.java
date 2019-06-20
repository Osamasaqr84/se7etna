package com.codesroots.osamaomar.sehetna.presentation.register;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.codesroots.osamaomar.sehetna.Entities.LoginResponse;
import com.codesroots.osamaomar.sehetna.Entities.Register;
import com.codesroots.osamaomar.sehetna.Entities.User;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.FileUtils;
import com.codesroots.osamaomar.sehetna.data.remote.ApiInterface;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class RegisterViewModel extends ViewModel {
    // TODO: Implement the ViewModel.

    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<Register> registerMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> errorinRegister = new MutableLiveData<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ApiInterface apiInterface;
    private Context context;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    RegisterViewModel(ApiInterface apiService1,Context con) {
        apiInterface = apiService1;
        context = con;
    }

    public void addUser(User user)
    {
        if (validate(user))
        {
            registerUser(user);
        }
    }

   public void userLogin(User user)
    {
        if (validateLogin(user))
        {
            loginUser(user);
        }
    }

    private void registerUser(User user) {
        mCompositeDisposable.add(
                apiInterface.userregister(createPartFromString(user.getUsername()),
                        createPartFromString(user.getPassword()),
                        createPartFromString(user.getEmail()),
                        createPartFromString("1"),
                        createPartFromString("1"),
                        createPartFromString(user.getGroup()),
                        prepareFilePart("photo",user.getUri()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postDataResponseForRegister,
                                this::postError));
    }

    private void loginUser(User user) {
        mCompositeDisposable.add(
                apiInterface.userlogin(user.getUsername(),user.getPassword())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::postDataResponseForLogin,
                                this::postError));
    }

    private void postDataResponseForLogin(LoginResponse response) {

        loginResponseMutableLiveData.postValue(response);
    }

    private void postDataResponseForRegister(Register register) {
        registerMutableLiveData.postValue(register);
    }

    private void postError(Throwable throwable) {
        errorinRegister.postValue(throwable);
    }


    private boolean validate(User user) {
        if (user.getUsername().matches("")||user.getPassword().matches("")||
                user.getEmail().matches("")||user.getRepassword().matches("")||user.getUri()==null) {
            errorMessage.postValue(context.getText(R.string.complete).toString());
            return false;
        }
        else if (!user.getPassword().matches(user.getRepassword())) {
            errorMessage.postValue(context.getText(R.string.passworsnotmatch).toString());
            return false;
        }
        else  if (!user.getEmail().matches(emailPattern)) {
            errorMessage.postValue(context.getText(R.string.notvalideemail).toString());
            return false;
        }
        else
            return true;
    }

    private boolean validateLogin(User user) {
        if (user.getUsername().matches("")||user.getPassword().matches("")) {
            errorMessage.postValue(context.getText(R.string.complete).toString());
            return false;
        }
        else
            return true;
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String name, Uri fileUri) {
        File file = null;
        String filename = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            file = FileUtils.getFile(context, fileUri);
        }
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("photo"),file );
        try {
            filename=  URLEncoder.encode( file.getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return MultipartBody.Part.createFormData(name, filename, requestFile);
    }
}
