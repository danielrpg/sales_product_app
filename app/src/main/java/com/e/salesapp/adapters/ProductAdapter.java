package com.e.salesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.salesapp.R;
import com.e.salesapp.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<Product> mProductList;
    private Context mContext;
    private ProductAdapter.OnProductClickListener mProductClickListener;

    public ProductAdapter(List<Product> productList, Context context, OnProductClickListener listener) {
        this.mProductList = productList;
        this.mContext = context;
        this.mProductClickListener = listener;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View productCard = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductHolder(productCard);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Product product = mProductList.get(position);
        TextView tv_name = holder.mTv_product;
        ImageView iv_poster = holder.mIv_poster;

        Picasso.get()
                .load(mContext.getResources().getString(R.string.image))
                .into(iv_poster);
        tv_name.setText(product.getProductName());

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public static final int ONE_PRODUCT = 1;
        public static final String ZERO_PRODUCTS = "0";

        private TextView mTv_product;
        private ImageView mIv_poster;
        private ImageButton mBtn_addQuantity;
        private ImageButton mBtn_decreaseQuantity;
        private ImageButton mBtn_addToCart;
        private EditText mEt_quantity;

        ProductHolder(@NonNull View itemView) {
            super(itemView);
            mIv_poster = (ImageView) itemView.findViewById(R.id.iv_poster);
            mTv_product = (TextView) itemView.findViewById(R.id.tv_productName);
            mEt_quantity = (EditText) itemView.findViewById(R.id.et_quantity);
            mBtn_decreaseQuantity = (ImageButton) itemView.findViewById(R.id.btn_decreaseQuantity);
            mBtn_addQuantity = (ImageButton) itemView.findViewById(R.id.btn_addQuantity);
            mBtn_addToCart = (ImageButton) itemView.findViewById(R.id.btn_cart);

            mEt_quantity.setText(ZERO_PRODUCTS);

            mBtn_addQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int lastQuantity = Integer.parseInt(mEt_quantity.getText().toString());
                    String quantityIncreased = String.valueOf(lastQuantity + ONE_PRODUCT);
                    mEt_quantity.setText(quantityIncreased);
                }
            });
            mBtn_decreaseQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int lastQuantity = Integer.parseInt(mEt_quantity.getText().toString());
                    if (lastQuantity > 0) {
                        String quantityDecreased = String.valueOf(lastQuantity - ONE_PRODUCT);
                        mEt_quantity.setText(quantityDecreased);
                    }
                }
            });
            mBtn_addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mProductClickListener.onAddToCart(getAdapterPosition());
                }
            });
            mIv_poster.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mProductClickListener.onImageClick(getAdapterPosition());
        }
    }

    public interface OnProductClickListener {
        void onImageClick(int position);
        void onAddToCart(int position);
    }
}
