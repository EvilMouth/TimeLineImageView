# TimeLineImageView

TimeLineImageView
时间轴，支持水平垂直，直线虚线

![image](https://github.com/yuhangjiayou/TimeLineImageView/raw/master/png/1.png)
![image](https://github.com/yuhangjiayou/TimeLineImageView/raw/master/png/2.png)

## Step One

```
Add it in your root build.gradle at the end of repositories:  
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

## Dependency

```groovy
compile 'com.github.izyhang:TimeLineImageView:{lastVersion}'
```

## Usage

```xml
<library.evilm.timeLineImageView.TimeLineImageView
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:paddingTop="20dp"
        android:src="@mipmap/ic_launcher" />
```
`lineOrientation` - 线方向 vertical or horizontal  
`lineColor` - 线颜色  
`lineSize` - 线大小  
`lineStart` - 是否画开始线  
`lineEnd` - 是否画结束线  
`lineType` - 线类型(直线或虚线) line or dash  
`dashWidth` - 虚线宽度  
`dashGap` - 虚线间隔  
