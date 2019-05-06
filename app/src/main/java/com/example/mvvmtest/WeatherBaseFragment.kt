package com.example.mvvmtest

import android.support.v4.app.Fragment
import android.view.animation.Animation

abstract class WeatherBaseFragment: Fragment() {
    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {

        return super.onCreateAnimation(transit, enter, nextAnim)
    }
}