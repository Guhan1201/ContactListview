package com.example.contactlistview.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.contactlistview.ViewModel.ContactDetailViewModel;
import com.example.contactlistview.databinding.ActivityDetailedBinding;
import com.example.contactlistview.R;
import com.example.contactlistview.model.ContactDetail;

//ContactDetailActivity
public class ContactDetailActivity extends AppCompatActivity {

    private ActivityDetailedBinding binding;
    private ContactDetailViewModel contactDetailViewModel;
    private ObservableField<ContactDetail> contactDetailObserver = new ObservableField<>();
    private ContactDetail contactDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailed);
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        binding.setContactDetail(contactDetail);
        contactDetail = new ContactDetail();
        ContactDetailViewModel.ViewModelFactory factory = new ContactDetailViewModel.ViewModelFactory(id);
        contactDetailViewModel = ViewModelProviders.of(this,factory).get(ContactDetailViewModel.class);
        contactDetailViewModel.contactDetailMutableLD.observe(this, new Observer<ContactDetail>() {
            @Override
            public void onChanged(@Nullable ContactDetail detail) {
                contactDetail.set(detail);
                contactDetailObserver.set(contactDetail);
                Log.e("name",contactDetailObserver.get().getName());
            }
        });
    }
}
