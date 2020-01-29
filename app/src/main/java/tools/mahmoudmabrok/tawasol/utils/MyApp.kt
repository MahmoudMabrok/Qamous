package tools.mahmoudmabrok.tawasol.utils

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tools.mahmoudmabrok.tawasol.di.databaseModule

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeKoin()
    }

    private fun initializeKoin() {
        "initialize".log()
        startKoin {
            androidContext(this@MyApp)
            modules(
                    listOf(
                            databaseModule
                    )
            )

        }
    }

}