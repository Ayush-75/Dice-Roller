package com.example.basicdiceroller

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.basicdiceroller.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private var randomInt: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.roll.setOnClickListener {
            rollDice()
            binding.roll.text = getString(R.string.change_with_click)
//            Toast.makeText(this, randomInt.toString(), Toast.LENGTH_SHORT).show()


            toastTime()
        }


    }


    private fun toastTime() {
        val toast =
            Toast.makeText(this, randomInt.toString(), Toast.LENGTH_SHORT)
        toast.show()

        CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            toast.cancel()
            Log.d("Coroutine Thread", "toastTime: thread name  ${Thread.currentThread().name} ")
        }

    }


    private fun rollDice() {


        randomInt = Random.nextInt(6) + 1
        val drawableResources = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        binding.randomDiceImage.setImageResource(drawableResources)


    }
}