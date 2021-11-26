import java.util.*

fun main(){
    println("Hola mundo")
    imprimir("Jose")
    calcularSueldo(100)
    /*Uso de variables
    *Tipo nombre = valor;
    * Ej: Int edad = 12;
    * Variable mutables e inmutables
    * Inmutable uso de (val)
    * val inmutable: String = "Adrian"
    * Mutable uso de (var)
    * var mutable: String = "Vicente"
    * val > var*/

    /*Uso de Dock Typing*/
    val ejemploVariable = "Nombre"
    var numero = 12

    /*Tipos de variables de Java*/
    val nombre: String = "Jose"
    val sueldo: Double = 1244.3
    val estadoCivil: Char = 's'
    val fecha: Date = Date()

    /*Condicionales
    Condicional switch
    when(estadoCivil) {
    ('s')->{
        condicion...}
    ('c') -> {
        condicion...}
    else ->  condicion...}*/

    /*Funciones*/
    /*void*/
}
fun imprimir(nombre: String): Unit {
    println("Nombre: ${nombre}")
}
fun calcularSuelo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoEspecial: Double? = null,
): Double {
    /*String -> String?
    * Int -> Int?
    * Date -> Date?
    * Double -> Double?*/
    if(bonoEspecial == null ){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}