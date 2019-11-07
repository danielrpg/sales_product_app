package com.e.salesapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class ProductDetailsActivity extends AppCompatActivity {

    CarouselView mCarouselView;
    String[] sampleImages = {   getResources().getString(R.string.image),
                                getResources().getString(R.string.image1),
                                getResources().getString(R.string.image2)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        mCarouselView = (CarouselView) findViewById(R.id.carouselView);
        mCarouselView.setPageCount(sampleImages.length);
        mCarouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(sampleImages[position]).into(imageView);
        }
    };
}
