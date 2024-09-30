package com.example.quizapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(navigationToQuizScreen: () -> Unit) {
    var showDialog by remember { mutableStateOf(true) }
    var showLandingPage by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }

    if (showDialog) {
        ShowAlertDialog(
            isDialogOpen = showDialog,
            onDismiss = {
                showDialog = false
                showLandingPage = true
            },
            onNameEntered = { name ->
                userName = name
            }
        )
    }

    if (showLandingPage) {
        LandingPage(userName, navigationToQuizScreen)
    }
}

@Composable
fun LandingPage(userName: String, navigationToQuizScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = WindowInsets.statusBars
                    .asPaddingValues()
                    .calculateTopPadding()
            )
            .background(color = Color(0xFFFFC0CB)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quiz Master",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            fontSize = 80.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "Test Your Knowledge With Interactive Quizzes",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.padding(50.dp))
        Text(
            text = "Hello, $userName!",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.padding(50.dp))
        Button(
            onClick = { navigationToQuizScreen() },
            modifier = Modifier
                .height(70.dp)
                .width(200.dp)
                .border(BorderStroke(2.dp, Color.Black), CircleShape)
        ) {
            Text(text = "Start Your Quiz")
        }
    }
}

