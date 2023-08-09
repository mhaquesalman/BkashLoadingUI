package com.example.myuiapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.bastanfar.semicirclearcprogressbar.SemiCircleArcProgressBar
import java.util.Timer
import java.util.TimerTask

private const val TAG = "CustomView"
class MainActivity : AppCompatActivity(), Animation.AnimationListener {
    lateinit var customView: View
    lateinit var view: View
    lateinit var semiCircle: SemiCircleArcProgressBar
//    lateinit var view2: View
//    lateinit var view3: View
    var isComplete = false
    var timer = Timer()

    lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customView = findViewById<CustomView>(R.id.customView)
        view = findViewById(R.id.view)
        semiCircle = findViewById(R.id.semiCircle)
//        view2 = findViewById(R.id.view2)
//        view3 = findViewById(R.id.view3)

        animation = AnimationUtils.loadAnimation(this, R.anim.move_anim)



        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                Log.d(TAG, "onAnimationStart: ")
            }

            override fun onAnimationEnd(p0: Animation?) {
                Log.d(TAG, "onAnimationEnd: ")
                Toast.makeText(this@MainActivity, "Done", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })


/*        view.setOnClickListener {
            Log.d(TAG, "onCreate: ${animation.hasEnded()}")
            view3.startAnimation(animation)
            Log.d(TAG, "onCreate 2: ${animation.hasEnded()}")
            if (animation.hasEnded()) {
                Log.d(TAG, "onCreate:  ok")
            }

        }*/



/*        view.setOnLongClickListener {
            object : CountDownTimer(3500, 1) {
                override fun onTick(p0: Long) {
                    if (!view.isPressed) {
                        Toast.makeText(this@MainActivity, "cancel", Toast.LENGTH_SHORT).show()
                        animation.cancel()
                        cancel()
                    } else {
                        view3.startAnimation(animation)
                    }
                }

                override fun onFinish() {
                    Toast.makeText(this@MainActivity, "Done", Toast.LENGTH_SHORT).show()
                }
            }.start()
            true
        } */


        view.setOnTouchListener { view, motionEvent ->

            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    semiCircle.visibility = View.VISIBLE
//                    semiCircle.setPercentWithAnimation(200)
                    if (!isComplete)
                        loadProgressAnimation(100)
                    true
                }
                MotionEvent.ACTION_UP -> {
                    semiCircle.visibility = View.INVISIBLE
                    timer.cancel()
                    semiCircle.setPercent(0)
                    true
                }
                else -> false
            }

            true
        }


    }

    fun loadProgressAnimation(percent: Int) {
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            var step = 0
            override fun run() {
                step += 2
                if (step <= percent) semiCircle.setPercent(step)
                Handler(Looper.getMainLooper()).post {
                    if (step == percent) {
                        Toast.makeText(this@MainActivity, "Done", Toast.LENGTH_SHORT).show()
                        semiCircle.visibility = View.INVISIBLE
                        semiCircle.setPercent(0)
                        isComplete = true
                    }
                }
                if (step == percent) {
                    Thread.sleep(4 * 1000)
                    finish()
                }
            }
        }, 0, 50)

    }



    override fun onAnimationStart(p0: Animation?) {
    }

    override fun onAnimationEnd(p0: Animation?) {
    }

    override fun onAnimationRepeat(p0: Animation?) {
    }
}