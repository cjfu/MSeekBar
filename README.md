# MSeekBar
Android圆角的进度条控件，支持环形与线型

[![依赖方法](https://jitpack.io/v/cjfu/MSeekBar.svg)](https://jitpack.io/#cjfu/MSeekBar)

## 依赖方法：

1、将其添加到项目的 build.gradle中:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  2、添加依赖：

	dependencies {
	        implementation 'com.github.cjfu:MSeekBar:Tag'
	}

## 效果图：

![效果图](https://github.com/cjfu/MSeekBar/blob/master/demo.gif) 

## 可配置项：

        <!--进度最大值-->
        <attr name="msb_max" format="integer"/>
        <!--进度值-->
        <attr name="msb_progress" format="integer"/>
        <!--进度条类型 circle:圆形 line:线形-->
        <attr name="msb_mode" format="integer">
            <enum name="line" value="0"/>
            <enum name="circle" value="1"/>
        </attr>
        <!--圆形进度条最小进度时的角度位置：从圆环最右侧的点开始，顺时针旋转的角度-->
        <attr name="msb_circle_start" format="integer"/>
        <!--圆形进度条最大进度时的角度位置：从圆环最右侧的点开始，顺时针旋转的角度-->
        <attr name="msb_circle_end" format="integer"/>
        <!--圆形进度条圆环宽度-->
        <attr name="msb_circle_width" format="dimension"/>
        <!--进度条底色渐变开关-->
        <attr name="msb_gradient" format="boolean"/>
        <!--进度条底色渐变开始颜色 渐变开启时生效-->
        <attr name="msb_gradient_start_color" format="color|reference"/>
        <!--进度条底色渐变结束颜色 渐变开启时生效-->
        <attr name="msb_gradient_end_color" format="color|reference"/>
        <!--进度条底色渐变色方向 渐变开启时生效-->
        <attr name="msb_gradient_orientation" format="integer">
            <!-- 进度由小到大的方向进行渐变 -->
            <enum name="horizontal" value="0" />
            <!-- 进度相同时宽度上进行渐变 -->
            <enum name="vertical" value="1" />
        </attr>
        <!--进度颜色-->
        <attr name="msb_progress_color" format="color|reference"/>
        <!--进度底色 渐变关闭时生效-->
        <attr name="msb_background" format="color|reference"/>

## 实现原理
其实现原理也很简单，绘制两条线，一条作为进度条进度背景，一条作为进度。在细节方面做了一些优化，并且使常用功能的可配置。欢迎大家使用
