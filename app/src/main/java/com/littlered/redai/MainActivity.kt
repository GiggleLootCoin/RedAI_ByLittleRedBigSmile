package com.littlered.redai

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private val bg = Color.rgb(10, 10, 13)
    private val panel = Color.rgb(24, 23, 28)
    private val red = Color.rgb(238, 28, 55)
    private lateinit var conversation: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(22, 26, 22, 18)
            setBackgroundColor(bg)
        }
        val header = TextView(this).apply {
            text = "R  RedAI"
            textSize = 27f
            setTextColor(Color.WHITE)
            typeface = Typeface.create("sans-serif", Typeface.BOLD)
        }
        root.addView(header)
        root.addView(label("PRIVATE INTELLIGENCE STUDIO", 10f, red))
        val brain = TextView(this).apply {
            text = "YOUR PRIVATE AI\n\nStandalone app foundation\nThe on-device brain is not bundled yet."
            textSize = 16f
            setTextColor(Color.WHITE)
            setPadding(18, 18, 18, 18)
            setBackgroundColor(panel)
        }
        val brainParams = LinearLayout.LayoutParams(-1, -2).apply { topMargin = 24 }
        root.addView(brain, brainParams)
        root.addView(label("YOUR CONVERSATION", 11f, red).also { it.setPadding(0, 24, 0, 10) })

        val scroll = ScrollView(this)
        conversation = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(14, 14, 14, 14)
            setBackgroundColor(panel)
        }
        conversation.addView(message("RED AI", "Welcome. I’m RedAI, created and developed by Red aka LittleRedBigSmile."))
        scroll.addView(conversation)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))

        val composer = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL }
        val input = EditText(this).apply {
            hint = "Message RedAI…"
            setHintTextColor(Color.LTGRAY)
            setTextColor(Color.WHITE)
            setSingleLine(false)
            setBackgroundColor(panel)
            setPadding(12, 10, 12, 10)
        }
        composer.addView(input, LinearLayout.LayoutParams(0, -2, 1f))
        val send = Button(this).apply {
            text = "SEND"
            setTextColor(Color.WHITE)
            setBackgroundColor(red)
            setOnClickListener {
                val value = input.text.toString().trim()
                if (value.isNotEmpty()) {
                    conversation.addView(message("YOU", value))
                    conversation.addView(message("RED AI", "The chat interface is in place. The local inference engine is the next integration step."))
                    input.text.clear()
                }
            }
        }
        composer.addView(send)
        root.addView(composer, LinearLayout.LayoutParams(-1, -2).apply { topMargin = 12 })
        setContentView(root)
    }

    private fun label(value: String, size: Float, color: Int) = TextView(this).apply {
        text = value
        textSize = size
        setTextColor(color)
        letterSpacing = 0.12f
    }

    private fun message(who: String, body: String) = TextView(this).apply {
        text = "$who\n$body"
        textSize = 15f
        setTextColor(Color.WHITE)
        setPadding(12, 12, 12, 12)
        setBackgroundColor(panel)
        val p = LinearLayout.LayoutParams(-1, -2).apply { bottomMargin = 10 }
        layoutParams = p
    }
}
