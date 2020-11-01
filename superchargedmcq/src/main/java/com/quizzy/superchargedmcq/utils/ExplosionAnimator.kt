package com.quizzy.superchargedmcq.utils

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import java.util.*

internal class ExplosionAnimator(
    container: RadioButtonDrawable,
    bound: Rect?,
    color: Int,
    number: Int,
    outerRadius: Int,
    innerRadius: Int
) : ValueAnimator() {
    private val mPaint: Paint
    private val mParticles: Array<Particle?>
    private val mBound: Rect
    private val mContainer: RadioButtonDrawable
    private val mColor: Int
    private var GAP = 10
    private var mOuterRadius = 10
    private var mInnerRadius = 10
    private fun generateParticle(random: Random, index: Int, max: Int): Particle {
        val particle: Particle = Particle()
        val nextDegree = Math.max(random.nextInt(360 / max) - GAP, 0) + (index * (360 / max) + GAP)
        val magnifiers = degreeToDirection(nextDegree)
        particle.xMag = magnifiers[0]
        particle.yMag = magnifiers[1]
        particle.baseCenterX = mBound.centerX().toFloat()
        particle.cx = mBound.centerX().toFloat()
        particle.baseCenterY = mBound.centerY().toFloat()
        particle.cy = mBound.centerY().toFloat()
        particle.radius = mInnerRadius.toFloat()
        particle.baseRadius = particle.radius
        return particle
    }

    //Drawing pixels
    fun draw(canvas: Canvas): Boolean {
        if (!isRunning) {
            return false
        }
        for (particle in mParticles) {
            particle!!.advance(animatedValue as Float)
            mPaint.color = mColor
            canvas.drawCircle(particle.cx, particle.cy, particle.radius, mPaint)
        }
        mContainer.invalidateSelf()
        return true
    }

    override fun start() {
        super.start()
        mContainer.invalidateSelf()
    }

    private inner class Particle {
        var cx = 0f
        var cy = 0f
        var radius = 0f
        var baseRadius = 0f
        var baseCenterX = 0f
        var baseCenterY = 0f
        var yMag = 0f
        var xMag = 0f
        fun advance(factor: Float) {
            if (factor <= 0.80) {
                radius = baseRadius * (1 - factor * 0.98765f)
            } else if (factor > 0.80 && factor <= 0.99) {
                radius = baseRadius * (0.19f + factor * 0.20202020f)
            }
            if (factor > 0.99) radius = baseRadius * (0.21f - 0.21f * factor)
            cx = baseCenterX + xMag * factor * mOuterRadius
            cy = baseCenterY + yMag * factor * mOuterRadius
        }
    }

    private fun degreeToDirection(degree: Int): FloatArray {
        return floatArrayOf(
            Math.cos(Math.toRadians(degree.toDouble())).toFloat(),
            (-Math.sin(Math.toRadians(degree.toDouble()))).toFloat()
        )
    }

    //Constructor
    init {
        mInnerRadius = innerRadius
        mOuterRadius = outerRadius
        mPaint = Paint()
        mBound = Rect(bound)
        mColor = color
        mParticles = arrayOfNulls(number)
        val random = Random(System.currentTimeMillis())
        for (i in 0 until number) {
            mParticles[i] = generateParticle(random, i, number)
        }
        mContainer = container
        setFloatValues(0f, 1f)
        GAP = 50 / number
    }
}