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
        val ruta = ficheroEntrada.readObject() as Ruta
        arrayRutas.add(ruta)
    } catch (eof : EOFException){
        ficheroEntrada.close()
    }

    for (i in arrayRutas.indices)
        val entradaRutasSQL = " INSERT INTO RUTES VALUES " + " nu"


    statement.executeUpdate(entradaRutasSQL)
    statement.close()
    connexio.close()
}
