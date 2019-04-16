package com.example.contactlistview.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.contactlistview.R;
import com.example.contactlistview.ViewModel.ContactViewModel;
import com.example.contactlistview.databinding.ActivityMainBinding;
import com.example.contactlistview.model.ContactDetail;

import java.util.ArrayList;
import java.util.List;

//Android studio suggested changes
//ContactListActivity - CHANGE NAME PLS

public class ListActivity extends AppCompatActivity {


    //Please initialise vars only when they are going to be used. DONE
    //TODO ALL variables should be declared final - See final variables meaning in web.
    RecyclerView.LayoutManager mLayoutManager;
    private ContactViewModel model;
    private ObservableBoolean progress;
    private ObservableBoolean error;
    ContactListAdapter adapter;

    //TODO Rename it to "binding". Unless there is one more binding variable. names can be short and meaningful.
    ActivityMainBinding mainBinding;
    //TODO Rename ContactDetail to Contact
    List<ContactDetail> contactlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ContactViewModel.ViewModelFactory factory = new ContactViewModel.ViewModelFactory("id");
        //TODO Pls change variable name to viewmodel.
        model = ViewModelProviders.of(this, factory).get(ContactViewModel.class);

        mLayoutManager = new LinearLayoutManager(this);
        progress = new ObservableBoolean(true);
        mainBinding.setProgress(progress);
        //TODO Default value is false. use empty constructor
        error = new ObservableBoolean(false);
        mainBinding.setError(error);
        //All visiblilty moves out of this class. Data bound.      DONE

        contactlist = new ArrayList<>();

        adapter = new ContactListAdapter(contactlist, model);
        mainBinding.recylerview.setAdapter(adapter);
        //function inside adapter, which receives a list,     DONE
        //function definition, list.addAll(the list u get in function parameter)    DONE
        populateRecylerView();


        mainBinding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateRecylerView();
                mainBinding.swiperefreshlayout.setRefreshing(false);
            }
        });
    }

    public void populateRecylerView() {

        //TODO Maintiain a seperate livedata for error in VM. Observer that LD in UI, and show snackbar or whatever.
        //Error handling should not be base on contactlist LD.
        model.contactListLiveData.observe(this, new Observer<List<ContactDetail>>() {
            @Override
            public void onChanged(@Nullable List<ContactDetail> contactListDetail) {
                //  contactListAdapter.addItemsToList(contactListDetails);   DONE
                //notifydatasetchanged.    DONE
                if (contactListDetail == null) {
                    mainBinding.errortext.setText("No internet connection");
                    error.set(true);
                } else {
                    contactlist = contactListDetail;
                    //TODO Remove setlayoutmanager and set it in oncreate.
                    mainBinding.recylerview.setLayoutManager(mLayoutManager);
                    //TODO. Change name to addItemsToList
                    adapter.addItem(contactlist);
                    adapter.notifyDataSetChanged();
                }
                progress.set(false);
            }

        });
    }
}
