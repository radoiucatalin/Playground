package com.example.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                Log.d("RadoiuC", "New item emited: $it")
            }
    }
}