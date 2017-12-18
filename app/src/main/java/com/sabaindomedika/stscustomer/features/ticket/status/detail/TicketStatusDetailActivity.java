package com.sabaindomedika.stscustomer.features.ticket.status.detail;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseDialogFragment;
import com.sabaindomedika.stscustomer.basecommon.BaseFragment;
import com.sabaindomedika.stscustomer.basecommon.BaseMvpActivity;
import com.sabaindomedika.stscustomer.constant.StatusTicketCons;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.ticket.CloseTicketFragment;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusActivity;
import com.sabaindomedika.stscustomer.features.ticket.status.adapter.ServiceReportAdapter;
import com.sabaindomedika.stscustomer.features.ticket.status.adapter.ServiceReportAdapter.onClickButton;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.model.TicketType;
import com.sabaindomedika.stscustomer.model.servicereport.Datum;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Fajar Rianda on 18/06/2017.
 */
public class TicketStatusDetailActivity extends
    BaseMvpActivity<TicketStatusDetailView, TicketStatusDetailPresenter> implements
    TicketStatusDetailView {

  String id_ticket;
  @Inject
  ApiService apiService;
  BaseFragment fragment;
  BaseDialogFragment Dfragment;
  @Bind(R.id.rcvContent)
  RecyclerView rcvContent;
  private ServiceReportAdapter serviceReportAdapter;
  @Bind(R.id.toolbar)
  Toolbar toolbar;
  @Bind(R.id.txtTicketNumber)
  TextView txtTicketNumber;
  @Bind(R.id.txtDate)
  TextView txtDate;
  @Bind(R.id.txtTicketType)
  TextView txtTicketType;
  @Bind(R.id.txtName)
  TextView txtName;
  @Bind(R.id.txtPhoneNumber)
  TextView txtPhoneNumber;
  @Bind(R.id.txtDescription)
  TextView txtDescription;
  @Bind(R.id.btncancel)
  Button btncancel;
  @Bind(R.id.btnclose)
  Button btnclose;
  @Bind(R.id.btnpdf)
  Button btnpdf;
  @Bind(R.id.txtContentAvailable)
  TextView txtContentAvailable;
  @Bind(R.id.progressBar)
  ProgressBar progressBar;

  public static void start(Context context, Ticket ticket) {
    Bundle bundle = new Bundle();
    bundle.putParcelable(Ticket.class.getSimpleName(), ticket);
    bundle.putParcelable(TicketType.class.getSimpleName(), ticket.getTicketType().getData());
    Intent intent = new Intent(context, TicketStatusDetailActivity.class);
    intent.putExtras(bundle);
    context.startActivity(intent);
  }

  @Override
  protected void onStart() {
    super.onStart();
    DaggerInit.networkComponent(this).inject(this);
  }

  @NonNull
  @Override
  public TicketStatusDetailPresenter createPresenter() {
    return new TicketStatusDetailPresenter(this);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ticket_status_detail);
    ButterKnife.bind(this);
    init();
    setupToolbar();
  }

  private void init() {
    Ticket ticket = getIntent().getExtras().getParcelable(Ticket.class.getSimpleName());
    TicketType ticketType = getIntent().getExtras().getParcelable(TicketType.class.getSimpleName());
    txtTicketType.setText(ticketType.getName());
    id_ticket = ticket.getId();
    progressBar.setVisibility(View.GONE);
    showContent(ticket);
    serviceReportAdapter = new ServiceReportAdapter(new ArrayList<Datum>(0),
        getApplicationContext(), new onClickButton() {
      @Override
      public void onClickListener(int id_report) {
        DialogFragment dialogFragment = new ServiceReportFragment(id_report, id_ticket);
        dialogFragment.show(getFragmentManager(), "Part View");
      }
    });
    rcvContent.setHasFixedSize(true);
    rcvContent.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    rcvContent.setAdapter(serviceReportAdapter);
    Log.e("init", "TicketStatusDetailActivity" + id_ticket);
  }

  @OnClick(R.id.btncancel)
  public void onCancel() {
    Builder cancelDialogBuilder = new Builder(this);
    cancelDialogBuilder.setMessage("Anda yakin membatalkan tiket ini ?");
    cancelDialogBuilder.setPositiveButton("Ya", (dialog1, which) -> {
      apiService.cancelTicket(id_ticket)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(object -> {
            if (object.getData().getStatus().equalsIgnoreCase(StatusTicketCons.CANCEL)) {
              dialog1.dismiss();
              dismiss();
            }
          }, error -> {
            dialog1.dismiss();
            ErrorHelper.thrown(error);
          });
    });
    cancelDialogBuilder.setNegativeButton("Tidak", (dialog1, which) -> {
      dialog1.dismiss();
    });
    AlertDialog cancelDialog = cancelDialogBuilder.create();
    cancelDialog.show();
  }

  @OnClick(R.id.btnclose)
  public void onClose() {
    Ticket ticket = getIntent().getExtras().getParcelable(Ticket.class.getSimpleName());
    String id = ticket.getId();
    DialogFragment dialogFragment = new CloseTicketFragment(id);
    dialogFragment.show(getFragmentManager(), "Sparepart Value");
  }

  @OnClick(R.id.btnpdf)
  public void onPdfDownload() {
    btnpdf.setVisibility(View.GONE);
    progressBar.setVisibility(View.VISIBLE);
    presenter.downloadPdf(id_ticket, this);
  }

  private boolean writeResponseBodyToDisk(ResponseBody body) {
    try {
      File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "Ticket.pdf");
      Uri path = Uri.fromFile(futureStudioIconFile);
      Uri realpath = Uri.parse(path.toString().replace("file","content"));
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setDataAndType(realpath, "application/pdf");
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(intent);
      InputStream inputStream = null;
      OutputStream outputStream = null;
      try {
        byte[] fileReader = new byte[4096];

        long fileSize = body.contentLength();
        long fileSizeDownloaded = 0;

        inputStream = body.byteStream();
        outputStream = new FileOutputStream(futureStudioIconFile);

        while (true) {
          int read = inputStream.read(fileReader);

          if (read == -1) {
            break;
          }

          outputStream.write(fileReader, 0, read);

          fileSizeDownloaded += read;

          Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
        }

        outputStream.flush();

        return true;
      } catch (IOException e) {
        return false;
      } finally {
        if (inputStream != null) {
          inputStream.close();
        }

        if (outputStream != null) {
          outputStream.close();
        }
      }
    } catch (IOException e) {
      return false;
    }
}

  public void dismiss() {
    Intent i = new Intent(getApplicationContext(), TicketStatusActivity.class);
    startActivity(i);
    finish();
  }

  private void showContent(Ticket ticket) {
    Boolean is_true = false;
    Boolean is_true_close = false;
    Boolean is_true_closed = false;
    Log.e("showContent", "TicketStatusDetailActivity" + ticket.getStatus());
    if (ticket.getStatus().equals("new")) {
      is_true = true;
    }
    if (ticket.getStatus().equals("confirmed")) {
      is_true = true;
    }
    if (ticket.getStatus().equals("done")) {
      is_true_close = true;
    }
    if (ticket.getStatus().equals("closed")) {
      is_true_closed = true;
    }
    if (ticket.getStatus().equals("held")){
      progressBar.setVisibility(View.VISIBLE);
      presenter.loadServiceReport(id_ticket, this);
    }
    if (is_true == true) {
      btncancel.setVisibility(View.VISIBLE);
    }
    if (is_true_close == true) {
      btnclose.setVisibility(View.VISIBLE);
    }
    if (is_true_closed == true) {
      btnpdf.setVisibility(View.VISIBLE);
    }
    txtTicketNumber.setText(ticket.getNumber());
    txtDate.setText(ticket.getTimes().getDate());
    txtName.setText(ticket.getStaffName());
    txtPhoneNumber.setText(ticket.getStaffPhoneNumber());
    txtDescription.setText(ticket.getDescription());
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("Status Tiket");
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void showData(ResponseBody body) {
    boolean writtenToDisk = writeResponseBodyToDisk(body);
    Log.d(TAG, "file download was a success? " + writtenToDisk);
  }

  @Override
  public void showLoading(boolean firstLoad) {
    if (!firstLoad) {
      progressBar.setVisibility(View.GONE);
    }
  }

  @Override
  public void showError(Throwable throwable) {
    if (isChild()) {
      ErrorHelper.thrown(throwable);
    }
  }

  @Override
  public void showServiceReport(List<Datum> serviceReport) {
    serviceReportAdapter.UpdateData(serviceReport);
  }
}
