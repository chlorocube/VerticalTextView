package jp.co.chlorocube.verticaltextsample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import jp.co.chlorocube.verticaltext.DirectionTextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        supportActionBar?.hide()

        val textVertical = findViewById<DirectionTextView>(R.id.text_vertical)
        val textVerticalEdge = findViewById<DirectionTextView>(R.id.text_vertical_edge)
        val button = findViewById<Button>(R.id.button_direction_switch)
        button.setOnClickListener {
            textVertical.switchDirection()
            textVerticalEdge.switchDirection()
        }
    }
}