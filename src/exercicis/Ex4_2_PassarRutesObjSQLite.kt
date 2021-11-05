package exercicis

import java.io.EOFException
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.sql.DriverManager
import kotlin.test.assertContentEquals

fun main(args: Array<String>) {
    val ficheroEntrada = ObjectInputStream(FileInputStream("Rutes.obj"))
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

    for (i in 0 until arrayRutas.size) {
        statement.executeUpdate("INSERT into RUTES VALUES" + "(${i+1}," + "'${arrayRutas[i].nom}'," + "${arrayRutas[i].desnivell}," + "${arrayRutas[i].desnivellAcumulat})")
        for (j in 0 until arrayRutas[i].llistaDePunts.size) {
            statement.executeUpdate("INSERT into PUNTS VALUES" + "(${i+1}," + "${j+1}," + "'${arrayRutas[i].llistaDePunts[j].nom}'," + "${arrayRutas[i].llistaDePunts[j].coord.latitud}," + "${arrayRutas[i].llistaDePunts[j].coord.longitud})")
        }
    }
    //println(entradaRutasSQL)

    statement.close()
    connexio.close()
}
