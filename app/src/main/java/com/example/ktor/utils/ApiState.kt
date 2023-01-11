package com.example.ktor.utils

//sealed class ApiState<T>(
// val data: T? = null,
// val error: Throwable? = null
//) {
// class Success<T>(data: T) : ApiState<T>(data)
// class Loading<T> : ApiState<T>()
// class Failure<T>(throwable: Throwable, data: T? = null) : ApiState<T>(data, throwable)
//}
sealed class ApiState<T>(
 val data: T? = null,
 val error: Throwable? = null
) {
 class Success<T>(data: T) : ApiState<T>(data)
 class Loading<T>(data: T? = null) : ApiState<T>(data)
 class Error<T>(throwable: Throwable, data: T? = null) : ApiState<T>(data, throwable)
}


//sealed class ApiState<T>(
// val data: T? = null,
// val message: String? = null
//) {
// class Success<T>(data: T) : ApiState<T>(data)
// class Loading<T> : ApiState<T>()
// class Failure<T>(data: T? = null, message: String? = null) : ApiState<T>(data, message)
//}