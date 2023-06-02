package com.captsone.padicure.data

import android.util.Log
import com.captsone.padicure.Utils.extractErrorMessage
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Repository @Inject constructor(
    private val auth: FirebaseAuth
) : RepositoryResource {
    override suspend fun login(user: SignInUser): Response {
        return suspendCoroutine { continuation ->
            auth.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val data: AuthResult? = task.result
                        val response = Response.IsSuccessful(data)
                        continuation.resume(response)
                    } else {
                        val error = task.exception.toString()
                        val response = Response.IsError(error = true, message = extractErrorMessage(error))
                        continuation.resume(response)
                    }
                }
        }
    }

    override suspend fun register(user: SignUpUser): Response {
        return suspendCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val data: AuthResult? = task.result
                        val response = Response.IsSuccessful(data)
                        continuation.resume(response)
                    } else {
                        val error = task.exception.toString()
                        val response = Response.IsError(error = true, message = extractErrorMessage(error))
                        continuation.resume(response)
                    }
                }
        }
    }

    override suspend fun isLogin(): Response {
       return Response.IsSuccessful(auth.currentUser != null)
    }

    override suspend fun test() {
        Log.d("Test", "Test")
    }


}