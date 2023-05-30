package com.captsone.padicure.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class Repository @Inject constructor(
//    private val auth: FirebaseAuth
) : RepositoryResource {
//    override suspend fun login(user: SignInUser): Response {
//        val isError = Response.IsError(false, error = false, message = "")
//        auth.signInWithEmailAndPassword(
//            user.email, user.password
//        ).addOnCompleteListener {
//            if (it.isSuccessful) {
//                isError.data = true
//                isError.error = false
//            } else {
//                isError.error = true
//                isError.message = it.exception.toString()
//            }
//        }
//        return isError
//    }
//
//    override suspend fun register(user: SignUpUser): Response {
//        val isError = Response.IsError(false, error = false, message = "")
//        auth.createUserWithEmailAndPassword(
//            user.email, user.password
//        ).addOnCompleteListener {
//            if (it.isSuccessful) {
//                isError.data = true
//                isError.error = false
//            } else {
//                isError.error = true
//                isError.message = it.exception.toString()
//            }
//        }
//        return isError
//    }
//
//    override suspend fun isLogin(): Response {
//        return Response.IsError(
//            auth.currentUser != null,
//            false,
//            message = ""
//        )
//    }

    override suspend fun test() {
        Log.d("Test", "Test")
    }


}