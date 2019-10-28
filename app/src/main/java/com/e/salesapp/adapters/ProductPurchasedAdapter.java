package com.e.salesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.e.salesapp.R;

import java.util.List;

import static com.e.salesapp.adapters.ProductAdapter.ProductHolder.ONE_PRODUCT;

public class ProductPurchasedAdapter extends RecyclerView.Adapter<ProductPurchasedAdapter.ProductPurchasedHolder> {

    private List<String> mProductList;
    private Context mContext;

    public ProductPurchasedAdapter(List<String> mProductList, Context mContext) {
        this.mProductList = mProductList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProductPurchasedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View productCard = inflater.inflate(R.layout.product_purchased_item, parent, false);
        return new ProductPurchasedAdapter.ProductPurchasedHolder(productCard);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductPurchasedHolder holder, int position) {
        String product = mProductList.get(position);
        TextView tv_name = holder.mTv_productName;

        tv_name.setText(product);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ProductPurchasedHolder extends RecyclerView.ViewHolder{

        private TextView mTv_productName;
        private EditText mEt_quantity;
        private ImageButton mBtn_addQuantity;
        private ImageButton mBtn_decreaseQuantity;
        private TextView mTv_price;

        public ProductPurchasedHolder(@NonNull View itemView) {
            super(itemView);

            mTv_productName = (TextView) itemView.findViewById(R.id.tv_name);
            mEt_quantity = (EditText) itemView.findViewById(R.id.et_quantityPurch);
            mBtn_decreaseQuantity = (ImageButton) itemView.findViewById(R.id.btn_decreaseQuantityPurch);
            mBtn_addQuantity = (ImageButton) itemView.findViewById(R.id.btn_addQuantityPurch);

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
        }
    }
}
