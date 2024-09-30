package com.example.quizapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class QuizQuestion(
    var question:String,
    var options:List<String>,
    var correctOption:String,

)

@Composable
fun QuizScreen(viewModel: QuestionViewModel){
    val currentQuestionIndex by viewModel.currentQuestionIndex
    val question = viewModel.questions[currentQuestionIndex]
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isAnswerRevealed by remember { mutableStateOf(false) }
    val isLastQuestion = currentQuestionIndex == viewModel.questions.size - 1



    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(text = question.question,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        question.options.forEach { option ->
            val backgroundColor = when {
                isAnswerRevealed && option == question.correctOption -> Color.Green // Correct option
                isAnswerRevealed && option == selectedOption && option != question.correctOption -> Color.Red // Wrong option
                else -> Color.Transparent // Default background
            }
            Row(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable(enabled = !isAnswerRevealed) {
                    selectedOption = option
                    isAnswerRevealed = true

                }
                .background(color = backgroundColor, shape = RoundedCornerShape(30.dp)),
                verticalAlignment = Alignment.CenterVertically
                ){
                Text(text = " $option", modifier = Modifier.padding(start = 8.dp), fontSize = 20.sp)
            }

        }
        Spacer(modifier = Modifier.padding(20.dp))
       if(isLastQuestion){
           Text(
               text = "This is the last question.",
               modifier = Modifier.padding(bottom = 20.dp),
               style = MaterialTheme.typography.bodyLarge,
               color = Color.Blue
           )
           Button(
               onClick = {
                   // Move to next question when "Next" is clicked
                   viewModel.previousQuestion()
                   selectedOption = null
                   isAnswerRevealed = false
               },
               modifier = Modifier
//                      .align(Alignment.End)
                   .padding(end = 10.dp)
           ) {
               Text(text = "Previous Question")
           }
       }else{
          Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
              horizontalArrangement = Arrangement.SpaceBetween
              ){
              Button(
                  onClick = {
                      // Move to next question when "Next" is clicked
                      viewModel.previousQuestion()
                      selectedOption = null
                      isAnswerRevealed = false
                  },
                  modifier = Modifier
//                      .align(Alignment.End)
                      .padding(end = 10.dp)
              ) {
                  Text(text = "Previous Question")
              }
              Button(
                  onClick = {
                      // Move to next question when "Next" is clicked
                      viewModel.nextQuestion()
                      selectedOption = null
                      isAnswerRevealed = false
                  },
                  modifier = Modifier
//                      .align(Alignment.End)
                      .padding(end = 10.dp)
              ) {
                  Text(text = "Next Question")
              }
          }
       }
    }
}