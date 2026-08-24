package yourshopmarket.household.yourhomeflow.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val FlowColorScheme = lightColorScheme(
    primary = FlowTerracotta,
    onPrimary = FlowSurface,
    primaryContainer = FlowChip,
    onPrimaryContainer = FlowTerracottaDark,
    secondary = FlowTeal,
    onSecondary = FlowSurface,
    background = FlowCream,
    onBackground = FlowInk,
    surface = FlowSurface,
    onSurface = FlowInk,
    surfaceVariant = FlowChip,
    onSurfaceVariant = FlowMuted,
    outline = FlowBorder,
)

@Composable
fun ProductAppIMVXBTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = FlowColorScheme,
        typography = AppTypography,
        content = content,
    )
}
