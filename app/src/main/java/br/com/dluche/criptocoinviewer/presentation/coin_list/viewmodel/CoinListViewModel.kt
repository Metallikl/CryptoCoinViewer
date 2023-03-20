package br.com.dluche.criptocoinviewer.presentation.coin_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dluche.criptocoinviewer.common.AppCoroutinesDispatchers
import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.domain.model.CoinPaginationData
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoin
import br.com.dluche.criptocoinviewer.domain.usescases.GetCoinsNextPageUseCase
import br.com.dluche.criptocoinviewer.domain.usescases.GetCoinsUseCase
import br.com.dluche.criptocoinviewer.extensions.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val getCoinsNextPageUseCase: GetCoinsNextPageUseCase,
    private val dispatchers: AppCoroutinesDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state.asStateFlow()

    init {
        getCoins()
    }

    fun getCoins(forceReset: Boolean = false) {
        viewModelScope.launch(dispatchers.io()) {
            _state.value = CoinListState(
                isLoading = true
            )
            //
            getCoinsUseCase(forceReset = forceReset).collect { result ->
                when (result) {
                    is EitherResult.Error -> {
                        _state.value = CoinListState(
                            isError = true,
                            message = result.error.message.orEmpty()
                        )
                    }
                    is EitherResult.Success -> {
                        _state.value = CoinListState(
                            coinList = result.data
                        )
                    }
                }
            }
        }
    }

    fun updateSearchText(newSearch: String?) {
        _state.update {
            it.copy(
                search = newSearch
            )
        }
    }

    fun filterCoins() {
        viewModelScope.launch(dispatchers.io()) {
            _state.update { curState ->
                curState.copy(
                    isLoading = true
                )
            }
            //
            getCoinsNextPageUseCase(
                paginationData = CoinPaginationData(
                    search = _state.value.search
                )
            ).collect { result ->
                when (result) {
                    is EitherResult.Error -> {
                        _state.value = CoinListState(
                            isError = true,
                            message = result.error.message.orEmpty()
                        )
                    }
                    is EitherResult.Success -> {
                        _state.update { curState ->
                            curState.copy(
                                isLoading = false,
                                coinList = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun getCoinsNextPage() {
        viewModelScope.launch(dispatchers.io()) {
            _state.update { curState ->
                curState.copy(
                    isLoadingNextPage = true
                )
            }
            //
            getCoinsNextPageUseCase(
                buildPaginationData()
            ).collect { result ->
                when (result) {
                    is EitherResult.Error -> {
                        _state.value = CoinListState(
                            isErrorNextPage = true,
                            message = result.error.message.orEmpty()
                        )
                    }
                    is EitherResult.Success -> {
                        _state.update { state ->
                            val newCoinList = state
                                .coinList
                                .toMutableList()
                                .also {
                                    it.addAll(result.data)
                                }
                            //
                            CoinListState(
                                search = state.search,
                                coinList = newCoinList.toList()
                            )
                        }
                    }
                }
            }
        }
    }

    private fun buildPaginationData() = CoinPaginationData(
        search = _state.value.search,
        offset = _state.value.coinList.size
    )
}

data class CoinListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isLoadingNextPage: Boolean = false,
    val isErrorNextPage: Boolean = false,
    val search: String? = null,
    val message: String = String().emptyString(),
    val coinList: List<CryptoCoin> = emptyList(),
)