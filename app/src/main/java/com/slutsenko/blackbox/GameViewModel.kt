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
        carBuyRateLiveData.value = Random.nextInt(600, 1000)
        planeBuyRateLiveData.value = Random.nextInt(600, 1000)
        shipBuyRateLiveData.value = Random.nextInt(700, 1000)
        bikeBuyRateLiveData.value = Random.nextInt(600, 1000)

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
        var currentPrice = 0
        when (radioButtonLiveData.value) {
            Thing.SHIP -> {
                currentPrice =
                    quantityLiveData.value?.times(shipBuyRateLiveData.value!!)!!
                if (moneyLiveData.value?.minus(currentPrice)!! > 0) {
                    setRate()
                    shipLiveData.value = shipLiveData.value?.plus(quantityLiveData.value ?: 0)
                    moneyLiveData.value =
                        moneyLiveData.value?.minus(currentPrice)
                } else {
                    messageLiveData.value = "not enough money"
                }
            }
            Thing.CAR -> {
                currentPrice =
                    quantityLiveData.value?.times(carBuyRateLiveData.value!!)!!
                if (moneyLiveData.value?.minus(currentPrice)!! > 0) {
                    setRate()
                    carLiveData.value = carLiveData.value?.plus(quantityLiveData.value ?: 0)
                    moneyLiveData.value =
                        moneyLiveData.value?.minus(currentPrice)
                } else {
                    messageLiveData.value = "not enough money"
                }


            }
            Thing.PLANE -> {
                currentPrice =
                    quantityLiveData.value?.times(planeBuyRateLiveData.value!!)!!
                if (moneyLiveData.value?.minus(currentPrice)!! > 0) {
                    setRate()
                    planeLiveData.value = planeLiveData.value?.plus(quantityLiveData.value ?: 0)
                    moneyLiveData.value =
                        moneyLiveData.value?.minus(currentPrice)!!
                } else {
                    messageLiveData.value = "not enough money"
                }
            }
            Thing.BIKE -> {
                currentPrice =
                    quantityLiveData.value?.times(bikeBuyRateLiveData.value!!)!!
                if (moneyLiveData.value?.minus(currentPrice)!! > 0) {
                    setRate()
                    bikeLiveData.value = bikeLiveData.value?.plus(quantityLiveData.value ?: 0)
                    moneyLiveData.value =
                       moneyLiveData.value?.minus(currentPrice)!!
                } else {
                    messageLiveData.value = "not enough money"
                }
            }

        }

    }


    fun onClickSell() {
        var currentPrice = 0
        when (radioButtonLiveData.value) {

            Thing.SHIP -> {
                if (shipLiveData.value!! >= quantityLiveData.value!!) {
                    setRate()
                    currentPrice =
                        quantityLiveData.value?.times(shipSellRateLiveData.value!!)!!
                    shipLiveData.value = shipLiveData.value?.minus(quantityLiveData.value !!)

                } else {
                    messageLiveData.value = "you don't have ${quantityLiveData.value} ships"
                }
            }
            Thing.CAR -> {
                if (carLiveData.value!! >= quantityLiveData.value!!) {
                    setRate()
                    currentPrice =
                        quantityLiveData.value?.times(carSellRateLiveData.value!!)!!
                    carLiveData.value = carLiveData.value?.minus(quantityLiveData.value !!)

                } else {
                    messageLiveData.value = "you don't have ${quantityLiveData.value} cars"
                }
            }
            Thing.PLANE -> {
                if (planeLiveData.value!! >= quantityLiveData.value!!) {
                    setRate()
                    currentPrice =
                        quantityLiveData.value?.times(planeSellRateLiveData.value!!)!!
                    planeLiveData.value = planeLiveData.value?.minus(quantityLiveData.value !!)

                } else {
                    messageLiveData.value = "you don't have ${quantityLiveData.value} planes"
                }
            }
            Thing.BIKE -> {
                if (bikeLiveData.value!! >= quantityLiveData.value!!) {
                    setRate()
                    currentPrice =
                        quantityLiveData.value?.times(bikeSellRateLiveData.value!!)!!
                    bikeLiveData.value = bikeLiveData.value?.minus(quantityLiveData.value !!)

                } else {
                    messageLiveData.value = "you don't have ${quantityLiveData.value} bikes"
                }
            }

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