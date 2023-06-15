package com.captsone.padicure.data

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.captsone.padicure.utils.Utils.extractErrorMessage
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import okhttp3.MultipartBody
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Repository @Inject constructor(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val apiService: PadicureApiService
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

    override suspend fun getUserData(): Response {
        return suspendCoroutine {continuation ->
            val urlPhoto = auth.currentUser?.photoUrl ?: "https://picsum.photos/200/300".toUri()
            val response = Response.IsSuccessful(
                UserData(auth.currentUser?.uid ?: "Empty",
                    auth.currentUser?.displayName ?: "Empty",
                    auth.currentUser?.email ?: "Empty",
                    urlPhoto.toString())
            )
            continuation.resume(response)
        }
    }

    override suspend fun setUserData(name: String, profilePictURL: String): Response {
        return suspendCoroutine {
            continuation ->
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(Uri.parse(profilePictURL))
                .build()
            auth.currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    val response = Response.IsSuccessful("Success Update Data")
                    continuation.resume(response)
                } else {
                    val error = it.exception.toString()
                    val response = Response.IsError(error = true, message = extractErrorMessage(error))
                    continuation.resume(response)
                }
            }
        }
    }

    override suspend fun logout(): Response {
        return suspendCoroutine {
            continuation ->
            auth.signOut()
            val response = Response.IsSuccessful("Berhasil Logout")
            continuation.resume(response)
        }
    }
    override suspend fun getListData(): Response {
        return try{
            val response = apiService.getListData()
            Response.IsSuccessful(response)
        } catch (exception: Exception){
            Response.IsError(true, exception.message.toString())
        }
    }

    override suspend fun getDetailData(id: Int): Response {
        return try {
            val response = apiService.getDetailData(id)
            Response.IsSuccessful(response)
        } catch (exception: Exception){
            Response.IsError(true, exception.message.toString())
        }
    }

    override suspend fun scanData(photo: MultipartBody.Part): Response {
        return try{
            val response = apiService.predictPicture(
                "https://predict-dqo6vd65jq-et.a.run.app/predict",
                photo
            )
            Response.IsSuccessful(response)
        } catch (exception: Exception){
            Response.IsError(true, exception.message.toString())
        }
    }
}