package com.example.ilyaniafiodau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationComposeApp()
        }
    }
}


val LocalNavController = staticCompositionLocalOf<NavController> {
    throw IllegalStateException("No nav controller found")
}
@Composable
fun HomeScreen() {
    val navController = LocalNavController.current

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                navController.navigate(Route.Main.route)
            }
        ) {
            Text(text = "Main")
        }
        Button(
            onClick = {
                navController.navigate(Route.Home.route)
            }
        ) {
            Text(text = "Home")
        }
        Button(
            onClick = {
                navController.navigate(Route.Settings.route)
            }
        ) {
            Text(text = "Settings")
        }

    }
}

@Composable
fun SettingsScreen() {
    var isNotificationsEnabled by remember { mutableStateOf(true) }
    var isDarkThemeEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(text = "Настройки", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Уведомления")

        Switch(
            checked = isNotificationsEnabled,
            onCheckedChange = { isNotificationsEnabled = it },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(text = "Темная тема")

        Switch(
            checked = isDarkThemeEnabled,
            onCheckedChange = { isDarkThemeEnabled = it },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Действие для сброса настроек */ }) {
            Text(text = "Сбросить настройки")
        }
    }
}

sealed class Route(val route: String) {
    data object Home : Route("home")
    data object Main : Route("main")
    data object Settings : Route("settings")
}


@Composable
fun NavigationComposeApp() {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.Main.route,
        ) {
            composable(Route.Main.route) {
                MainScreen()
            }
            composable(Route.Home.route) {
                HomeScreen()
            }
            composable(Route.Settings.route) {
                SettingsScreen()
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current

    val items = remember { listOf(Pair("Creative Area", R.drawable.img1), Pair("Workshops", R.drawable.img2), Pair("Classrooms", R.drawable.img3),
        Pair("Conferences", R.drawable.img4), Pair("Cafeterias", R.drawable.img5), Pair("Cafeterias", R.drawable.img5), Pair("Cafeterias", R.drawable.img5), Pair("Cafeterias", R.drawable.img5), Pair("Cafeterias", R.drawable.img5), Pair("Cafeterias", R.drawable.img5)) }


    Scaffold(
        topBar = {
            // Определение состояния для текстового поля поиска
            val searchQuery = remember { mutableStateOf(TextFieldValue("")) }

            // Создание корневого контейнера
            Row(modifier = Modifier.fillMaxWidth().padding(15.dp).background(colorResource(R.color.purple_700))) {
                Box(modifier = Modifier.weight(1f)) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = stringResource(id = R.string.logo),
                        modifier = Modifier
                            .width(50.dp)
                            .padding(6.dp)
                            .height(50.dp)
                            .background(colorResource(R.color.purple_700)),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Fit,
                        alpha = DefaultAlpha
                    )
                }
                Column(modifier = Modifier.fillMaxWidth().background(Color.White).weight(4f))
                {
                    // Текстовое поле для поиска
                    TextField(
                        value = searchQuery.value,
                        onValueChange = {
                            // Обновляем состояние при изменении текста
                            searchQuery.value = it
                        },
                        label = { Text("Поиск") }, // Подсказка для поля
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            // Здесь можно добавить иконку для очистки поискового запроса
                            if (searchQuery.value.text.isNotEmpty()) {
                                IconButton(onClick = { searchQuery.value = TextFieldValue("") }) {
                                    Icon(Icons.Default.Clear, contentDescription = "Очистить")
                                }
                            }
                        }
                    )

                    // Здесь можно добавить логику для отображения результатов поиска
                    // на основе значения `searchQuery.value.text`

                    //Spacer(modifier = Modifier.height(16.dp))
                    //Text("Ваш поисковый запрос: ${searchQuery.value.text}")
                }

                Row(
                    modifier = Modifier
                        .padding(6.dp)
                        .weight(1f)
                ) {
                    IconButton(onClick = {
                        // Здесь добавьте логику обработки нажатия
                        println("User profile icon clicked") // Например, вывод в консоль
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_account), // Укажите свой ресурс иконки
                            contentDescription = "User Profile Icon",
                            modifier = Modifier.size(50.dp).background(Color.White),
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth() // Заполнение ширины
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier.alpha(1f),
                        onClick = {
                            navController.navigate(Route.Main.route)
                        }
                    ) {
                        Text(text = "Main")
                    }
                    Button(
                        onClick = {
                            navController.navigate(Route.Home.route)
                        }
                    ) {
                        Text(text = "Home")
                    }
                    Button(
                        onClick = {
                            navController.navigate(Route.Settings.route)
                        }
                    ) {
                        Text(text = "Settings")
                    }
                }
            }
        }
    ) { innerPadding ->
        // Ваш основной контент здесь
        Column(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.purple_700))) {


            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                columns = GridCells.Fixed(2),
            ) {
                items(items = items) {
                    OneItem(it.first, it.second)
                }
            }


        }
    }

}

@Composable
fun OneItem(name: String, id: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(200.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp,
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp,
                )
            )
            .background(colorResource(R.color.purple_500)),
    ) {
        Box() {
            Image(
                painter = painterResource(id = id),
                contentDescription = stringResource(id = R.string.picture),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(150.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomStart = 10.dp,
                            bottomEnd = 10.dp,
                        )
                    ),
                alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds,
                alpha = DefaultAlpha
            )
        }


        Text(
            text = name,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxWidth()
                .padding(bottom = 8.dp),

            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    MainScreen()
//}