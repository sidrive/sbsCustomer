package com.sabaindomedika.stscustomer.basecommon;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class BaseFragment extends Fragment {

  protected Context context;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  public boolean onBackPressed() {
    return false;
  }

  public BaseActivity getBaseActivity() {
    return (BaseActivity) getActivity();
  }

  public FragmentManager getBaseFragmentManager() {
    if (Build.VERSION.SDK_INT >= 17) return getChildFragmentManager();
    return getFragmentManager();
  }
}
