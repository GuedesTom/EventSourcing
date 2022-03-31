import org.assertj.core.api.Assertions.assertThat
import java.util.Date
import kotlin.test.Test
import kotlin.test.assertEquals


class AccountTest {
    @Test
    fun `should increase balance`() {
        val account = Account()
        account.deposit(100)
        assertEquals(100, account.value())
    }
    @Test
    fun `should decrease balance`() {
        val account = Account()
        account.withdraw(100)
        assertEquals(0, account.value())
    }
    @Test
    fun `should not allow negative balance`() {
        val account = Account()
        account.withdraw(100)
        account.withdraw(100)
        assertEquals(0, account.value())
    }
    @Test
    fun `should evolve balance`() {
        val state = evolve(listOf(AccountDepositEvent(10, AccountState(10), Date())))

        assertThat(state).isEqualTo(AccountState(10))
    }
    @Test
    fun `should evolve balance with multiple events`() {
        val state = evolve(listOf(
                AccountDepositEvent(10, AccountState(10), Date()),
                AccountDepositEvent(10, AccountState(20), Date()),
                AccountDepositEvent(10, AccountState(30), Date()),
                AccountDepositEvent(10, AccountState(40), Date()),
                AccountDepositEvent(10, AccountState(50), Date()),
                AccountDepositEvent(10, AccountState(60), Date()),
                AccountDepositEvent(10, AccountState(70), Date()),
                AccountDepositEvent(10, AccountState(80), Date()),
                AccountDepositEvent(10, AccountState(90), Date()),
                AccountDepositEvent(10, AccountState(100), Date())
        ))

        assertThat(state).isEqualTo(AccountState(100))
    }
    @Test
    fun `should evolve balance with multiple events and withdraw`() {
        val state = evolve(listOf(
                AccountDepositEvent(10, AccountState(10), Date()),
                AccountDepositEvent(10, AccountState(20), Date()),
                AccountDepositEvent(10, AccountState(30), Date()),
                AccountDepositEvent(10, AccountState(40), Date()),
                AccountDepositEvent(10, AccountState(50), Date()),
                AccountDepositEvent(10, AccountState(60), Date()),
                AccountDepositEvent(10, AccountState(70), Date()),
                AccountDepositEvent(10, AccountState(80), Date()),
                AccountDepositEvent(10, AccountState(90), Date()),
                AccountDepositEvent(10, AccountState(100), Date()),
                AccountWithdrawEvent(90, AccountState(100), Date())
        ))

        assertThat(state).isEqualTo(AccountState(10))
    }
}