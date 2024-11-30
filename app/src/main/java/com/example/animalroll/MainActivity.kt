package com.example.animalroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animalroll.ui.theme.AnimalRollTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimalRollTheme {
                GuessTheAnimal()
            }
        }
    }
}

@Preview
@Composable
fun GuessTheAnimal() {
    AppWithButtonAndImages(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun AppWithButtonAndImages(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var showAnimalName by remember { mutableStateOf(false) }
    //countdown variable
    var countdown by remember { mutableStateOf(10) }

    val animals = listOf(
        Pair(R.drawable.lion, "Lion"),
        Pair(R.drawable.horse, "Horse"),
        Pair(R.drawable.zebra, "Zebra"),
        Pair(R.drawable.leopard, "Leopard"),
        Pair(R.drawable.antelop, "Antelope"),
        Pair(R.drawable.bear, "Bear"),
        Pair(R.drawable.buffalo, "Buffalo"),
        Pair(R.drawable.cow, "Cow"),
        Pair(R.drawable.crocodile, "Crocodile"),
        Pair(R.drawable.deer, "Deer"),
        Pair(R.drawable.dog, "Dog"),
        Pair(R.drawable.elephant, "Elephant"),
        Pair(R.drawable.goat, "Goat"),
        Pair(R.drawable.hippo, "Hippopotamus"),
        Pair(R.drawable.moose, "Moose"),
        Pair(R.drawable.ox, "Ox"),
        Pair(R.drawable.racoon, "Raccoon"),
        Pair(R.drawable.squirrel, "Squirrel"),
        Pair(R.drawable.yak, "Yak"),
        Pair(R.drawable.tiger, "Tiger")
    )

    val currentAnimal = animals[(result - 1) % animals.size]

    // Countdown Logic Launched effect widget used
    LaunchedEffect(result) {
        showAnimalName = false
        countdown = 7
        while (countdown > 0) {
            delay(1000L) // 1-second delay
            countdown--
        }
        showAnimalName = true
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Guess Animal Name",
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(90.dp)
        )
        Image(
            painter = painterResource(currentAnimal.first),
            contentDescription = currentAnimal.second,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        if (showAnimalName) {
            Text(
                text = currentAnimal.second,
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(20.dp)
            )
        } else {
            Text(
                text = "Clock:  $countdown",
                color = Color.DarkGray,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(11.dp))
        //Next previous button logic. Strings name in Strings.xml file
        Row(
            horizontalArrangement = Arrangement.spacedBy(70.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 90.dp)
        ) {
            Button(onClick = { result = if (result == 1) animals.size else result - 1 }) {
                Text(stringResource(R.string.previous))
            }
            Button(onClick = { result = if (result == animals.size) 1 else result + 1 }) {
                Text(stringResource(R.string.next))
            }
        }
    }
}
