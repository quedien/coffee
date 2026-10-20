package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "menu"
            ) {
                composable("menu") {
                    MenuScreen(navController)
                }

                composable("detail") {
                    DetailScreen(navController)
                }

                composable("success") {
                    SuccessScreen(navController)
                }
            }
        }
    }
}

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Кофе с собой",
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 24.dp)
        )
        Text(
            text = "Выбери свой напиток",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )

        CoffeeItem(name = "Латте", price = "120 ₽") {
            navController.navigate("detail")
        }
        Spacer(modifier = Modifier.height(16.dp))
        CoffeeItem(name = "Капучино", price = "180 ₽") {
            navController.navigate("detail")
        }
    }
}

@Composable
fun CoffeeItem(name: String, price: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(all = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.cup_coffee),
                contentDescription = "Кофе",
                modifier = Modifier.size(64.dp)
            )
            Text(text = name, fontSize = 22.sp)
            Text(text = price, fontSize = 18.sp)
        }
    }
}

@Composable
fun DetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Назад"
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.cup_glass),
            contentDescription = "Капучино",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 32.dp)
        )

        Text(text = "Капучино", fontSize = 32.sp, modifier = Modifier.padding(top = 16.dp))

        Text(
            text = "Классический итальянский кофе с бархатной молочной пенкой",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Row(modifier = Modifier.padding(top = 24.dp)) {
            Text(text = "200 мл", modifier = Modifier.padding(8.dp))
            Text(text = "300 мл", modifier = Modifier.padding(8.dp))
            Text(text = "400 мл", modifier = Modifier.padding(8.dp))
        }

        Text(text = "180 ₽", fontSize = 24.sp, modifier = Modifier.padding(top = 24.dp))

        Button(
            onClick = { navController.navigate("success") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text(text = "Добавить в заказ", fontSize = 18.sp)
        }
    }
}

@Composable
fun SuccessScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Успех",
            modifier = Modifier.size(100.dp),
            tint = Color.Green
        )

        Text(
            text = "Заказ оформлен!",
            fontSize = 28.sp,
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = "Ваш капучино будет готов через 5 минут",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Заказ №28",
            fontSize = 22.sp,
            modifier = Modifier.padding(top = 24.dp)
        )

        Button(
            onClick = {
                navController.popBackStack(route = "menu", inclusive = false)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp)
        ) {
            Text(text = "На главную", fontSize = 18.sp)
        }
    }
}