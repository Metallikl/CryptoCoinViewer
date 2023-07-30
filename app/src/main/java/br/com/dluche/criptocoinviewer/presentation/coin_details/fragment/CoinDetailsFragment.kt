package br.com.dluche.criptocoinviewer.presentation.coin_details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import br.com.dluche.criptocoinviewer.databinding.FragmentCoinDetailsBinding
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoinDetails
import br.com.dluche.criptocoinviewer.domain.model.Tag
import br.com.dluche.criptocoinviewer.domain.model.Team
import br.com.dluche.criptocoinviewer.extensions.loadSrcWithGlide
import br.com.dluche.criptocoinviewer.presentation.coin_details.adapter.CoinDetailTagAdapter
import br.com.dluche.criptocoinviewer.presentation.coin_details.adapter.CoinDetailTeamAdapter
import br.com.dluche.criptocoinviewer.presentation.coin_details.viewmodel.CoinDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class CoinDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCoinDetailsBinding
    private val viewModel: CoinDetailsViewModel by viewModels()
    private val tagsAdapter: CoinDetailTagAdapter by lazy {
        CoinDetailTagAdapter()
    }
    private val teamsAdapter: CoinDetailTeamAdapter by lazy {
        CoinDetailTeamAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailsBinding.inflate(layoutInflater)
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.state
            .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
            .onEach {
                viewModel.state.collect { state ->
                    handleContentVisibility(state)
                    setupLayout(state.coinDetail)
                }
            }.launchIn(lifecycleScope)
    }

    private fun handleContentVisibility(state: CoinDetailsViewModel.CoinDetailsState) {
        binding.incShimmer.root.isVisible = state.isLoading
        binding.gpContent.isVisible = !state.isLoading
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupLayout(coinDetail: CryptoCoinDetails) {
        setupLogo(coinDetail.logo)
        setCoinName(coinDetail.name)
        setupDescription(coinDetail.description)
        setupTagList(coinDetail.tags)
        setupTeamList(coinDetail.team)
    }

    private fun setupDescription(description: String) {
        binding.tvDescription.text = description
    }

    private fun setupTeamList(teams: List<Team>) {
        binding.rvMembers.adapter = teamsAdapter.apply {
            submitList(teams)
        }
    }

    private fun setupTagList(tags: List<Tag>) {
        binding.rvTags.adapter = tagsAdapter.apply {
            submitList(tags)
        }
    }

    private fun setupLogo(logoUrl: String?) {
        binding.ivLogo.loadSrcWithGlide(
            logoUrl.orEmpty()
        )
    }

    private fun setCoinName(name: String) {
        binding.tvName.text = name
    }


}