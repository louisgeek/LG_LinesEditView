# LouisMultiLineEditText
Android EditText多行文本输入  字数统计  限制数量Demo

![image](https://raw.githubusercontent.com/louisgeek/ClassicLinesEditView/master/screenshots/pic0.png)


![image](https://raw.githubusercontent.com/louisgeek/ClassicLinesEditView/master/screenshots/pic2.png)




Step 1. Add the JitPack repository to your build file


Add it in your root build.gradle at the end of repositories:

	  allprojects {
	  	repositories {
			...
			maven { url 'https://jitpack.io' }
	  	}
	  }
Step 2. Add the dependency  [![](https://jitpack.io/v/louisgeek/ClassicLinesEditView.svg)](https://jitpack.io/#louisgeek/ClassicLinesEditView)

  	dependencies {
	          compile 'com.github.louisgeek:ClassicLinesEditView:x.x.x'
	  }





attr


	 	<!--  
			默认 app:maxCount="240"   
			默认 app:IgnoreCnOrEn="true"
		-->



code

		mClassicLinesEditView.setHintText("hintText");
	    mClassicLinesEditView.setContentText("ContentText");
	
	    Log.i(TAG, "onCreate: getHintText"+mClassicLinesEditView.getHintText());
	    Log.i(TAG, "onCreate: getContentText"+mClassicLinesEditView.getContentText());
