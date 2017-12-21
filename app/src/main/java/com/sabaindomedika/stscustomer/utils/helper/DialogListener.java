package com.sabaindomedika.stscustomer.utils.helper;

import android.content.DialogInterface;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by raka on 10/13/17.
 */

public interface DialogListener extends MvpView {
  void dialogPositive(DialogInterface dialogInterface, String[] permission);
  void dialogNegative(DialogInterface dialogInterface);
  void dialogSetting(DialogInterface dialogInterface);

}
