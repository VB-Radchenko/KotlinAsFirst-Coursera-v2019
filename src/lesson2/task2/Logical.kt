@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.sqrt
import kotlin.math.max
import kotlin.math.min

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val number1 = number / 1000
    val number2 = (number - (number1 * 1000)) / 100
    val number3 = (number - (number1 * 1000) - (number2 * 100)) / 10
    val number4 = number % 10
    return when {
        number1 + number2 == number3 + number4 -> true
        else -> false
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    return when {
        x1 == x2 -> true
        y1 == y2 -> true
        kotlin.math.abs(x1 - x2) == kotlin.math.abs(y1 - y2) -> true
        else -> false
    }

}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */

fun daysInMonth(month: Int, year: Int): Int {

    val leapYear: Int
    if (year % 100 == 0) {
        if (year % 400 == 0) {
            leapYear = 1
        } else leapYear = 0
    } else if (year % 4 == 0) {
        leapYear = 1
    } else leapYear = 0
    /*
    println("leapYear $leapYear")
    println("month $month")
    println("year $year")
    */
    return when {
        month == 1 -> 31
        ((month == 2) && (leapYear == 1)) -> 29
        ((month == 2) && (leapYear == 0)) -> 28
        month == 3 -> 31
        month == 4 -> 30
        month == 5 -> 31
        month == 6 -> 30
        month == 7 -> 31
        month == 8 -> 31
        month == 9 -> 30
        month == 10 -> 31
        month == 11 -> 30
        month == 12 -> 31
        else -> 0
    }

}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
    val deltaCenter = sqrt(sqr(x2 - x1) + sqr(y2 - y1))
    //  println("deltaCenter $deltaCenter")
    return when {
        (deltaCenter + r1 <= r2) -> true
        else -> false
    }
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {

    val brickMax = max(max(a, b), c)
    val brickMin = min(min(a, b), c)
    val brickAve = a + b + c - brickMax - brickMin
    val holeMax = max(r, s)
    val holeMin = min(r, s)
    println("brickMax $brickMax")
    println("brickMin $brickMin")
    println("brickAve $brickAve")
    println("holeMax $holeMax")
    println("holeMin $holeMin")
    println("----------------")


    return when {
        brickMax <= holeMin -> true
        (brickMax <= holeMax) && (brickAve <= holeMin) -> true
        (brickMax <= holeMax) && (brickMin <= holeMin) -> true
        (brickAve <= holeMax) && (brickMin <= holeMin) -> true
        else -> false
    }
}
