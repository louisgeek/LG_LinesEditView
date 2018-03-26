# ClassicLinesEditView
多行文本输入框 带计数  限制文字数量

![image](https://raw.githubusercontent.com/louisgeek/ClassicLinesEditView/master/screenshots/pic.png)


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

```xml

	 	<!--  
			默认 app:maxCount="240"   
			默认 app:IgnoreCnOrEn="true"
		-->
 <com.classichu.lineseditview.LinesEditView
        android:layout_margin="10dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
```

code

```java
		mClassicLinesEditView.setHintText("hintText");
	    mClassicLinesEditView.setContentText("ContentText");
	
	    Log.i(TAG, "onCreate: getHintText"+mClassicLinesEditView.getHintText());
	    Log.i(TAG, "onCreate: getContentText"+mClassicLinesEditView.getContentText());
```
