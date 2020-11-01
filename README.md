# supercharged_mcq
This is an androidx ready super customisable library written in Kotlin to easily add MCQs to your application.

## Usage:
```
### Gradle:
In your project gradle, add
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  In app's gradle file add
  dependencies {
	        implementation 'com.github.jacob-abe:supercharged_mcq:$latest_version'
	}
```
```
### Maven:
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
### What is this repository for? ###

This is based off of  arivista-digital / AD_Android_MCQ_Library,
only difference is I made it easier to import and use and added some crucial functionalities so you dont have to bother.

### Solutions ###

* Single Answer Selection
* Mutiple Answer Selection
* Correct and Wrong answers differentiate by color
* Submit Answer
* Clear Selection
* Reveal Answer
* Hide answers from quiz takers
* Observable pattern to get user's inputs

### How to use ###

 * activity_main Layout initialize
 
```
<com.quizzy.superchargedmcq.ui.SuperChargedChoiceView
        android:id="@+id/supercharged_mcq_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```
* MainActivity Funtionalites
* Add the questions
### Single Choice Questions ###
```
 val choicesSingle = ArrayList<ChoiceModel>()
        choicesSingle.add(ChoiceModel(1, "Option 1", false))
        choicesSingle.add(ChoiceModel(2, "Option 2", false))
        choicesSingle.add(ChoiceModel(3, "Option 3", false))
        choicesSingle.add(ChoiceModel(4, "Option 4", true))
        choicesSingle.add(ChoiceModel(5, "Option 5", false))
```
### Mutiple Choice Questions ###

```
 val choicesMulti = ArrayList<ChoiceModel>()
        choicesMulti.add(ChoiceModel(1, "Option 1", true))
        choicesMulti.add(ChoiceModel(2, "Option 2", true))
        choicesMulti.add(ChoiceModel(3, "Option 3", false))
        choicesMulti.add(ChoiceModel(4, "Option 4", false))
        choicesMulti.add(ChoiceModel(5, "Option 5", false))
```

* Custom view object creation
```
      val superChargedChoiceView = findViewById<SuperChargedChoiceView>(R.id.supercharged_mcq_view)
```

* Set Question just call the fuctions on custom view 

```
        arivistaview.setQuestion("Single choice question?");
        arivistaview.setChoiceType(choicesSingle, ChoiceType.SINGLE)

        arivistaview.setQuestion("Multi choice question?")
        arivistaview.setChoiceType(choicesMulti, ChoiceType.MULTIPLE)
```
*Added functionalities
superChargedChoiceView.setHiddenMode() to hide answers from end quiz taker
setUserAnswer(choice: ChoiceModel) of your choice to pre select a choice

### GIF ###

 ![MCQ Gif](/app/screenshots/mcq.gif)

### Links ###
* Link to original repo: https://github.com/arivista-digital/AD_Android_MCQ_Library

### Contribute freely!
I'm pretty liberal with accepting pull requests and will merge if it adds any good functionality. You can also post issues and enhancement requests.
Happy coding!
