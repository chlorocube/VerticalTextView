VerticalTextView
====

Android用の縦書きTextViewライブラリ。<br>
<br>
・ TextViewを継承しています。<br>
・ 縦書きと横書きを切り替えできます。<br>
・ 縦書きについて、半角文字には対応していません。

Screenshot
----------
![screenshotVTV.png](https://github.com/chlorocube/VerticalTextView/blob/main/screenshot/ScreenshotVTV.png)

使用フォントは、フォントな様の「<a href=http://www.fontna.com/blog/1651/>にくまるフォント</a>」です。

Download
--------

grab via Gradle:
```
repositories {
    maven {
        url 'https://chlorocube.github.io/VerticalTextView/repository'
    }
}

dependencies {
    implementation 'jp.co.chlorocube:verticaltext:1.0.0'
}
```

Usage
-----

xmlでの記述例：
```xml
    <jp.co.chlorocube.verticaltext.DirectionTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="#38b48b"
        android:padding="12dp"
        android:fontFamily="@font/フォントファイル名"
        android:layout_gravity="end"
        android:textSize="20sp"
        android:textColor="@color/black"
        android:text="縦書き"/>
    <jp.co.chlorocube.verticaltext.EdgeDirectionTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="#e6b422"
        android:padding="12dp"
        android:fontFamily="@font/フォントファイル名"
        android:layout_gravity="end"
        android:textSize="20sp"
        android:textColor="@color/black"
        android:text="アウトラインつき縦書き"/>
```

コードでの記述例：
```kotlin
    fun hogehoge() {

        val textVertical = DirectionTextView(this)
        val textVerticalEdge = EdgeDirectionTextView(this)

        // 縦書きor横書きを切り替える (コードのみ)
        textVertical.switchDirection()

        // 現在縦書きかどうか？ (コードのみ)
        val isVerticalText = textVertical.isVerticalText()

        // アウトラインの色と幅を設定 (コードのみ)
        textVerticalEdge.setEdgeColor(Color.RED)
        textVerticalEdge.setEdgeWidth(9f)
    }
```

ライブラリ使用にあたっての注意点
-------

<ul>
<li>フォントによっては、縦書きTextViewと相性のよろしくないものがあります。<br>
(フォントグリフそのものに上下余白が含まれるNoto系や源系など)<br>
</li>
<li>フォントによっては、縦書きの場合に、特定の文字の表示位置がおかしいかもしれません。<br>
(「ぁ」「…」など)<br>
その場合は、<a href=https://github.com/chlorocube/VerticalTextView/blob/main/verticaltext>verticaltextフォルダ</a>ごとコピーしてCharSettingVerticalクラスの中の位置設定の値を調整してご使用ください。
</li>
<li>フォントサイズは小さめ推奨。<br>
フォントサイズが大きいと、縦書きの場合に、下方向にはみ出してしまう不具合があります。</li>
</ul>

License
-------

```txt
Copyright 2021 chlorocube

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```