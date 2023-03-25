package br.com.dluche.criptocoinviewer.presentation.coin_list.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dluche.criptocoinviewer.databinding.FragmentCoinsListBinding
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin
import br.com.dluche.criptocoinviewer.extensions.showSnackbar
import br.com.dluche.criptocoinviewer.presentation.coin_list.adapter.CoinListAdapter
import br.com.dluche.criptocoinviewer.presentation.coin_list.viewmodel.CoinListState
import br.com.dluche.criptocoinviewer.presentation.coin_list.viewmodel.CoinListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinsListFragment : Fragment() {

    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var binding: FragmentCoinsListBinding
    private val adapter: CoinListAdapter by lazy {
        CoinListAdapter(::onCoinClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinsListBinding.inflate(layoutInflater, container, false)
        setObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniLayout()
    }

    private fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect { state ->
                    if (!state.isLoadingNextPage) {
                        adapter.submitList(state.coinList)
                    }
                    //
                    binding.srCoins.isRefreshing = state.isLoading
                    binding.rvCoins.isVisible = !state.isLoading
                    binding.svCoin.setQuery(state.search, false)
                    binding.btnError.isVisible = state.isErrorNextPage
                    handleErrorMessage(state)
                    handleShimmer(state)
                }
            }
        }
    }

    private fun handleErrorMessage(state: CoinListState) {
        if (state.message.isNotEmpty() && (state.isErrorNextPage || state.isError)) {
            showSnackbar(
                binding.root,
                state.message
            )
            //
            viewModel.resetMessage()
        }
    }

    private fun handleShimmer(state: CoinListState) {
        binding.incShimmer.root.isVisible = state.isLoading
    }

    private fun iniLayout() {
        with(binding) {
            initSearchViewCoin()
            initSwipeRefresh()
            initRecycleCoins()
            initErrorNextPageButton()
        }
    }

    private fun FragmentCoinsListBinding.initErrorNextPageButton() {
        btnError.setOnClickListener {
            viewModel.getCoinsNextPage()
        }
    }

    private fun FragmentCoinsListBinding.initRecycleCoins() {
        rvCoins.adapter = adapter
        rvCoins.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                (recyclerView.layoutManager as LinearLayoutManager).let {
                    val itemCount = it.itemCount
                    val lastVisibleItemPosition = it.findLastVisibleItemPosition()
                    val offset = if (itemCount > 3) ITEM_COUNT_OFFSET else ITEM_COUNT_NONE_OFFSET
                    val triggerForNextPager = itemCount - ITEM_COUNT_OFFSET
                    //
                    if (lastVisibleItemPosition >= triggerForNextPager && !viewModel.state.value.isLoadingNextPage) {
                        viewModel.getCoinsNextPage()
                    }
                }
            }
        })
    }

    private fun FragmentCoinsListBinding.initSwipeRefresh() {
        srCoins.setOnRefreshListener {
            viewModel.getCoins(forceReset = true)
        }
    }

    private fun FragmentCoinsListBinding.initSearchViewCoin() {
        svCoin.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.updateSearchText(query)
                viewModel.filterCoins()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearchText(newText)
                return true
            }

        })
    }

    private fun onCoinClick(coin: CryptoCoin) {
        findNavController().navigate(
            CoinsListFragmentDirections.actionCoinsListFragmentToCoinDetailsFragment(coin.id)
        )
    }

    private fun onErrorClick() {

    }

    companion object {
        private const val ITEM_COUNT_OFFSET = 3
        private const val ITEM_COUNT_NONE_OFFSET = 0
    }

}