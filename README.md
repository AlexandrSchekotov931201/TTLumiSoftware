# TTLumiSoftware

Проект создан в качестве выполнения тестового задания для LumiSoftware.

В проекте используется api https://docs.etherscan.io/api-endpoints/accounts#get-a-list-of-normal-transactions-by-address.
Запрос на получения списка транзакций работает с ограничением 5 запросов в секунду.

Чтобы ограничения не было так же можно использовать key api указав его в классе BaseRepository в поле apiKey


```
abstract class BaseRepository {
    protected val apiKey: String = "" // ВАШ api key token

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> T): ApiResult<T> = try {
        val result = apiToBeCalled.invoke()
        ApiResult.Success(data = result)
    } catch (e: Exception) {
        ApiResult.Error(e)
    }
}
```

Получить api key можно тут - https://docs.etherscan.io/getting-started/viewing-api-usage-statistics
