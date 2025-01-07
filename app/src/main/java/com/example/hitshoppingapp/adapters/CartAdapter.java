package com.example.hitshoppingapp.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hitshoppingapp.models.Item;
import com.example.hitshoppingapp.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private ArrayList<Item> shoppingCart;

    public CartAdapter(ArrayList<Item> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        EditText editCountView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView);
            editCountView = itemView.findViewById(R.id.editCountView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        Item item = shoppingCart.get(position);

        holder.textViewName.setText(shoppingCart.get(position).getName());
        holder.editCountView.setText(String.valueOf(shoppingCart.get(position).getCount()));
        holder.imageView.setImageResource((int)shoppingCart.get(position).getImage());

        holder.editCountView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    long newCount = Long.parseLong(s.toString());
                    item.setCount(newCount);
                } catch (NumberFormatException e) {
                    item.setCount(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingCart.size();
    }
}