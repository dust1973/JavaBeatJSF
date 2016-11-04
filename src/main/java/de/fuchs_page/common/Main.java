package de.fuchs_page.common;

import java.sql.SQLException;

/**
 * Created by FuchsA on 03.11.2016.
 */
public class Main {


    public static void main(String[] args) throws SQLException {

        KategorieBean kategorien = new KategorieBean();
        System.out.println(kategorien.getKategorieList());

    }

}
