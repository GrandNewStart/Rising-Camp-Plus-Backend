package com.ade.tinder

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val intent = result.data ?: return@registerForActivityResult
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        Log.d("TEST", task.result.id ?: "")
        Log.d("TEST", task.result.email ?: "")
        Log.d("TEST", task.result.displayName ?: "")
        Log.d("TEST", task.result.familyName ?: "")
        Log.d("TEST", task.result.givenName ?: "")
        Log.d("TEST", task.result.idToken ?: "")
        Log.d("TEST", task.result.serverAuthCode ?: "")

    }
    private val okHttpClient = OkHttpClient.Builder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInButton: SignInButton = findViewById(R.id.signInButton)
        signInButton.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            val client = GoogleSignIn.getClient(this, gso)
            activityResult.launch(client.signInIntent)
        }
    }

    private fun signIn(id: String) {
        val jsonObj = JSONObject().apply {
            this.put("ssoId", id)
        }
        val body = jsonObj.toString().toRequestBody(JSON)
        val request = Request.Builder()
            .url("localhost:8080/users")
            .post(body)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("TEST", call.toString())
                Log.e("TEST", e.message ?: "")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("TEST", call.toString())
                Log.d("TEST", response.toString())
            }
        })
    }

    companion object {
        val JSON = "application/json; charset=utf-8".toMediaType()
        val CLIENT_ID = "108997937179-ml6oe8lka7al6erped7sus3n46ck72l7.apps.googleusercontent.com"
    }

}