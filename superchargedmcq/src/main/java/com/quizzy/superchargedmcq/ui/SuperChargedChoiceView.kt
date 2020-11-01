package com.quizzy.superchargedmcq.ui


import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.content.res.ColorStateList
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.widget.CompoundButtonCompat
import androidx.lifecycle.MutableLiveData
import com.quizzy.superchargedmcq.R
import com.quizzy.superchargedmcq.models.ChoiceModel
import com.quizzy.superchargedmcq.utils.RadioButtonProperties
import kotlinx.coroutines.Job


open class SuperChargedChoiceView : LinearLayout {

    var userSelectedAnswer : MutableLiveData<String> = MutableLiveData()
    var userSelectedOptionCorrectStatus: MutableLiveData<Boolean> = MutableLiveData()
    var hiddenMode = false
    var radioButton: SuperChargedRadioButton? = null
    var checkBox: AppCompatCheckBox? = null
    var radioGroup: RadioGroup? = null

    var submitBtn: Button? = null
    var revealBtn: Button? = null
    var clearBtn: Button? = null

    var linearLayout: LinearLayout? = null

    var submitBtn1: Button? = null
    var revealBtn1: Button? = null
    var clearBtn1: Button? = null

    var linearLayout1: LinearLayout? = null

    var choicesListSingle = ArrayList<ChoiceModel>()
    var choicesListMulti = ArrayList<ChoiceModel>()

    var items = ArrayList<AppCompatCheckBox>()

    var radioProperties = RadioButtonProperties()

    var ctx: Context? = null

    lateinit var job: Job

    constructor(context: Context) : super(context) {
        ctx = context
        init()

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        ctx = context

        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        ctx = context
        init()
    }

    private fun init() {

    }

    //Add TextView
    fun addTextView(quetion: String) {

        View.inflate(context, R.layout.text_view_layout, this)
        orientation = VERTICAL

        val textView = TextView(context)
        textView.textSize = 20f
        textView.setTextColor(Color.BLACK)
        textView.text = quetion

        addView(textView)
    }

    //Add radio Buttons and checkboxes
    fun setChoiceType(choices: ArrayList<ChoiceModel>, choiceType: ChoiceType) {
        if (choiceType == ChoiceType.SINGLE) {
            choicesListSingle = choices
            View.inflate(context, R.layout.custom_radiobutton, this)
            radioGroup = RadioGroup(context)

            for (choice in choices) {
                addRadioButtons(choice.choiceText)
            }
            addView(radioGroup)
            addFormControlButtonsSingle()
        } else {
            choicesListMulti = choices
            View.inflate(context, R.layout.custom_checkbox, this)
            for (choice in choices) {
                addCheckBoxs(choice.choiceText)
            }
            addFormControlButtonsMulti()
        }
    }

    //Add Radio Buttons
    fun addRadioButtons(choice: String) {
        radioButton = SuperChargedRadioButton(context)
        radioButton!!.setText(choice)
        radioGroup?.addView(radioButton)

        //Radio Button Change Listener
        radioButton!!.setOnCheckedChangeListener() { compoundButton: CompoundButton, b: Boolean ->
            if (b) {
                submitButtonVisibility(submitBtn!!, true)
                clearButtonVisibility(clearBtn!!, true)
            }
        }

    }

    //Add CheckBoxes
    fun addCheckBoxs(choice: String) {
        checkBox = AppCompatCheckBox(context)

        checkBox!!.setText(choice)

        setCheckBoxColor(checkBox!!,Color.BLACK,Color.GRAY)

        addView(checkBox)
        items.add(checkBox!!)

        //Radio Button Change Listener
        checkBox!!.setOnCheckedChangeListener() { compoundButton: CompoundButton, b: Boolean ->
            if (b) {
                submitButtonVisibility(submitBtn1!!, true)
                clearButtonVisibility(clearBtn1!!, true)
            }
        }

    }

    //Add submit clear reveal Button
    fun addFormControlButtonsSingle() {
        linearLayout = LinearLayout(context)
        linearLayout!!.orientation = HORIZONTAL

        submitBtn = Button(context)
        submitBtn!!.text = "Submit"
        submitBtn!!.width = ViewGroup.LayoutParams.WRAP_CONTENT
        submitBtn!!.height = ViewGroup.LayoutParams.WRAP_CONTENT
        submitButtonVisibility(submitBtn!!, false)


        clearBtn = Button(context)
        clearBtn!!.text = "Clear"
        clearBtn!!.width = ViewGroup.LayoutParams.WRAP_CONTENT
        clearBtn!!.height = ViewGroup.LayoutParams.WRAP_CONTENT
        clearButtonVisibility(clearBtn!!, false)

        revealBtn = Button(context)
        revealBtn!!.text = "Reveal"
        revealBtn!!.width = ViewGroup.LayoutParams.WRAP_CONTENT
        revealBtn!!.height = ViewGroup.LayoutParams.WRAP_CONTENT
        revealButtonVisibility(revealBtn!!, false)


        linearLayout!!.addView(submitBtn)
        linearLayout!!.addView(clearBtn)
        linearLayout!!.addView(revealBtn)

        addView(linearLayout)

        //Answer Submit listener
        submitBtn!!.setOnClickListener() {
            submitRadionButtonAnswer(choicesListSingle)
            submitButtonVisibility(submitBtn!!, false)
            revealButtonVisibility(revealBtn!!, true)
            getAnswerOfUser(choicesListSingle)
        }
        //Clear Checked Radio Buttons
        clearBtn!!.setOnClickListener() {

            revealButtonVisibility(revealBtn!!, false)
            submitButtonVisibility(submitBtn!!, false)
            clearButtonVisibility(clearBtn!!, false)
            radioButtonSelectionClear()

        }
        //Reveal Answers
        revealBtn!!.setOnClickListener() {

            answerRevealRadioButton(choicesListSingle)
            submitButtonVisibility(submitBtn!!, false)
            clearButtonVisibility(clearBtn!!, true)
            revealButtonVisibility(revealBtn!!, false)
        }
    }


    //Add submit clear reveal Button
    fun addFormControlButtonsMulti() {
        linearLayout1 = LinearLayout(context)
        linearLayout1!!.orientation = HORIZONTAL

        submitBtn1 = Button(context)
        submitBtn1!!.text = "Submit"
        submitBtn1!!.width = ViewGroup.LayoutParams.WRAP_CONTENT
        submitBtn1!!.height = ViewGroup.LayoutParams.WRAP_CONTENT
        submitButtonVisibility(submitBtn1!!, false)


        clearBtn1 = Button(context)
        clearBtn1!!.text = "Clear"
        clearBtn1!!.width = ViewGroup.LayoutParams.WRAP_CONTENT
        clearBtn1!!.height = ViewGroup.LayoutParams.WRAP_CONTENT
        clearButtonVisibility(clearBtn1!!, false)

        revealBtn1 = Button(context)
        revealBtn1!!.text = "Reveal"
        revealBtn1!!.width = ViewGroup.LayoutParams.WRAP_CONTENT
        revealBtn1!!.height = ViewGroup.LayoutParams.WRAP_CONTENT
        revealButtonVisibility(revealBtn1!!, false)


        linearLayout1!!.addView(submitBtn1)
        linearLayout1!!.addView(clearBtn1)
        linearLayout1!!.addView(revealBtn1)

        addView(linearLayout1)

        //Answer Submit listener
        submitBtn1!!.setOnClickListener() {

            sumbitCheckBoxAnswer(choicesListMulti)
            submitButtonVisibility(submitBtn1!!, false)
            revealButtonVisibility(revealBtn1!!, true)
        }
        //Clear Checked Radio Buttons
        clearBtn1!!.setOnClickListener() {

            revealButtonVisibility(revealBtn1!!, false)
            submitButtonVisibility(submitBtn1!!, false)
            clearButtonVisibility(clearBtn1!!, false)
            checkBoxSelectionClear()

        }
        //Reveal Answers
        revealBtn1!!.setOnClickListener() {

            answerRevealCheckbox(choicesListMulti)
            submitButtonVisibility(submitBtn1!!, false)
            clearButtonVisibility(clearBtn1!!, true)
            revealButtonVisibility(revealBtn1!!, false)
        }
    }


    //Set question Titls
    fun setQuestion(quetion: String) {
        addTextView(quetion)
    }

    //Submit Answer Radio Buttons and Color Change
    fun submitRadionButtonAnswer(choicesList: ArrayList<ChoiceModel>) {

        try {
            if(hiddenMode){
                val checkedRadioButtonId = radioGroup!!.checkedRadioButtonId

                if (checkedRadioButtonId != 0) {

                    radioButton = findViewById<SuperChargedRadioButton>(radioGroup!!.checkedRadioButtonId)
                    if (radioButton!!.isChecked) {
                        radioGroupList()
                    }
                } else {

                    //Toast.makeText(context, "Please to select answer", Toast.LENGTH_LONG).show()
                }
            }else{
                val checkedRadioButtonId = radioGroup!!.checkedRadioButtonId

                if (checkedRadioButtonId != 0) {

                    radioButton = findViewById<SuperChargedRadioButton>(radioGroup!!.checkedRadioButtonId)
                    if (radioButton!!.isChecked) {

                        if (choicesList.get(checkedRadioButtonId - 1).isC_ans_w_ans)
                            radioButton!!.setColor(radioProperties.RADIO_BUTTON_CORRECT_COLOR)
                        else
                            radioButton!!.setColor(radioProperties.RADIO_BUTTON_WRONG_COLOR)
                        radioGroupList()
                    }
                } else {

                    //Toast.makeText(context, "Please to select answer", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: Exception) {

            //Toast.makeText(context, "Please to select answer", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    // Submit Answer CheckBox and Color Change
    fun sumbitCheckBoxAnswer(choicesList: ArrayList<ChoiceModel>) {
        var i = 0
        for (item in items) {
            if (choicesList.get(i).isC_ans_w_ans && item.isChecked) {
                item.isClickable = false
               // item.setTextColor(Color.GREEN)
                setCheckBoxColor(item,Color.GREEN,Color.RED)
            } else {
                if(item.isChecked){
                    //item.setTextColor(Color.RED)
                    setCheckBoxColor(item,Color.RED,Color.GRAY)
                }else{
                    item.isEnabled = false
                }
            }
            i++
        }
    }

    //Reset colors in radio buttons
    fun radioGroupList() {

        for (i in 0 until radioGroup!!.childCount) {

            val b = radioGroup!!.getChildAt(i) as SuperChargedRadioButton
            if (!b.isChecked) {
                b.setColor(radioProperties.RADIO_BUTTON_DEFAULT_COLOR)
                b.isEnabled = false
            }
        }
    }

    //Clear Radio Button Selection
    fun radioButtonSelectionClear() {

        for (i in 0 until radioGroup!!.childCount) {
            val b = radioGroup!!.getChildAt(i) as SuperChargedRadioButton
            b.setColor(radioProperties.RADIO_BUTTON_DEFAULT_COLOR)
            b.isEnabled = true
        }
        radioGroup!!.clearCheck()
    }

    // Clear Radio CheckBox Selection
    fun checkBoxSelectionClear() {
        for (item in items) {
            item.isEnabled = true
            item.isChecked = false
            item.isClickable = true
           // item.setTextColor(Color.BLACK)
            setCheckBoxColor(item,Color.BLACK,Color.GRAY)
        }
    }

    fun getAnswerOfUser(choicesList: ArrayList<ChoiceModel>){

        for (i in 0 until radioGroup!!.childCount) {
            val b = radioGroup!!.getChildAt(i) as SuperChargedRadioButton
            if(b.isChecked){
                userSelectedAnswer.value = choicesList[i].choiceText
                userSelectedOptionCorrectStatus.value = choicesList[i].isC_ans_w_ans
                break
            }
        }
    }

    fun setUserAnswer(choice: ChoiceModel){
        for (i in 0 until radioGroup!!.childCount) {
            val b = radioGroup!!.getChildAt(i) as SuperChargedRadioButton
            if(choicesListSingle[i].choiceText==choice.choiceText){
                b.isChecked = true
            }
        }
    }

    fun setHiddenMode(){
        hiddenMode = true
        revealBtn?.let { revealButtonHide(it) }
        revealBtn1?.let { revealButtonHide(it) }
    }

    //Reveal Answer
    fun answerRevealRadioButton(choicesList: ArrayList<ChoiceModel>) {

        for (i in 0 until radioGroup!!.childCount) {

            val b = radioGroup!!.getChildAt(i) as SuperChargedRadioButton

            if (choicesList.get(i).isC_ans_w_ans) {
                b.isChecked = true
                b.setColor(radioProperties.RADIO_BUTTON_CORRECT_COLOR)
                b.isEnabled=true
            } else {

                b.setColor(radioProperties.RADIO_BUTTON_DEFAULT_COLOR)
                b.isChecked = false
                b.isEnabled = false
            }
        }
    }

    //Reveal check Box Answer
    fun answerRevealCheckbox(choicesList: ArrayList<ChoiceModel>) {
        var i = 0;
        for (item in items) {
            if (choicesList.get(i).isC_ans_w_ans) {
                item.isChecked = true
                item.isEnabled = true
                item.isClickable = false
                //item.setTextColor(Color.GREEN)
                setCheckBoxColor(item,Color.GREEN,Color.GRAY)
            } else {
                item.isEnabled=false
                item.isChecked = false
            }
            i++
        }
    }

    //Submit button controls
    fun submitButtonVisibility(submit: Button, visibility: Boolean) {

        submit.isEnabled = visibility
    }

    //Clear button controls
    fun clearButtonVisibility(clear: Button, visibility: Boolean) {

        clear.isEnabled = visibility
    }

    //Reveal button controls
    fun revealButtonVisibility(reveal: Button, visibility: Boolean) {

        reveal.isEnabled = visibility
    }

    fun revealButtonHide(reveal: Button) {

        reveal.visibility = View.GONE
    }


    //Set checkbox color
    fun setCheckBoxColor(checkBox: AppCompatCheckBox, checkedColor: Int, uncheckedColor: Int) {
        val states = arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf())
        val colors = intArrayOf(checkedColor, uncheckedColor)
        CompoundButtonCompat.setButtonTintList(checkBox, ColorStateList(states, colors))
    }
}