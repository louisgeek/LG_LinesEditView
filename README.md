# LouisMultiLineEditText
Android EditText多行文本输入  字数统计  限制数量Demo

![image](https://raw.githubusercontent.com/louisgeek/LouisMultiLineEditText/master/screenshots/pic0.png)


![image](https://raw.githubusercontent.com/louisgeek/LouisMultiLineEditText/master/screenshots/pic2.png)



Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.louisgeek:LouisMultiLineEditText:v1.0.0'
	}



attr


	 	<!--  
			默认 app:maxCount="240"   
			默认 app:IgnoreCnOrEn="true"
		-->
    <com.louisgeek.multiedittextviewlib.MultiEditInputView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="5dp"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        app:maxCount="100"
		app:contentText="content"
		app:contentHeight="200dp"
        />


code

		mMultiEditInputView.setHintText("hintText");
        mMultiEditInputView.setContentText("ContentText");

        Log.i(TAG, "onCreate: getHintText"+mMultiEditInputView.getHintText());
        Log.i(TAG, "onCreate: getContentText"+mMultiEditInputView.getContentText());
