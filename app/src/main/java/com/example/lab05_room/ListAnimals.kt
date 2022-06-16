package com.example.lab05_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab05_room.data.APIService
import com.example.lab05_room.databinding.ActivityListAnimalsBinding
import kotlinx.android.synthetic.main.activity_list_animals.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListAnimals : AppCompatActivity() {

    private lateinit var binding: ActivityListAnimalsBinding
    lateinit var animalsViewModel: AnimalsViewModel
    lateinit var animalsAdapter: AnimalsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListAnimalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupList()
        initRecyclerView()
    }

    private fun setupViewModel() {
        val factory = AnimalsViewModelFactory(APIService())
        animalsViewModel = ViewModelProvider(this, factory).get(AnimalsViewModel::class.java)
    }

    private fun setupList() {
        animalsAdapter = AnimalsAdapter()
        rvListAnimals.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = animalsAdapter.withLoadStateHeaderAndFooter(
                header = AnimalsLoadStateAdapter { animalsAdapter.retry() },
                footer = AnimalsLoadStateAdapter { animalsAdapter.retry() }
            )
            setHasFixedSize(true)
        }

    }

    private fun initRecyclerView() {
        lifecycleScope.launch {
            animalsViewModel.pager.collectLatest { pagedData ->
                animalsAdapter.submitData(pagedData)
            }
        }
    }
}