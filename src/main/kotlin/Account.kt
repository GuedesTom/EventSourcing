class Account() {

    private val eventStore = mutableListOf<AccountEvent>()

    fun deposit(amount: Int) {
        val newEvents = decide(evolve(eventStore), AccountDepositCommand(amount))
        eventStore.addAll(newEvents)
    }

    fun withdraw(amount: Int) {
        if (amount > value()) {
            println("Insufficient funds")
        }
        val newEvents = decide(evolve(eventStore), AccountWithdrawCommand(amount))
        eventStore.addAll(newEvents)
    }

    fun value(): Int {
        return evolve(eventStore).balance
    }

    fun printStatement() {
        println("             date             ||   balance   ||   amount")
        eventStore.forEach {
            println("${it.date} ||     ${it.amount}     ||     ${it.balance.balance}")
        }
    }
}