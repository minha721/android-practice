package com.ssafy.mycustomview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class CustomWeather: ConstraintLayout {
    lateinit var img: ImageView
    lateinit var txt: TextView

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init()
        getAttrs(attrs)
    }

    private fun init() {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_weather, this, false)
        addView(view)
        img = findViewById(R.id.iv_image)
        txt = findViewById(R.id.tv_txt)
    }

    // attrs.xml 파일로부터 속성 정보 확보 - typedArray
    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomWeather)
        setTypedArray(typedArray)
    }

    // 속성값을 view 요소들에 연결
    private fun setTypedArray(typedArray: TypedArray) {
        img.setImageResource(
            typedArray.getResourceId(
                R.styleable.CustomWeather_weatherImg,
                R.drawable.ic_launcher_foreground
            )
        )
        txt.text = typedArray.getText(R.styleable.CustomWeather_weatherTxt)
        typedArray.recycle()
    }
}