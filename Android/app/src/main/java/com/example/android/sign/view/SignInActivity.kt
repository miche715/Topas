package com.example.android.sign.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.android.R
import com.example.android.base.BaseActivity
import com.example.android.contact.view.ContactActivity
import com.example.android.databinding.ActivitySignInBinding
import com.example.android.sign.viewmodel.SignViewModel
import com.example.android.utility.LoadingDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in)
{
    private val signViewModel: SignViewModel by viewModels()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(this@SignInActivity) }

    private var activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        if(it.resultCode == RESULT_OK)
        {
            val googleSignInAccount = GoogleSignIn.getSignedInAccountFromIntent(it.data).getResult(ApiException::class.java)
            val credential: AuthCredential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
            signViewModel.signInGoogle(googleSignInAccount, credential)

//            {googleSignInAccount ->
//                googleSignInAccount.idToken
//                if(tokenId != null && tokenId != "")
//                {
//                    val credential: AuthCredential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
//                    BaseApplication.firebaseAuth.signInWithCredential(credential).addOnCompleteListener()
//                    {
//                        if(BaseApplication.firebaseAuth.currentUser != null)
//                        {
//                            val user: FirebaseUser = BaseApplication.firebaseAuth.currentUser!!
//                            email = user.email.toString()
//                            Log.e(TAG, "email : $email")
//                            Log.e(TAG, "uid : ${user.uid}")
//                            val googleSignInToken = googleSignInAccount.idToken ?: ""
//                            if(googleSignInToken != "")
//                            {
//                                Log.e(TAG, "googleSignInToken : $googleSignInToken")
//                            }
//                            else
//                            {
//                                Log.e(TAG, "googleSignInToken이 null")
//                            }
//                        }
//                    }
//                }
//            }
        }
    }

    override fun onInitialize()
    {
        setToolBar(binding.toolBar)

        binding.signViewModel = signViewModel
        binding.signInActivity = this@SignInActivity

        checkInternetPermission()

        signViewModel.signInResult.observe(this@SignInActivity)
        {
            if((it is Boolean) && it)  // 로그인 성공 - Boolean, 로그인 실패 - String
            {
                Intent(this@SignInActivity, ContactActivity::class.java).run()
                {
                    startActivity(this)
                    finish()
                }
            }
            else
            {
                Snackbar.make(binding.root, it as String, Snackbar.LENGTH_SHORT).show()
            }
            loadingDialog.dismiss()
        }

        signViewModel.signInGoogleResult.observe(this@SignInActivity)
        {
            if(it)
            {
                Intent(this@SignInActivity, ContactActivity::class.java).run()
                {
                    startActivity(this)
                    finish()
                }
            }
            else
            {
                Intent(this@SignInActivity, SignUpGoogleActivity::class.java).run()
                {
                    startActivity(this)
                }
            }
            loadingDialog.dismiss()
        }
    }

    private fun checkInternetPermission()
    {
        val internetPermission = ContextCompat.checkSelfPermission(this@SignInActivity, Manifest.permission.INTERNET)

        if(internetPermission != PackageManager.PERMISSION_GRANTED)
        {
            requestInternetPermission()
        }
    }

    private fun requestInternetPermission()
    {
        ActivityCompat.requestPermissions(this@SignInActivity, arrayOf(Manifest.permission.INTERNET), 1000)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        when(requestCode)
        {
            1000 ->  if(grantResults[0] != PackageManager.PERMISSION_GRANTED) { finishAffinity() }
        }
    }

    fun onSignInButtonClick(view: View)
    {
        hideKeyBoard(view.windowToken)
        loadingDialog.show()

        signViewModel.signIn(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
    }

    fun onFindEmailTextViewClick()
    {
        println("이메일 찾기")
    }

    fun onFindPasswordTextViewClick()
    {
        println("비밀번호 찾기")
    }

    fun onSignUpButtonClick()
    {
        Intent(this@SignInActivity, SignUpActivity::class.java).run()
        {
            startActivity(this)
        }
    }

    fun onSignGoogleButtonClick()
    {
        loadingDialog.show()

        CoroutineScope(Dispatchers.IO).launch()
        {
            with(GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build())
            {
                GoogleSignIn.getClient(this@SignInActivity, this).signInIntent.run()
                {
                    activityResultLauncher.launch(this)
                }
            }
        }
    }
}