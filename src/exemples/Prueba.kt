package exemples

import exercicis.Ruta
import java.io.EOFException
import java.io.FileInputStream
import java.io.ObjectInputStream

    fun main(args: Array<String>) {
        val ficheroLecturaObjeto = ObjectInputStream(FileInputStream("Rutes.obj"))
        try {
            while (true){
                val lectura = ficheroLecturaObjeto.readObject() as Ruta
                println("Nombre: " + lectura.nom)
                println("Desnivel: " + lectura.desnivell)
                println("Desnivel Acumulado: " + lectura.desnivellAcumulat)
                val puntos = lectura.llistaDePunts
                for (i in 1..puntos.lastIndex){
                    println("\tNombre Punto: " + lectura.getPuntNom(i) + " (" + lectura.getPuntLatitud(i) + ") "+ " (" + lectura.getPuntLongitud(i) + ")")
                }
                println("\n")
            }
        } catch (eof : EOFException){
            ficheroLecturaObjeto.close()
        }
    }
