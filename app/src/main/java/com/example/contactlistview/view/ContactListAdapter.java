package com.example.contactlistview.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.contactlistview.ContactsApp;
import com.example.contactlistview.R;
import com.example.contactlistview.databinding.CardviewBinding;
import com.example.contactlistview.model.CardClickListener;
import com.example.contactlistview.model.Contact;

import java.util.List;

//ContactListAdapter. Pls change.
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {
    public final List<Contact> contactList;
    private Context context;

    private CardClickListener callback;


    public ContactListAdapter(@NonNull List<Contact> contactList, CardClickListener callback) {
        this.contactList = contactList;
        this.callback = callback;
        this.context = ContactsApp.getAppContext();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardviewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.cardview, viewGroup, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Contact contact = this.contactList.get(i);
        myViewHolder.bind(contact);
        // myViewHolder.cardviewBinding.

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void addItemToList(List<Contact> list) {
        contactList.clear();
        contactList.addAll(list);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardviewBinding cardviewBinding;

        public MyViewHolder(CardviewBinding cardviewBinding) {
            super(cardviewBinding.getRoot());
            this.cardviewBinding = cardviewBinding;
        }

        public void bind(Contact contact) {
            cardviewBinding.setObject(contact);
            cardviewBinding.setCardClickListener(callback);
            cardviewBinding.executePendingBindings();
        }
    }
}
