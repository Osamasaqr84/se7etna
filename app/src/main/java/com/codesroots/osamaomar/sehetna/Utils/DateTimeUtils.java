package com.codesroots.osamaomar.sehetna.Utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    public static Date  getDateObject(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj = sdf.parse(date);
            return dateObj;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }


    public static String  getdate(String date)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj= sdf.parse(date);
            Log.d("newdatein",dateObj.getTime()+"");
            String timestamp = String.valueOf(dateObj.getTime());//  //Example -> in ms
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
            Log.d("dateString",dateString);

            return dateString;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static String  getHour(String date)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj= sdf.parse(date);
            Log.d("newhourin",dateObj.getTime()+"");
            String timestamp = String.valueOf(dateObj.getTime());//  //Example -> in ms
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");

            String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
            Log.d("hour",dateString);

            return dateString;
        }
        catch (ParseException e) {
           Log.d("hourError" , e.getMessage());
        }

        return null;
    }



    public  static boolean isNowBetweenDateTime(Date startTime, Date endTime)
    {
        final Date now = new Date();
        return now.after(startTime) && now.before(endTime);
    }


}
