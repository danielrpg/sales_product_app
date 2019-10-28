package com.e.salesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class CategoriesListFragment extends Fragment {

    OnCategorySelected mCategoryListener;

    CategoriesListFragment(OnCategorySelected categorySelected)
    {
        this.mCategoryListener = categorySelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRoot = inflater.inflate(R.layout.fragment_categories_list, container, false);
        Button btn_category = mRoot.findViewById(R.id.btn_category);
        btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCategoryListener.onClickCategory();
            }
        });
        return mRoot;
    }

    public interface OnCategorySelected{
        void onClickCategory();
    }
}