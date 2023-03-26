package co.tiagoaguiar.ganheinamega

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class quinaActiviy : AppCompatActivity() {


    private lateinit var prefs: SharedPreferences
    private lateinit var btnMega : ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quina)


        val editTextQuina: EditText = findViewById(R.id.edit_number_quina)
        val txtResultQuina: TextView = findViewById(R.id.txt_result_quina)
        val btnGenerateQuina: Button = findViewById(R.id.btn_generate_quina)
        val txtValoresJogosQuina: TextView = findViewById(R.id.txt_ValoresJogosQuina)

        btnMega = findViewById(R.id.btn_mega)
        btnMega.setOnClickListener {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }

        prefs = getSharedPreferences("database_quina",Context.MODE_PRIVATE)
        val result_quina = prefs.getString("result_quina",null)
        if(result_quina != null){
            txtResultQuina.text = "Ultima aposta $result_quina"
        }

        btnGenerateQuina.setOnClickListener {
            val text = editTextQuina.text.toString()
            numberGeneratorQuina(text,txtResultQuina)

            val textPriceQuina = editTextQuina.text.toString()
            priceBets(textPriceQuina,txtValoresJogosQuina)
        }

    }

    private fun numberGeneratorQuina(text: String, txtResultQuina: TextView){

        if (text.isEmpty()) {
            Toast.makeText(this, "Informe um número entre 5 e 15", Toast.LENGTH_LONG).show()
            return
        }

        val qtdQuina = text.toInt()
        if (qtdQuina < 5 || qtdQuina > 15) {
            Toast.makeText(this, "Informe um número entre 5 e 15", Toast.LENGTH_LONG).show()
            return
        }

        val numbersQuina = mutableListOf<Int>()
        val randomQuina = Random()
        while (true) {
            val number = randomQuina.nextInt(80)
            numbersQuina.add(number + 1)

            if (numbersQuina.size == qtdQuina) {
                break
            }
        }

        txtResultQuina.text = numbersQuina.joinToString(" - ")

        val editor = prefs.edit()
        editor.putString("result_quina",txtResultQuina.text.toString())
        editor.apply()


        }


    private fun priceBets(textPrice: String,txtValoresJogosQuina : TextView){

        if(textPrice.isEmpty()){
            Toast.makeText(this, "Informe um número entre 5 e 15", Toast.LENGTH_LONG).show()
            return
        }
        val qtdQuina = textPrice.toInt()
        if (qtdQuina == 5) {
            txtValoresJogosQuina.text = "valor da aposta: R$2,00"
        }else if (qtdQuina == 6){
            txtValoresJogosQuina.text = "valor da aposta: R$12,00"
        }else if (qtdQuina == 7){
            txtValoresJogosQuina.text = "valor da aposta: R$42,00"
        }else if (qtdQuina == 8){
            txtValoresJogosQuina.text = "valor da aposta: R$112,00"
        }else if (qtdQuina == 9){
            txtValoresJogosQuina.text = "valor da aposta: R$112,00"
        }else if (qtdQuina == 10){
            txtValoresJogosQuina.text = "valor da aposta: R$504,00"
        }else if (qtdQuina == 11){
            txtValoresJogosQuina.text = "valor da aposta: R$924,00"
        }else if (qtdQuina == 12){
            txtValoresJogosQuina.text = "valor da aposta: R$1.584,00"
        }else if (qtdQuina == 13){
            txtValoresJogosQuina.text = "valor da aposta: R$2.574,00"
        }else if (qtdQuina == 14){
            txtValoresJogosQuina.text = "valor da aposta: R$4.004,00"
        }else if (qtdQuina == 15){
            txtValoresJogosQuina.text = "valor da aposta: R$6.006,00"
        }



    }

}




