package de.fuchs_page.common;

import de.fuchs_page.common.db.DBVerbindung;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FuchsA on 03.11.2016.
 */
@ManagedBean(name="kategorie")
@SessionScoped
public class KategorieBean implements Serializable {

    private String query = "exec [Reporting].[uspGetAktuellerVerkaufAlleFilialenUndKategorien]";
    private ResultSet rs;
    private String outcome = "index";
    private String userInput = "";


    public List<String> getKategorieList() throws SQLException {

        DBVerbindung dbVerbindung = new DBVerbindung();
        Connection con = dbVerbindung.con;

        if(con==null)
            throw new SQLException("Can't get database connection");

        PreparedStatement preparedStatement = con.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        rs = preparedStatement.executeQuery();

        if(rs==null)
            throw new SQLException("Can't get raw");

        List<String> kategorien = new ArrayList<String>();

        while (rs.next()) {
            kategorien.add(rs.getRow() - 1, rs.getString(1));
        }
        dbVerbindung.schliesseVerbindung();
        return kategorien;
    }



    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String submit(){
        this.userInput = "The user has entered \""+this.userInput+" \"";
        return "";
    }

}
