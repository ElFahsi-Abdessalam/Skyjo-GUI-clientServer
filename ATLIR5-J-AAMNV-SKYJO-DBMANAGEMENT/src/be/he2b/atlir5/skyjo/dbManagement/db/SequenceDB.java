package be.he2b.atlir5.skyjo.dbManagement.db;

import be.he2b.atlir5.skyjo.dbManagement.exception.DBException;

/**
 *
 * @author mad8
 */
public class SequenceDB {
    static final String USERS = "USERS";
    static final String GAMES = "GAMEID";

    public static synchronized int getNextNum(String sequence) throws DBException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            String query = "Update \"main\".\"sqlite_SEQUENCE\" set \"seq\" = \"seq\"+1 where \"name\" ='" + sequence + "'";
            java.sql.PreparedStatement update = connexion.prepareStatement(query);
            update.execute();
            java.sql.Statement stmt = connexion.createStatement();
            query = "Select (\"seq\") FROM \"main\".\"sqlite_SEQUENCE\" where (\"name\")='" + sequence + "'";
            java.sql.ResultSet rs = stmt.executeQuery(query);
            int nvId;
            if (rs.next()) {
                nvId = rs.getInt("seq");
                return nvId;
            } else {
                throw new DBException("Nouveau n° de séquence inaccessible!");
            }
        } catch (java.sql.SQLException eSQL) {
            throw new DBException("Nouveau n° de séquence inaccessible!\n" + eSQL.getMessage());
        }
    }
}
