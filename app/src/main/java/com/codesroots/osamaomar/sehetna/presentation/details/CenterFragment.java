package com.codesroots.osamaomar.sehetna.presentation.details;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.codesroots.osamaomar.sehetna.Entities.PhotosResponse;
import com.codesroots.osamaomar.sehetna.Entities.RatingResponse;
import com.codesroots.osamaomar.sehetna.Entities.SearchEntity;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.presentation.MainActivity;
import com.codesroots.osamaomar.sehetna.presentation.details.directionfragment.DirectionFragment;
import com.codesroots.osamaomar.sehetna.presentation.details.infofragment.InfoFragment;
import com.codesroots.osamaomar.sehetna.presentation.details.photos.PhotosFragment;
import com.codesroots.osamaomar.sehetna.presentation.details.posts.PostsFragment;
import com.codesroots.osamaomar.sehetna.presentation.details.ratesFragmenr.RatesFragment;
import com.codesroots.osamaomar.sehetna.presentation.mainfragment.MainFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CenterFragment extends Fragment {

    private CenterViewModel mViewModel;
    private Toolbar toolbar;
    ImageView centerImage, cover, removeListB, favIcon, unFavIcon, reserve;
    TextView name;
    Fragment postsFragment, infoFragment, photosFragment, directionFragment, ratesFragment;
    String currentFragment = "";
    private TabLayout tabLayout;
    int healthcare_id, user_id = PreferenceHelper.getUserId(), favid;
    Bundle bundle = new Bundle();
    Bundle bundle2 = new Bundle();
    private NonSwipeableViewPager viewPager;
    TextView reservationdate;
    int year, month, day;
    String resertdate;

    private int[] tabIcons = {
            R.drawable.postss,
            R.drawable.details,
            R.drawable.images,
            R.drawable.directions,
            R.drawable.rates
    };

    private static BottomSheetBehavior bottomSheetBehaviorRes;
    ConstraintLayout mainLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.center_details_fragment, container, false);

        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tabs);
        centerImage = view.findViewById(R.id.logo_img);
        cover = view.findViewById(R.id.main_img);
        name = view.findViewById(R.id.name);
        favIcon = view.findViewById(R.id.ivFav);
        unFavIcon = view.findViewById(R.id.ivFavN);
        removeListB = view.findViewById(R.id.removeListR);
        mainLayout = view.findViewById(R.id.mainLayout);
        reserve = view.findViewById(R.id.reserve);
        bottomSheetBehaviorRes = BottomSheetBehavior.from(mainLayout);
        bottomSheetBehaviorRes.setHideable(false);
        healthcare_id = getArguments().getInt("healthcare_id", 0);
        mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(CenterViewModel.class);
        ((MainActivity) getActivity()).changeSBVisibility(0);
        bottomSheetBehaviorRes.setState(BottomSheetBehavior.STATE_EXPANDED);
        directionFragment = new DirectionFragment();
        infoFragment = new InfoFragment();
        postsFragment = new PostsFragment();
        ratesFragment = new RatesFragment();
        photosFragment = new PhotosFragment();

        reserve.setOnClickListener(v ->
        {
            if (PreferenceHelper.getUserId() > 0)
                openDialogForReserve();
            else
                Snackbar.make(v, "يجب تسجيل الدخول", Snackbar.LENGTH_SHORT).show();
        });


        if (healthcare_id == 0) {
            favIcon.setVisibility(View.GONE);
            unFavIcon.setVisibility(View.GONE);
            reserve.setVisibility(View.GONE);
        }
        favIcon.setOnClickListener(v ->
        {
            if (user_id > 0) {
                if (favid > 0)
                    mViewModel.DeleteFav(favid);
                else
                    mViewModel.AddToFav(healthcare_id, user_id);
            } else
                Snackbar.make(view, "يجب تسجيل الدخول اولا ", Snackbar.LENGTH_SHORT).show();
        });

        unFavIcon.setOnClickListener(v ->
                {
                    if (user_id > 0) {
                        if (favid > 0)
                            mViewModel.DeleteFav(favid);
                        else
                            mViewModel.AddToFav(healthcare_id, user_id);
                    } else
                        Snackbar.make(view, "يجب تسجيل الدخول اولا ", Snackbar.LENGTH_SHORT).show();
                }
        );

        mViewModel.addToFavMutableLiveData.observe(this, addToFavouritesResponse ->
        {
            if (addToFavouritesResponse.getIdfav() > 0 && addToFavouritesResponse.isSuccess()) {
                favid = addToFavouritesResponse.getIdfav();
                unFavIcon.setVisibility(View.GONE);
                favIcon.setVisibility(View.VISIBLE);
            } else if (addToFavouritesResponse.isSuccess()) {
                favid = 0;
                unFavIcon.setVisibility(View.VISIBLE);
                favIcon.setVisibility(View.GONE);
            } else
                Toast.makeText(getActivity(), getText(R.string.error_occure), Toast.LENGTH_SHORT).show();
        });

        mViewModel.reservationMutableLiveData.observe(this, reservation ->
        {
            if (reservation.getReservation().getId() > 0)
                Toast.makeText(getContext(), "تم الحجز بنجاح", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), "خطا!!حاول مرة أخري", Toast.LENGTH_SHORT).show();
        });

        mViewModel.googleDetailsCenterMutableLiveData.observe(this, detailsCenter -> {
            try {
            bundle.putDouble("lat", detailsCenter.getResult().getGeometry().getLocation().getLat());
            bundle.putDouble("lng", detailsCenter.getResult().getGeometry().getLocation().getLng());
            bundle.putString("adress", detailsCenter.getResult().getFormatted_address());
            bundle.putString("phone", detailsCenter.getResult().getFormatted_phone_number());
            bundle.putString("website", detailsCenter.getResult().getWebsite());
            bundle.putBoolean("state", detailsCenter.getResult().getOpening_hours().isOpen_now());
            bundle.putString("name", detailsCenter.getResult().getName());
            infoFragment.setArguments(bundle);
            List<PhotosResponse.DataBean.HcphotosBean> photos = new ArrayList<>();
            List<RatingResponse.DataBean> rates = new ArrayList<>();
            String firstphoto = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=" +
                    detailsCenter.getResult().getPhotos().get(0).getWidth() +
                    "&photoreference=" + detailsCenter.getResult().getPhotos().get(0).getPhoto_reference() +
                    "&key=" + getString(R.string.google_maps_key);

            Glide.with(getContext())
                    .load(firstphoto)
                    .into(cover);
            Glide.with(getContext())
                    .load(firstphoto)
                    .into(centerImage);

            for (int i = 0; i < detailsCenter.getResult().getPhotos().size(); i++)
                photos.add(new PhotosResponse.DataBean.HcphotosBean(0, "https://maps.googleapis.com/maps/api/place/photo?maxwidth=" +
                        detailsCenter.getResult().getPhotos().get(i).getWidth() +
                        "&photoreference=" + detailsCenter.getResult().getPhotos().get(i).getPhoto_reference() +
                        "&key=" + getString(R.string.google_maps_key)));

            for (int i = 0; i < detailsCenter.getResult().getReviews().size(); i++)
                rates.add(new RatingResponse.DataBean(detailsCenter.getResult().getReviews().get(i).getRating()
                        , detailsCenter.getResult().getReviews().get(i).getText(),
                        detailsCenter.getResult().getReviews().get(i).getAuthor_name(), detailsCenter.getResult().getReviews().get(i).getProfile_photo_url()));
            bundle2.putString("helthcare_id", "0");
            bundle2.putParcelableArrayList("photos", (ArrayList<? extends Parcelable>) photos);
            bundle2.putParcelableArrayList("rates", (ArrayList<? extends Parcelable>) rates);
            photosFragment.setArguments(bundle2);
            directionFragment.setArguments(bundle);
            ratesFragment.setArguments(bundle2);
            setupViewPager(viewPager, true);
            tabLayout.setupWithViewPager(viewPager);
            setupTabIcons(true);
            }
            catch (Exception e){}
        });

        mViewModel.throwableMutableLiveData.observe(this, throwable ->
                Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_SHORT).show()
        );

        if (getArguments() != null) {
            SearchEntity entity = getArguments().getParcelable("currItem");
            healthcare_id = entity.getId();
            favid = entity.getFavid();

            if (entity.getHosTypeid()!=2) {
                reserve.setVisibility(View.GONE);
            }

            if (entity.getGoogleItem()) {
                mViewModel.getGoogleCenterDetails(entity.getPlace_Id(), "AIzaSyDPa2J9AKnxyYTcKTUzH3rmFqiXTIezXuM");
            } else {
                if (entity.isFavourited())
                    unFavIcon.setVisibility(View.GONE);
                else
                    favIcon.setVisibility(View.GONE);

                Glide.with(getContext())
                        .load(entity.getCoverPhoto())
                        .into(cover);
                Glide.with(getContext())
                        .load(entity.getImgUrl())
                        .into(centerImage);

                name.setBackgroundResource(android.R.color.transparent);
                name.setText(entity.getName());
                bundle2.putInt("helthcare_id", entity.getId());
                bundle.putDouble("lat", entity.getLat());
                bundle.putDouble("lng", entity.getLng());
                bundle.putString("adress", entity.getAdress());
                bundle.putString("phone", entity.getPhoneNumber());
                bundle.putString("website", entity.getWebsite());
                bundle.putBoolean("state", entity.getState());
                if (entity.getState()) {
                    if (entity.getClosingHour() != null)
                        bundle.putString("hour", entity.getClosingHour());
                } else {
                    if (entity.getOpeningHour() != null)
                        bundle.putString("hour", entity.getOpeningHour());
                }
                directionFragment.setArguments(bundle);
                infoFragment.setArguments(bundle);
                postsFragment.setArguments(bundle2);
                photosFragment.setArguments(bundle2);
                ratesFragment.setArguments(bundle2);
                setupViewPager(viewPager, false);
                tabLayout.setupWithViewPager(viewPager);
                setupTabIcons(false);
            }
        }

        removeListB.setOnClickListener(v -> {
            bottomSheetBehaviorRes.setHideable(true);
            bottomSheetBehaviorRes.setState(BottomSheetBehavior.STATE_HIDDEN);
            ((MainActivity) getActivity()).changeSBVisibility(1);
            new Handler().postDelayed(() -> getFragmentManager().beginTransaction()
                    .replace(R.id.main_view, new MainFragment())
                    .addToBackStack(null).commit(), 1000);
        });

        return view;
    }

    private CenterViewModelFactory getViewModelFactory() {
        return new CenterViewModelFactory(this.getActivity().getApplication());
    }

    private void setupTabIcons(boolean fromgoogle) {
        if (!fromgoogle) {
            tabLayout.getTabAt(0).setIcon(tabIcons[0]);
            tabLayout.getTabAt(1).setIcon(tabIcons[1]);
            tabLayout.getTabAt(2).setIcon(tabIcons[2]);
            tabLayout.getTabAt(3).setIcon(tabIcons[3]);
            tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        } else {
            tabLayout.getTabAt(0).setIcon(tabIcons[1]);
            tabLayout.getTabAt(1).setIcon(tabIcons[2]);
            tabLayout.getTabAt(2).setIcon(tabIcons[3]);
            tabLayout.getTabAt(3).setIcon(tabIcons[4]);
        }
    }

    private void setupViewPager(ViewPager viewPager, boolean fromgoogle) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        if (!fromgoogle)
            adapter.addFragment(postsFragment, getString(R.string.posts));

        adapter.addFragment(infoFragment, getString(R.string.details));
        adapter.addFragment(photosFragment, getString(R.string.phots));
        adapter.addFragment(directionFragment, getString(R.string.directions));
        adapter.addFragment(ratesFragment, getString(R.string.rates));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() == null) {
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle backbutton's click listener
                    if (bottomSheetBehaviorRes.getState() == BottomSheetBehavior.STATE_EXPANDED)
                        bottomSheetBehaviorRes.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    if (bottomSheetBehaviorRes.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                        bottomSheetBehaviorRes.setHideable(true);
                        bottomSheetBehaviorRes.setState(BottomSheetBehavior.STATE_HIDDEN);
                        ((MainActivity) getActivity()).changeBNVisibility(1);
                        ((MainActivity) getActivity()).changeSBVisibility(1);
                        ((MainActivity) getActivity()).clearMap();

                        new Handler().postDelayed(() -> getFragmentManager().beginTransaction().replace(R.id.main_view, new MainFragment()).commit(), 1000);
                    }
                    return true;
                } else {
                    return true;
                }
            }
        });
    }

    private void openDialogForReserve() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.alert_add_reserve, null);
        dialogBuilder.setView(dialogView);
        reservationdate = dialogView.findViewById(R.id.from);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) ;
        day = c.get(Calendar.DAY_OF_MONTH);
        reservationdate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), datePickerListener, year, month+1, day);
            SimpleDateFormat simpledateformate = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date = simpledateformate.parse(day + "/" + month + "/" + year);
                datePickerDialog.getDatePicker().setMinDate(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            datePickerDialog.show();
        });

        TextView save;
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setTitle(" اضافة حجز جديد ");
        alertDialog.show();
        save = dialogView.findViewById(R.id.save);
        save.setOnClickListener(v -> {
            if (!reservationdate.getText().toString().matches("")) {
                mViewModel.addReservation(resertdate, healthcare_id, PreferenceHelper.getUserId());
                alertDialog.dismiss();
            } else
                reservationdate.setError("أدخل تاريخ الحجز");
        });
    }

    @SuppressLint("SetTextI18n")
    DatePickerDialog.OnDateSetListener datePickerListener = (view, selectedYear, selectedMonth, selectedDay) -> {
        year = selectedYear;
        day = selectedDay;
        month = selectedMonth;
        reservationdate.setText(selectedYear + "-" + (month) + "-"
                + selectedDay);
        resertdate = reservationdate.getText().toString();
    };
}
