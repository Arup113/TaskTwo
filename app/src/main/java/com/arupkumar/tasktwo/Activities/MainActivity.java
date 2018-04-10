package com.arupkumar.tasktwo.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.arupkumar.tasktwo.Adapter.UsersListAdapter;
import com.arupkumar.tasktwo.Model.UserDetails;
import com.arupkumar.tasktwo.R;
import com.arupkumar.tasktwo.Utils.DividerItemDecoration;
import com.arupkumar.tasktwo.Volley.OrderListRequest;
import com.arupkumar.tasktwo.Volley.VolleyUtils;
import com.arupkumar.tasktwo.Volley.Implementation.IRequestResponseCallBack;

/**
 * Created by Arup Kumar Pramanik on 04/10/2018.
 */

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getName().toString();
    private UserDetails userDetailsList;
    private Activity mActivity;
    private Context mContext;
    private UsersListAdapter mAdapter;

    // UI references.
    private LinearLayout activity_list_lv_progress_bar;
    private RecyclerView activity_list_recycler_view;
    private TextView activity_list_tv_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestUserDetailList();
    }

    @Override
    void setContentView() {
        setContentView(R.layout.activity_main);
        mActivity = this;
        mContext = getApplicationContext();
    }

    @Override
    void setupActionBar() {

    }


    @Override
    void initializeEditTextComponents() {

    }

    @Override
    void initializeButtonComponents() {

    }

    @Override
    void initializeTextViewComponents() {
        activity_list_tv_empty = findViewById(R.id.activity_list_tv_empty);
    }

    @Override
    void initializeImageViewComponents() {

    }

    @Override
    void initializeOtherViewComponents() {
        activity_list_lv_progress_bar = findViewById(R.id.activity_list_lv_progress_bar);
        activity_list_recycler_view = findViewById(R.id.activity_list_recycler_view);
        activity_list_recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        activity_list_recycler_view.setLayoutManager(mLayoutManager);
        activity_list_recycler_view.addItemDecoration(new DividerItemDecoration(this));
        activity_list_recycler_view.setItemAnimator(new DefaultItemAnimator());
        activity_list_lv_progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    void initializeViewComponentsEventListeners() {

    }

    @Override
    void removeViewComponentsEventListeners() {

    }

    @Override
    void exitThisWithAnimation() {
        finish();
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_out);
    }

    @Override
    void startNextWithAnimation(Intent intent, boolean isFinish) {
        if (isFinish) {
            finish();
        }
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in,
                R.anim.trans_left_out);
    }

    @Override
    public void onClick(View v) {

    }

    private void requestUserDetailList() {
        activity_list_lv_progress_bar.setVisibility(View.VISIBLE);
        new OrderListRequest("req_user_list", null, new IRequestResponseCallBack() {
            @Override
            public void onSuccess(Object[] t) {
                userDetailsList = (UserDetails) t[0];
                loadUserLists();
            }

            @Override
            public void onError(VolleyError volleyError) {
                volleyError.printStackTrace();
                loadUserLists();
                VolleyUtils.showVolleyResponseError(mActivity, volleyError, false);
            }
        }).doIt();
    }

    private void loadUserLists() {
        if (userDetailsList.getUsers().size() == 0) {
            activity_list_lv_progress_bar.setVisibility(View.GONE);
            activity_list_recycler_view.setVisibility(View.GONE);
            activity_list_tv_empty.setVisibility(View.VISIBLE);
        } else {
            mAdapter = new UsersListAdapter(mContext, userDetailsList.getUsers());
            activity_list_recycler_view.setAdapter(mAdapter);
            activity_list_lv_progress_bar.setVisibility(View.GONE);
            activity_list_tv_empty.setVisibility(View.GONE);
            activity_list_recycler_view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_with_search, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(this);
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    if (!query.equals(null) && !query.isEmpty()) {
                        mAdapter.getFilter().filter(query, new Filter.FilterListener() {
                            @Override
                            public void onFilterComplete(int count) {
                                if (mAdapter.getItemCount() != 0) {
                                    activity_list_tv_empty.setVisibility(View.GONE);
                                    activity_list_recycler_view.setVisibility(View.VISIBLE);
                                } else {
                                    activity_list_tv_empty.setVisibility(View.VISIBLE);
                                    activity_list_recycler_view.setVisibility(View.GONE);
                                }
                            }
                        });

                    } else {
                        mAdapter = new UsersListAdapter(mContext, userDetailsList.getUsers());
                        activity_list_recycler_view.setAdapter(mAdapter);
                        if (mAdapter.getItemCount() != 0) {
                            activity_list_tv_empty.setVisibility(View.GONE);
                            activity_list_recycler_view.setVisibility(View.VISIBLE);
                        } else {
                            activity_list_tv_empty.setVisibility(View.VISIBLE);
                            activity_list_recycler_view.setVisibility(View.GONE);
                        }
                    }
                } catch (NullPointerException ex) {
                    activity_list_tv_empty.setVisibility(View.VISIBLE);
                    activity_list_recycler_view.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    if (!newText.equals(null) && !newText.isEmpty()) {
                        mAdapter.getFilter().filter(newText, new Filter.FilterListener() {
                            @Override
                            public void onFilterComplete(int count) {
                                if (mAdapter.getItemCount() != 0) {
                                    activity_list_tv_empty.setVisibility(View.GONE);
                                    activity_list_recycler_view.setVisibility(View.VISIBLE);
                                } else {
                                    activity_list_tv_empty.setVisibility(View.VISIBLE);
                                    activity_list_recycler_view.setVisibility(View.GONE);
                                }
                            }
                        });
                    } else {
                        mAdapter = new UsersListAdapter(mContext, userDetailsList.getUsers());
                        activity_list_recycler_view.setAdapter(mAdapter);
                        if (mAdapter.getItemCount() != 0) {
                            activity_list_tv_empty.setVisibility(View.GONE);
                            activity_list_recycler_view.setVisibility(View.VISIBLE);
                        } else {
                            activity_list_tv_empty.setVisibility(View.VISIBLE);
                            activity_list_recycler_view.setVisibility(View.GONE);
                        }
                    }
                } catch (NullPointerException ex) {
                    activity_list_tv_empty.setVisibility(View.VISIBLE);
                    activity_list_recycler_view.setVisibility(View.GONE);
                }
                return false;
            }
        });
        return true;
    }
}
