package com.sabaindomedika.stscustomer.features.ticket;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseDialogFragment;

/**
 * Created by Fajar Rianda on 14/05/2017.
 */
public class CloseTicketFragment extends BaseDialogFragment {

  public static CloseTicketFragment newInstance() {
    return new CloseTicketFragment();
  }

  @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = new Dialog(context);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(getContentView());
    return dialog;
  }

  @SuppressLint("InflateParams") private View getContentView() {
    View view = inflater.inflate(R.layout.fragment_close_ticket, null);
    ButterKnife.bind(this, view);
    return view;
  }
}
