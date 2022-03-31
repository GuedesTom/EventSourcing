package Main

import Account


fun main(args: Array<String>) {
        val account = Account()
        while(true) {
            println("1. Deposit")
            println("2. Withdraw")
            println("3. Balance")
            println("4. Statement")
            println("5. Exit")
            val choice = readLine()!!.toInt()
            when(choice) {
                1 -> {
                    println("Enter amount to deposit: ")
                    val amount = readLine()!!.toInt()
                    account.deposit(amount)
                }
                2 -> {
                    println("Enter amount to withdraw: ")
                    val amount = readLine()!!.toInt()
                    account.withdraw(amount)
                }
                3 -> {
                    println("Balance: ${account.value()}")
                }
                4 -> {
                    println("${account.printStatement()}")
                }
                5 -> {
                    println("Exiting...")
                    return
                }
                else -> {
                    println("Invalid choice")
                }
            }
        }
    }