package presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.screens.main.MainScreen
import presentation.theme.BOLD_SILVER_BACKGROUND_COLOR
import presentation.theme.PrimaryColor
import presentation.theme.SPLASH_ANIMATED_BG_COLOR

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        SplashScreenContent(navigator)
    }
}


@Composable
fun SplashScreenContent(navigator: Navigator? = null) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 1500,
            )
        )

        delay(10L)
        navigator?.push(MainScreen)
    })
    SplashAnimationWithContent()
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashAnimationWithContent() {
    val logo = painterResource("logo.xml")
    val angle1Y = remember {
        Animatable(20f)
    }
    val angle2 = remember {
        Animatable(20f)
    }
    LaunchedEffect(angle1Y, angle2) {
        launch {
            angle1Y.animateTo(180f, animationSpec = tween(1500))
        }
        launch {
            angle2.animateTo(180f, animationSpec = tween(1500))
        }
    }

    Surface(Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .background(SPLASH_ANIMATED_BG_COLOR),
            onDraw = {
                withTransform({
                    // translate(angle1Y.value)
                    scale(scaleX = angle1Y.value, scaleY = angle2.value)

                }) {
                    drawCircle(color = BOLD_SILVER_BACKGROUND_COLOR, radius = 8f)
                }
            }
        )
        Image(
            modifier = Modifier
                .width(40.dp)
                .padding(horizontal = 96.dp),
            contentScale = ContentScale.Fit,
            painter = logo,
            colorFilter = ColorFilter.tint(PrimaryColor),
            contentDescription = null
        )
    }

}

