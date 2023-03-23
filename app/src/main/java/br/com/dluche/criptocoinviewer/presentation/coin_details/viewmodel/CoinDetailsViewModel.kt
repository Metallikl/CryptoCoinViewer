package br.com.dluche.criptocoinviewer.presentation.coin_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dluche.criptocoinviewer.R
import br.com.dluche.criptocoinviewer.common.AppCoroutinesDispatchers
import br.com.dluche.criptocoinviewer.common.Constants.ARGS_PARAM_COIN_ID
import br.com.dluche.criptocoinviewer.common.ContentProvider
import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoinDetails
import br.com.dluche.criptocoinviewer.domain.usescases.GetCoinDetailsUseCase
import br.com.dluche.criptocoinviewer.extensions.emptyString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinDetailsViewModel @Inject constructor(
    private val dispatchers: AppCoroutinesDispatchers,
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val contentProvider: ContentProvider
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailsState())
    val state = _state.asStateFlow()

    init {
        val coinId = savedStateHandle[ARGS_PARAM_COIN_ID] ?: String.emptyString()
        _state.update { curState ->
            curState.copy(coinId = coinId)
        }
        getCoinDetails()
    }

    private fun getCoinDetails() {
        viewModelScope.launch(dispatchers.io()) {
            _state.update { curState ->
                curState.copy(
                    isLoading = true,
                    isError = false
                )
            }
            //
            getCoinDetailsUseCase(_state.value.coinId).collect { result ->
                when (result) {
                    is EitherResult.Error -> {
                        handleError(result.error)
                    }
                    is EitherResult.Success -> {
                        handleSuccess(result.data)
                    }
                }
            }

        }

    }

    private fun handleSuccess(data: CryptoCoinDetails) {
        _state.update { curState ->
            curState.copy(
                isLoading = false,
                isError = false,
                coinDetail = data
            )
        }
    }

    private fun handleError(result: Throwable) {
        _state.update { curState ->
            curState.copy(
                isLoading = false,
                isError = true,
                coinDetail = CryptoCoinDetails(),
                message = result.message ?: contentProvider.getString(R.string.generic_error)
            )
        }
    }

    data class CoinDetailsState(
        val coinId: String = String.emptyString(),
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val message: String = String.emptyString(),
        val coinDetail: CryptoCoinDetails = CryptoCoinDetails()
    )
}