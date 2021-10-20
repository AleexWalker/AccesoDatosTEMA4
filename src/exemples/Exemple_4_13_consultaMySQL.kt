package exemples

import java.sql.DriverManager

fun main(args: Array<String>) {
    val url = "jdbc:mysql://89.36.214.106:3306/factura"
    val usuari = "factura"
    val password = "factura"

    val con = DriverManager.getConnection(url, usuari, password)

    val st = con.createStatement()
    val rs = st.executeQuery("SELECT * FROM poble")
    while (rs.next()) {
        print("" + rs.getInt(1) + "\t")
        println(rs.getString(2))
    }
    st.close()
    con.close()
}