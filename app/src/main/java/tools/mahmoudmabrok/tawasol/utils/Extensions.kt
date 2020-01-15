package tools.mahmoudmabrok.tawasol.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast


fun String.log(){
    Log.d("TestApp",this)
}

fun Context.show(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun Activity.goTo(dst:Class<*>){
    startActivity(Intent(this,dst))
}

