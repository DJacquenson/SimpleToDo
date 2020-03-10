package com.example.simpletodo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnClickListener{
        void onItemClicked(int position);
    }

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }
    List<String> items;

    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener  longClickListener, OnClickListener clickListener){
        this.items = items;
        this.longClickListener=longClickListener;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Usse layout inflater to inflate to view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent, false);
        // wrap it inside a view Holder and return it
         return new ViewHolder(todoView);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        // Grab the item at the position
        String item = items.get(position);
        //Bind the item into the specied view holder
        holder.bind(item);
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    // Container to provide easy access to view List
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  clickListener.onItemClicked(getAdapterPosition());

                                              }
                                          });

            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            // Remove the item from the recycler View
                            longClickListener.onItemLongClicked(getAdapterPosition());
                            //OnLongClickListener.onItemLongClicked(getAdapterPosition());
                            return true;
                        }
                    });
        }
    }
}