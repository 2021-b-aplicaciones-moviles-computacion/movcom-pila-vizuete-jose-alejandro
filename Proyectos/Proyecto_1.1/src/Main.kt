import java.util.*
import kotlin.collections.ArrayList

fun main(){
    println("Hola mundo")
    imprimir("Jose")

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
    calcularSueldo(100.00)
    calcularSueldo(100.00,20.00)
    calcularSueldo(100.00,20.00,25.00)

    //Parametros nombrados
    /*Se puede cambiar el orden de como se quiere enviar los
    parametros*/
    calcularSueldo(sueldo = 100.00, bonoEspecial = 12.50, tasa = 5.00)
    calcularSueldo(sueldo = 100.00, tasa = 5.00)
    calcularSueldo(tasa = 5.00, sueldo = 250.12)
    //Arreglos
    val ArregloEstatico: Array<Int> = arrayOf(1,2,3)
    val nombreEstatico: Array<String> = arrayOf("Juan", "Diego")
    //Arreglos dinamicos

    val ArregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5)
    ArregloDinamico.add(6)
    ArregloDinamico.add(7)
    ArregloDinamico.add(8)
/*
    println(ArregloDinamico)
    //Operador
    // Foreach -> Permite la iteracion del arreglo
    val respuestaForEach: Unit = ArregloDinamico
        .forEach { valorActual: Int ->
            println("valorActual: ${valorActual}")
        }
            ArregloDinamico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Pos: ${indice} valorActual ${valorActual}")
        }
    //map
    val respuestaMap: List<Double> = ArregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble()+10.00
        }
    println(respuestaMap)
    val respuestaMap1: List<Double> = ArregloDinamico.map { it + 10.00 }
    println(respuestaMap1)

    //FILTER
    val respuestaFilter: List<Int> = ArregloDinamico
        .filter { valorActual: Int  ->
          val resultado: Boolean = valorActual > 5
            return@filter resultado
        }
    println(respuestaFilter)
    val respuestaFilter1: List<Int> = ArregloDinamico.filter { it <=5 }
    println(respuestaFilter1)
    // Operadores condicionales Any & All
    val respuestaAny: Boolean = ArregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    val respuestaAll: Boolean = ArregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAny)
    println(respuestaAll)
    //Operador REDUCE
    val respuestaReduce: Int = ArregloDinamico
        .reduce { acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual)
        }
    println(respuestaReduce)
    val arreglo = arrayListOf<Int>(2,4,5,6,7,8)
    val respuestaReduceFold: Int = arreglo
        .fold(100){
            acumulado, valorActual ->
            return@fold acumulado - valorActual
        }
    println(respuestaReduceFold)
    
    //ejercicio
    println(ArregloDinamico)
    val vidaActual: Double = ArregloDinamico
        .map { it * 1.50 }
        .filter { it > 10 }
        .fold(100.00 ,{ acc, i -> acc - i })
        .also { println(it) }
    println("Vida actual ${vidaActual}")
    */
    //Ejecucion clase sumar
    val ejemplo1 = Suma(1,2)
    val ejemplo2 = Suma(null,3)
    val ejemplo3 = Suma(4,null)
    val ejemplo4 = Suma(null,null)

    println("valor1: "+ejemplo1.sumar())
    println("valor2: "+ejemplo2.sumar())
    println("valor3: "+ejemplo3.sumar())
    println("valor1: "+ejemplo4.sumar())
    println(Suma.historial)


}
fun imprimir(nombre: String): Unit {
    println("Nombre: ${nombre}")
}
fun calcularSueldo (
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
abstract class NumerosJava {
    //Variables generales
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int,
    ){
        this.numeroUno = uno
        this.numeroDos= dos
        println("Inicializar...")

    }
}
abstract class Numeros(
    //Constructor Primario
    protected var numeroUno: Int,
    protected var numeroDos: Int,
) {
    init {
        println("Inicializar...")
    }
}
class Suma (
    //Constructor Primario
    uno: Int,
    dos: Int,
): Numeros(
    uno,
    dos
) {
    init{
        this.numeroUno
        this.numeroDos
    }
    //Constructor cuando uno de los valores es nulo
    constructor(
       uno: Int?,
       dos: Int,
    ) : this(
        if(uno == null) 0 else uno,
        dos
    ){
        //Codigo segundo constructor
    }
    constructor(
        uno: Int,
        dos: Int?,
    ): this(
        uno,
        if(dos == null) 0 else dos,
    )
    {
        //BLoque de codigo para tercer constrcutor
    }
    //Constructor cuando ambos valores son nulos
    constructor(
        uno: Int?,
        dos: Int?,
    ): this(
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos,
    )
    {
        //BLoque de codigo para constrcutor
    }

    //Declaración de métodos y propiedades estáticas
    companion object {
        val pi = 3.14;
        val historial = arrayListOf<Int>()
        fun agregarHistorial(valorNuevo: Int) {
            historial.add(valorNuevo)
        }
    }


    //Funciones
    //public fun sumar(): Int{
     fun sumar(): Int {
         val total: Int = numeroUno + numeroDos
        agregarHistorial(total)
        return total
     }

}



