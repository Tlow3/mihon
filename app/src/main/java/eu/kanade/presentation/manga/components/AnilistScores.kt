package eu.kanade.presentation.manga.components

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.i18n.stringResource

/**
 * Scores from AniList for an entry linked to it, already written in the user's AniList scoring format.
 * A null score is one AniList doesn't have: no community average yet, or no score from the user.
 */
@Immutable
data class AnilistScores(
    val community: String?,
    val user: String?,
    /** Made up by a test build rather than read from AniList. */
    val isTestData: Boolean = false,
)

@Composable
fun AnilistScoresRow(
    scores: AnilistScores,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val muted = MaterialTheme.colorScheme.onSurfaceVariant
    val strong = MaterialTheme.colorScheme.onSurface
    val accent = MaterialTheme.colorScheme.primary
    val notScored = stringResource(MR.strings.anilist_not_scored)
    val communityLabel = stringResource(MR.strings.anilist_community)
    val youLabel = stringResource(MR.strings.anilist_you)
    val text = buildAnnotatedString {
        withStyle(SpanStyle(color = accent, fontWeight = FontWeight.Bold)) {
            append("AniList")
        }
        withStyle(SpanStyle(color = muted)) { append("  ·  $communityLabel ") }
        withStyle(SpanStyle(color = strong, fontWeight = FontWeight.SemiBold)) {
            append(scores.community ?: "–")
        }
        withStyle(SpanStyle(color = muted)) { append("  ·  $youLabel ") }
        withStyle(SpanStyle(color = strong, fontWeight = FontWeight.SemiBold)) {
            append(scores.user ?: notScored)
        }
        if (scores.isTestData) {
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.error)) { append("  ·  test data") }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}
