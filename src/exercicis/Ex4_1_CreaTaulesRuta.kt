package exercicis

import java.sql.DriverManager

fun main(args: Array<String>) {
    val url = "jdbc:sqlite:Rutes.sqlite"
    val connexio = DriverManager.getConnection(url)
    val statement = connexio.createStatement()

    val entradaRutesSQL = "CREATE TABLE RUTES(" +
            "num_r INTEGER PRIMARY KEY, " +
            "nom_r TEXT, " +
            "desn TEXT, " +
            "desn_ac TEXT " +
            ")"

    val entradaPuntsSQL = "CREATE TABLE PUNTS(" +
            "num_r INTEGER, " +
            "num_p INTEGER, " +
            "nom_p TEXT, " +
            "latitud REAL, " +
            "longitud REAL, " +
            "PRIMARY KEY (num_r , num_p), " +
            "FOREIGN KEY (num_r) REFERENCES RUTES (num_r) " +
            ")"

    statement.executeUpdate(entradaRutesSQL)
    statement.executeUpdate(entradaPuntsSQL)
    statement.close()
    connexio.close()
}