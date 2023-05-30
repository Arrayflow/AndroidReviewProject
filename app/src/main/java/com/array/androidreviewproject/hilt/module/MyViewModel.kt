package com.array.androidreviewproject.hilt.module

import com.array.androidreviewproject.hilt.bean.Student
import javax.inject.Inject


class MyViewModel {
    /**
     * 1、注入Application时，已经由Dagger完成
     * 2、注入Activity时，只能由ActivityComponent及以下的容器绑定
     */
}

/**
 * 如果将YourViewModel的构造函数添加@Inject注解，则该类为已经被绑定的类
 * 可以直接将Student进行注入，无需添加@Inject注解
 */
class YourViewModel @Inject constructor(var student: Student) {

}