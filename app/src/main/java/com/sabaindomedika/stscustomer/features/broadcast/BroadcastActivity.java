package com.sabaindomedika.stscustomer.features.broadcast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.model.broadcast.Data;
import com.sabaindomedika.stscustomer.model.broadcast.Datum;
import com.sabaindomedika.stscustomer.model.broadcast.ResponseBroadcast;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ikun on 02/02/18.
 */

public class BroadcastActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    ApiService apiService;
    BroadcastAdapter broadcastAdapter;
    @Bind(R.id.lvBroadcast)
    RecyclerView lvBroadcast;

    public static void start(Context context) {
        Intent intent = new Intent(context, BroadcastActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        ButterKnife.bind(this);
        DaggerInit.networkComponent(this).inject(this);
        setupToolbar();
        init();
        loadData();
    }

    private void init() {
        broadcastAdapter = new BroadcastAdapter(new ArrayList<Data>(0), getApplicationContext());
        lvBroadcast.setHasFixedSize(true);
        lvBroadcast.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        lvBroadcast.setAdapter(broadcastAdapter);

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Broadcast");
    }

    private void loadData() {
        apiService.getBroadcast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((ResponseBroadcast object) -> {

                    broadcastAdapter.UpdateData(object.getData());
                }, error -> {
                    if (!isFinishing()) {
                        Log.e("BroadcastActivity", "loadData: eroroeroeorer"+error);
                        ErrorHelper.thrown(error);
                    }
                });
    }
}
