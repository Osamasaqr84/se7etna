package com.codesroots.osamaomar.sehetna.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import java.util.List;

public class ViewsUtils {

    public static void setupDiffRecycle(RecyclerView post_images, List<String> images, Context context) {
/*Integer spanCount  =  1;
        if(imagesCount>1 ){spanCount =2;}*/
        final GridLayoutManager mng_layout = new GridLayoutManager(context, 2);
        mng_layout.setOrientation(LinearLayout.HORIZONTAL);
        mng_layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position) == 0 ? 2 : 1;
            }
        });
        post_images.setLayoutManager(mng_layout);
        post_images.setAdapter(new com.codesroots.osamaomar.sehetna.presentation.mainfragment.adapter.PostImagesAdapter(images,context));
    }


    public static  Activity getActivity(Context mContext) {
        Context context = mContext;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
