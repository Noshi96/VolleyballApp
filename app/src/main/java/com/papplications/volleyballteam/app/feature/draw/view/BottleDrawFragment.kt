package com.papplications.volleyballteam.app.feature.draw.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.papplications.volleyballteam.databinding.FragmentBottleDrawBinding
import kotlin.random.Random


class BottleDrawFragment : Fragment() {

    private lateinit var _binding: FragmentBottleDrawBinding
    private val binding get() = _binding
    private var spinning: Boolean = false
    private val random: Random = Random

    private var bottle: ImageView? = null
    private var lastDir: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottleDrawBinding.inflate(inflater, container, false)

        bottle = binding.imageViewBottle

        bottle!!.setOnClickListener {
            spinBottle(it)
        }

        return binding.root
    }

    fun spinBottle(v: View?) {
        var spinDegree: Int = 0
        if (!spinning) {
            val newDir: Int = random.nextInt(until = 3600)
            val pivotX: Float = bottle?.width?.toFloat()?.div(2) ?: 1.0f
            val pivotY: Float = bottle?.height?.toFloat()?.div(2) ?: 1.0f
            val rotate: Animation =
                RotateAnimation(lastDir.toFloat(), newDir.toFloat(), pivotX, pivotY)
            rotate.duration = 2500
            rotate.fillAfter = true
            rotate.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    spinning = true
                }

                override fun onAnimationEnd(animation: Animation) {
                    spinning = false
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
            spinDegree = newDir % 360
            lastDir = newDir
            bottle?.startAnimation(rotate)
        }
    }
}