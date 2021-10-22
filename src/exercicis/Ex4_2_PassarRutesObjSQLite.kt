package exercicis

import java.io.EOFException
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.sql.DriverManager
import kotlin.test.assertContentEquals

fun main(args: Array<String>) {
    val ficheroEntrada = ObjectInputStream(FileInputStream("Rutes.obj"))
    val lectura = ficheroEntrada.readObject()
    val arrayRutas = arrayListOf<Ruta>()

    val url = "jdbc:sqlite:Rutes.sqlite"
    val connexio = DriverManager.getConnection(url)
    val statement = connexio.createStatement()

    try {
        while (true) {
            val ruta = ficheroEntrada.readObject() as Ruta
            arrayRutas.add(ruta)
        }
    } catch (eof : EOFException){
        ficheroEntrada.close()
    }
    println(arrayRutas[1].nom)

    for (i in 0 until arrayRutas.size) {
        val entradaRutasSQL = "INSERT into RUTES VALUES" + "(${i+1}," + "'${arrayRutas[i].nom}'," + "${arrayRutas[i].desnivell}," + "${arrayRutas[i].desnivellAcumulat})"
        if (i == 1)
            statement.executeUpdate(entradaRutasSQL)
        println(entradaRutasSQL)
        for (j in 0 until arrayRutas[i].llistaDePunts.size) {
            val entradaPuntosSQL = "INSERT into PUNTS VALUES" + "(${i+1}," + "${j+1}," + "'${arrayRutas[i].llistaDePunts[j].nom}'," + "${arrayRutas[i].llistaDePunts[j].coord.latitud}," + "${arrayRutas[i].llistaDePunts[j].coord.longitud})"
            if (i == 1)
                statement.executeUpdate(entradaPuntosSQL)
            println(entradaPuntosSQL)
        }
    }
    //println(entradaRutasSQL)

    statement.close()
    connexio.close()
}
