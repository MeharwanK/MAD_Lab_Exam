package com.example.mad_lab_exam

import android.content.Context
import android.health.connect.datatypes.units.Temperature
import android.os.Bundle
import android.util.Log
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mad_lab_exam.ui.theme.MAD_Lab_ExamTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAD_Lab_ExamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MidLabExam()
                }
            }
        }
    }
}




@Composable
fun MidLabExam(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "home" ){

        composable(route = "home"){
            SplashScreen(navController = navController)}

        composable(route = "mapC"){
            MapScreen(navController = navController)}

        composable(route = "cityS"){
            CityScreen(navController = navController)}
    }
}
@Composable
fun SplashScreen(modifier: Modifier = Modifier,
                 context: Context = LocalContext.current,
                 navController : NavHostController){


    Column(modifier=Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription ="" ,
            Modifier.padding(top = 30.dp))

        Text(text = "Sky Sight",
            Modifier
                .padding(10.dp)
                .size(100.dp),
                fontWeight = FontWeight.Bold)

        LaunchedEffect(Unit) {

            delay(3000) // Wait for 3 seconds
            navController.navigate("mapC")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(modifier: Modifier = Modifier,
              context: Context = LocalContext.current,
              navController : NavHostController
){

    var name: String by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Select City") }) },
        bottomBar = { BottomAppBar {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Heart"
            )
        }
        }
    ) {
        paddingValues -> Column(modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = painterResource(id = R.drawable.logo), contentDescription ="LogoOfWeather" )
        
        Image(painter = painterResource(id = R.drawable.map),
            contentDescription ="MapImage",
            Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .fillMaxSize())
        
        TextField(value = name, onValueChange = {
            name=it
        })
        
            Button(onClick = {
                navController.navigate("cityS")
            }, Modifier.padding(bottom = 10.dp)) {
                    Text("Show Weather",
                        Modifier.padding(10.dp))
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityScreen(modifier: Modifier = Modifier,
               context: Context = LocalContext.current,
               navController : NavHostController){
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Weather Details") }) },
        bottomBar = { BottomAppBar {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Heart"
            )
        }
        }
    ) {
        paddingValues -> Column(modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("City Name: Karachi",
                Modifier.padding(30.dp),
                fontWeight = FontWeight.Bold)
        Row {
            Icon(painter = painterResource(id = R.drawable.baseline_thermostat_24), contentDescription = "")
            Column (Modifier.padding(5.dp)){
                Text(text = "Temperature", fontWeight=FontWeight.Bold)
                Text(text = "32C", fontWeight = FontWeight.Medium)
            }
        }
        Row {
            Icon(painter = painterResource(id = R.drawable.baseline_wind_power_24), contentDescription = "")
            Column (Modifier.padding(5.dp)){
                Text(text = "Humidity", fontWeight=FontWeight.Bold)
                Text(text = "79%",fontWeight = FontWeight.Medium)
            }
        }
        Row {
            Icon(painter = painterResource(id = R.drawable.baseline_sunny_24), contentDescription = "")
            Column (Modifier.padding(5.dp)){
                Text(text = "Condition", fontWeight=FontWeight.Bold)
                Text(text = "Sunny",fontWeight = FontWeight.Medium)
            }
        }
        
        
        

        
        }
    }
    
    

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MAD_Lab_ExamTheme {
        Greeting("Android")
    }
}