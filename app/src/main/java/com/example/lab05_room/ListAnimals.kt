package com.example.lab05_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab05_room.data.APIService
import com.example.lab05_room.databinding.ActivityListAnimalsBinding
import kotlinx.android.synthetic.main.activity_list_animals.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListAnimals : AppCompatActivity() {

    private lateinit var binding: ActivityListAnimalsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListAnimalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // initRecyclerView()

        searchWithToken()
        initRecyclerView()
    }

    private fun initRecyclerView() {
       rvListAnimals.apply{
            layoutManager = LinearLayoutManager(this@ListAnimals)
           val decoration = DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL)
           addItemDecoration(decoration)
           //adapter =
       }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://apiv3.iucnredlist.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchWithToken() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getAnimals()
            val animals = call.body()
            if (call.isSuccessful) {
                //RecyclerView
            } else {
                //Error
                Log.e("ERROR:", "Se rompió")
            }
        }
    }
}