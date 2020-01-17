package tools.mahmoudmabrok.tawasol.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat


fun String.log(){
    Log.d("TestApp",this)
}

fun String.clear(): String {
     return this.filter { it.isLetter() }
}

fun Context.show(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun Activity.goTo(dst:Class<*>){
    startActivity(Intent(this,dst))
}

fun Context.getImage(name:String): Drawable? {
    val resID = this.resources.getIdentifier(name ,
            "drawable",
            applicationContext.packageName )
    return ActivityCompat.getDrawable(this,resID)
}

fun Context.isExists(name:String): Boolean {
    val resID = this.resources.getIdentifier(name ,
            "drawable",
            applicationContext.packageName )
    return resID != 0
}



