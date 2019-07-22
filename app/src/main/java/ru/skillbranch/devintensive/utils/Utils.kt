package ru.skillbranch.devintensive.utils

object Utils {
    val maps = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )
    fun parseFullName (fullName: String?): Pair<String?, String?>{
        //TODO FIX null null
        val parts : List<String>? = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        return Pair (firstName, lastName)
    }

    fun transliteration(payload: String, divider:String = "_"): String {

        var newStr = ""
        payload.forEach {
            newStr += maps[it.toString()]?:it
        }
        return newStr
    }


    fun toInitials(firstName: String?, lastName: String?): String? {
        var Initials = ""
        if (firstName != null) Initials += firstName?.reversed()?.takeLast(1)
        if (lastName != null) Initials += lastName?.reversed()?.takeLast(1)
        return Initials
    }
}