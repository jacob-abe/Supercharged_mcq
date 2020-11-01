package com.quizzy.superchargedmcq.models

data class ChoiceModel(var id: Int, var choiceText: String, var isC_ans_w_ans: Boolean){
        constructor():this(0,"",false)
    }