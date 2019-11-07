package com.e.salesapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.e.salesapp.adapters.ProductPurchasedAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductPurchasedListActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_purchased_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.cartListToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView rvMovies = findViewById(R.id.rv_cartProducts);
        List<String> cartList = new ArrayList<>();

        //fake data
        cartList.add("Fruits");
        cartList.add("Vegetables");
        cartList.add(getIntent().getStringExtra("product"));

        ProductPurchasedAdapter mProductAdapter = new ProductPurchasedAdapter(cartList, this);

        rvMovies.setHasFixedSize(true);
        rvMovies.setAdapter(mProductAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
