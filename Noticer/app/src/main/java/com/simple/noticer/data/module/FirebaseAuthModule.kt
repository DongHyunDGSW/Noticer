package com.simple.noticer.data.module

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.simple.noticer.ui.fragments.SearchFragment
import com.simple.noticer.ui.view.activities.LoginActivity
import java.util.*

class FirebaseAuthModule {
    companion object {
        private val callbackManager: CallbackManager = CallbackManager.Factory.create()
        private val auth = FirebaseAuth.getInstance()
        lateinit var user : FirebaseUser
        lateinit var task : Task<AuthResult>

        fun facebookLogin(activity : LoginActivity) : FirebaseUser?{
            var firebaseUser : FirebaseUser?= null
            LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

                override fun onSuccess(result: LoginResult?) {
                    handleFacebookAccessToken(result?.accessToken, activity)
                    Log.d("TAG_RESULT", "$firebaseUser")
                }
                override fun onCancel() {

                }

                override fun onError(error: FacebookException?) {
                    Log.d("TAG_ERROR", error?.message!!)
                }
            })
            return firebaseUser
        }

        private fun handleFacebookAccessToken(token: AccessToken?, activity: LoginActivity){
            Log.d("MainActivity", "handleFacebookAccessToken:$token")
            if (token != null) {
                val credential = FacebookAuthProvider.getCredential(token.token)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener(activity) { task ->
                        this.task = task
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MainActivity", "signInWithCredential:success")
                            user = auth.currentUser!!
                            activity.loginTaskDone(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MainActivity", "signInWithCredential:failure", task.exception)
                            Snackbar.make(activity.window.decorView.rootView , "로그인에 실패했습니다..", Snackbar.LENGTH_LONG).show()
                        }
                    }
                 }
              }

        fun registerWithEmail(email : String, password: String, context: LoginActivity) {
            if(email.isEmpty() || password.isEmpty()) {
                Snackbar.make(context.window.decorView.rootView , "아이디나 비밀번호에 입력이 돠지 않은 부분이 있습니다.", Snackbar.LENGTH_LONG).show()
            }else {
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(context) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "createUserWithEmail:success")
                                user = auth.currentUser
                                Snackbar.make(context.window.decorView.rootView , "회원가입 성공!", Snackbar.LENGTH_LONG).show()
                                context.loginTaskDone(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "createUserWithEmail:failure", task.exception)
                                Snackbar.make(context.window.decorView.rootView , "회원가입에 실패했습니다..", Snackbar.LENGTH_LONG).show()
                            }
                        }
            }
        }

        fun loginWithEmail(email : String, password : String, context : LoginActivity) {
            if(email.isEmpty() || password.isEmpty()) {
                Snackbar.make(context.window.decorView.rootView , "아이디나 비밀번호에 입력이 돠지 않은 부분이 있습니다.", Snackbar.LENGTH_LONG).show()
            }else {
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(context) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "createUserWithEmail:success")
                                user = auth.currentUser
                                Snackbar.make(context.window.decorView.rootView , "로그인 성공!", Snackbar.LENGTH_LONG).show()
                                context.loginTaskDone(user)
                                context.onSaveLogin(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "createUserWithEmail:failure", task.exception)
                                Snackbar.make(context.window.decorView.rootView , "로그인에 실패했습니다..", Snackbar.LENGTH_LONG).show()
                            }
                        }
            }
        }

        fun currentUser() : FirebaseUser? {
            return user
        }
    }


}