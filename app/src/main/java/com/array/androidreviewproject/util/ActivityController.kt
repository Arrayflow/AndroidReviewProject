package com.array.androidreviewproject.util

import android.app.Activity
import java.lang.ref.WeakReference
import java.util.Stack

object ActivityController {
    //维护一个存储Activity的虚引用栈
    private val activities = Stack<WeakReference<Activity>>()

    //定义一些常用方法
    fun pushTask(task: WeakReference<Activity>?) {
        activities.push(task)
    }

    fun removeTask(task: WeakReference<Activity>?) {
        activities.remove(task)
    }

    fun removeTask4Id(taskId: Int) {
        if (taskId < activities.size) {
            activities.removeAt(taskId)
        }
    }

    fun removeAllTask() {
        activities.removeAllElements()
    }

    fun getTopActivity(): WeakReference<Activity>? {
        return activities.peek()
    }
}