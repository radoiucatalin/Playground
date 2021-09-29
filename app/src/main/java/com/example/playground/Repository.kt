package com.example.playground

import io.reactivex.rxjava3.core.Single

class Repository {

    fun getData() : Single<Int> = Single.just(1)
}