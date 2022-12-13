package com.example.miasti_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var Pc1 = findViewById<ImageButton>(R.id.Pc1)
        var Pc2 = findViewById<ImageButton>(R.id.Pc2)
        var Rj45_1 = findViewById<ImageButton>(R.id.Rj45_1)
        var Rj45_2 = findViewById<ImageButton>(R.id.Rj45_2)
        var Pc1_Ip = findViewById<EditText>(R.id.Pc1_IP)
        var Pc2_Ip = findViewById<EditText>(R.id.Pc2_IP)
        var Pc1_PortId = findViewById<EditText>(R.id.PortPc1Id)
        var Pc2_PortId = findViewById<EditText>(R.id.PortPc2Id)
        var Pc1_Ipsec = findViewById<Switch>(R.id.ipsecPC1)
        var Pc2_Ipsec = findViewById<Switch>(R.id.ipsecPC2)
        var Pc1_saveProporties = findViewById<Button>(R.id.buttonPc1ProportiesSave)
        var Pc2_saveProporties = findViewById<Button>(R.id.buttonPc2ProportiesSave)

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
            if(Pc1_Ip.toString().length>3){
                Pc1Ip = Pc1_Ip.toString()
            }

            Pc1Port = Pc1_PortId.toString()
        }

        Pc2_saveProporties.setOnClickListener{
            Pc2Ip = Pc2_Ip.toString()
            Pc2Port = Pc2_PortId.toString()
        }

        Pc1.setOnClickListener{
            Toast.makeText(this@MainActivity,"Ip PC1 ="+ Pc1Ip.toString()+"Port Pc1 = "+Pc1Port.toString(),
                Toast.LENGTH_LONG).show()
        }

        Pc2.setOnClickListener{
            Toast.makeText(this@MainActivity,"Ip PC2 ="+ Pc2Ip.toString()+"Port Pc2 = "+Pc2Port.toString(),
                Toast.LENGTH_LONG).show()
        }


    }
}