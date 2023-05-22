package com.array.androidreviewproject.util

import java.lang.StringBuilder
import java.util.ArrayList

class DSL {

}

fun main() {
    val tb = Tab()
    tb.tr {
        td { "apple" }
        td { "watermelon" }
        td { "peat" }
        td { "banana" }
        td { "pear" }
    }
    println(tb.html())
}

class Td {
    var content = ""

    fun html() = "\n\t\t<td>$content</td>"
}

class Tr {
    private val children = ArrayList<Td>()

    fun td(b: Td.() -> String) {
//        实际写法
//        val td = Td()
//        td.content = td.b()
//        children.add(td)

//        简写
        Td().apply {
            content = b()
            children.add(this)
        }
    }

    fun html(): String {
        return StringBuilder().run {
            append("\n\t<tr>")
            for (child in children) {
                append(child.html())
            }
            append("\n\t</tr>")
            toString()
        }
    }
}

class Tab {
    private val children = ArrayList<Tr>()

    fun tr(b: Tr.() -> Unit) {
        Tr().apply {
            b()
            children.add(this)
        }
    }

    fun html(): String {
        return StringBuilder().run {
            append("<table>")
            for (child in children) {
                append(child.html())
            }
            append("\n</table>")
            toString()
        }
    }
}