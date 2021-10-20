package exemples

import java.sql.DriverManager

fun main(args: Array<String>) {
    val url = "jdbc:sqlite:proveta.sqlite"

    val	con = DriverManager.getConnection(url)

    val st = con.createStatement ()
    st.executeUpdate("CREATE TABLE T2 (c1 TEXT)")
    st.close()

    con.close()
}