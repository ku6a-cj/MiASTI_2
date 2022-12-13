package com.example.miasti_2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.net.InetAddress
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
        val animation: Animation = AlphaAnimation(1.0f, 0.0f)
        //animation time of disappearing button after selecting it
        animation.duration = 1000


        var Pc1Ip = "0.0.0.0"
        var Pc2Ip = "0.0.0.0"
        var Pc1Port = "0000"
        var Pc2Port = "0000"
        var Pc1Ipsec = false
        var Pc2Ipsec = false

        Pc1_Ipsec?.setOnCheckedChangeListener({ _ , isChecked ->
            val message = if (isChecked) "IpsecPc1:ON" else "IpsecPc2:OFF"
            Pc1Ipsec = if (isChecked) true else false
            Toast.makeText(this@MainActivity, message,
                Toast.LENGTH_SHORT).show()
        })

        Pc2_Ipsec?.setOnCheckedChangeListener({ _ , isChecked ->
            val message = if (isChecked) "IpsecPc1:ON" else "IpsecPc2:OFF"
            Pc2Ipsec = if (isChecked) true else false
            Toast.makeText(this@MainActivity, message,
                Toast.LENGTH_SHORT).show()
        })

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
                Toast.makeText(this@MainActivity,"Ip PC1 ="+ Pc1Ip.toString() +"Port Pc1 = "+ Pc1Port,
                    Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity,"Bad ip adress",Toast.LENGTH_LONG).show()
            }

        }

        Pc2.setOnClickListener{
            if(isValidIPAddress(Pc1Ip)) {
                Toast.makeText(
                    this@MainActivity, "Ip PC2 =" + Pc2Ip + "Port Pc2 = " + Pc2Port,
                    Toast.LENGTH_LONG
                ).show()
            }else {
                Toast.makeText(this@MainActivity,"Bad ip adress",Toast.LENGTH_LONG).show()
            }
        }


    }
}