package com.example.bennaftali.helloworld

import android.content.Intent
import android.opengl.EGL14
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dynamic_text_view.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        button.setOnClickListener {
            hello.setText("you clicked me")
            //setContentView(R.layout.dynamic_text_view)
/*
            if (dynamic != null && dynamic.visibility == View.INVISIBLE)
                dynamic.visibility = View.VISIBLE
            else if (dynamic != null)
                dynamic.visibility = View.INVISIBLE
*/
            if (dynamic != null && dynamic.parent != null) {
                //val vg : ViewGroup = dynamic.parent as ViewGroup
                //vg.removeView(dynamic)
                rootContainer.removeView(dynamic)
                System.gc()
            }
            else {
                this.layoutInflater.inflate(R.layout.dynamic_text_view, rootContainer)
                ConstraintSet().apply {
                    clone(rootContainer)

                    connect(dynamic.id, ConstraintSet.TOP, button.id, ConstraintSet.BOTTOM)
                    connect(dynamic.id, ConstraintSet.LEFT, rootContainer.id, ConstraintSet.LEFT)
                    connect(dynamic.id, ConstraintSet.RIGHT, rootContainer.id, ConstraintSet.RIGHT)
                    applyTo(rootContainer)

                    Intent("clicked button")
                }
            }
        }
    }
}
