package com.sabaindomedika.stscustomer.basecommon;

import android.view.View;
import butterknife.ButterKnife;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public abstract class BaseViewHolder {

  public BaseViewHolder(View view) {
    ButterKnife.bind(this, view);
    view.setTag(this);
  }
}
