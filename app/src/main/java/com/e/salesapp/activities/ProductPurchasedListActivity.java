package com.e.salesapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.salesapp.R;
import com.e.salesapp.adapters.ProductPurchasedAdapter;
import com.e.salesapp.helpers.ConnectionSQLiteHelper;
import com.e.salesapp.models.Product;
import com.e.salesapp.utilities.SwipeToDeleteCallback;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static com.e.salesapp.utilities.CartUtility.TABLE_CART;
import static com.e.salesapp.utilities.CartUtility.VERSION;

public class ProductPurchasedListActivity extends AppCompatActivity {

    RecyclerView rvMovies;
    Toolbar toolbar;
    List<Product> cartList = new ArrayList<>();
    ProductPurchasedAdapter mProductAdapter;
    ConstraintLayout constraintLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_purchased_list);
        toolbar = (Toolbar) findViewById(R.id.cartListToolbar);
        rvMovies = findViewById(R.id.rv_cartProducts);
        constraintLayout = findViewById(R.id.constraintLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        retrieveDataFromDatabase();


        mProductAdapter = new ProductPurchasedAdapter(cartList, this);

        rvMovies.setHasFixedSize(true);
        rvMovies.setAdapter(mProductAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        enableSwipeToDeleteAndUndo();
    }

    private void retrieveDataFromDatabase() {
        ConnectionSQLiteHelper connectionSQLiteHelper = new ConnectionSQLiteHelper(this, TABLE_CART, null, VERSION);
        ArrayList<Product> products = connectionSQLiteHelper.getProducts();

        for (Product product : products) {
            if (product != null)
                cartList.add(product);
        }

    }


    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                boolean result;

                final Product item = mProductAdapter.getData().get(position);

                ConnectionSQLiteHelper connectionSQLiteHelper = new ConnectionSQLiteHelper(getBaseContext(), TABLE_CART, null, VERSION);
                result = connectionSQLiteHelper.deleteProduct(item);

                if (result) {
                    mProductAdapter.removeItem(position);
                    Snackbar snackbar = Snackbar
                            .make(constraintLayout, "product removed.", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mProductAdapter.restoreItem(item, position);
                            rvMovies.scrollToPosition(position);
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                }
                else{
                    Snackbar snackbar = Snackbar
                            .make(constraintLayout, "something went wrong", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(rvMovies);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        rvMovies.removeAllViews();
        super.onDestroy();
    }
}
