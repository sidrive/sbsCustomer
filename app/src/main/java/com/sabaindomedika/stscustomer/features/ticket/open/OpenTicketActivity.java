package com.sabaindomedika.stscustomer.features.ticket.open;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.features.ticket.DivisionTypeFragment;
import com.sabaindomedika.stscustomer.features.ticket.TicketTypeFragment;
import com.sabaindomedika.stscustomer.features.ticket.open.service.ServiceFormFragment;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class OpenTicketActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener{

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_open_ticket);
    ButterKnife.bind(this);
    navigateToTicketType();
  }

  public static void start(Context context){
    Intent intent = new Intent(context,OpenTicketActivity.class);
    context.startActivity(intent);
  }


  private void navigateToTicketType(){
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.frameContainer, TicketTypeFragment.newInstance()).commit();
  }

  public void navigateToDivisionType(){
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.add(R.id.frameContainer, DivisionTypeFragment.newInstance()).commit();
    getFragmentManager().addOnBackStackChangedListener(this);
  }

  public void navigateToForm(){
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.add(R.id.frameContainer, ServiceFormFragment.newInstance()).commit();
    getFragmentManager().addOnBackStackChangedListener(this);
  }

  @Override public void onBackPressed() {
    if (getFragmentManager().getBackStackEntryCount() > 0) {
      getFragmentManager().popBackStack();
    } else {
      super.onBackPressed();
    }
  }

  @Override public void onBackStackChanged() {
    if (getFragmentManager().getBackStackEntryCount() > 0) {
      getFragmentManager().removeOnBackStackChangedListener(this);
    }
  }
}

