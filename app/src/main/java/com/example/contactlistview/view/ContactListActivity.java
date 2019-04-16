package com.example.contactlistview.view;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.contactlistview.R;
import com.example.contactlistview.ViewModel.ContactListViewModel;
import com.example.contactlistview.databinding.ActivityMainBinding;
import com.example.contactlistview.events.ContactDetailEvent;
import com.example.contactlistview.model.Contact;
import java.util.ArrayList;
import java.util.List;
public class ContactListActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    private ContactListViewModel viewModel;
    private ObservableBoolean progress;
    private ObservableBoolean errorMessage;
    private ContactListAdapter adapter;

    //TODO Rename it to "binding". Unless there is one more binding variable in the same class. names can be short and meaningful.  DONE
    //private
   private ActivityMainBinding binding;
   private List<Contact> contactlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(ContactListViewModel.class);
        mLayoutManager = new LinearLayoutManager(this);
        errorMessage = new ObservableBoolean();
        progress = new ObservableBoolean(true);
        contactlist = new ArrayList<>();
        adapter = new ContactListAdapter(contactlist, viewModel);

        binding.setProgress(progress);
        binding.setError(errorMessage);

        binding.recylerview.setAdapter(adapter);
        binding.recylerview.setLayoutManager(mLayoutManager);

        populateRecylerView();
        donavigation();

        binding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateRecylerView();
                binding.swiperefreshlayout.setRefreshing(false);
            }
        });
    }


    private void donavigation() {
        //TODO Live data name change please

        viewModel.contactDetailEventMutableLiveData.observe(this, new Observer<ContactDetailEvent>() {
            @Override
            public void onChanged(@Nullable ContactDetailEvent contactDetailEvent) {
                Intent intent = new Intent(ContactListActivity.this,ContactDetailActivity.class);
                intent.putExtra("id", contactDetailEvent.getId());
                startActivity(intent);
            }
        });
    }

    public void populateRecylerView() {

        viewModel.contactListLiveData.observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contactList) {
                adapter.addItemToList(contactList);
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.errorLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                binding.errortext.setText("No internet connection");
                errorMessage.set(true);
            }
        });
        progress.set(false);
    }
}
