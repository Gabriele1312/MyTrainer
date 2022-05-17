package com.example.mytrainer

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.FirebaseDatabase

class DBhelper {

    companion object{
        private val DB = FirebaseDatabase
            .getInstance("https://mytrainer-42e1b-default-rtdb.europe-west1.firebasedatabase.app/") //URL del database
            .getReference("EserciziPalestra") //crea nodo

        //legge dati dal DB
        fun read(todoEventListener: ChildEventListener){
            DB.addChildEventListener(todoEventListener)
        }

        //aggiorna o inserice oggetto Esercizi in firebase come sotto nodo (figlio) --> CHIAVE - VALORE
        fun set(key: String, item: Esercizi){
            DB.child(key).setValue(item)
        }

        //elimina valore di quel nodo
        fun remove(key: String){
            DB.child(key).removeValue()
        }
    }
}