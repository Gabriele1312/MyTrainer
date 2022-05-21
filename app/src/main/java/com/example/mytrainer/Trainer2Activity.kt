package com.example.mytrainer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Trainer2Activity : AppCompatActivity() {

    lateinit var adapter: ArrayAdapter<Esercizi>
    lateinit var listView: ListView
    lateinit var floatActionButton: FloatingActionButton
    val data = ArrayList<Esercizi>() //Struttura dati dove metto i dati letti da FireBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer2)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_checked, data) //adapter per listView

        //chiama LETTURA con listener di eventi
        DBhelper.read(getEserciziListener())

        //LIST VIEW
        listView = findViewById<ListView>(R.id.lv)
        listView.adapter = adapter //interfaccia grafica legata ai dati

        //CANCELLA dal DB l'informazione
        listView.setOnItemLongClickListener{adapterView, view, pos, l ->
            DBhelper.remove(data[pos].key) //cancella il nodo con quella chiave
            true
        }

        //FLOATING BUTTON che chiama funzione di inserimento esercizio
        floatActionButton = findViewById<FloatingActionButton>(R.id.fab)
        floatActionButton.setOnClickListener(inserisciEsercizio())
    }



    //listener per la lettura dei dati
    private fun getEserciziListener(): ChildEventListener {
        val listener = object: ChildEventListener{

            //aggiunta figlio
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val item = snapshot.getValue(Esercizi::class.java)
                data.add(item!!) //aggiunge item alla lista
                val index = data.indexOf(item) //ritorna indice
                listView.setItemChecked(index, data[index].checked) //aziona sulla list view la spunta check per quel item
                adapter.notifyDataSetChanged() //ridisegna la list view in base ai nuovi dati che si trovano sull'adapter
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val item = snapshot.getValue(Esercizi::class.java)
                val index = data.indexOf(item); //indice elemento
                //val itemChanged = data.find { item?.key.equals(it.key) }
                data[index].set(item!!); //aggiorna l'elemento in posizione indice con il nuovo valore
                listView.setItemChecked(index, data[index].checked)
                adapter.notifyDataSetChanged(); //ridisegna list view
            }

            //rimozione figlio
            override fun onChildRemoved(snapshot: DataSnapshot) {
                val item = snapshot.getValue(Esercizi::class.java)
                data.remove(item)
                adapter.notifyDataSetChanged() //ridisegna listView
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }


            //situazione di errore
            override fun onCancelled(error: DatabaseError) {
               Log.d("DBERROR", "Errore del database")
            }


        }

        return listener
    }

    //quando clicchi si chiede di inserire la stringa che rappresenta oggetto ToDoitem
    private fun inserisciEsercizio(): View.OnClickListener? {
        val listener = View.OnClickListener {

            val alert =  AlertDialog.Builder(this@Trainer2Activity)
            alert.setTitle("Inserisci Esercizio")
            val et = EditText(this@Trainer2Activity) //inserisce testo nella edit text
            alert.setView(et)

            //quando si clicca su OK
            alert.setPositiveButton("Inserisci") {
                    dialog, which ->

                val text = et.text.toString() //prende le cose da fare
                val simpleData = SimpleDateFormat("dd-M-yyyy_hh:m:ss") //formato data
                val newTodo = Esercizi(text, false, simpleData.format(Date())) //passa come chiave la data
                DBhelper.set(newTodo.key, newTodo) //aggiunge sul DB
            }

            //quando si clicca su CANCELLA
            alert.setNegativeButton("Elimina"){

                    dialog, which ->
                dialog.cancel()
            }

            alert.show() //mi permette di visualizzare Alert Dialog
        }
        return listener
    }
}