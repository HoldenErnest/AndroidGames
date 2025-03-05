package com.holden.newgames

import kotlin.math.sqrt

data class Vector2(var x: Float, var y: Float) {

    operator fun plus(other: Vector2): Vector2 =
        Vector2(this.x + other.x, this.y + other.y)

    operator fun minus(other: Vector2): Vector2 =
        Vector2(this.x - other.x, this.y - other.y)

    operator fun times(scalar: Float): Vector2 =
        Vector2(this.x * scalar, this.y * scalar)

    fun vectorTowardsPoint(v2: Vector2) : Vector2 { // distance to a point from this vector
        return Vector2(x - v2.x, y - v2.y)
    }

    fun magnitude(): Float =
        sqrt(x * x + y * y)

    fun normalized(): Vector2 {
        val mag = magnitude()
        return if (mag > 0) Vector2(x / mag, y / mag) else Vector2(0f, 0f)
    }
}