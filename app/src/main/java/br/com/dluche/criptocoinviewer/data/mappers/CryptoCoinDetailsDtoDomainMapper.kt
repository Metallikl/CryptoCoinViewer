package br.com.dluche.criptocoinviewer.data.mappers

import br.com.dluche.criptocoinviewer.common.Mapper
import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail.CryptoDetailsDto
import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail.TagDto
import br.com.dluche.criptocoinviewer.data.model.response.crypto_coin_detail.TeamDto
import br.com.dluche.criptocoinviewer.domain.model.CryptoCoinDetails
import br.com.dluche.criptocoinviewer.domain.model.Tag
import br.com.dluche.criptocoinviewer.domain.model.Team

class CryptoCoinDetailsDtoDomainMapper : Mapper<CryptoDetailsDto, CryptoCoinDetails> {
    override fun mapTo(from: CryptoDetailsDto): CryptoCoinDetails {
        return CryptoCoinDetails(
            description = from.description,
            firstDataAt = from.firstDataAt,
            id = from.id,
            isActive = from.isActive,
            isNew = from.isNew,
            lastDataAt = from.lastDataAt,
            logo = from.logo,
            message = from.message,
            name = from.name,
            openSource = from.openSource,
            rank = from.rank,
            startedAt = from.startedAt,
            symbol = from.symbol,
            tags = mapTags(from.tags),
            team = mapTeam(from.team),
            type = from.type
        )
    }

    private fun mapTags(tags: List<TagDto>): List<Tag> {
        return tags.map {
            Tag(
                coinCounter = it.coinCounter,
                icoCounter = it.icoCounter,
                id = it.id,
                name = it.name
            )
        }
    }

    private fun mapTeam(team: List<TeamDto>): List<Team> {
        return team.map {
            Team(
                id = it.id,
                name = it.name,
                position = it.position
            )
        }
    }
}