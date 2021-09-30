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

class MainFragment : Fragment() {

    private val parentViewModel: MainViewModel by activityViewModels()
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("RadoiuC", "MainFragment - onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        viewModel.changeValue(1)

        parentViewModel.liveData.observe(viewLifecycleOwner) {
            Log.d("RadoiuC", "MainFragment received value from activity view model :$it")
        }

        viewModel.mainFragmentLiveData.observe(viewLifecycleOwner) {
            Log.d("RadoiuC", "MainFragment received value from own view model: $it")
        }

        childFragmentManager.beginTransaction()
            .add(R.id.child_fragment_container, ChildFragment())
            .commit()

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.changeValue(10)
        }, 5000L)

        Handler(Looper.getMainLooper()).postDelayed({
            parentViewModel.changeValue("value set in child")
        }, 3000L)
    }
}