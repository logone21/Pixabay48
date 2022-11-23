package com.example.pixabay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabay.databinding.ActivityMainBinding
import com.example.pixabay.model.ImageModel
import com.example.pixabay.model.PixaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var photoAdapter = PhotoAdapter(arrayListOf())
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)){
                    binding.requestByImage(++page)
                }
            }
        })
    }
    private fun initClickers(){
        with(binding){
            changePage.setOnClickListener {
                requestByImage(++page)
            }
            findBtn.setOnClickListener {
                requestByImage(page)
                page= 1
            }
        }
    }

    private fun ActivityMainBinding.requestByImage(page:Int) {
        App.api.getImages(keyWord = photoEd.text.toString(), perPage = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    response.body()?.hits?.let { listmodel ->
                        listmodel.forEach {
                            photoAdapter.addlist(it)

                        }
                        binding.recyclerView.adapter = photoAdapter
                    }

                }
                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure${t.message}")
                }

            })
    }
}