package com.example.playground

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels

class ChildFragment : Fragment() {

    private val parentViewModel: MainFragmentViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    private val activityViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("RadoiuC", "ChildFragment - onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        parentViewModel.mainFragmentLiveData.observe(viewLifecycleOwner) {
            Log.d("RadoiuC", "ChildFragment received value from parent fragment view model: $it")
        }

        activityViewModel.liveData.observe(viewLifecycleOwner) {
            Log.d("RadoiuC", "ChildFragment - received value from activity view model: $it")
        }

        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("RadoiuC", "ChildFragment posting value in parent fragment")
            parentViewModel.changeValue(21321)
        }, 8000L)
    }
}