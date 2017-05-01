package com.sabaindomedika.stscustomer.utils.helper;

import android.widget.ListView;

/**
 * Created by Fajar Rianda on 01/03/2017.
 */
public class LoadMoreHelper {

  public static void page(PageHelper pageHelper) {
    pageHelper.setPage(0);
  }

  public static boolean isNeedToLoad(ListView mListView, PageHelper pageHelper) {
    if (mListView.getAdapter() == null) {
      return false;
    }

    int totalItemCount = mListView.getCount();
    int currentItem = mListView.getCount() > 0
        ? mListView.getLastVisiblePosition()
        + mListView.getFooterViewsCount()
        + mListView.getHeaderViewsCount()
        : 0;

    int minItem = mListView.getFooterViewsCount() + mListView.getHeaderViewsCount();

    return totalItemCount > minItem
        && currentItem >= totalItemCount
        && !pageHelper.isLastPage()
        && !pageHelper.isOnHold();
  }

  public static boolean isNeedToLoadTop(ListView mListView, PageHelper pageHelper) {
    if (mListView.getAdapter() == null) {
      return false;
    }

    int totalItemCount = mListView.getCount();
    int currentItem = mListView.getFirstVisiblePosition();

    int minItem = mListView.getFooterViewsCount() + mListView.getHeaderViewsCount();

    return totalItemCount > minItem
        && currentItem < minItem
        && !pageHelper.isLastPage()
        && !pageHelper.isOnHold();
  }
}
