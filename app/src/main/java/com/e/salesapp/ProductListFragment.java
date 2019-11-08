package com.e.salesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.e.salesapp.adapters.ProductAdapter;
import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment
        implements ProductAdapter.OnProductClickListener{
    List<String> mProductList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mProductListRoot = inflater.inflate(R.layout.fragment_product_list, container, false);
        RecyclerView rvMovies = mProductListRoot.findViewById(R.id.rv_products);

        //fake data
        mProductList.add("Fruits");
        mProductList.add("Vegetables");
        mProductList.add("Product1");

        ProductAdapter mProductAdapter = new ProductAdapter(mProductList, getContext(), this);

        rvMovies.setHasFixedSize(true);
        rvMovies.setAdapter(mProductAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        return mProductListRoot;
    }

    @Override
    public void onImageClick(int position) {
        Intent productDetailsIntent = new Intent(getActivity(), ProductDetailsActivity.class);
        startActivity(productDetailsIntent);
    }

    @Override
    public void onAddToCart(int position) {
        Intent cartIntent = new Intent(getActivity(), ProductPurchasedListActivity.class);
        cartIntent.putExtra("product",mProductList.get(position));
        startActivity(cartIntent);
    }
}