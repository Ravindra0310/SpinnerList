package com.example.spinnerlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.spinnerlist.Model.SampleModal
import com.example.spinnerlist.ViewModel.DataViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnCheckboxListner {

    lateinit var listViewAdapter: ExpandableListAdapter
    lateinit var parentList: List<String>
    lateinit var childList: HashMap<String, List<String>>
    var viewmodel=DataViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel.setZero()
        showList()
        setAdapter()
        populateData()
    }

    private fun setAdapter() {
        listViewAdapter = ExpandableListAdapter(this, parentList, childList,this)
        expandableListView.setAdapter(listViewAdapter)


    }

    private fun showList() {
        parentList = ArrayList()
        childList = HashMap()

        (parentList as ArrayList<String>).add("Select Account Number")
        (parentList as ArrayList<String>).add("Select Branch")
        (parentList as ArrayList<String>).add("Select Location")


        var sampleModal=loadJSONFromAsset()

        val accountList:MutableList<String> = ArrayList()
        sampleModal.filterData?.get(0)?.hierarchy?.get(0)?.accountNumber?.let { accountList.add(it) }

        val branchList:MutableList<String> = ArrayList()
        val locationList:MutableList<String> = ArrayList()

        val account= sampleModal.filterData?.get(0)?.hierarchy?.get(0)?.brandNameList
        if (account != null) {
            for(i in account){
                i?.brandName?.let { branchList.add(it) }
               for (j in i?.locationNameList!!){
                   j?.locationName?.let { locationList.add(it) }
               }
            }
        }

        childList[parentList[0]]=accountList
        childList[parentList[1]]=branchList
        childList[parentList[2]]=locationList

    }

    fun loadJSONFromAsset(): SampleModal {
        val assetManager = assets
        val ims = this.assets.open("TestJSON.json").bufferedReader().use { it.readText() }

        val gson = Gson()


        val SampleModal: SampleModal = gson.fromJson(ims, SampleModal::class.java)
        return SampleModal
    }



    override fun clickListnerChecked(title: String) {
        if(title.equals("Select Account Number")){
            viewmodel.accountNumberSelected.value= viewmodel.accountNumberSelected.value?.plus(1)
        }
        if(title.equals("Select Branch")){
            viewmodel.branchNumberSelected.value= viewmodel.branchNumberSelected.value?.plus(1)
        }
        if(title.equals("Select Location")){
            viewmodel.locationNumberSelected.value= viewmodel.locationNumberSelected.value?.plus(1)
        }
    }

    override fun clickListnerUnChecked(title: String) {
        if(title.equals("Select Account Number")){
            viewmodel.accountNumberSelected.value= viewmodel.accountNumberSelected.value?.minus(1)
        }
        if(title.equals("Select Branch")){
            viewmodel.branchNumberSelected.value= viewmodel.branchNumberSelected.value?.minus(1)
        }
        if(title.equals("Select Location")){
            viewmodel.locationNumberSelected.value= viewmodel.locationNumberSelected.value?.minus(1)
        }

    }

    fun populateData(){

        viewmodel.accountNumberSelected.observe(this, Observer {
            textviewAccount.text=it.toString()
        })

        viewmodel.branchNumberSelected.observe(this, Observer {
            textViewBrand.text=it.toString()
        })

        viewmodel.locationNumberSelected.observe(this, Observer {
            textViewLocation.text=it.toString()
        })
    }

}