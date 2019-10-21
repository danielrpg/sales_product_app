package com.e.salesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.salesapp.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<String> productList;
    private Context context;
    private ProductAdapter.OnProductClickListener listener;

    public ProductAdapter(List<String> productList, Context context, OnProductClickListener listener) {
        this.productList = productList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View productCard = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductHolder(productCard);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        String product = productList.get(position);
        TextView tv_name = holder.tv_product;
        tv_name.setText(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_product;
        private ImageView iv_poster;
        private ImageButton btn_addQuantity;
        private ImageButton btn_decreaseQuantity;
        private EditText et_quantity;

        ProductHolder(@NonNull View itemView) {
            super(itemView);
            iv_poster = (ImageView) itemView.findViewById(R.id.iv_poster);
            tv_product = (TextView) itemView.findViewById(R.id.tv_productName);
            et_quantity = (EditText) itemView.findViewById(R.id.et_quantity);
            btn_decreaseQuantity = (ImageButton) itemView.findViewById(R.id.btn_decreaseQuantity);
            btn_addQuantity = (ImageButton) itemView.findViewById(R.id.btn_addQuantity);

            et_quantity.setText("0");

            btn_addQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int lastQuantity = Integer.parseInt(et_quantity.getText().toString());
                    String quantityIncreased = String.valueOf(lastQuantity + 1);
                    et_quantity.setText(quantityIncreased);
                }
            });
            btn_decreaseQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int lastQuantity = Integer.parseInt(et_quantity.getText().toString());
                    if (lastQuantity > 0) {
                        String quantityDecreased = String.valueOf(lastQuantity - 1);
                        et_quantity.setText(quantityDecreased);
                    }
                }
            });
            iv_poster.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onImageClick(getAdapterPosition());
        }
    }

    public interface OnProductClickListener {
        void onImageClick(int position);
    }
}
