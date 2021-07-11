package com.lordsam.mykart.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lordsam.mykart.R
import com.lordsam.mykart.adapters.AddressListAdapter
import com.lordsam.mykart.firestore.FireStoreClass
import com.lordsam.mykart.modals.Address
import com.lordsam.mykart.utility.Constants
import com.lordsam.mykart.utility.SwipeToDeleteCallback
import com.lordsam.mykart.utility.SwipeToEditCallback
import kotlinx.android.synthetic.main.activity_address_list.*

class AddressListActivity : BaseActivity() {

    private var mSelectAddress: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)

        setupActionBar()

        tv_add_address.setOnClickListener {
            val intent = Intent(this@AddressListActivity, AddEditAddressActivity::class.java)
            //startActivity(intent)
            //sending a result to addeditadress such that for selecting :code 121
            startActivityForResult(intent, Constants.ADD_ADDRESS_REQUEST_CODE)
        }

        if (intent.hasExtra(Constants.EXTRA_SELECT_ADDRESS)) {
            mSelectAddress =
                intent.getBooleanExtra(Constants.EXTRA_SELECT_ADDRESS, false)
        }

        if (mSelectAddress) {
            tv_title.text = resources.getString(R.string.title_select_address)
        }

        getAddressList()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.ADD_ADDRESS_REQUEST_CODE) {

                getAddressList()
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            // A log is printed when user close or cancel the image selection.
            Log.e("Request Cancelled", "To add the address.")
        }
    }

//    override fun onResume() {
//        super.onResume()
//        getAddressList()
//
//    }


    private fun setupActionBar() {

        setSupportActionBar(toolbar_address_list_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.white_arrow)
        }

        toolbar_address_list_activity.setNavigationOnClickListener { onBackPressed() }
    }

    private fun getAddressList() {

        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        FireStoreClass().getAddressesList(this@AddressListActivity)
    }

    fun successAddressListFromFirestore(addressList: ArrayList<Address>) {

        // Hide the progress dialog
        hideProgressDialog()


        // Print all the list of addresses in the log with name.
//        for (i in addressList) {
//
//            Log.i("Name and Address", "${i.name} ::  ${i.address}")
//        }

        if (addressList.size > 0) {

            rv_address_list.visibility = View.VISIBLE
            tv_no_address_found.visibility = View.GONE

            rv_address_list.layoutManager = LinearLayoutManager(this@AddressListActivity)
            rv_address_list.setHasFixedSize(true)

            val addressAdapter = AddressListAdapter(this@AddressListActivity, addressList, mSelectAddress)
            rv_address_list.adapter = addressAdapter

            //swipe to edit handler
            if (!mSelectAddress) {
                val editSwipeHandler = object : SwipeToEditCallback(this) {
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                        val adapter = rv_address_list.adapter as AddressListAdapter
                        adapter.notifyEditItem(
                            this@AddressListActivity,
                            viewHolder.adapterPosition
                        )
                    }
                }

                //determine the touch action on RV
                val editItemTouchHelper = ItemTouchHelper(editSwipeHandler)
                editItemTouchHelper.attachToRecyclerView(rv_address_list)

                //swipe to delete handler
                val deleteSwipeHandler = object : SwipeToDeleteCallback(this) {
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                        // Show the progress dialog.
                        showProgressDialog(resources.getString(R.string.please_wait))


                        FireStoreClass().deleteAddress(
                            this@AddressListActivity,
                            addressList[viewHolder.adapterPosition].id
                        )
                    }
                }
                val deleteItemTouchHelper = ItemTouchHelper(deleteSwipeHandler)
                deleteItemTouchHelper.attachToRecyclerView(rv_address_list)
            }
        } else {
            rv_address_list.visibility = View.GONE
            tv_no_address_found.visibility = View.VISIBLE
        }
    }

    fun deleteAddressSuccess() {

        // Hide progress dialog.
        hideProgressDialog()

        Toast.makeText(
            this@AddressListActivity,
            resources.getString(R.string.err_your_address_deleted_successfully),
            Toast.LENGTH_SHORT
        ).show()

        getAddressList()
    }
}