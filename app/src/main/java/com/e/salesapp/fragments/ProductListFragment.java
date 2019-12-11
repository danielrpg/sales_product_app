package com.e.salesapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.salesapp.activities.ProductDetailsActivity;
import com.e.salesapp.activities.ProductPurchasedListActivity;
import com.e.salesapp.R;
import com.e.salesapp.adapters.ProductAdapter;
import com.e.salesapp.helpers.ConnectionSQLiteHelper;
import com.e.salesapp.models.Product;

import java.util.ArrayList;
import java.util.List;

import static com.e.salesapp.utilities.CartUtility.TABLE_CART;
import static com.e.salesapp.utilities.CartUtility.VERSION;

public class ProductListFragment extends Fragment
        implements ProductAdapter.OnProductClickListener {
    private static final int FIRST_POSITION = 0;
    List<Product> mProductList = new ArrayList<>();
    ProductAdapter mProductAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mProductListRoot = inflater.inflate(R.layout.fragment_product_list, container, false);
        RecyclerView rvMovies = mProductListRoot.findViewById(R.id.rv_products);

        //TODO retrieve data from API
        //fake data
        Product product1 = new Product("apple", 12, 34.5, "fruits");
        Product product2 = new Product("orange", 23, 35.6, "fruits");
        Product product3 = new Product("watermelon", 34, 12.5, "fruits");
        Product product4 = new Product("banana", 45, 56.5, "fruits");

        mProductList.add(product1);
        mProductList.add(product2);
        mProductList.add(product3);
        mProductList.add(product4);
        mProductAdapter = new ProductAdapter(mProductList, getContext(), this);

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
        Product product = mProductList.get(position);
        boolean isAdded = false;

        String productName = product.getProductName();
        int quantity = product.getQuantity();
        Double price = product.getPrice();
        String category = product.getCategory();

        ConnectionSQLiteHelper connectionSQLiteHelper = new ConnectionSQLiteHelper(getActivity(), TABLE_CART, null, VERSION);
        //TODO verify if product is available
        List<Product> productsFound = connectionSQLiteHelper.getProductById(product);
        Product cartProduct = null;

        if (!productsFound.isEmpty()) {
            cartProduct = productsFound.get(FIRST_POSITION);
        }

        if (cartProduct != null) {
            quantity = quantity + cartProduct.getQuantity();
            isAdded = connectionSQLiteHelper.updateProduct(productName, quantity, price, category, cartProduct);
        } else {
            isAdded = connectionSQLiteHelper.insertProduct(productName, quantity, price, category);
        }

        if (isAdded) {
            Toast.makeText(getContext(), quantity + " " + productName + " added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}