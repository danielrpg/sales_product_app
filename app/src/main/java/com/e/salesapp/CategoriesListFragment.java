package com.e.salesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class CategoriesListFragment extends Fragment {

    OnCategorySelected listener;

    CategoriesListFragment(OnCategorySelected categorySelected)
    {
        this.listener = categorySelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categories_list, container, false);
        Button button = root.findViewById(R.id.category1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("category");
                listener.onClickCategory();
            }
        });
        return root;
    }

    public interface OnCategorySelected{
        void onClickCategory();
    }
}