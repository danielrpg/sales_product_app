package com.e.salesapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class ProductDetailsActivity extends AppCompatActivity {

    CarouselView mCarouselView;
    String[] sampleImages = {"https://www.worldatlas.com/r/w728-h425-c728x425/upload/46/cb/e1/shutterstock-252338818.jpg",
                            "https://previews.123rf.com/images/aiselin/aiselin1809/aiselin180900081/107486908-lots-of-fruits-strawberries-blueberries-mango-orange-grapefruit-banana-apple-grapes-kiwis-on-the-whi.jpg",
                            "https://previews.123rf.com/images/cooperr007/cooperr0071711/cooperr007171100268/89193608-image-of-a-lot-of-fruits-and-vegetables-closeup.jpg"};

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
