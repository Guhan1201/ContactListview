package com.example.contactlistview.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.contactlistview.Model.ContactListDetail;
import com.example.contactlistview.Model.ProfileDetail;
import com.example.contactlistview.Model.UIcallback;
import com.example.contactlistview.R;
import com.example.contactlistview.ViewModel.ContactListViewModel;

import java.util.List;

//ContactDetailActivity
public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        Toolbar topToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(topToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollview);
        final TextView errormessage = (TextView) findViewById(R.id.text);
        topToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        errormessage.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.INVISIBLE);
        ContactListDetail contactListDetail = (ContactListDetail) getIntent().getParcelableExtra("object");
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(contactListDetail.getName());
        String letter = String.valueOf(contactListDetail.getName().charAt(0));
        ImageView roundimage = (ImageView) findViewById(R.id.image);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(letter, contactListDetail.getColor());
        roundimage.setImageDrawable(drawable);
        Log.e("id", contactListDetail.getAddress());
        ContactListViewModel contactListViewModel = new ContactListViewModel(contactListDetail.getId());
        contactListViewModel.getProfile(new UIcallback() {
            @Override
            public void onSuccess(List<ContactListDetail> contactListDetailList) {

            }

            @Override
            public void onSuccessObject(ProfileDetail contactListDetail) {
                TextView username, email, address, id, language, mobile, time;
                username = (TextView) findViewById(R.id.username);
                email = (TextView) findViewById(R.id.email);
                address = (TextView) findViewById(R.id.address);
                id = (TextView) findViewById(R.id.id);
                language = (TextView) findViewById(R.id.language);
                mobile = (TextView) findViewById(R.id.mobile);
                time = (TextView) findViewById(R.id.time);
                progressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                username.setText(contactListDetail.getName());
                email.setText(contactListDetail.getEmail());
                address.setText(contactListDetail.getAddress());
                id.setText(contactListDetail.getId());
                language.setText(contactListDetail.getLanguage());
                mobile.setText(contactListDetail.getMobile());
                time.setText(contactListDetail.getTime_zone());
            }

            @Override
            public void errorResponse(String error) {
                errormessage.setVisibility(View.VISIBLE);
                errormessage.setText("No internet connection");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onClick(int position) {

            }
        });
    }


}
