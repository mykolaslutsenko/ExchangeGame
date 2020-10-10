package com.slutsenko.blackbox

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import kotlin.random.Random

class GameViewModel : ViewModel() {

    var messageLiveData: MutableLiveData<String> = MutableLiveData()
    var numberOfActionLiveData: MutableLiveData<Int> = MutableLiveData()
    var numberOfActionToWinLiveData: MutableLiveData<Int> = MutableLiveData()

    var radioButtonLiveData: MutableLiveData<Thing> = MutableLiveData()
    var quantityLiveData: MutableLiveData<String> = MutableLiveData()

    var moneyLiveData: MutableLiveData<Double> = MutableLiveData()
    var moneyToWinLiveData: MutableLiveData<Double> = MutableLiveData()

    var carLiveData: MutableLiveData<String> = MutableLiveData()
    var planeLiveData: MutableLiveData<String> = MutableLiveData()
    var shipLiveData: MutableLiveData<String> = MutableLiveData()
    var bikeLiveData: MutableLiveData<String> = MutableLiveData()

    var carBuyRateLiveData: MutableLiveData<Double> = MutableLiveData()
    var planeBuyRateLiveData: MutableLiveData<Double> = MutableLiveData()
    var shipBuyRateLiveData: MutableLiveData<Double> = MutableLiveData()
    var bikeBuyRateLiveData: MutableLiveData<Double> = MutableLiveData()

    var carSellRateLiveData: MutableLiveData<Double> = MutableLiveData()
    var planeSellRateLiveData: MutableLiveData<Double> = MutableLiveData()
    var shipSellRateLiveData: MutableLiveData<Double> = MutableLiveData()
    var bikeSellRateLiveData: MutableLiveData<Double> = MutableLiveData()


    var decimalFormat = DecimalFormat("#.#")

    init {
        initStartValue()
        setRate()
    }


    private fun setRate() {
        carBuyRateLiveData.value = decimalFormat.format(Random.nextDouble(20.0, 30.0)).toDouble()
        planeBuyRateLiveData.value = decimalFormat.format(Random.nextDouble(60.0, 80.0)).toDouble()
        shipBuyRateLiveData.value = decimalFormat.format(Random.nextDouble(50.0, 70.0)).toDouble()
        bikeBuyRateLiveData.value = decimalFormat.format(Random.nextDouble(5.0, 15.0)).toDouble()

        minusOneAction()
    }

    private fun initStartValue() {
        moneyLiveData.value = 500.0
        moneyToWinLiveData.value = 600.0
        numberOfActionLiveData.value = 26
        numberOfActionToWinLiveData.value = 25
        carLiveData.value = "0"
        planeLiveData.value = "0"
        shipLiveData.value = "0"
        bikeLiveData.value = "0"
        quantityLiveData.value = "1"
        radioButtonLiveData.value = Thing.SHIP
    }


    fun onClickBuy() {
        var currentPrice = 0.0
        when (radioButtonLiveData.value) {
            Thing.SHIP -> {
                currentPrice =
                    quantityLiveData.value?.toDouble()?.times(shipBuyRateLiveData.value!!)!!
                if (moneyLiveData.value?.minus(currentPrice)!! > 0) {
                    setRate()
                    shipLiveData.value =
                        shipLiveData.value?.toInt()?.plus(quantityLiveData.value!!.toInt())
                            .toString()
                    moneyLiveData.value =
                        decimalFormat.format(moneyLiveData.value?.minus(currentPrice)!!).toDouble()
                } else {
                    messageLiveData.value = "not enough money"
                }
            }
            Thing.CAR -> {
                currentPrice =
                    quantityLiveData.value?.toDouble()?.times(carBuyRateLiveData.value!!)!!
                if (moneyLiveData.value?.minus(currentPrice)!! > 0) {
                    setRate()
                    carLiveData.value =
                        carLiveData.value?.toInt()?.plus(quantityLiveData.value!!.toInt())
                            .toString()
                    moneyLiveData.value =
                        decimalFormat.format(moneyLiveData.value?.minus(currentPrice)!!).toDouble()
                } else {
                    messageLiveData.value = "not enough money"
                }


            }
            Thing.PLANE -> {
                currentPrice =
                    quantityLiveData.value?.toDouble()?.times(planeBuyRateLiveData.value!!)!!
                if (moneyLiveData.value?.minus(currentPrice)!! > 0) {
                    setRate()
                    planeLiveData.value =
                        planeLiveData.value?.toInt()?.plus(quantityLiveData.value!!.toInt())
                            .toString()
                    moneyLiveData.value =
                        decimalFormat.format(moneyLiveData.value?.minus(currentPrice)!!).toDouble()
                } else {
                    messageLiveData.value = "not enough money"
                }
            }
            Thing.BIKE -> {
                currentPrice =
                    quantityLiveData.value?.toDouble()?.times(bikeBuyRateLiveData.value!!)!!
                if (moneyLiveData.value?.minus(currentPrice)!! > 0) {
                    setRate()
                    bikeLiveData.value =
                        bikeLiveData.value?.toInt()?.plus(quantityLiveData.value!!.toInt())
                            .toString()
                    moneyLiveData.value =
                        decimalFormat.format(moneyLiveData.value?.minus(currentPrice)!!).toDouble()
                } else {
                    messageLiveData.value = "not enough money"
                }
            }

        }

    }


    fun onClickSell() {
        var currentPrice = 0.0
        when (radioButtonLiveData.value) {

            Thing.SHIP -> {
                if (shipLiveData.value?.toInt()!! >= quantityLiveData.value?.toInt()!!) {
                    setRate()
                    currentPrice =
                        quantityLiveData.value?.toDouble()?.times(shipSellRateLiveData.value!!)!!
                    shipLiveData.value =
                        shipLiveData.value?.toInt()?.minus(quantityLiveData.value!!.toInt())
                            .toString()
                } else {
                    messageLiveData.value = "you don't have ${quantityLiveData.value} ships"
                }
            }
            Thing.CAR -> {
                if (carLiveData.value?.toInt()!! >= quantityLiveData.value?.toInt()!!) {
                    setRate()
                    currentPrice =
                        quantityLiveData.value?.toDouble()?.times(carSellRateLiveData.value!!)!!
                    carLiveData.value =
                        carLiveData.value?.toInt()?.minus(quantityLiveData.value!!.toInt())
                            .toString()
                } else {
                    messageLiveData.value = "you don't have ${quantityLiveData.value} cars"
                }
            }
            Thing.PLANE -> {
                if (planeLiveData.value?.toInt()!! >= quantityLiveData.value?.toInt()!!) {
                    setRate()
                    currentPrice =
                        quantityLiveData.value?.toDouble()?.times(planeSellRateLiveData.value!!)!!
                    planeLiveData.value =
                        planeLiveData.value?.toInt()?.minus(quantityLiveData.value!!.toInt())
                            .toString()
                } else {
                    messageLiveData.value = "you don't have ${quantityLiveData.value} planes"
                }
            }
            Thing.BIKE -> {
                if (bikeLiveData.value?.toInt()!! >= quantityLiveData.value?.toInt()!!) {
                    setRate()
                    currentPrice =
                        quantityLiveData.value?.toDouble()?.times(bikeSellRateLiveData.value!!)!!
                    bikeLiveData.value =
                        bikeLiveData.value?.toInt()?.minus(quantityLiveData.value!!.toInt())
                            .toString()
                } else {
                    messageLiveData.value = "you don't have ${quantityLiveData.value} bikes"
                }
            }

        }
        moneyLiveData.value =
            decimalFormat.format(moneyLiveData.value?.plus(currentPrice)).toDouble()

    }

    fun onClickMinus() {
        if (quantityLiveData.value?.toInt()!! > 1) {
            quantityLiveData.value = quantityLiveData.value?.toInt()?.minus(1).toString()
        }

    }

    fun onClickPlus() {
        quantityLiveData.value = quantityLiveData.value?.toInt()?.plus(1).toString()
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