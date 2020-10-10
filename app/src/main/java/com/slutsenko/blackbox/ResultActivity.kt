package com.slutsenko.blackbox

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.slutsenko.blackbox.GameActivity.Companion.EXTRA_RESULT
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        txt_result.text = intent.getStringExtra(EXTRA_RESULT)
    }

    fun tryAgain(v: View) {
        startActivity(Intent(this, GameActivity::class.java))
    }
}