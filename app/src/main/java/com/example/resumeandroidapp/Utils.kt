package com.example.resumeandroidapp

object Utils {

    fun getCategoryAndDescription(categoryList: String): ArrayList<Pair<String, String>> {
        if (categoryList.isEmpty())
            return arrayListOf()
        return categoryList
            .split(";")
            .filter { it.isNotBlank() && it.contains(",") }
            .mapNotNull {
                val parts = it.split(",", limit = 2)
                if (parts.size == 2) {
                    Pair(parts[0].trim(), parts[1].trim())
                } else null
            }.toCollection(ArrayList())
    }


    fun makeDetailString(detail: String, category: String, description: String): String {
        val uniformedString = "$detail$category,$description;"
        return uniformedString
    }
}