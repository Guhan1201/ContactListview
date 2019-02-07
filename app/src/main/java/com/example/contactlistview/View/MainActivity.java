package com.example.contactlistview.View;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.contactlistview.Model.ContactListDetail;
import com.example.contactlistview.Model.ProfileDetail;
import com.example.contactlistview.Model.UIcallback;
import com.example.contactlistview.R;
import com.example.contactlistview.ViewModel.ContactListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout relative = (RelativeLayout) findViewById(R.id.relative);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);

        final TextView errormessage = (TextView) findViewById(R.id.text);
        errormessage.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        ContactListViewModel contactListViewModel = new ContactListViewModel();
        contactListViewModel.fetchdata(new UIcallback() {
            @Override
            public void onSuccess(final List<ContactListDetail> contactListDetailList) {
                ContactListAdapter adapter = new ContactListAdapter(contactListDetailList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);
                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        ContactListAdapter adapter = new ContactListAdapter(contactListDetailList);
                        recyclerView.setAdapter(adapter);
                        swipeRefreshLayout.setRefreshing(false);

                    }
                });
            }

            @Override
            public void onSuccessObject(ProfileDetail profileDetail) {

            }

            @Override
            public void errorResponse(String error) {

                errormessage.setVisibility(View.VISIBLE);
                errormessage.setText("No internet connection");
                progressBar.setVisibility(View.GONE);
                noConnectionRefresh(swipeRefreshLayout);
            }
        });
    }
    public void noConnectionRefresh(final SwipeRefreshLayout swipeRefreshLayout){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

}
