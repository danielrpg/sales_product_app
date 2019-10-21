package com.e.salesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.salesapp.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<String> productList;
    private Context context;

    public ProductAdapter (List<String> productList,Context context){
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //View productCard = inflater.inflate(R.layout.product_card,parent,false);
        //return new ProductHolder(productCard);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        String product = productList.get(position);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{

        ProductHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
