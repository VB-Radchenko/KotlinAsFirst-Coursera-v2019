@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

fun main() {
    /*
    for (inc in 1..200) {
        val result1 = ageDescription(inc)
        println("$result1")
    }*/
    val result = triangleKind(3.0, 7.5, 4.0)
    println("$result")
    val result1 = triangleKind(5.0, 3.0, 4.0)
    println("$result1")
    val result2 = triangleKind(4.0, 6.0, 8.0)
    println("$result2")
    val result3 = triangleKind(1.0, 1.5, 1.5)
    println("$result3")
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    return when {
        (age % 100 in 5..20) || (age in 5..20) || (age % 10 in 5..9) || (age % 10 == 0) -> "$age лет"       /* **5-*20 лет хх5-хx9 лет*/
        age % 10 == 1 -> "$age год"            /* хх1 год*/
        age % 10 in 2..4 -> "$age года"        /* хх2-хx4 года*/
        else -> "Недопустимый возраст $age"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val l1 = t1 * v1
    val l2 = t2 * v2
    val l3 = t3 * v3
    val lFull = l1 + l2 + l3
    val lHalf = lFull / 2
    return when {
        lHalf <= l1 -> lHalf / v1
        ((lHalf <= (l1 + l2)) && (lHalf > l1)) -> ((lHalf - l1) / v2) + t1
        else -> (((lHalf - (l1 + l2)) / v3) + t1 + t2)
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    return when {
        (kingX == rookX1) || (kingY == rookY1) && (kingX == rookX2) || (kingY == rookY2) -> 3
        (kingX == rookX1) || (kingY == rookY1) -> 1
        (kingX == rookX2) || (kingY == rookY2) -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {

    val deltaX2 = if (kingX > bishopX) {
        kingX - bishopX
    } else {
        bishopX - kingX
    }
    val deltaY2 = if (kingY > bishopY) {
        kingY - bishopY
    } else {
        bishopY - kingY
    }

    return when {
        (kingX == rookX) || (kingY == rookY) && (deltaX2 == deltaY2) -> 3
        (kingX == rookX) || (kingY == rookY) -> 1
        (deltaX2 == deltaY2) -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
// нахождение максимального значения
    val triangleMIN = min(min(a, b), c)                         // самая короткая сторона
    val triangleMAX = max(max(a, b), c)                         // самая длинная сторона
    val triangleMean = a + b + c - (triangleMAX + triangleMIN)  // средняя сторона
//   println("triangleMIN = $triangleMIN")
//   println("triangleMAX = $triangleMAX")
//   println("triangleMean = $triangleMean")
    return when {
        ((triangleMAX < (triangleMean - triangleMIN)) || (triangleMAX > (triangleMean + triangleMIN))) -> -1
        triangleMAX * triangleMAX == triangleMIN * triangleMIN + triangleMean * triangleMean -> 1
        triangleMAX * triangleMAX < triangleMIN * triangleMIN + triangleMean * triangleMean -> 0
        triangleMAX * triangleMAX > triangleMIN * triangleMIN + triangleMean * triangleMean -> 2
        else -> -1
    }
}


/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when {
        (b < c) -> -1
        (d < a) -> -1

        (a < c) && (b >= c) && (d > b) -> b - c
        (a < c) && (b > c) && (b > d) -> d - c

        (a > c) && (d >= a) && (b > d) -> d - a
        (a > c) && (d > a) && (d > b) -> b - a
        else -> -1
    }

}



