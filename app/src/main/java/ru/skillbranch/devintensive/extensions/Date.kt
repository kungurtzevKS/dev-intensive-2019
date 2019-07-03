package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*



const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24*HOUR


fun Date.format (pattern: String = "HH:mm:ss dd:MM:YY"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add (value:Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time

    time +=when (units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time

    return this
}
fun Date.humanizeDiff(date:Date = Date()): String {
    var diffInMillies = (date.time - this.time).toInt()
    var diffInSec = diffInMillies / 1000
    var diffInMin = diffInSec / 60
    var diffInHour = diffInMin / 60
    var diffInDay = diffInHour / 24
    var REZ = ""
    if (diffInMillies > 0) {
        when {
            diffInSec <= 1 -> REZ = "только что"
            diffInSec <= 45 -> REZ = "несколько секунд назад"
            diffInSec <= 75 -> REZ = "минуту назад"
            diffInMin <= 45 -> REZ =
                "$diffInMin ${if (diffInMin%100 in 11..14) "минут" else if (diffInMin % 10 == 1) "минуту"
                else if (diffInMin % 10 == 0 || diffInMin % 10 >= 5) "минут" else "минуты"} назад"
            diffInMin <= 75 -> REZ = "час назад"
            diffInHour <= 22 -> REZ =
                "$diffInHour ${if (diffInMin in 11..14) "часов" else if (diffInHour % 10 == 1) "час"
                else if (diffInHour % 10 == 0 || diffInHour % 10 >= 5) "часов" else "часа"} назад"
            diffInHour <= 26 -> REZ = "день назад"
            diffInDay <= 360 -> REZ = "$diffInDay ${if (diffInMin in 11..14) "дней" else if (diffInDay % 10 == 1) "день"
            else if (diffInDay % 10 == 0 || diffInDay % 10 >= 5) "дней" else "дня"} назад"
            diffInDay > 360 -> REZ = "более года назад"
        }
        return REZ
    } else {
        diffInMillies *= -1
        diffInSec *= -1
        diffInMin *= -1
        diffInHour *= -1
        diffInDay *= -1
        when {
            diffInSec <= 1 -> REZ = "только что"
            diffInSec <= 45 -> REZ = "через несколько секунд"
            diffInSec <= 75 -> REZ = "через минуту"
            diffInMin <= 45 -> REZ =
                "через $diffInMin ${if (diffInMin%100 in 11..14) "минут" else if (diffInMin % 10 == 1) "минуту"
                else if (diffInMin % 10 == 0 || diffInMin % 10 >= 5) "минут" else "минуты"}"
            diffInMin <= 75 -> REZ = "через час"
            diffInHour <= 22 -> REZ =
                "через $diffInHour ${if (diffInMin in 11..14) "часов" else if (diffInHour % 10 == 1) "час"
                else if (diffInHour % 10 == 0 || diffInHour % 10 >= 5) "часов" else "часа"}"
            diffInHour <= 26 -> REZ = "через день"
            diffInDay <= 360 -> REZ = "через $diffInDay ${if (diffInMin in 11..14) "дней" else if (diffInDay % 10 == 1) "день"
            else if (diffInDay % 10 == 0 || diffInDay % 10 >= 5) "дней" else "дня"}"
            diffInDay > 360 -> REZ = "более чем через год"
        }
        return REZ
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}