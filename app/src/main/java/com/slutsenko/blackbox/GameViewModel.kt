package com.slutsenko.blackbox

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel : ViewModel() {

    var messageLiveData: MutableLiveData<String> = MutableLiveData()
    var numberOfActionLiveData: MutableLiveData<Int> = MutableLiveData()
    var numberOfActionToWinLiveData: MutableLiveData<Int> = MutableLiveData()

    var radioButtonLiveData: MutableLiveData<Thing> = MutableLiveData()
    var quantityLiveData: MutableLiveData<Int> = MutableLiveData()

    var moneyLiveData: MutableLiveData<Int> = MutableLiveData()
    var moneyToWinLiveData: MutableLiveData<Int> = MutableLiveData()

    var carLiveData: MutableLiveData<Int> = MutableLiveData()
    var planeLiveData: MutableLiveData<Int> = MutableLiveData()
    var shipLiveData: MutableLiveData<Int> = MutableLiveData()
    var bikeLiveData: MutableLiveData<Int> = MutableLiveData()

    var carBuyRateLiveData: MutableLiveData<Int> = MutableLiveData()
    var planeBuyRateLiveData: MutableLiveData<Int> = MutableLiveData()
    var shipBuyRateLiveData: MutableLiveData<Int> = MutableLiveData()
    var bikeBuyRateLiveData: MutableLiveData<Int> = MutableLiveData()

    var carSellRateLiveData: MutableLiveData<Int> = MutableLiveData()
    var planeSellRateLiveData: MutableLiveData<Int> = MutableLiveData()
    var shipSellRateLiveData: MutableLiveData<Int> = MutableLiveData()
    var bikeSellRateLiveData: MutableLiveData<Int> = MutableLiveData()

    init {
        initStartValue()
        setRate()
    }


    private fun setRate() {
        carBuyRateLiveData.value = Random.nextInt(300, 700)
        planeBuyRateLiveData.value = Random.nextInt(600, 1000)
        shipBuyRateLiveData.value = Random.nextInt(700, 1200)
        bikeBuyRateLiveData.value = Random.nextInt(200, 400)

        minusOneAction()
    }

    private fun initStartValue() {
        moneyLiveData.value = 5000
        moneyToWinLiveData.value = 6000
        numberOfActionLiveData.value = 26
        numberOfActionToWinLiveData.value = 25
        carLiveData.value = 0
        planeLiveData.value = 0
        shipLiveData.value = 0
        bikeLiveData.value = 0
        quantityLiveData.value = 1
        radioButtonLiveData.value = Thing.SHIP
    }


    fun onClickBuy() {
        when (radioButtonLiveData.value) {
            Thing.SHIP -> {
                buyThing(shipLiveData, shipBuyRateLiveData)
            }
            Thing.CAR -> {
                buyThing(carLiveData, carBuyRateLiveData)
            }
            Thing.PLANE -> {
                buyThing(planeLiveData, planeBuyRateLiveData)
            }
            Thing.BIKE -> {
                buyThing(bikeLiveData, bikeBuyRateLiveData)
            }
        }
    }

    private fun buyThing(itemLiveData: MutableLiveData<Int>, buyRateLiveData: MutableLiveData<Int>) {
        var currentPrice = 0
        currentPrice = quantityLiveData.value?.times(buyRateLiveData.value!!)!!
        if (moneyLiveData.value?.minus(currentPrice)!! > 0) {
            setRate()
            itemLiveData.value = itemLiveData.value?.plus(quantityLiveData.value ?: 0)
            moneyLiveData.value =
                moneyLiveData.value?.minus(currentPrice)
        } else {
            messageLiveData.value = "not enough money"
        }
    }

    fun onClickSell() {
        when (radioButtonLiveData.value) {
            Thing.SHIP -> {
                sellThing(shipLiveData, shipSellRateLiveData)
            }
            Thing.CAR -> {
                sellThing(carLiveData, carSellRateLiveData)
            }
            Thing.PLANE -> {
                sellThing(planeLiveData, planeSellRateLiveData)
            }
            Thing.BIKE -> {
                sellThing(bikeLiveData, bikeSellRateLiveData)
            }
        }
    }

    private fun sellThing(itemLiveData: MutableLiveData<Int>, sellRateLiveData: MutableLiveData<Int>) {
        var currentPrice = 0
        if (itemLiveData.value!! >= quantityLiveData.value!!) {
            setRate()
            currentPrice = quantityLiveData.value?.times(sellRateLiveData.value!!)!!
            itemLiveData.value = itemLiveData.value!!.minus(quantityLiveData.value !!)

        } else {
            messageLiveData.value = "you don't have this thing"
        }
        moneyLiveData.value =
            moneyLiveData.value?.plus(currentPrice)
    }


    fun onClickMinus() {
        if (quantityLiveData.value!! > 1) {
            quantityLiveData.value = quantityLiveData.value?.minus(1)
        }

    }

    fun onClickPlus() {
        quantityLiveData.value = quantityLiveData.value?.plus(1)
    }


    fun setRadioButtonValue(s: Int) {
        when (s) {
            1 -> radioButtonLiveData.value = Thing.SHIP
            2 -> radioButtonLiveData.value = Thing.CAR
            3 -> radioButtonLiveData.value = Thing.PLANE
            4 -> radioButtonLiveData.value = Thing.BIKE
        }
    }

    private fun minusOneAction() {
        numberOfActionLiveData.value = numberOfActionLiveData.value?.minus(1)
    }

    enum class Thing {
        CAR,
        PLANE,
        SHIP,
        BIKE
    }
}