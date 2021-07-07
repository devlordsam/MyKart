package com.lordsam.mykart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lordsam.mykart.R
import com.lordsam.mykart.databinding.FragmentProductsBinding
import com.lordsam.mykart.firestore.FireStoreClass
import com.lordsam.mykart.modals.Product
import com.lordsam.mykart.ui.activities.AddProductActivity
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : BaseFragment() {

    private lateinit var productsViewModel: ProductsViewModel
    private var _binding: FragmentProductsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        getProductListFromFireStore()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productsViewModel =
            ViewModelProvider(this).get(ProductsViewModel::class.java)

        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_product_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_add_product) {
            startActivity(Intent(activity, AddProductActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getProductListFromFireStore() {
        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        // Call the function of Firestore class.
        FireStoreClass().getProductsList(this@ProductsFragment)
    }

    fun successProductsListFromFireStore(productsList: ArrayList<Product>) {

        // Hide Progress dialog.
        hideProgressDialog()

        for(i in productsList)
            Log.i("productName", i.title)

//        if (productsList.size > 0) {
//            rv_my_product_items.visibility = View.VISIBLE
//            tv_no_products_found.visibility = View.GONE
//
//            rv_my_product_items.layoutManager = LinearLayoutManager(activity)
//            rv_my_product_items.setHasFixedSize(true)
//
//            val adapterProducts =
//                MyProductsListAdapter(requireActivity(), productsList, this@ProductsFragment)
//            rv_my_product_items.adapter = adapterProducts
//        } else {
//            rv_my_product_items.visibility = View.GONE
//            tv_no_products_found.visibility = View.VISIBLE
//        }
    }
}