import java.time.Instant
import java.util.*

fun decide(state: AccountState, accountCommand: AccountDepositCommand): List<AccountEvent> {
    return listOf(AccountDepositEvent(state, accountCommand.deposit))
}

fun decide(state: AccountState, accountCommand: AccountWithdrawCommand): List<AccountEvent> {
    return if (state.balance < accountCommand.withdraw) emptyList()
    else listOf(AccountWithdrawEvent(state, accountCommand.withdraw))
}


fun evolve(events: List<AccountEvent>): AccountState {
    var initialValue = 0

    events.forEach { event ->
        if (event is AccountDepositEvent) initialValue += event.amount
        if (event is AccountWithdrawEvent) initialValue -= event.amount
    }

    return AccountState(initialValue)
}


fun AccountDepositEvent(state: AccountState, deposit: Int): AccountDepositEvent {
    return AccountDepositEvent(deposit, balance = state, Date.from(Instant.now()))
}

fun AccountWithdrawEvent(state: AccountState, withdraw: Int): AccountWithdrawEvent {
    return AccountWithdrawEvent(withdraw, balance = state, Date.from(Instant.now()))
}


data class AccountState(val balance: Int)


interface AccountEvent {
    val amount: Int
    val balance: AccountState
    val date: Date
}
data class AccountDepositEvent(override val amount: Int, override val balance: AccountState, override val date: Date) : AccountEvent
data class AccountWithdrawEvent(override val amount: Int, override val balance: AccountState, override val date: Date) : AccountEvent


interface AccountCommand
data class AccountDepositCommand(val deposit: Int) : AccountCommand
data class AccountWithdrawCommand(val withdraw: Int) : AccountCommand