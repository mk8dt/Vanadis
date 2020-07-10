package com.mk8.vanadis.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mk8.data.ItemWeb
import com.mk8.data.MainState
import com.mk8.data.ScreenState
import com.mk8.vanadis.databinding.MainLayoutBinding
import com.mk8.vanadis.databinding.WebItemviewBinding
import com.mk8.vanadis.extension.gone
import com.mk8.vanadis.extension.visible
import com.mk8.vanadis.screen.base.BaseFragment
import com.mk8.vanadis.screen.base.BaseRecyclerView
import com.mk8.vanadis.screen.base.BaseViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    private val mainViewModel by viewModel<MainViewModel>()
    private val mainAdapter = MainAdapter { routeToWebDetail(it) }
    private val mainObserver = Observer<ScreenState<MainState>> { updateUI(it) }
    private lateinit var mainBinding: MainLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainBinding = MainLayoutBinding.inflate(layoutInflater)
        return mainBinding.root
    }

    override fun initView() {
        init(mainViewModel)
        mainBinding.webList.run {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = mainAdapter
        }

        mainViewModel.mainState.observe(viewLifecycleOwner, mainObserver)
    }

    private fun updateUI(state: ScreenState<MainState>) {
        when (state) {
            ScreenState.Loading -> showLoading()
            is ScreenState.Render -> {
                hideLoading()
                processMainState(state.renderState)
            }
        }
    }

    private fun processMainState(renderState: MainState) {
        when (renderState) {
            is MainState.DrawItems -> mainAdapter.setList(renderState.items)
            is MainState.ShowErrorMessage -> showError(renderState.message)
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        mainBinding.mainProgressBar.visible()
    }

    private fun hideLoading() {
        mainBinding.mainProgressBar.gone()
    }

    private fun routeToWebDetail(url: String) {
        val action = MainFragmentDirections.routeToDetail(url)
        navController?.navigate(action)
    }

    inner class MainAdapter(private val onClick: (String) -> Unit) : BaseRecyclerView<ItemWeb, MainAdapter.Holder>() {

        override fun getViewHolder(parent: ViewGroup) = Holder(WebItemviewBinding.inflate(layoutInflater))

        inner class Holder(private val webItemBinding: WebItemviewBinding) : BaseViewHolder<ItemWeb>(webItemBinding) {

            override fun bindData(item: ItemWeb) {
                with(webItemBinding) {
                    nameItemView.text = item.name
                    root.setOnClickListener { onClick.invoke(item.url) }
                }
            }
        }
    }
}