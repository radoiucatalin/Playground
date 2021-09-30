package com.example.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val repository = Repository()

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.liveData.observe(this) {
            Log.d("RadoiuC", "Main activity received value: $it")
        }

        repository.getData()
            .toObservable()
            .repeatWhen {
//                it.doOnNext {
//                    Log.d("RadoiuC", "$it")
//                }.concatMap {
                Observable.timer(1, TimeUnit.SECONDS)
//                }.doOnNext {
//                    Log.d("RadoiuC", "After concat value: $it")
//                }.doOnComplete {
//                    Log.d("RadoiuC", "On complete")
//                }
            }.subscribe {
//                Log.d("RadoiuC", "New item emited: $it")
            }

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MainFragment()).commit()

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.changeValue("new value")
        }, 2000L)
    }
}