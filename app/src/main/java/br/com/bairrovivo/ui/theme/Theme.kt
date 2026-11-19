package br.com.bairrovivo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Cores = lightColorScheme(
    primary = Color(0xFF205A43), onPrimary = Color.White,
    primaryContainer = Color(0xFFDCECCE), onPrimaryContainer = Color(0xFF193B2B),
    secondary = Color(0xFF6B7655), secondaryContainer = Color(0xFFDCECCE),
    onSecondaryContainer = Color(0xFF193B2B), background = Color(0xFFF6F8F3),
    onBackground = Color(0xFF1D3025), surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1D3025), surfaceVariant = Color(0xFFE8EDE3),
    onSurfaceVariant = Color(0xFF536154), outline = Color(0xFF748274)
)

@Composable
fun BairroVivoTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Cores, content = content)
}
