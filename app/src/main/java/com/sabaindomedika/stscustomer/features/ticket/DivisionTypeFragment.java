package com.sabaindomedika.stscustomer.features.ticket;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseFragment;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.ticket.open.OpenTicketActivity;
import com.sabaindomedika.stscustomer.model.Division;
import com.sabaindomedika.stscustomer.utils.Preferences;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import java.util.List;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static butterknife.ButterKnife.bind;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class DivisionTypeFragment extends BaseFragment {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.lyDivisionContainer) LinearLayout lyDivisionContainer;
  @Bind(R.id.txtContentAvailable) TextView txtContentAvailable;
  @Bind(R.id.progressBar) ProgressBar progressBar;

  @Inject ApiService apiService;

  public static DivisionTypeFragment newInstance(String ticketType) {
    Bundle bundle = new Bundle();
    bundle.putString(String.class.getSimpleName(), ticketType);
    DivisionTypeFragment fragment = new DivisionTypeFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_division_type, container, false);
    bind(this, view);
    return view;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setupToolbar();
    DaggerInit.networkComponent(context).inject(this);
    loadDivison();
  }

  private void loadDivison() {
    if (Preferences.getDivision() == null) {
      apiService.getDivisions()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(division -> {
            setupLoading(true);
            Preferences.setDivision(division.getData());
            setupDivision();
          }, error -> {
            setupLoading(false);
            if (isVisible()) ErrorHelper.thrown(error);
          });
    } else {
      setupLoading(true);
      setupDivision();
    }
  }

  private void setupDivision() {
    List<Division> divisions = Preferences.getDivision();

    for (int i = 0; i < divisions.size(); i++) {
      Division division = divisions.get(i);

      View view = LayoutInflater.from(context)
          .inflate(R.layout.list_item_default, lyDivisionContainer, false);
      TextView txtDivision = ButterKnife.findById(view, R.id.txtId);
      txtDivision.setText(division.getName());
      view.setOnClickListener(v -> {
        navigatetoForm(division.getId(),division.getName());
      });

      lyDivisionContainer.addView(view, i);

    }
  }

  private void setupToolbar() {
    getBaseActivity().setSupportActionBar(toolbar);
    getBaseActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    toolbar.setNavigationOnClickListener(v -> {
      getBaseActivity().onBackPressed();
    });
  }

  private void setupLoading(boolean loadingSuccess) {
    progressBar.setVisibility(View.GONE);
    if (!loadingSuccess) {
      txtContentAvailable.setVisibility(View.VISIBLE);
      SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
      stringBuilder.append(getString(R.string.refresh));
      stringBuilder.setSpan(new ClickableSpan() {
        @Override public void onClick(View widget) {
          loadDivison();
        }
      }, 0, getString(R.string.refresh).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

      stringBuilder.setSpan(
          new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)), 0,
          getString(R.string.refresh).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      txtContentAvailable.setMovementMethod(LinkMovementMethod.getInstance());
      txtContentAvailable.setText(stringBuilder);
    } else {
      txtContentAvailable.setVisibility(View.GONE);
    }
  }

  private void navigatetoForm(String divisionId, String divisionName) {
    String ticketType = getArguments().getString(String.class.getSimpleName());
    OpenTicketActivity activity = (OpenTicketActivity) getActivity();
    activity.navigateToForm(ticketType, divisionId, divisionName);
  }
}
