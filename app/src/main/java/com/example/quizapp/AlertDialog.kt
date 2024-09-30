package com.example.quizapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ShowAlertDialog(isDialogOpen: Boolean, onDismiss: () -> Unit, onNameEntered: (String) -> Unit) {
    val userName = remember { mutableStateOf("") }

    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Please Enter Your Name") },
            text = {
                Column {
                    TextField(
                        value = userName.value,
                        onValueChange = { newName -> userName.value = newName },
                        label = { Text("Name") },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onNameEntered(userName.value)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE), // Background color
                        contentColor = Color.White // Text color
                    ),
                    shape = RoundedCornerShape(12.dp), // Custom shape
                    modifier = Modifier
                        .padding(horizontal = 16.dp) // Add padding around the button
                        .height(50.dp) // Custom height
                        .fillMaxWidth(0.4f) // Width as a percentage of parent width
                ) {
                    Text("OK", fontSize = 16.sp, fontWeight = FontWeight.Bold) // Custom text style
                }
            }
        )
    }
}
