package app.domain.invoicing.repositoryDB

import androidx.compose.runtime.toMutableStateList
import app.base.utils.BaseResult
import app.domain.invoicing.account.Email
import com.example.login.data.dao.AccountDao
import com.example.login.data.dao.BusinessDao
import com.example.login.data.dao.PersonalDao
import com.example.login.data.model.Account
import com.example.login.data.model.AccountException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepositoryDB @Inject constructor(private val accountDao: AccountDao, private val personalDao: PersonalDao, private val businessDao: BusinessDao) {

    suspend fun validate(email: Email, password: String): BaseResult<Account> {
        val account = accountDao.validate(email, password)
        val result: BaseResult<Account> = account?.let{
            BaseResult.Success(account)
        } ?: BaseResult.Error(AccountException.NoExistAccount)
        return result
    }

    fun getDataBase(): AccountDao {
        return accountDao
    }

    fun getData(): Flow<List<Account>> {
        return accountDao.getAllAccount()
    }

    suspend fun getDataList(): MutableList<List<Account>>{
        return (accountDao.getAllAccount().toList().toMutableStateList())
    }

    suspend fun exist(email: String): Boolean{

        return (accountDao.getAccountByEmail(Email(email)) != null)
    }

    suspend fun create(account: Account): BaseResult<Account> {
        return if(accountDao.getAccountByEmail(account.email) == null){
            accountDao.insert(account)
            BaseResult.Success(account)
        } else BaseResult.Error(AccountException.TakenEmail(account.email))
    }

    suspend fun delete(account: Account): Boolean {
        val personalList = personalDao
            .getAll()
            .first()
            .filter { it.idAccount == account.id }
        personalList.forEach { personalDao.delete(it) }

        val businessList = businessDao
            .getAll()
            .first()
            .filter { it.idAccount == account.id }
        businessList.forEach { businessDao.delete(it) }

        accountDao.delete(account)
        return true
    }
}