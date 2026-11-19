package br.com.bairrovivo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.bairrovivo.ui.screens.HomeScreen
import br.com.bairrovivo.ui.theme.BairroVivoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { BairroVivoTheme { HomeScreen() } }
    }
}
