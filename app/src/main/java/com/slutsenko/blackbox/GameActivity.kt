package com.slutsenko.blackbox

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.slutsenko.blackbox.databinding.ActivityGameBinding
import java.text.DecimalFormat
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var binding: ActivityGameBinding
    lateinit var viewModel: GameViewModel
    var decimalFormat = DecimalFormat("#.#")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        viewModel.messageLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.carBuyRateLiveData.observe(this, Observer {
            viewModel.carSellRateLiveData.value =
                decimalFormat.format(it.times(Random.nextDouble(0.80, 1.19))).toDouble()
        })

        viewModel.planeBuyRateLiveData.observe(this, Observer {
            viewModel.planeSellRateLiveData.value =
                decimalFormat.format(it.times(Random.nextDouble(0.80, 1.19))).toDouble()
        })
        viewModel.bikeBuyRateLiveData.observe(this, Observer {
            viewModel.bikeSellRateLiveData.value =
                decimalFormat.format(it.times(Random.nextDouble(0.80, 1.19))).toDouble()
        })

        viewModel.shipBuyRateLiveData.observe(this, Observer {
            viewModel.shipSellRateLiveData.value =
                decimalFormat.format(it.times(Random.nextDouble(0.80, 1.19))).toDouble()
        })

        viewModel.moneyLiveData.observe(this, Observer {
            if (it >= viewModel.moneyToWinLiveData.value!!) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(EXTRA_RESULT, EXTRA_WIN)
                startActivity(intent)
                finish()
            }
        })

        viewModel.numberOfActionLiveData.observe(this, Observer {
            if (it < 1) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(EXTRA_RESULT, EXTRA_LOSE)
                startActivity(intent)
                finish()
            }
        })
    }

    companion object {
        const val EXTRA_RESULT = "EXTRA_RESULT"
        const val EXTRA_WIN = "YOU WIN! WANT TO PLAY AGAIN?"
        const val EXTRA_LOSE = "YOU LOSE! WANT TO TRY AGAIN?"
    }
}