package com.biswas.route.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.TextView;

import com.biswas.route.R;


public class CommonMethods
{
    public static AlertDialog alertDialog;
    private static BottomSheetDialog bottomSheetDialog;

    public static void showOkAlertDialog(String message, String title, Context context, boolean isCancelable)
    {
        dismissAlertDialog();
        AlertDialog alertboxDialog = showAlertDialogue(context);
        alertboxDialog.setCancelable(isCancelable);
        alertboxDialog.setTitle(title);
        alertboxDialog.setMessage(message);
        alertboxDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getResources().getText(R.string.ok), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        try
        {
            alertboxDialog.show();
        }
        catch (Exception e)
        {

        }
    }


    private static AlertDialog showAlertDialogue(Context context)
    {
        if (Build.VERSION.SDK_INT >= 21)
        {
            alertDialog = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert).create();
        } else
        {
            alertDialog = new AlertDialog.Builder(context).create();
        }
        return alertDialog;
    }

    public static void dismissAlertDialog()
    {
        try
        {
            if (alertDialog != null)
                alertDialog.dismiss();
        }
        catch (Exception e)
        {

        }
    }

    public static void showBottomProgressDialoge(Activity context, String text)
    {
        hideBottomProgressDialoge();
        View view = context.getLayoutInflater().inflate(R.layout.bottom_sheet_progress, null);
        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(false);
        TextView tvtext = view.findViewById(R.id.tv_text);
        if (text != null) tvtext.setText(text);
        bottomSheetDialog.show();

    }

    public static void hideBottomProgressDialoge()
    {
        try
        {
            if (bottomSheetDialog != null)
                if (bottomSheetDialog.isShowing()) bottomSheetDialog.cancel();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
