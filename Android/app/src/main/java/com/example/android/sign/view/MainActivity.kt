package com.example.android.sign.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.R
import com.example.android.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.android.base.BaseApplication.Companion.firebaseAuth
import com.example.android.base.BaseApplication.Companion.firebaseFirestore
import com.example.android.base.BaseApplication.Companion.firebaseStorage
import com.example.android.base.BaseApplication.Companion.currentUser

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private val TAG = this.javaClass.simpleName

    private lateinit var launcher: ActivityResultLauncher<Intent>

    private var email: String = ""
    private var tokenId: String? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if(it.resultCode == RESULT_OK)
            {
                try
                {
                    GoogleSignIn.getSignedInAccountFromIntent(it.data).getResult(ApiException::class.java).let()
                    {googleSignInAccount ->
                        tokenId = googleSignInAccount.idToken
                        if(tokenId != null && tokenId != "")
                        {
                            val credential: AuthCredential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)

                            firebaseAuth.signInWithCredential(credential).addOnCompleteListener()
                            {
                                if(firebaseAuth.currentUser != null)
                                {
                                    val user: FirebaseUser = firebaseAuth.currentUser!!
                                    email = user.email.toString()
                                    Log.e(TAG, "email : $email")
                                    Log.e(TAG, "uid : ${user.uid}")
                                    val googleSignInToken = googleSignInAccount.idToken ?: ""

                                    if(googleSignInToken != "")
                                    {
                                        Log.e(TAG, "googleSignInToken : $googleSignInToken")
                                    }
                                    else
                                    {
                                        Log.e(TAG, "googleSignInToken이 null")
                                    }
                                }
                            }
                        }
                    }
                }
                catch(e: Exception)
                {
                    e.printStackTrace()
                }
            }
        }

        binding.run {
            googleLoginButton.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build()
                    val googleSignInClient = GoogleSignIn.getClient(this@MainActivity, gso)
                    val signInIntent: Intent = googleSignInClient.signInIntent
                    launcher.launch(signInIntent)
                }
            }
        }
    }
}