StrokeTextView
======

可以自定义描边的TextView

Import
--------

Maven:
```xml
<dependency>
  <groupId>Tatsumi</groupId>
  <artifactId>module.stroketextview.Views</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```

Gradle:
```groovy
compile 'Tatsumi:module.stroketextview.Views:1.0'
```

How To Use
-------------
1、XML
	
    <module.stroketextview.Views.StrokeTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        stroke:innerColor="#FFFF00"
        stroke:strokeColor="#FF0000"
		stroke:strokeWidth="5" />
2、constructor
	
	public StrokeTextView(Context context, int stokeColor, int innerColor, int strokeWidth)
不建议修改strokeWidth 默认为5，过大会导致描边重叠