package com.example.contactlistview.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.contactlistview.Model.ContactListDetail;
import com.example.contactlistview.Model.ProfileDetail;
import com.example.contactlistview.Model.UIcallback;
import com.example.contactlistview.R;
import com.example.contactlistview.ViewModel.ContactListViewModel;

import java.util.List;

//Android studio suggested changes
//ContactListActivity

public class MainActivity extends AppCompatActivity  {

    //adapter declaration
    //list declaration
    //VM declare

    ContactListAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
    private RecyclerView recyclerView ;
    private ProgressBar progressBar ;
    private SwipeRefreshLayout swipeRefreshLayout;
    //VM initialize
    private TextView errormessage ;
    private List<ContactListDetail> contactlistDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =(RecyclerView) findViewById(R.id.recylerview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        errormessage = (TextView) findViewById(R.id.text);

        errormessage.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);


        //Adapter - initialize
        //Layoutmanager - initialize
        //recyclerview setadapter.

        //API Call
        //Onsuccess,
        // list populate.
        // adapter.notifydatasetchanged.
        populateRecylerView(recyclerView,progressBar,errormessage,swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateRecylerView(recyclerView,progressBar,errormessage,swipeRefreshLayout);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    //Should have only one listener for a view.


    /*public void noConnectionRefresh(final SwipeRefreshLayout swipeRefreshLayout){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }*/

    //Populate recyclerview. name change.
    public void populateRecylerView(final RecyclerView recyclerView , final ProgressBar progressBar, final TextView errormessage, final SwipeRefreshLayout swipeRefreshLayout){
        ContactListViewModel contactListViewModel = new ContactListViewModel();
        contactListViewModel.fetchdata(new UIcallback() {
            @Override
            public void onSuccess(final List<ContactListDetail> contactListDetailList) {
                contactlistDetailList =contactListDetailList;
                adapter = new ContactListAdapter(contactListDetailList,this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onSuccessObject(ProfileDetail profileDetail) {

            }

            @Override
            public void errorResponse(String error) {
                //Handle recyclerview visibility
                errormessage.setVisibility(View.VISIBLE);
                errormessage.setText("No internet connection");
                progressBar.setVisibility(View.GONE);
                //noConnectionRefresh(swipeRefreshLayout);
            }

            @Override
            public void onClick(int position) {
                Log.e("erreo","working");
                ContactListDetail contactListDetail = contactlistDetailList.get(position);
                Log.e("name", contactListDetail.getName());
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("object", contactListDetail);
                startActivity(intent);


            }
        });

    }



}
