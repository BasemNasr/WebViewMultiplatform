package presentation.screens.main


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState

object MainScreen : Screen {

    @Composable
    override fun Content() {
        MaterialTheme {
            val webViewState = rememberWebViewState("https://alalmiyalhura.com/")
            Surface(Modifier.fillMaxSize()) {
                /*val text = webViewState.let {
                    "${it.pageTitle ?: ""} ${it.loadingState} ${it.lastLoadedUrl ?: ""}"
                }*/
                WebView(
                    state = webViewState,
                    modifier = Modifier.fillMaxSize()
                )
                //Text(text)
            }

        }
    }


}