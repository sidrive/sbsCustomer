package com.sabaindomedika.stscustomer.basecommon;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;

/**
 * Created by Fajar Rianda on 14/05/2017.
 */
public class BaseDialogFragment extends DialogFragment {

  protected Context context;
  protected LayoutInflater inflater;

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    context = activity;
    inflater = LayoutInflater.from(context);
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  protected BaseActivity getBaseActivity() {
    return (BaseActivity) getActivity();
  }

  public FragmentManager getBaseFragmentManager() {
    if (Build.VERSION.SDK_INT >= 17) return getChildFragmentManager();
    return getFragmentManager();
  }

}
