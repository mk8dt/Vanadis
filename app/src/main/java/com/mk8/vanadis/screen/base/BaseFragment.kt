package com.mk8.vanadis.screen.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.mk8.vanadis.R

abstract class BaseFragment : Fragment() {

    private lateinit var currentViewModel: BaseViewModel
    protected val navController: NavController? by lazy { activity?.findNavController(R.id.nav_host) }

    fun init(viewModel: BaseViewModel) {
        currentViewModel = ViewModelProvider(this)[viewModel::class.java]
        currentViewModel.init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun initView()
}