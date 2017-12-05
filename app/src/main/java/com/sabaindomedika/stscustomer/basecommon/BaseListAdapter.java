package com.sabaindomedika.stscustomer.basecommon;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sabaindomedika.stscustomer.utils.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Fajar Rianda on 04/04/2017.
 */
public class BaseListAdapter<T> extends BaseAdapter {

  protected List<T> listData = new ArrayList<>();
  protected Context context;
  protected LayoutInflater inflater;
  private ViewGroup parent;
  private int lastPosition = -1;

  public BaseListAdapter(Context context) {
    this.context = context;
    inflater = LayoutInflater.from(this.context);
  }

  @Override public int getCount() {
    return listData.size();
  }

  @Override public T getItem(int position) {
    return listData.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    this.parent = parent;
    return convertView;
  }

  public List<T> getListData() {
    return listData;
  }

  public void pushData(final List<T> data) {
    pushData(data, 0);
  }

  public void pushData(final List<T> data, int page) {
    applyData(data, page, false);
    notifyDataSetChanged();
  }

  public void pushDataReverse(final List<T> data, int page) {
    Collections.reverse(data);
    applyData(data, page, true);
    notifyDataSetChanged();
  }

  /* Applying data to list */
  protected void applyData(final List<T> data, int page, boolean reverse) {
    if (data == null) {
      Logger.log(Log.DEBUG,"TicketData null pushed");
      return;
    }

    if (isFirstPage(page)) {
      listData.clear();
      lastPosition = -1;
    }

    if (reverse) {
      listData.addAll(0, data);
    } else {
      listData.addAll(data);
    }
  }

  protected boolean isFirstPage(int page) {
    return page == 0;
  }

  /* Delete All List */
  public void clear() {
    listData.clear();
    notifyDataSetChanged();
  }

  public T getFirstData() {
    if (listData == null || listData.isEmpty()) {
      return null;
    }
    return listData.get(0);
  }

  public T getLastData() {
    if (listData == null || listData.isEmpty()) {
      return null;
    }
    return listData.get(listData.size() - 1);
  }

  public void remove(int position) {
    if (position < listData.size()) {
      listData.remove(position);
      notifyDataSetChanged();
    }
  }

  public void setData(final int position, T data) {
    if (position < listData.size()) {
      listData.set(position, data);
      notifyDataSetChanged();
    }
  }
}
