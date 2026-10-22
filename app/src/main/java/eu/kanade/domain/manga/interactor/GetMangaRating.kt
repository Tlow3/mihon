package eu.kanade.domain.manga.interactor

import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow
import tachiyomi.domain.manga.repository.MangaRepository

@Inject
class GetMangaRating(
    private val mangaRepository: MangaRepository,
) {

    suspend fun await(mangaId: Long): Int? {
        return mangaRepository.getRating(mangaId)
    }

    fun subscribe(mangaId: Long): Flow<Int?> {
        return mangaRepository.getRatingAsFlow(mangaId)
    }
}
