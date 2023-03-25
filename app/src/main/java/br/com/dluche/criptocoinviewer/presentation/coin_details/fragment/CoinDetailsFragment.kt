package br.com.dluche.criptocoinviewer.presentation.coin_details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.dluche.criptocoinviewer.R
import br.com.dluche.criptocoinviewer.presentation.coin_details.viewmodel.CoinDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinDetailsFragment : Fragment() {
    private val viewModel: CoinDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCoinDetails()
//        lifecycleScope.launchWhenResumed {
//            viewModel.state.collect { state ->
//                Toast.makeText(context,state.coinId,Toast.LENGTH_LONG).show()
//            }
//        }
    }

}