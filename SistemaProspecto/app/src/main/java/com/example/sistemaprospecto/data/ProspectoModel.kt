package com.example.sistemaprospecto.data


data class ProspectoModel(
    var id: Int,
    var nombre: String,
    var primerapellido: String,
    var segundoapellido: String,
    var calle: String,
    var numero: String,
    var colonia: String,
    var codigopostal: String,
    var telefono: String,
    var rfc: String,
    var estatus:String,
    var observaciones: String
)

data class ProspectoList(
    var prospectoList: List<ProspectoModel>
)
