package com.example.paymentwithflutterwave


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.flutterwave.raveandroid.RaveUiManager
import android.widget.Toast
import com.flutterwave.raveandroid.RavePayActivity
import com.flutterwave.raveandroid.rave_java_commons.RaveConstants
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    lateinit var  button: Button
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        button.setOnClickListener {

            makepayment()
        }
    }

    private fun makepayment() {
        RaveUiManager(this).setAmount(14000.00)
            .setCurrency("NGN")
            .setEmail("okezi002@gmail.com")
            .setfName("JOSEPH")
            .setlName("OKEH")
            .setNarration("hotel management payment")
            .setPublicKey("FLWPUBK_TEST-0e16bc4b9570aa74766ceed264872d5a-X")
            .setEncryptionKey("FLWSECK_TEST9a9f62a35ac6")
            .setTxRef(System.currentTimeMillis().toString() + "Ref")
            .setPhoneNumber("08032537411", true)
            .acceptAccountPayments(true)
            .acceptCardPayments(true)
            .acceptMpesaPayments(false)
            .acceptAchPayments(false)
            .acceptSaBankPayments(false)
            .acceptUkPayments(false)
            .allowSaveCardFeature(true)
            .onStagingEnv(true)
            .shouldDisplayFee(true)
            .showStagingLabel(true)
            .initialize()
    }



    @SuppressLint("ResourceAsColor")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === RaveConstants.RAVE_REQUEST_CODE &&  data != null) {
            val message: String? = data.getStringExtra("response")
            if (resultCode === RavePayActivity.RESULT_SUCCESS) {
                Snackbar.make(this.textView, "Transaction Successful", Snackbar.LENGTH_LONG).setBackgroundTint(R.color.teal_700).show()
            } else if (resultCode === RavePayActivity.RESULT_ERROR) {
                Snackbar.make(this.textView, "An Error Occur", Snackbar.LENGTH_LONG).setBackgroundTint(R.color.teal_200).show()
            } else if (resultCode === RavePayActivity.RESULT_CANCELLED) {
                Snackbar.make(this.textView, "Transaction Canceled", Snackbar.LENGTH_LONG).setBackgroundTint(R.color.teal_200).show()
            }
        }
    }
}