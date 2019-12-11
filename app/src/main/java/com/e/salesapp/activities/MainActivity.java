package com.e.salesapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.e.salesapp.R;
import com.e.salesapp.fragments.CategoriesListFragment;
import com.e.salesapp.fragments.ProductListFragment;
import com.e.salesapp.helpers.ConnectionSQLiteHelper;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import static com.e.salesapp.utilities.CartUtility.TABLE_CART;
import static com.e.salesapp.utilities.CartUtility.VERSION;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CategoriesListFragment.OnCategorySelected {

    private FragmentManager mFragmentManager;
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        emptyCart();
        initializeComponents();
        showMainView();
    }

    private void showMainView() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .replace(R.id.mainActivityContainer, new CategoriesListFragment(this))
                .commit();
    }

    private void initializeComponents() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawer = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    private void emptyCart() {
        ConnectionSQLiteHelper connectionSQLiteHelper = new ConnectionSQLiteHelper(this, TABLE_CART, null, VERSION);
        connectionSQLiteHelper.dropTable(TABLE_CART);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent optionIntent;
        switch (id) {
            case R.id.nav_gallery:
                optionIntent = new Intent(this, InformationActivity.class);
                startActivity(optionIntent);
                break;
            case R.id.nav_slideshow:
                optionIntent = new Intent(this, VoucherDetailsActivity.class);
                startActivity(optionIntent);
                break;
            case R.id.nav_tools:
                optionIntent = new Intent(this, ProductDetailsActivity.class);
                startActivity(optionIntent);
                break;
            default:
                break;

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_navbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_cart) {
            Intent cartIntent = new Intent(this, ProductPurchasedListActivity.class);
            startActivity(cartIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickCategory() {
        mFragmentManager.beginTransaction()
                .replace(R.id.mainActivityContainer, new ProductListFragment())
                .addToBackStack(null)
                .commit();
    }
}