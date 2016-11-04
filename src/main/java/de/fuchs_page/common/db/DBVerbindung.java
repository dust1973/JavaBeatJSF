package de.fuchs_page.common.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by FuchsA on 14.03.2016.
 */
public class DBVerbindung extends DbConfig {

    public Connection con;

    /**
     * Konstruktor stellt die Daten zum Verbindungsaufbau zusammen.
     */

    public DBVerbindung() {
        try {
            //Laedt den Datenbanktreiber
            Class.forName(DbConfig.TREIBER); //Beim Laden der JDBC-Treiberklasse, registriert diese sich beim JDBC-Treibermanager.
            //Stellt die Verbindung her
            con = DriverManager.getConnection(DbConfig.DBASE, DbConfig.DBUSER, DbConfig.DBPASS);

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC-Treiber nicht gefunden: " + e.toString());
        } catch (SQLException sqle) {
            System.out.println(sqle.toString());
        }

    }


    public void schliesseVerbindung() {
        try {
            con.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.toString());
        }
    }


}
