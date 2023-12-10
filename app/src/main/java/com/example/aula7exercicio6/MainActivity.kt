package com.example.aula7exercicio6

import CustomAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arr: MutableList<String> = mutableListOf(
            "Maria", "Alana", "Amanda", "José", "André", "Marcia", "Lucia", "Carla", "Mario",
            "Brunno", "Cezar", "Claudio", "Renan", "Uiles", "Benedito", "Poliana", "Tatiana",
            "Eliane", "Cleide", "Monik", "Verônica", "Cecilia", "Maitê", "Mariana", "Chico",
            "Marcelo", "Alan"
        )
        // cria o objeto RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        // define a classe atual como gerenciadora do layout
        recyclerView.layoutManager = LinearLayoutManager(this)
        // define o adaptador
        val adapter = CustomAdapter(arr)
        // vincula a lista RecyclerView ao adaptador definido na linha anterior
        recyclerView.adapter = adapter

        // Adiciona uma linha divisória entre os itens da lista para melhorar a viibilidade.
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)

        // Cria a instância do SimpleItemTouchHelperCallback
        val itemTouchHelperCallback = SimpleItemTouchHelperCallback(adapter)
        //Permite que os usuários reordenem os itens da lista arrastando e soltando.
        // Adiciona o ItemTouchHelper ao RecyclerView
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    //A implementação do ItemTouchHelper e do SimpleItemTouchHelperCallback permite adicionar
    // funcionalidades de arrastar e soltar (drag and drop) à sua lista.
    // Isso permite que o usuário reordene os itens na lista arrastando-os.
    class SimpleItemTouchHelperCallback(private val adapter: CustomAdapter) :
        ItemTouchHelper.Callback() {

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            return makeMovementFlags(dragFlags, 0)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            adapter.swapItems(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // Não é necessário implementar no momento
        }
    }
}
