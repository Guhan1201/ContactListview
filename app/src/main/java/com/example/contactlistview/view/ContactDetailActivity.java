package com.example.contactlistview.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.contactlistview.databinding.ActivityDetailedBinding;
import com.example.contactlistview.model.Contact;
import com.example.contactlistview.model.ProfileDetail;
import com.example.contactlistview.R;
import com.example.contactlistview.ViewModel.ContactViewModel;

//ContactDetailActivity
public class ProfileActivity extends AppCompatActivity {
    ColorGenerator generator = ColorGenerator.MATERIAL;
    ProgressBar progressBar;
    TextView errormessage, name;
    ScrollView scrollView;
    ImageView roundimage;
    String letter;
    TextView username, email, address, id, language, mobile, time;
    ActivityDetailedBinding activityDetailedBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        final ActivityDetailedBinding activityDetailedBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar topToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(topToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        scrollView = (ScrollView) findViewById(R.id.scrollview);
        errormessage = (TextView) findViewById(R.id.text);
        topToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        errormessage.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.INVISIBLE);
        Contact contact = (Contact) getIntent().getParcelableExtra("object");
        name = (TextView) findViewById(R.id.name);

        letter = String.valueOf(contact.getName().charAt(0));
        roundimage = (ImageView) findViewById(R.id.image);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(letter, generator.getRandomColor());
        roundimage.setImageDrawable(drawable);

        ContactViewModel model = ViewModelProviders.of(this).get(ContactViewModel.class);
        model = new ContactViewModel(contact.getId());

//        ContactListViewModel contactListViewModel = new ContactListViewModel(contactListDetail.getId());
//        contactListViewModel.getProfile(new UIcallback() {
//            @Override
//            public void onSuccess(List<ContactListDetail> contactList) {
//
//            }
//
//            @Override
//            public void onSuccessObject(ProfileDetail contactListDetail) {
//
//
////                username = (TextView) findViewById(R.id.username);
////                email = (TextView) findViewById(R.id.email);
////                address = (TextView) findViewById(R.id.address);
////                id = (TextView) findViewById(R.id.id);
////                language = (TextView) findViewById(R.id.language);
////                mobile = (TextView) findViewById(R.id.mobile);
////                time = (TextView) findViewById(R.id.time);
////                progressBar.setVisibility(View.GONE);
////                scrollView.setVisibility(View.VISIBLE);
////                username.setText(contactListDetail.getName());
////                email.setText(contactListDetail.getEmail());
////                address.setText(contactListDetail.getAddress());
////                id.setText(contactListDetail.getId());
////                language.setText(contactListDetail.getLanguage());
////                mobile.setText(contactListDetail.getMobile());
////                time.setText(contactListDetail.getTime_zone());
//            }
//
//            @Override
//            public void errorResponse(String error) {
//                errormessage.setVisibility(View.VISIBLE);
//                errormessage.setText("No internet connection");
//                progressBar.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onClick(int position) {
//
//            }
//        });
//    }


    }
}
