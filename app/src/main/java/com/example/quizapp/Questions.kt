package com.example.quizapp


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuestionViewModel:ViewModel(){
    private val _currentQuestionIndex = mutableIntStateOf(0)
    val currentQuestionIndex: State<Int> = _currentQuestionIndex

    private val questionList = listOf(
        QuizQuestion("What is the capital of India?", listOf("Chennai","Mumbai","Delhi","Kanpur"),"Delhi",),
        QuizQuestion("What is the Capital of France?", listOf("Moscow","Paris","London","Berlin"),"Paris",),
        QuizQuestion("Which Country hosted the 2024 Olympics?", listOf("Paris","Los Angeles","Sydney","Brazil"),"Paris",),
        QuizQuestion("How many States and Union Territories does India have?", listOf("28,9","29,8","28,8","29,9"),"28,8",),
        QuizQuestion("The largest state of India is: ", listOf("Maharashtra","Rajasthan","Madhya Pradesh","Uttar Pradesh"),"Rajasthan",)
    )
    val questions: List<QuizQuestion> = questionList
    fun nextQuestion(){
        if (_currentQuestionIndex.value <= questionList.size - 1) {
            _currentQuestionIndex.value++
        }
    }
    fun previousQuestion(){
        if(_currentQuestionIndex.value > 0){
            _currentQuestionIndex.value--
        }
    }

}