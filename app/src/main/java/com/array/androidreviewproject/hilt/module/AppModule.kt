package com.array.androidreviewproject.hilt.module

import com.array.androidreviewproject.hilt.bean.Student
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//由Hilt提供的ApplicationComponent标准容器组件，表示这个Module和AppComponent建立了关联
@InstallIn(SingletonComponent::class)
@Module //Module注入
class AppModule {

    //指定依赖作用域, ApplicationComponent默认是Singleton
    @Singleton
    //提供构建方法
    @Provides
    fun provideStudent(): Student {
        return Student()
    }
}