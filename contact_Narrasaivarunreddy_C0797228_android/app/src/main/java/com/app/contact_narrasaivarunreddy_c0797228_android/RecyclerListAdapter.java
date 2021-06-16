package com.app.contact_narrasaivarunreddy_c0797228_android;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.BindViewHolder> {

    List<Contact> contactList;

    public RecyclerListAdapter(List<Contact> contactList){
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public BindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new BindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BindViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void updateList(List<Contact> searchList) {
        this.contactList = searchList;
        notifyDataSetChanged();
    }

    class BindViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvEmail, tvPhone,tvAddress;
        ImageView imgMore;
        public BindViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bindData(int position){
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            imgMore =  itemView.findViewById(R.id.imgMenu);

            tvName.setText(String.format("%s %s", contactList.get(position).getFName(), contactList.get(position).getLName()));
            tvEmail.setText(contactList.get(position).getEmailAddress());
            tvAddress.setText(contactList.get(position).getAddress());
            tvPhone.setText(contactList.get(position).getPhoneNumber());

            imgMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayAdapter arrayAdapter =  new ArrayAdapter(v.getContext(), android.R.layout.simple_list_item_1, new String[]{"Edit", "Delete"});
                    ListPopupWindow listPopupWindow = new ListPopupWindow(v.getContext());
                    listPopupWindow.setAdapter(arrayAdapter);

                    listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int index, long id) {

                            if(index == 0){
                                Intent intent = new Intent(v.getContext(),EditContactActivity.class);
                                intent.putExtra("DATA", contactList.get(position));
                                v.getContext().startActivity(intent);
                            }else{
                                ContactDatabase.getInstance(v.getContext().getApplicationContext()).contactDao().deleteContact(contactList.get(position));
                                contactList.remove(position);
                                notifyItemRemoved(position);
                            }
                            listPopupWindow.dismiss();

                        }
                    });
                    listPopupWindow.setAnchorView(v);
                    listPopupWindow.setWidth(300);
                    listPopupWindow.show();
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ArrayAdapter arrayAdapter =  new ArrayAdapter(v.getContext(), android.R.layout.simple_list_item_1, new String[]{"Call", "Email"});
                    ListPopupWindow listPopupWindow = new ListPopupWindow(v.getContext());
                    listPopupWindow.setAdapter(arrayAdapter);


                    listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                            try {
                                Intent intent;
                                if (index == 0) {
                                    String phone[] = contactList.get(position).getPhoneNumber().split(",");
                                    intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone[0], null));
                                } else {
                                    String addresses[] = contactList.get(position).getEmailAddress().split(",");

                                    intent = new Intent(Intent.ACTION_SENDTO);
                                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                                    intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                                }
                                v.getContext().startActivity(intent);
                            }catch(Exception e){}
                            listPopupWindow.dismiss();
                        }
                    });

                    listPopupWindow.setAnchorView(itemView);
                    listPopupWindow.show();
                    return false;
                }
            });

        }
    }
}
