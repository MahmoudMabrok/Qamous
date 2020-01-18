package tools.mahmoudmabrok.tawasol.utils

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.core.app.ActivityCompat


fun String.log() {
    Log.d("TestApp", this)
}

fun String.clear(): String {
    return this.filter { it.isLetter() }
}

fun Context.show(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.goTo(dst: Class<*>) {
    startActivity(Intent(this, dst))
}

fun Context.getImage(name: String): Drawable? {
    val resID = this.resources.getIdentifier(name,
            "drawable",
            applicationContext.packageName)
    return ActivityCompat.getDrawable(this, resID)
}

fun Context.isExists(name: String): Boolean {
    val resID = this.resources.getIdentifier(name,
            "drawable",
            applicationContext.packageName)
    return resID != 0
}

fun View.animItem() {
    val scalx = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.5f, 1f)
    val scaly = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.5f, 1f)
    val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f)
    ObjectAnimator.ofPropertyValuesHolder(this, scalx, scaly, alpha).apply {
        interpolator = OvershootInterpolator()
        duration = 800
    }.start()
}

fun View.animateItemWithAction(a: () -> Unit) {
    ObjectAnimator.ofFloat(this, View.ALPHA,0f,1.0f).apply {
        duration = 700
    }.start()
            .apply {
                postDelayed(a, 800)
            }
}




