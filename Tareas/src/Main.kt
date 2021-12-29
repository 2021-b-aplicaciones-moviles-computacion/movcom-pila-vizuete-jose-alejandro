import java.util.*
import kotlin.collections.ArrayList
fun main(){
/*Creacion de arreglos para almacenar*/
    var nombreLibros: ArrayList<String> = arrayListOf()
    var autorLibros: ArrayList<String> = arrayListOf()
    var fechaLibros: ArrayList<Date> = arrayListOf()
    var costoLibros: ArrayList<Double> = arrayListOf()
    var edicionLibros: ArrayList<Int> = arrayListOf()
    var disponibleLibros: ArrayList<Boolean> = arrayListOf()
    var nombreBibliotecas: ArrayList<String> = arrayListOf()
    var direccionBibliotecas: ArrayList<String> = arrayListOf()
    var fechaBibliotecas: ArrayList<Date> = arrayListOf()
 /*Instanciado de clases*/
    var libro = Libros()
    var biblioteca = Bibliotecas()
    var condicion: Int =1
    /*Conexion de clases y menu*/
   do {
       val opcion1 : Int = menu()
       //Funciones PARA LIBROS
       if (opcion1 == 1){
           val opcion2: Int = menuLibros()
           /*Opcion agregar un nuevo libro*/
           if(opcion2 == 1 ){
               println("Ingrese nombre del libro: ")
               var nom: String = readLine().toString()
               nombreLibros = libro.agregarNombre(nombreLibros, nom)
               println("Ingrese el nombre del autor: ")
               var aut: String = readLine().toString()
               autorLibros = libro.agregarAutor(autorLibros, aut)
               var fec: Date = Date()
               fechaLibros = libro.agregarFecha(fechaLibros,fec)
               //println(fechaLibros)
               print("Ingrese el valor del libro(digital/físico): ")
               var costo: Double = readLine()?.toDouble()!!
               costoLibros = libro.agregarCosto(costoLibros,costo)
               println("Ingrese la edicion del libro en numeros: (2,3)")
               var edicion: Int = readLine()?.toInt()!!
               edicionLibros = libro.agregarEdicion(edicionLibros, edicion)
               println("Se encuentra disponible el libro: si/no")
               var disponible: String = readLine().toString()
               if(disponible.contentEquals("si")){
                   disponibleLibros = libro.agregarDisponible(disponibleLibros, true)
               }else{
                   disponibleLibros = libro.agregarDisponible(disponibleLibros, false)
               }
               println("Se ha creado el libro correctamente....\n" +
                    "nombre del libro: ${nom}\n" +
                    "nombre del autor: ${aut}\n" +
                    "fecha de registro:  ${fec}\n" +
                    "costo del libro: ${costo}\n" +
                    "# de edicion: ${edicion}\n" +
                    "disponible: ${disponible}")
            condicion = continuar()
        }
           /*Opcion de buscar un libro*/
           else if(opcion2 == 2){
               val opcion3: Int = menuLibrosBuscar()
               if(opcion3 == 1 ){
                   println("Ingrese el nombre del libro: ")
                   var busqueda: String = readLine().toString()
                   var nomL: Int = libro.buscarNombre(nombreLibros, busqueda)
                   if(nomL>=0){
                       libro.mostrarUno(nomL, nombreLibros,autorLibros, fechaLibros, costoLibros, edicionLibros, disponibleLibros)
                       condicion = continuar()
                   }else{
                       println("No existe un registro con la búsqueda realizada.....")
                       condicion = continuar()
                   }
               }
               else if(opcion3 == 2){
                   println("Ingrese el autor del libro")
                   var busqueda: String = readLine().toString()
                   var autL: ArrayList<Int> = libro.buscarAutor(autorLibros,busqueda)
                   if (!autL.isEmpty()){
                       libro.mostrarVarios(autL,nombreLibros,autorLibros, fechaLibros, costoLibros, edicionLibros, disponibleLibros)
                       condicion = continuar()
                   }else{
                       println("No se ha encontrado ningun libro con el autor ingresado")
                       condicion = continuar()
                   }
               }
               else if(opcion3 == 3){
                   println("Ingrese el numero de edicion del libro que esta buscando: ")
                   var busqueda: Int = readLine()?.toInt()!!
                   var ediL: ArrayList<Int> =  libro.buscaEdicion(edicionLibros, busqueda)
                   if (!ediL.isEmpty()){
                       libro.mostrarVarios(ediL,nombreLibros,autorLibros, fechaLibros, costoLibros, edicionLibros, disponibleLibros)
                       condicion = continuar()
                   }else{
                       println("No se ha encontrado ningun libro con las ediciones mostradas")
                       condicion = continuar()
                   }
               }
               else if (opcion3==4) {
                   var dispL: ArrayList<Int>
                   println("Ingresar la disponibilidad del libro" +
                           "\n Si" +
                           "\n No")
                   var busqueda: String = readLine().toString()
                   if(busqueda.equals("Si")|| busqueda.equals("si")){
                       dispL= libro.buscaDisponible(disponibleLibros, true)
                       if(!dispL.isEmpty()) {
                        libro.mostrarVarios(dispL, nombreLibros, autorLibros, fechaLibros, costoLibros, edicionLibros, disponibleLibros)
                       }else{
                           println("No se ha encontrado resultados en la busqueda")
                       }
                   }else if (busqueda.equals("No")|| busqueda.equals("no")){
                       dispL= libro.buscaDisponible(disponibleLibros, false)
                       if(!dispL.isEmpty()) {
                           libro.mostrarVarios(dispL, nombreLibros, autorLibros, fechaLibros, costoLibros, edicionLibros, disponibleLibros)
                       }else{
                           println("No se ha encontrado resultados en la busqueda")
                       }
                   }
                   condicion = continuar()


               }
           }
           /*Opcion Actualizar un libro*/
           else if(opcion2 == 3) {
               val opcion3: Int = menuLibrosBuscarA()
               //Actualizar libro por nombre
               if (opcion3 == 1) {
                   println("Ingrese el nombre del libro: ")
                   var nomL: Int = -1
                   var busqueda: String = readLine().toString()
                   nomL = libro.buscarNombre(nombreLibros, busqueda)
                   if (nomL >= 0) {
                       nombreLibros = libro.actualizarNombre(nomL, nombreLibros)
                       autorLibros = libro.actualizarAutor(nomL, autorLibros)
                       costoLibros = libro.actualizarCosto(nomL, costoLibros)
                       disponibleLibros = libro.actualizarDisponible(nomL, disponibleLibros)
                       println("se ha actualizado el libro correctamente.....")
                       libro.mostrarUno(
                           nomL,
                           nombreLibros,
                           autorLibros,
                           fechaLibros,
                           costoLibros,
                           edicionLibros,
                           disponibleLibros
                       )
                       condicion = continuar()
                   } else {
                       println("No se ha encontrado el libro para actualizar.....")
                       condicion = continuar()
                   }
               }
               //Actualizar libro por autor
               else if (opcion3 == 2) {
                   println("Ingrese el nombre del libro: ")
                   var busqueda: String = readLine().toString()
                   val autL: ArrayList<Int> = libro.buscarAutor(autorLibros, busqueda)
                   if (!autL.isEmpty()) {
                       if (autL.size == 1) {
                           nombreLibros = libro.actualizarNombre(autL[0], nombreLibros)
                           autorLibros = libro.actualizarAutor(autL[0], autorLibros)
                           costoLibros = libro.actualizarCosto(autL[0], costoLibros)
                           disponibleLibros = libro.actualizarDisponible(autL[0], disponibleLibros)
                           println("se ha actualizado el libro correctamente.....")
                           libro.mostrarUno(
                               autL[0],
                               nombreLibros,
                               autorLibros,
                               fechaLibros,
                               costoLibros,
                               edicionLibros,
                               disponibleLibros
                           )
                           condicion = continuar()
                       } else {
                           var seleccion = libro.seleccionarAutor(autL, busqueda, nombreLibros)
                           nombreLibros = libro.actualizarNombre(seleccion, nombreLibros)
                           autorLibros = libro.actualizarAutor(seleccion, autorLibros)
                           costoLibros = libro.actualizarCosto(seleccion, costoLibros)
                           disponibleLibros = libro.actualizarDisponible(seleccion, disponibleLibros)
                           println("se ha actualizado el libro correctamente.....")
                           libro.mostrarUno(
                               seleccion,
                               nombreLibros,
                               autorLibros,
                               fechaLibros,
                               costoLibros,
                               edicionLibros,
                               disponibleLibros
                           )
                           condicion = continuar()
                       }
                   } else {
                       println("No se ha encontrado ningun libro con el autor mencionado....")
                       condicion = continuar()
                   }
               }
               //Actualizar libros por disponibilidad
               else if (opcion3 == 3) {
                   println("La disponibilidad del libro es: \nSi\nNo\nSeleccione la respuesta correcta")
                   var dispL = ArrayList<Int>()
                   var busqueda: String = readLine().toString()
                   if (busqueda.equals("SI") || busqueda.equals("Si") || busqueda.equals("si")) {
                       dispL = libro.buscaDisponible(disponibleLibros, true)
                   } else if (busqueda.equals("No") || busqueda.equals("NO") || busqueda.equals("no")) {
                       dispL = libro.buscaDisponible(disponibleLibros, false)
                   }
                   if (!dispL.isEmpty()) {
                       if (dispL.size == 1) {
                           nombreLibros = libro.actualizarNombre(dispL[0], nombreLibros)
                           autorLibros = libro.actualizarAutor(dispL[0], autorLibros)
                           costoLibros = libro.actualizarCosto(dispL[0], costoLibros)
                           disponibleLibros = libro.actualizarDisponible(dispL[0], disponibleLibros)
                           println("se ha actualizado el libro correctamente.....")
                           libro.mostrarUno(dispL[0], nombreLibros, autorLibros,fechaLibros,costoLibros, edicionLibros,disponibleLibros)
                           condicion = continuar()
                       } else {
                           var seleccion: Int = -1
                           if (busqueda.equals("SI") || busqueda.equals("Si") || busqueda.equals("si")) {
                               seleccion = libro.seleccionarDisponible(dispL, true, nombreLibros)
                           } else if (busqueda.equals("No") || busqueda.equals("NO") || busqueda.equals("no")) {
                               seleccion = libro.seleccionarDisponible(dispL, false, nombreLibros)
                           }
                           nombreLibros = libro.actualizarNombre(seleccion, nombreLibros)
                           autorLibros = libro.actualizarAutor(seleccion, autorLibros)
                           costoLibros = libro.actualizarCosto(seleccion, costoLibros)
                           disponibleLibros = libro.actualizarDisponible(seleccion, disponibleLibros)
                           println("se ha actualizado el libro correctamente.....")
                           libro.mostrarUno(
                               seleccion,
                               nombreLibros,
                               autorLibros,
                               fechaLibros,
                               costoLibros,
                               edicionLibros,
                               disponibleLibros
                           )
                           condicion = continuar()
                       }
                   } else {
                       println("No se ha encontrado ningun libro con la busqueda seleccionada....")
                       condicion = continuar()
                   }
               }
               /*Opción eliminar registro de Libro*/
           }
           /*Opciones para Eliminar libros*/
           else if(opcion2 == 4){
               var opcion3 = menuLibrosEliminar()
               //Eliminar buscando nombre
               if(opcion3 == 1){
                   var nomL: Int = -1
                   println("Ingrese el nombre del libro a eliminar: ")
                   var busqueda: String = readLine().toString()
                   nomL = libro.buscarNombre(nombreLibros, busqueda)
                   if(nomL >=0 ){
                       nombreLibros = libro.eliminarNombre(nomL, nombreLibros)
                       autorLibros = libro.eliminarAutor(nomL, autorLibros)
                       fechaLibros = libro.eliminarFecha(nomL, fechaLibros)
                       costoLibros = libro.eliminarCosto(nomL, costoLibros)
                       edicionLibros = libro.eliminarEdicion(nomL, edicionLibros)
                       disponibleLibros = libro.eliminarDisp(nomL, disponibleLibros)
                       println("Se ha eliminado correctamente el registro del libro...")
                       condicion = continuar()
                   }else{
                       println("No se ha encontrado la busqueda correspondiente...")
                       condicion= continuar()
                   }

               }
               //Eliminar buscando autor
               else if (opcion3 == 2){
                   println("Ingrese el nombre del autor para eliminar:")
                   var busqueda: String = readLine().toString()
                   val autL: ArrayList<Int> = libro.buscarAutor(autorLibros, busqueda)
                   if (!autL.isEmpty()) {
                       if (autL.size == 1) {
                           nombreLibros = libro.eliminarNombre(autL[0], nombreLibros)
                           autorLibros = libro.eliminarAutor(autL[0], autorLibros)
                           fechaLibros = libro.eliminarFecha(autL[0], fechaLibros)
                           costoLibros = libro.eliminarCosto(autL[0], costoLibros)
                           edicionLibros = libro.eliminarEdicion(autL[0], edicionLibros)
                           disponibleLibros = libro.eliminarDisp(autL[0], disponibleLibros)
                           println("Se ha eliminado correctamente el registro del libro...")
                           condicion = continuar()
                       }else{
                           var seleccion = libro.seleccionarAutor(autL, busqueda, nombreLibros)
                           nombreLibros = libro.eliminarNombre(seleccion, nombreLibros)
                           autorLibros = libro.eliminarAutor(seleccion, autorLibros)
                           fechaLibros = libro.eliminarFecha(seleccion, fechaLibros)
                           costoLibros = libro.eliminarCosto(seleccion, costoLibros)
                           edicionLibros = libro.eliminarEdicion(seleccion, edicionLibros)
                           disponibleLibros = libro.eliminarDisp(seleccion, disponibleLibros)
                           println("Se ha eliminado correctamente el registro del libro...")
                           condicion = continuar()
                       }
                   }else{
                       println("No se ha encontrado ninguna coincidencia....")
                       condicion = continuar()
                   }
               }
               //Eliminar libros por disponibilidad
               else if (opcion3 == 3){
                   println("La disponibilidad del libro es: \nSi\nNo\nSeleccione la respuesta correcta")
                   var dispL = ArrayList<Int>()
                   var busqueda: String = readLine().toString()
                   if (busqueda.equals("SI") || busqueda.equals("Si") || busqueda.equals("si")) {
                       dispL = libro.buscaDisponible(disponibleLibros, true)
                   } else if (busqueda.equals("No") || busqueda.equals("NO") || busqueda.equals("no")) {
                       dispL = libro.buscaDisponible(disponibleLibros, false)
                   }
                   if (!dispL.isEmpty()) {
                       if (dispL.size == 1) {
                           nombreLibros = libro.eliminarNombre(dispL[0], nombreLibros)
                           autorLibros = libro.eliminarAutor(dispL[0], autorLibros)
                           fechaLibros = libro.eliminarFecha(dispL[0], fechaLibros)
                           costoLibros = libro.eliminarCosto(dispL[0], costoLibros)
                           edicionLibros = libro.eliminarEdicion(dispL[0], edicionLibros)
                           disponibleLibros = libro.eliminarDisp(dispL[0], disponibleLibros)
                           println("Se ha eliminado correctamente el registro del libro...")
                           condicion = continuar()
                       }else{
                           var seleccion: Int = -1
                           if (busqueda.equals("SI") || busqueda.equals("Si") || busqueda.equals("si")) {
                               seleccion = libro.seleccionarDisponible(dispL, true, nombreLibros)
                           } else if (busqueda.equals("No") || busqueda.equals("NO") || busqueda.equals("no")) {
                               seleccion = libro.seleccionarDisponible(dispL, false, nombreLibros)
                           }
                           nombreLibros = libro.eliminarNombre(seleccion, nombreLibros)
                           autorLibros = libro.eliminarAutor(seleccion, autorLibros)
                           fechaLibros = libro.eliminarFecha(seleccion, fechaLibros)
                           costoLibros = libro.eliminarCosto(seleccion, costoLibros)
                           edicionLibros = libro.eliminarEdicion(seleccion, edicionLibros)
                           disponibleLibros = libro.eliminarDisp(seleccion, disponibleLibros)
                           println("Se ha eliminado correctamente el registro del libro...")
                           condicion = continuar()

                       }
                   }
               }
           }
       }
       //FUNCIONES PARA BIBLIOTECAS
       else if (opcion1 == 2){
        val opcion3: Int = menuBiblioteca()
       /*Registrar nuevas bibliotecas*/
        if(opcion3 == 1 ){
            println("Ingrese el nombre de la Biblioteca: ")
            var nombreB: String = readLine().toString()
            nombreBibliotecas = biblioteca.agregarNombreB(nombreBibliotecas, nombreB)
            println("Ingrese la direccion de la Biblioteca: ")
            var direccionB: String = readLine().toString()
            direccionBibliotecas = biblioteca.agregarDireccionB(direccionBibliotecas, direccionB)
            var fechaB: Date = Date()
            fechaBibliotecas = biblioteca.agregarFechaB(fechaBibliotecas, fechaB)
            println("Se ha creado correctamente el registro de la biblioteca\n" +
                    "Nombre de la biblioteca: ${nombreB}\n" +
                    "Direccion de la biblioteca: ${direccionB}\n" +
                    "Fecha de registro: ${fechaB}")
            condicion = continuar()
        }
        /*Buscar una */
        else if(opcion3 == 2){

        }else if(opcion3 == 3){

        }else{

        }
    }
   }while (condicion == 1)
}
/*Funciones para desplegar menus*/
fun menu(): Int{
    val num: Int
    println("----------Sistema de navegación de biblioteca----------")
    println("Seleccione la opción a la que quiere acceder:\n" +
            "1.Sistema de libros\n" +
            "2.Sistema de Bibliotecas")
    println("Ingrese su opcion: ")
    num = readLine()?.toInt()!!
    println("-------------------------------------------------------")
    return num
}
fun menuLibros(): Int {
    val num: Int
    println("---------------Administracion de Libros----------------")
    println("Seleccione una de la opciones para realizar:\n" +
            "1. Agregar Libro\n" +
            "2. Buscar Libro\n" +
            "3. Actualizar libro\n" +
            "4. Eliminar Libro")
    println("-------------------------------------------------------" +
            "\nIngrese la opción:")
    num = readLine()?.toInt()!!
    return num;
}
fun menuBiblioteca(): Int {
    println("-------------Administracion de Bibliotecas-------------")
    println(
        "1. Ingresar Biblioteca\n" +
                "2. Buscar Biblioteca\n" +
                "3. Actualizar info biblioteca\n" +
                "4. Eliminar registro de biblioteca"
    )
    println(
        "-------------------------------------------------------" +
                "\nIngrese la opción:"
    )
    return readLine()?.toInt()!!
}
fun menuLibrosBuscar(): Int {
    println(
        "Seleccione la forma como desea realizar la búsqueda:\n" +
                "1. Nombre del libro\n" +
                "2. Nombre del autor\n" +
                "3. Numero de Edicion\n" +
                "4. Disponibilidad"
    )
    println("Ingrese su selección: ")
    return readLine()?.toInt()!!
}
fun menuLibrosBuscarA():Int{
    println(
        "Seleccione la forma como desea realizar la búsqueda:\n" +
                "1. Nombre del libro\n" +
                "2. Nombre del autor\n" +
                "3. Disponibilidad"
    )
    println("Ingrese su selección: ")
    return readLine()?.toInt()!!
}
fun menuLibrosEliminar(): Int{
    println("-------------------------------------------")
    println(
        "Seleccione la forma como desea realizar la búsqueda para eliminar:\n" +
                "1. Nombre del libro\n" +
                "2. Nombre del autor\n" +
                "3. Disponibilidad"
    )
    println("Ingrese su selección: ")
    return readLine()?.toInt()!!
}
fun continuar(): Int{
    println("Desea continuar: 1.Si / 2.No\nIngrese su respuesta:")
    var opcion = readLine()?.toInt()!!
    return opcion
}

/*Datos libro
* Nombre    String
* Autor     String
* Fecha     Date
* Costo     Decimal
* Edicion   Int
* Prestado  Boolean*/
class Libros(
){
    init {

    }
    /*Métodos para agregar nuevos libros*/
    fun agregarNombre(nombres: ArrayList<String>, nombre: String): ArrayList<String>{
        nombres.add(nombre)
        return nombres
    }
    fun agregarAutor(autores: ArrayList<String>, autor:String):ArrayList<String>{
        autores.add(autor)
        return autores
    }
    fun agregarFecha(fechas: ArrayList<Date>, fecha: Date):ArrayList<Date>{
        fechas.add(fecha)
        return fechas
    }
    fun agregarCosto(costos: ArrayList<Double>, costo: Double):ArrayList<Double>{
        costos.add(costo)
        return costos
    }
    fun agregarEdicion(ediciones: ArrayList<Int>, edicion: Int): ArrayList<Int> {
        ediciones.add(edicion)
        return ediciones
    }
    fun agregarDisponible(disponibles: ArrayList<Boolean>, disponible: Boolean): ArrayList<Boolean> {
        disponibles.add(disponible)
        return disponibles
    }
    /*Metodos para buscar Libros*/
    fun buscarNombre(nombres: ArrayList<String>, busqueda: String): Int{
        var ind: Int =-1
        nombres.forEachIndexed { indice: Int, valorActual:String ->
            if(valorActual.contentEquals(busqueda)){
                ind = indice
            }
        }
        return ind
    }
    fun buscarAutor(autores: ArrayList<String>, busqueda: String): ArrayList<Int>{
        var indices: ArrayList<Int> = arrayListOf()
        autores.forEachIndexed{indice: Int, valorActual: String ->
            if(valorActual.contentEquals(busqueda)){
                indices.add(indice)
            }
        }
        return indices
    }
    fun buscaEdicion(ediciones: ArrayList<Int>, busqueda:Int): ArrayList<Int>{
        var indices: ArrayList<Int> = arrayListOf()
        ediciones.forEachIndexed{indice: Int, valorActual: Int ->
            if(valorActual == busqueda){
                indices.add(indice)
            }
        }
        return indices
    }
    fun buscaDisponible(disponibles: ArrayList<Boolean>, busqueda: Boolean): ArrayList<Int>{
        var indices: ArrayList<Int> = arrayListOf()
        disponibles.forEachIndexed{indice: Int, valorActual: Boolean ->
            if(valorActual == busqueda){
                indices.add(indice)
            }
        }
        return indices
    }
    /*Mostrar resultados de busqueda*/
    fun mostrarUno(indice: Int, nombres: ArrayList<String>, autores: ArrayList<String>, fechas: ArrayList<Date>, costo: ArrayList<Double>,
    ediciones: ArrayList<Int>, disponibles: ArrayList<Boolean>,){
        println("-----------Libro encontrado-----------")
        println("Nombre del libro: ${nombres[indice]}\n" +
                "Nombre del autor: ${autores[indice]}\n" +
                "fecha de registro:  ${fechas[indice]}\n" +
                "costo del libro: ${costo[indice]}\n" +
                "# de edicion: ${ediciones[indice]}")
        if(disponibles[indice]){
            println("disponible: si")
        }else{
            println("disponible: no")
        }
        println("--------------------------------------")
    }
    fun mostrarVarios(indices: ArrayList<Int>, nombres: ArrayList<String>, autores: ArrayList<String>, fechas: ArrayList<Date>, costo: ArrayList<Double>,
                      ediciones: ArrayList<Int>, disponibles: ArrayList<Boolean>, ){
        indices.forEachIndexed { indice: Int, valorActual: Int ->
            println("-----------Libro encontrado-----------")
            println("Nombre del libro: ${nombres[valorActual]}\n" +
                    "Nombre del autor: ${autores[valorActual]}\n" +
                    "fecha de registro:  ${fechas[valorActual]}\n" +
                    "costo del libro: ${costo[valorActual]}\n" +
                    "# de edicion: ${ediciones[valorActual]}")
            if(disponibles[valorActual]){
                println("disponible: si")
            }else{
                println("disponible: no")
            }
            println("--------------------------------------")
        }
    }
    /*Funciones para actualizar los valores*/
    fun actualizarNombre(indice: Int, nombres:ArrayList<String>): ArrayList<String> {
        println(
            "Nombre de libro: Actual ( ${nombres.get(indice)}\n" +
                    "Ingrese el nuevo nombre: "
        )
        var nuevo: String = readLine().toString()
        if (nuevo.length > 0) {
            nombres[indice] = nuevo
        }
        return nombres

    }
    fun actualizarAutor(indice: Int, autores:ArrayList<String>): ArrayList<String>{
        println(
            "Nombre del autor Actual: ( ${autores.get(indice)}\n" +
                    "Ingrese el nuevo nombre: "
        )
        var nuevo: String = readLine().toString()
        if (nuevo.length > 0) {
            autores[indice] = nuevo
        }
        return autores

    }
    fun actualizarCosto(indice: Int, costos:ArrayList<Double>): ArrayList<Double>{
        println(
            "Valor actual del libro: ( ${costos.get(indice)}\n" +
                    "Ingrese el nuevo nombre: "
        )
        var nuevo: Double = readLine()?.toDouble()!!
        if (nuevo != costos.get(indice)) {
            costos[indice] = nuevo
        }
        return costos
    }
    fun actualizarDisponible(indice: Int, disponibles: ArrayList<Boolean>): ArrayList<Boolean>{
        println("Se encuentra el libro disponible: \n" +
                "Si\n" +
                "No\n" +
                "Seleccione la opcion correspondiente: ")
        var nuevo: String = readLine().toString()
        if(nuevo.equals("Si")|| nuevo.equals("SI")|| nuevo.equals("si")){
            disponibles[indice] = true
        }else{
            disponibles[indice] = false
        }
        return disponibles
    }
    fun seleccionarAutor(indices: ArrayList<Int>, autor: String, nombres: ArrayList<String>): Int{
        var seleccion: Int
        println("--------Autor:${autor} posee los siguientes libros")
        indices.forEachIndexed { indice: Int, valorActual: Int ->
            println("Libro: ${indice+1} : ${nombres[valorActual]}")
        }
        println("Ingrese la opcion que seleccionara: ")
        var num :Int = readLine()?.toInt()!!
        seleccion = indices[num-1]
        return seleccion
    }
    fun seleccionarDisponible(indices: ArrayList<Int>, disponible: Boolean, nombres:ArrayList<String>): Int{
        var seleccion: Int
        if(disponible){
            println("----------Los libros disponibles: ----------")
        }else{
            println("----------Los libros NO disponibles: ----------")
        }
        indices.forEachIndexed { indice: Int, valorActual: Int ->
            println("Libro: ${indice+1} : ${nombres[valorActual]}")
        }
        println("Ingrese la opcion que seleccionara: ")
        var num :Int = readLine()?.toInt()!!
        seleccion = indices[num-1]
        return seleccion
    }

    fun eliminarNombre(indice: Int, nombres: ArrayList<String>): ArrayList<String>{
        var cadena: String = nombres[indice]
        nombres.removeAt(indice)
        return nombres
    }
    fun eliminarAutor(indice: Int, autores: ArrayList<String>): ArrayList<String>{
        var cadena: String = autores[indice]
        autores.removeAt(indice)
        return autores
    }
    fun eliminarFecha(indice: Int, fechas: ArrayList<Date>): ArrayList<Date>{
        var cadena: Date = fechas[indice]
        fechas.removeAt(indice)
        return fechas
    }
    fun eliminarCosto(indice: Int, costos: ArrayList<Double>): ArrayList<Double>{
        var cadena: Double = costos[indice]
        costos.removeAt(indice)
        return costos
    }
    fun eliminarEdicion(indice: Int, ediciones: ArrayList<Int>): ArrayList<Int>{
        var cadena: Int = ediciones[indice]
        ediciones.removeAt(indice)
        return ediciones
    }
    fun eliminarDisp(indice: Int, disponibles: ArrayList<Boolean>): ArrayList<Boolean>{
        disponibles.removeAt(indice)
        return disponibles
    }
}
/*Datos Biblioteca
* Nombre    String
* Dirección String
* Fecha     Date
* */
class Bibliotecas(
){
    init {}
    fun agregarNombreB(nombres: ArrayList<String>, nombre: String): ArrayList<String>{
        nombres.add(nombre)
        return nombres
    }
    fun agregarDireccionB(direcciones: ArrayList<String>, direccion: String): ArrayList<String>{
        direcciones.add(direccion)
        return direcciones
    }
    fun agregarFechaB(fechas: ArrayList<Date>, fecha:Date): ArrayList<Date>{
        fechas.add(fecha)
        return fechas
    }
}
