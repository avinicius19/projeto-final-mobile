package br.com.up.pokedex

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.up.pokedex.network.Api
import com.squareup.picasso.Picasso

class PokeInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_poke_info)


        val nome : TextView = findViewById(R.id.poke_name)
        val imagem : ImageView = findViewById(R.id.poke_img)
        val tipo : TextView = findViewById(R.id.poke_tipo)
        val stats : TextView = findViewById(R.id.poke_stats)
        val habilidades : TextView = findViewById(R.id.poke_habilidades)
        val movimentos : TextView = findViewById(R.id.poke_movimentos)

        val id = intent.getStringExtra("id")
        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
        Picasso.get().load(url).into(imagem)

        Api().getPokemonDetailsById(id!!){
                details ->

            if(details != null){

                var tipoStr = "Tipo: "
                var statsStr = "Status:\n"
                var habilidadeStr = "Abilidades:\n"
                var movimentosStr = "Ataques:\n"

                nome.text = "Nome: " + details.name

                details.types.forEach {
                    tipoStr = tipoStr + it.type.name + "  "
                }
                tipo.text = tipoStr

                details.stats.forEach{
                    statsStr = statsStr + it.stat.name + ": " + it.base_stat + "\n"
                }
                stats.text = statsStr

                details.abilities.forEach {
                    habilidadeStr = habilidadeStr + it.ability.name + "  "
                }
                habilidades.text = habilidadeStr

                details.moves.forEach {
                    movimentosStr = movimentosStr + it.move.name + "\n"
                }
                movimentos.text = movimentosStr
            }
        }

    }
}