package com.example.todolistapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolistapp.data.TodoDatabase
import com.example.todolistapp.data.TodoRepository
import com.example.todolistapp.data.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTodoDatabase(app:Application):TodoDatabase{
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_database",
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideTodoDao(db:TodoDatabase):TodoRepository{
        return TodoRepositoryImpl(db.dao)
    }

}