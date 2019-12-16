package com.e.salesapp.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.e.salesapp.R;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class ProductDetailsActivity extends AppCompatActivity {

    CarouselView mCarouselView;
    String[] sampleImages ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        mCarouselView = (CarouselView) findViewById(R.id.carouselView);
        sampleImages = new String[]{getResources().getString(R.string.image),
                getResources().getString(R.string.image1),
                getResources().getString(R.string.image2)};
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
