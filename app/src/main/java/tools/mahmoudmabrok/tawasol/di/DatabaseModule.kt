package tools.mahmoudmabrok.tawasol.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.core.definition.Kind
import org.koin.dsl.module
import tools.mahmoudmabrok.tawasol.data.Repository
import tools.mahmoudmabrok.tawasol.data.local.WordDB

val databaseModule = module {


  //  single { Repository(get())}


/*
    single {
        WordDB.getAppDataBase(get())
    }*/


    single {
        Room.databaseBuilder(get(), WordDB::class.java,"myDB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }
}
