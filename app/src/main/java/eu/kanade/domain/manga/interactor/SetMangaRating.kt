package eu.kanade.domain.manga.interactor

import dev.zacsweers.metro.Inject
import tachiyomi.domain.manga.repository.MangaRepository

@Inject
class SetMangaRating(
    private val mangaRepository: MangaRepository,
) {

    /** A null [rating] clears it. */
    suspend fun await(mangaId: Long, rating: Int?) {
        mangaRepository.setRating(mangaId, rating)
    }
}
