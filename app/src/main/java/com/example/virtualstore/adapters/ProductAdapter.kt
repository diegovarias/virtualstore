package com.example.virtualstore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.virtualstore.R
import com.example.virtualstore.models.Product

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.activity_item, parent, false)) {
        private var productNameView: TextView? = null
        private var productPriceView: TextView? = null

        init {
            productNameView = itemView.findViewById(R.id.productName)
            productPriceView = itemView.findViewById(R.id.productPrice)
        }

        fun bind(product: Product) {
            productNameView?.text = product.name
            productPriceView?.text = "$${product.price}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: Product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size
}
