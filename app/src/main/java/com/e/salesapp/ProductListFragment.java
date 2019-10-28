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
        implements ProductAdapter.OnProductClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mRoot = inflater.inflate(R.layout.fragment_product_list, container, false);
        RecyclerView rvMovies = mRoot.findViewById(R.id.rv_products);
        List<String> productList = new ArrayList<>();

        //fake data
        productList.add("Fruits");
        productList.add("Vegetables");
        productList.add("Product1");

        ProductAdapter mProductAdapter = new ProductAdapter(productList, getContext(), this);

        rvMovies.setHasFixedSize(true);
        rvMovies.setAdapter(mProductAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        return mRoot;
    }

    @Override
    public void onImageClick(int position) {
        Intent productDetailsIntent = new Intent(getActivity(), ProductDetailsActivity.class);
        startActivity(productDetailsIntent);
    }
}