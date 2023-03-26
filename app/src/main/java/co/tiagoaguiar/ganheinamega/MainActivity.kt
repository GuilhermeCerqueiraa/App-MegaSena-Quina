package co.tiagoaguiar.ganheinamega

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private lateinit var btnQuina: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val editText: EditText = findViewById(R.id.edit_number)
        val txtResult: TextView = findViewById(R.id.txt_result)
        val btnGenerate: Button = findViewById(R.id.btn_generate)
        val txtValoresJogosMega : TextView = findViewById(R.id.txt_ValoresJogosMega)


        btnQuina = findViewById(R.id.btn_quina)
        btnQuina.setOnClickListener {
            val i = Intent(this,quinaActiviy::class.java)
            startActivity(i)
        }




        prefs = getSharedPreferences("database", Context.MODE_PRIVATE)
        val result = prefs.getString("result",null)
        if(result != null){
        txtResult.text = "Ultima aposta: $result"
        }


        btnGenerate.setOnClickListener {

            val text = editText.text.toString()
            numberGenerator(text, txtResult)

            val textPriceMega = editText.text.toString()
            priceBetsMega(textPriceMega,txtValoresJogosMega)
        }
    }


    private fun numberGenerator(text: String, txtResult: TextView) {

        if (text.isEmpty()) {
            Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }


        val qtd = text.toInt()
        if (qtd < 6 || qtd > 15) {
            Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        val numbers = mutableListOf<Int>()
        val random = Random()
        while (true) {
            val number = random.nextInt(60)
            numbers.add(number + 1)

            if (numbers.size == qtd) {
                break
            }
        }


        txtResult.text = numbers.joinToString(" - ")


        val editor = prefs.edit()
        editor.putString("result",txtResult.text.toString())
        editor.apply()


    }



    private fun priceBetsMega(textPriceMega: String,txtValoresJogosMega : TextView){

        if(textPriceMega.isEmpty()){
            Toast.makeText(this, "Informe um número entre 5 e 15", Toast.LENGTH_LONG).show()
            return
        }
        val qtd = textPriceMega.toInt()
        if (qtd == 6) {
            txtValoresJogosMega.text = "valor da aposta: R$4,50"
        }else if (qtd == 7){
            txtValoresJogosMega.text = "valor da aposta: R$31,50"
        }else if (qtd == 8){
            txtValoresJogosMega.text = "valor da aposta: R$126,00"
        }else if (qtd == 9){
            txtValoresJogosMega.text = "valor da aposta: R$378,00"
        }else if (qtd == 10){
            txtValoresJogosMega.text = "valor da aposta: R$945,00"
        }else if (qtd == 11){
            txtValoresJogosMega.text = "valor da aposta: R$2.079,00"
        }else if (qtd == 12){
            txtValoresJogosMega.text = "valor da aposta: R$4.158,00"
        }else if (qtd == 13){
            txtValoresJogosMega.text = "valor da aposta: R$7.722,00"
        }else if (qtd == 14){
            txtValoresJogosMega.text = "valor da aposta: R$13.513,50"
        }else if (qtd == 15){
            txtValoresJogosMega.text = "valor da aposta: R$22.552,50"
        }



    }


}