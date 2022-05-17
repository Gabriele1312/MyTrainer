package com.example.mytrainer

data class Esercizi(var descr: String, var checked: Boolean, var key: String) {

    //serve a FireBase per inizializzare --> va bene vuoto
    constructor() : this("", false, "")


    override fun toString(): String {
        return this.descr //ritorna solo la descrizione
    }

    //metodo per la copia dell'oggetto con nuovi elementi
    fun set(item: Esercizi){
        descr = item.descr
        checked = item.checked
        key = item.key
    }

}