# DrawableToolbox
[![gitHub release](https://img.shields.io/github/release/duanhong169/DrawableToolbox.svg?style=social)](https://github.com/duanhong169/DrawableToolbox/releases)
[![platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)
<a target="_blank" href="https://android-arsenal.com/api?level=14"><img src="https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat"></a>
[![license](https://img.shields.io/badge/license-Apache%202-green.svg)](https://github.com/duanhong169/DrawableToolbox/blob/master/LICENSE)

The missing DrawableToolbox for Android. Get rid of the boring and always repeated `drawable.xml` files.

<img src='art/screen-shot-1.jpg' height='500px'/> <img src='art/screen-video-1.gif' height='500px'/> <br/>
<img src='art/screen-shot-2.jpg' height='500px'/> <img src='art/screen-video-2.gif' height='500px'/>

## Features

* Create drawables programmatically
* Support `<shape>`, `<rotate>`, `<scale>`, `<ripple>` drawables

## Future works

* Support `LayerDrawable `(`<layer-list>`)
* Please file an issue

## Gradle

```
dependencies {
    implementation 'com.github.duanhong169:drawabletoolbox:${latestVersion}'
    ...
}
```

> Replace `${latestVersion}` with the latest version code. See [releases](https://github.com/duanhong169/DrawableToolbox/releases).

## Usage

Use the `DrawableBuilder` to setup the `Drawable` and call `build()` to create it. 

> Please check all the supported APIs in [DrawableBuilder.kt](https://github.com/duanhong169/DrawableToolbox/blob/master/drawabletoolbox/src/main/java/top/defaults/drawabletoolbox/DrawableBuilder.kt).

Here are some examples:

Code:

```java
DrawableBuilder()
        .rectangle()
        .hairlineBordered()
        .strokeColor(COLOR_DEFAULT)
        .strokeColorPressed(COLOR_PRESSED)
        .ripple()
        .build()
```

Result:

![Bordered with Ripple](art/sample-1.png)

Code:

```java
DrawableBuilder()
        .rectangle()
        .hairlineBordered()
        .mediumDashed()
        .strokeColor(COLOR_DEFAULT)
        .strokeColorPressed(COLOR_PRESSED)
        .ripple()
        .build()
```

Result:

![Medium-dashed, Bordered with Ripple](art/sample-2.png)

Code:

```java
DrawableBuilder()
        .rectangle()
        .rounded()
        .solidColor(COLOR_DEFAULT)
        .solidColorPressed(COLOR_PRESSED)
        .build()
```

Result:

![Rounded, Filled with States](art/sample-4.png)

Code:

```java
DrawableBuilder()
        .rectangle()
        .hairlineBordered()
        .longDashed()
        .rounded()
        .strokeColor(COLOR_DEFAULT)
        .strokeColorPressed(COLOR_PRESSED)
        .ripple()
        .build()
```

Result:

![Rounded, Long-dashed, Bordered with Ripple](art/sample-6.png)

Code:

```java
DrawableBuilder()
        .rectangle()
        .rounded()
        .gradient()
        .linearGradient()
        .angle(90)
        .startColor(COLOR_DEFAULT)
        .endColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        .ripple()
        .rippleColor(COLOR_PRESSED)
        .build()
```

Result:

![Rounded, Gradient with Ripple](art/sample-8.png)

Code:

```java
val baseBuilder = DrawableBuilder()
        .rectangle()
        .rounded()
        .gradient()
        .gradientType(GradientDrawable.LINEAR_GRADIENT)
        .angle(90)
val normalState = baseBuilder
        .startColor(COLOR_DEFAULT)
        .endColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        .build()
val pressedState = baseBuilder
        .startColor(COLOR_PRESSED)
        .endColor(ContextCompat.getColor(context, R.color.colorAccentDark))
        .build()

StateListDrawableBuilder()
        .normal(normalState)
        .pressed(pressedState)
        .build()
```

Result:

![Rounded, Gradient with States](art/sample-9.png)

Code:

```java
val baseBuilder = DrawableBuilder()
        .rectangle()
        .rounded()
        .hairlineBordered()
        .strokeColor(COLOR_DEFAULT)
        .solidColorSelected(COLOR_DEFAULT)
        .ripple()

return when(type) {
    SegmentedControlDrawableSpec.TYPE_LEFT_MOST -> {
        baseBuilder.topRightRadius(0)
                .bottomRightRadius(0)
                .build()
    }
    SegmentedControlDrawableSpec.TYPE_RIGHT_MOST -> {
        baseBuilder.topLeftRadius(0)
                .bottomLeftRadius(0)
                .build()
    }
    else -> {
        baseBuilder.cornerRadius(0).build()
    }
}
```

Result:

![Segmented Control](art/sample-10.png)

Code:

```java
// Rotate & Leveled the Ring
DrawableBuilder()
        .size(200)
        .ring()
        .useLevelForRing()
        .solidColor(COLOR_DEFAULT)
        .innerRadiusRatio(3f)
        .thicknessRatio(10f)
        .rotate(0f, 720f)
        .build()
```

Result:

![Rotate & Leveled the Ring](art/sample-11.png)

Code:

```java
// Rotate, Sweep & Flip the Ring
DrawableBuilder()
        .size(200)
        .ring()
        .innerRadiusRatio(3f)
        .thicknessRatio(10f)
        .gradient()
        .sweepGradient()
        .rotate(0f, 360f)
        .flip()
        .build()
```

Result:

![Rotate, Sweep & Flip the Ring](art/sample-12.png)

Code:

```java
// Rotate, Sweep & Scale the Oval with States
val baseBuilder = DrawableBuilder()
        .size(400)
        .oval()
        .gradient()
        .sweepGradient()
        .rotate(0f, 360f)
        .scale(0.5f)
        .scaleGravity(Gravity.START or Gravity.TOP)
val normalState = baseBuilder.build()
val pressedState = baseBuilder
        .startColor(COLOR_PRESSED)
        .endColor(0x7FFFFFFF)
        .build()
StateListDrawableBuilder()
        .normal(normalState)
        .pressed(pressedState)
        .build()
```

Result:

![Rotate, Sweep & Scale the Oval with States](art/sample-13.png)

Please check out the app sample code for more details.

## License

   Copyright 2018 Hong Duan

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.