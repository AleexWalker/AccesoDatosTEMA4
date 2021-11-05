package exercicis

import javax.swing.JFrame
import java.awt.EventQueue
import java.awt.BorderLayout
import javax.swing.JPanel
import java.awt.FlowLayout
import java.sql.DriverManager
import javax.swing.JComboBox
import javax.swing.JButton
import javax.swing.JTextArea
import javax.swing.JLabel

class Finestra : JFrame() {

    init {
        // Sentències per a fer la connexió
        val url = "jdbc:sqlite:Rutes.sqlite"
        val conexion = DriverManager.getConnection(url)

        /*
        val statement = conexion.createStatement()
        val result = statement.executeQuery("SELECT * FROM institut")
        while (result.next()) {
            print("" + result.getInt(1) + "\t")
            println(result.getString(2))
        }
        result.close()
        result.close()
        */

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("JDBC: Visualitzar Rutes")
        setSize(450, 450)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        val panell2 = JPanel(BorderLayout())
        add(panell1, BorderLayout.NORTH)
        add(panell2, BorderLayout.CENTER)

        val llistaRutes = arrayListOf<String>()
        // Sentències per a omplir l'ArrayList amb el nom de les rutes
        val statement = conexion.createStatement()
        val result = statement.executeQuery("SELECT * FROM RUTES")

        while (result.next()) {
            llistaRutes.add(result.getString(2))
        }
        result.close()
        statement.close()

        val combo = JComboBox<String>(llistaRutes.toTypedArray())
        panell1.add(combo)
        val eixir = JButton("Eixir")
        panell1.add(eixir)
        val area = JTextArea()
        panell2.add(JLabel("Llista de punts de la ruta:"),BorderLayout.NORTH)
        panell2.add(area,BorderLayout.CENTER)

        var resultPuntos = statement.executeQuery("SELECT * FROM PUNTS")

        combo.addActionListener() {
            // Sentèncis quan s'ha seleccionat un element del JComboBox
            // Han de consistir en omplir el JTextArea
            area.text = ""

            if (combo.selectedIndex == 0)
                resultPuntos = statement.executeQuery("SELECT * FROM PUNTS WHERE num_r = 1")
                while (resultPuntos.next())
                    area.text += (resultPuntos.getInt(2).toString() + " " + resultPuntos.getString(3) + " " + resultPuntos.getDouble(4) + " " + resultPuntos.getDouble(5) + "\n")
            if (combo.selectedIndex == 1)
                resultPuntos = statement.executeQuery("SELECT * FROM PUNTS WHERE num_r = 2")
                while (resultPuntos.next())
                    area.text += (resultPuntos.getInt(2).toString() + " " + resultPuntos.getString(3) + " " + resultPuntos.getDouble(4) + " " + resultPuntos.getDouble(5) + "\n")
        }

        eixir.addActionListener(){
            // Sentències per a tancar la connexió i eixir
        }
    }
}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        Finestra().isVisible = true
    }
}

