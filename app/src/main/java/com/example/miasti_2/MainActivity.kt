@file:Suppress("LocalVariableName")

package com.example.miasti_2

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {

        fun isValidIPAddress(ip:String):Boolean {
            // Regex for digit from 0 to 255
            val reg0To255 = ("(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])")
            // regex 0 To 255 followed by a dot, 4 times repeat
            // validation an IP address.
            val regex = (reg0To255 + "\\."
                    + reg0To255 + "\\."
                    + reg0To255 + "\\."
                    + reg0To255)
            val p = Pattern.compile(regex)
            val m = p.matcher(ip)
            return m.matches()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val envelope = findViewById<ImageView>(R.id.post)
        val envelope2 = findViewById<ImageView>(R.id.post2)
        val envelope3 = findViewById<ImageView>(R.id.post3)
        val Pc1 = findViewById<ImageButton>(R.id.Pc1)
        val Pc2 = findViewById<ImageButton>(R.id.Pc2)
        var Rj45_1 = findViewById<ImageButton>(R.id.Rj45_1)
        var Rj45_2 = findViewById<ImageButton>(R.id.Rj45_2)
        val Pc1_Ip = findViewById<EditText>(R.id.Pc1_IP)
        val Pc2_Ip = findViewById<EditText>(R.id.Pc2_IP)
        val Pc1_PortId = findViewById<EditText>(R.id.PortPc1Id)
        val Pc2_PortId = findViewById<EditText>(R.id.PortPc2Id)
        val Pc1_Ipsec = findViewById<Switch>(R.id.ipsecPC1)
        val Pc2_Ipsec = findViewById<Switch>(R.id.ipsecPC2)
        val Pc1_saveProporties = findViewById<Button>(R.id.buttonPc1ProportiesSave)
        val Pc2_saveProporties = findViewById<Button>(R.id.buttonPc2ProportiesSave)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val animation: Animation = AlphaAnimation(1.0f, 0.0f)
        //animation time of disappearing button after selecting it
        animation.duration = 1000

        envelope.visibility = View.INVISIBLE
        envelope2.visibility = View.INVISIBLE
        envelope3.visibility = View.INVISIBLE

        var Pc1Ip = "0.0.0.0"
        var Pc2Ip = "0.0.0.0"
        var Pc1Port = "0000"
        var Pc2Port = "0000"
        var Pc1Ipsec = false
        var Pc2Ipsec = false

        Pc1_Ipsec?.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "IpsecPc1:ON" else "IpsecPc2:OFF"
            Pc1Ipsec = isChecked
            Toast.makeText(
                this@MainActivity, message,
                Toast.LENGTH_SHORT
            ).show()
        }

        Pc2_Ipsec?.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "IpsecPc1:ON" else "IpsecPc2:OFF"
            Pc2Ipsec = isChecked
            Toast.makeText(
                this@MainActivity, message,
                Toast.LENGTH_SHORT
            ).show()
        }

        Pc1_saveProporties.setOnClickListener{
            Pc1_saveProporties.startAnimation(animation)
            Pc1Ip = Pc1_Ip.text.toString()
            Pc1Port = Pc1_PortId.text.toString()
        }

        Pc2_saveProporties.setOnClickListener{
            Pc2_saveProporties.startAnimation(animation)
            Pc2Ip = Pc2_Ip.text.toString()
            Pc2Port = Pc2_PortId.text.toString()
        }

        Pc1.setOnClickListener{
            if(isValidIPAddress(Pc1Ip))
            {
                Toast.makeText(this@MainActivity,"Ip PC1 ="+ Pc1Ip +"Port Pc1 = "+ Pc1Port,
                    Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity,"Bad ip address",Toast.LENGTH_LONG).show()
            }

        }

        Pc2.setOnClickListener{
            if(isValidIPAddress(Pc2Ip)) {
                Toast.makeText(
                    this@MainActivity, "Ip PC2 =" + Pc2Ip + "Port Pc2 = " + Pc2Port,
                    Toast.LENGTH_LONG
                ).show()
            }else {
                Toast.makeText(this@MainActivity,"Bad ip address",Toast.LENGTH_LONG).show()
            }
        }

        sendButton.setOnClickListener{
            if(Pc1Ip==Pc2Ip){
                Toast.makeText(this@MainActivity,"Ip address of Pc1 and Pc2 are the same",Toast.LENGTH_LONG).show()
            }else{
            if(Pc1Port!=Pc2Port){
                Toast.makeText(this@MainActivity,"Ports need to have the same value",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity,"Sending message",Toast.LENGTH_LONG).show()


                GlobalScope.launch(Dispatchers.Main) {
                    println("Thread woke up after 1 second")
                    envelope.visibility = View.VISIBLE
                    delay(2000)
                    Log.e("error","1")
                    envelope.visibility = View.INVISIBLE
                    envelope2.visibility = View.VISIBLE
                    delay(2000)
                    envelope2.visibility = View.INVISIBLE
                    Log.e("error","2")
                    envelope3.visibility = View.VISIBLE
                    delay(2000)
                    envelope3.visibility = View.INVISIBLE
                }

                if(Pc1Ipsec == true && Pc2Ipsec == true){
                    Toast.makeText(this@MainActivity,"Connection Successful",Toast.LENGTH_LONG).show()

                    GlobalScope.launch(Dispatchers.Main) {
                        println("Thread woke up after 1 second")
                        envelope3.visibility = View.VISIBLE
                        delay(2000)
                        Log.e("error","1")
                        envelope3.visibility = View.INVISIBLE
                        envelope2.visibility = View.VISIBLE
                        delay(2000)
                        envelope2.visibility = View.INVISIBLE
                        Log.e("error","2")
                        envelope.visibility = View.VISIBLE
                        delay(2000)
                        envelope.visibility = View.INVISIBLE
                    }

                    Toast.makeText(this@MainActivity,"Message receive",Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(this@MainActivity,"Ipsec error",Toast.LENGTH_LONG).show()
                }
            }

            }
        }



    }
}


