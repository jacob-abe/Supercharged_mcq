package com.quizzy.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.quizzy.superchargedmcq.models.ChoiceModel
import com.quizzy.superchargedmcq.ui.ChoiceType
import com.quizzy.superchargedmcq.ui.SuperChargedChoiceView
import com.quizzy.sample.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val choicesSingle = ArrayList<ChoiceModel>()
        choicesSingle.add(ChoiceModel(1, "Option 1", false))
        choicesSingle.add(ChoiceModel(2, "Option 2", false))
        choicesSingle.add(ChoiceModel(3, "Option 3", false))
        choicesSingle.add(ChoiceModel(4, "Option 4", true))
        choicesSingle.add(ChoiceModel(5, "Option 5", false))


        val superChargedChoiceView = findViewById<SuperChargedChoiceView>(R.id.super_charged_view)

        superChargedChoiceView.setQuestion("Single choice question?");
        superChargedChoiceView.setChoiceType(choicesSingle, ChoiceType.SINGLE)
        superChargedChoiceView.userSelectedAnswer.observe(this,{
            if(!it.isNullOrEmpty())
                Log.d("props", it.toString())
        })

        //arivistaview.setHiddenMode() to hide answers from end quiz taker
        //setUserAnswer(choice: ChoiceModel) of your choice to pre select a choice

        //Multi option MCQs
        /*val choicesMulti = ArrayList<ChoiceModel>()
        choicesMulti.add(ChoiceModel(1, "Option 1", true))
        choicesMulti.add(ChoiceModel(2, "Option 2", true))
        choicesMulti.add(ChoiceModel(3, "Option 3", false))
        choicesMulti.add(ChoiceModel(4, "Option 4", false))
        choicesMulti.add(ChoiceModel(5, "Option 5", false))

        superChargedChoiceView.setQuestion("Multi choice question?")
        superChargedChoiceView.setChoiceType(choicesMulti, ChoiceType.MULTIPLE)*/



    }
}