package com.sabaindomedika.stscustomer.features.ticket;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseDialogFragment;

/**
 * Created by Fajar Rianda on 14/05/2017.
 */
public class CloseTicketFragment extends BaseDialogFragment {

  public static final int DIALOG_REQUEST_CODE = 0x511;
  @Bind(R.id.btnSubmit) Button btnSubmit;
  String ticketId;
  int position;

  public static CloseTicketFragment newInstance(String ticketId, int position) {
    Bundle bundle = new Bundle();
    bundle.putString(String.class.getSimpleName(),ticketId);
    bundle.putInt(int.class.getSimpleName(), position);
    CloseTicketFragment closeTicketFragment = new CloseTicketFragment();
    closeTicketFragment.setArguments(bundle);
    return closeTicketFragment;
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

  @Override public void onStart() {
    super.onStart();
    init();
  }

  private void init() {
    Bundle bundle = getArguments();
    ticketId = bundle.getString(String.class.getSimpleName());
    position = bundle.getInt(int.class.getSimpleName());
  }

  @OnClick(R.id.btnSubmit) public void closeTicket(){
    dismiss();
  }

  @Override public void dismiss() {
    Intent intent = new Intent().putExtra(int.class.getSimpleName(),position);
    getTargetFragment().onActivityResult(getTargetRequestCode(), getBaseActivity().RESULT_OK,
        intent);
    super.dismiss();
  }
}
