package tools.mahmoudmabrok.tawasol.utils

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.InputMethodManager
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


fun Context.dismissKeyboard() {
    val imm by lazy { this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    val windowHeightMethod = InputMethodManager::class.java.getMethod("getInputMethodWindowVisibleHeight")
    val height = windowHeightMethod.invoke(imm) as Int
    if (height > 0) {
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
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




