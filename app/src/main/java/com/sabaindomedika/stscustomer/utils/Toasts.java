package com.sabaindomedika.stscustomer.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Fajar Rianda on 01/02/2017.
 */
public class Toasts {
  public Toasts() {
  }

  public static void show(Context context, @StringRes int text) {
    show(context, context.getString(text));
  }

  public static void show(Context context, String text) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
  }

  public static void show(Context context, @LayoutRes int resId, int gravity) {
    View view = LayoutInflater.from(context).inflate(resId, (ViewGroup) null);
    Toast toast = new Toast(context);
    toast.setView(view);
    toast.setGravity(gravity, 0, 0);
    toast.setDuration(Toast.LENGTH_SHORT);
    toast.show();
  }
}