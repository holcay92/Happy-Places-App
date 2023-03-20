package com.example.happyplaces.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happyplaces.adapters.HappyPlacesAdapter
import com.example.happyplaces.database.DatabaseHandler
import com.example.happyplaces.databinding.ActivityMainBinding
import com.example.happyplaces.models.HappyPlaceModel


class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding?.fabAddHappyPlace?.setOnClickListener {
            val intent = Intent(this@MainActivity, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }
        getHappyPlacesListFromLocalDB()
    }
    private fun setupHappyPlacesRecyclerView(happyPlaceList: ArrayList<HappyPlaceModel>) {

        binding?.rvHappyPlacesList?.layoutManager = LinearLayoutManager(this)

        binding?.rvHappyPlacesList?.setHasFixedSize(true)

        val placesAdapter = HappyPlacesAdapter(this, happyPlaceList)
        binding?.rvHappyPlacesList?.adapter = placesAdapter

    }
    private fun getHappyPlacesListFromLocalDB() {
        val dbHandler = DatabaseHandler(this)
        val getHappyPlaceList: ArrayList<HappyPlaceModel> = dbHandler.getHappyPlacesList()

        if (getHappyPlaceList.size > 0) {
          for(i in getHappyPlaceList){
            binding?.rvHappyPlacesList?.visibility = android.view.View.VISIBLE
            binding?.tvNoRecordsAvailable?.visibility = android.view.View.GONE
            setupHappyPlacesRecyclerView(getHappyPlaceList)
          }
        }
        else{
            binding?.rvHappyPlacesList?.visibility = android.view.View.GONE
            binding?.tvNoRecordsAvailable?.visibility = android.view.View.VISIBLE
        }
    }
}