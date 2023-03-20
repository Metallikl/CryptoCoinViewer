package br.com.dluche.criptocoinviewer.data.datasource

import br.com.dluche.criptocoinviewer.common.EitherResult
import br.com.dluche.criptocoinviewer.data.database.dao.CryptoCoinDao
import br.com.dluche.criptocoinviewer.data.model.entity.CryptoCoinEntity
import javax.inject.Inject

class CryptoCoinLocalDataSourceImpl @Inject constructor(
    private val coinDao: CryptoCoinDao
) : CryptoCoinLocalDataSource {

    override suspend fun insertCoins(coins: List<CryptoCoinEntity>): EitherResult<Any> {
        return try {
            coinDao.insertAll(coins)
            EitherResult.Success(Any())
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }
    }

    override suspend fun getCoins(offset: Int, limit: Int): EitherResult<List<CryptoCoinEntity>> {
        return try {
            EitherResult.Success(coinDao.getCoins(offset = offset, limit = limit))
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }
    }

    override suspend fun deleteCoins(): EitherResult<Any> {
        return try {
            coinDao.deleteAll()
            EitherResult.Success(Any())
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }
    }

    override suspend fun getCoinsNextPage(
        search: String?,
        offset: Int,
        limit: Int
    ): EitherResult<List<CryptoCoinEntity>> {
        return try {
            EitherResult.Success(
                coinDao.getCoinsNextPage(
                    search = search,
                    offset = offset,
                    limit = limit
                )
            )
        } catch (e: Exception) {
            EitherResult.Error(error = e)
        }

    }
}