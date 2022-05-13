package com.example.mytrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.facebook.*
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.oAuthCredential
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : AppCompatActivity(){

    lateinit var callbackManager: CallbackManager
    private lateinit var mFirebaseAuth: FirebaseAuth
    private val TAG = "FacebookAuthentication"
    lateinit var facebookBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //inizializza database firebase e facebook sdk
        mFirebaseAuth = FirebaseAuth.getInstance()
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        facebookBtn = findViewById(R.id.btn_facebook)

        //inizializza fb login button
        callbackManager = CallbackManager.Factory.create();

        facebookBtn.setOnClickListener(){

                btn_facebook.setEnabled(false)

                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"))
                LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d(TAG, "facebook:onSuccess:$loginResult")
                        handleFacebookAccessToken(loginResult.accessToken)
                    }

                    override fun onCancel() {
                        Log.d(TAG, "facebook:onCancel")
                        // ...
                    }

                    override fun onError(error: FacebookException) {
                        Log.d(TAG, "facebook:onError", error)
                        // ...
                    }
                })
        }
    }

    //quando l'activity si inizializza, controlla se l'utente è già loggato
    override fun onStart() {
        super.onStart()
        val currentUser = mFirebaseAuth.currentUser

        if(currentUser != null){
            updateUI(currentUser)
        }

    }

    //passa il risultato all'SDK di facebook
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    //se l'utente si è loggato correttamente , riceve un token di accesso, lo scambia per le credenziali di firebase
    //si autentica in firebase con le credenziali.
    private fun handleFacebookAccessToken(accessToken: AccessToken) {
        Log.d(TAG, "HandleFacebookToken $accessToken")

        val credential: AuthCredential = FacebookAuthProvider.getCredential(accessToken.token)

        mFirebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // login con successo, aggiorna UI con info utente
                    Log.d(TAG, "signInWithCredential:success")
                    val user = mFirebaseAuth.currentUser

                    btn_facebook.setEnabled(true)
                    updateUI(user)

                } else {
                    // login fallito, mostra messaggio a utente.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                    btn_facebook.setEnabled(true)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        Toast.makeText(this, " Login effettuato ", Toast.LENGTH_SHORT).show()

        //apre Dashboard
        val dashboard = Intent(this, DashboardActivity::class.java)
        startActivity(dashboard)
    }
}
