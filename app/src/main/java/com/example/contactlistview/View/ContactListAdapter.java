package com.example.contactlistview.View;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.contactlistview.ContactList;
import com.example.contactlistview.Model.ContactListDetail;
import com.example.contactlistview.R;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {


    List<ContactListDetail> contactListDetailList;
    Context context;
    String letter;
    ColorGenerator generator = ColorGenerator.MATERIAL;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ImageView letter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            letter = (ImageView) itemView.findViewById(R.id.gmailitem_letter);
            name = (TextView) itemView.findViewById(R.id.name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    ContactListDetail contactListDetail = contactListDetailList.get(position);
                    Log.e("name", contactListDetail.getName());
                    Intent intent = new Intent(ContactList.getAppContext(), ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("object", contactListDetail);
                    context.startActivity(intent);
                }
            });

        }
    }


    public ContactListAdapter(@NonNull List<ContactListDetail> contactListDetailList) {
        this.contactListDetailList = contactListDetailList;
        this.context = ContactList.getAppContext();
    }


    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.temp_file, viewGroup, false);
        Log.e("integer", Integer.toString(i));
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ContactListDetail contactListDetail = contactListDetailList.get(position);
        holder.name.setText(contactListDetail.getName());
        Log.e("onBindViewHolder", contactListDetail.getName());
        letter = String.valueOf(contactListDetail.getName().charAt(0));
        int color = generator.getRandomColor();
        contactListDetail.setColor(color);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(letter, color);
        holder.letter.setImageDrawable(drawable);

    }

    @Override
    public int getItemCount() {
        return contactListDetailList.size();
    }


}
