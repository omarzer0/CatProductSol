package az.zero.catproductsol.core

import retrofit2.Response


suspend fun <T> networkCall(
    action: suspend () -> Response<T>,
    onResponse: (ResponseState<T>) -> Unit,
) {
    onResponse(ResponseState.Loading())
    try {
        val response = action()
        if (response.isSuccessful) {
            if (response.body() != null) onResponse(ResponseState.Success(data = response.body()!!))
            else onResponse(ResponseState.Error(message = "Failed with null body!"))

        } else onResponse(ResponseState.Error(message = "code: ${response.code()}"))
    } catch (e: Exception) {
        onResponse(ResponseState.Error(message = e.localizedMessage))
    }
}


//fun <T> networkCallFlow(
//    action: suspend () -> Response<T>,
//): Flow<ResponseState<T>> = flow {
//    emit(ResponseState.Loading())
//    try {
//        val response = action()
//        if (response.isSuccessful) {
//            if (response.body() != null)
//                emit(ResponseState.Success(data = response.body()!!))
//            else
//                emit(ResponseState.Error(message = "Failed with null body!"))
//
//        } else
//            emit(ResponseState.Error(message = "code: ${response.code()}"))
//    } catch (e: Exception) {
//        emit(ResponseState.Error(message = e.localizedMessage))
//    }
//}