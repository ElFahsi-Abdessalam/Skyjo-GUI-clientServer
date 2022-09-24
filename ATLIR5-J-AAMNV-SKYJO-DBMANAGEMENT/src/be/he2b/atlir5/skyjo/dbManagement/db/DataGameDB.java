package be.he2b.atlir5.skyjo.dbManagement.db;

import be.he2b.atlir5.skyjo.dbManagement.dbdto.DataGameDto;
import be.he2b.atlir5.skyjo.dbManagement.exception.DBException;
import be.he2b.atlir5.skyjo.dbManagement.exception.DtoException;
import be.he2b.atlir5.skyjo.dbManagement.dbSpecification.DataGameSpecification;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Data game db.
 *
 * @author mad8
 */
public class DataGameDB {
    private static final String recordName = "DATA_GAME";

    /**
     * Gets all game data.
     *
     * @return all game data
     * @throws DtoException the dto exception
     * @throws DBException  the db exception
     */
    public static List<DataGameDto> getAllGameData() throws DtoException, DBException {
        List<DataGameDto> users = getCollection(new DataGameSpecification(0));
        return users;
    }

    /**
     * Gets collection.
     *
     * @param sel the sel
     * @return collection
     * @throws DBException the db exception
     */
    public static List<DataGameDto> getCollection(DataGameSpecification sel) throws DBException {
        List<DataGameDto> al = new ArrayList<>();
        try {
            String query = "Select (\"ID_GAME\"),(\"ID_USER\"),(\"SCORE\") FROM \"main\".\"DATA_GAME\"";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getIdGame()!= 0) {
                where = where + " (\"ID_GAME\") = ? ";
            }
            if (sel.getIdUser()!= 0) {
                if (!where.isEmpty()) {
                    where = where + " AND ";
                }
                where = where + " (\"ID_USER\") = ? ";
            } else {
            }

            if (where.length() != 0) {
                where = " where " + where + " order by (\"ID_GAME\")";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdGame()!= 0) {
                    stmt.setInt(i, sel.getIdGame());
                    i++;

                }
                if (sel.getIdUser()!= 0) {
                    stmt.setString(i, sel.getIdUser() + "%");
                    i++;
                }
            } else {
                query = query + " Order by (\"ID_GAME\")";
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new DataGameDto(rs.getInt("\"ID_GAME\""),rs.getInt("\"ID_USER\""),rs.getInt("\"SCORE\"")));
            }
        } catch (DtoException ex) {
            throw new DBException("Instanciation de "+recordName+" impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DBException("Instanciation de "+recordName+" impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    /**
     * Insert db int.
     *
     * @param record the record
     * @return int
     * @throws DBException the db exception
     */
    public static int insertDb(DataGameDto record) throws DBException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement( "INSERT INTO main.DATA_GAME VALUES (?,?,?);");
            insert.setInt(1, record.getIdGame());            
            insert.setInt(2, record.getIdUser());
            insert.setInt(3, record.getScore());
            insert.executeUpdate();
            return record.getIdGame();
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName+": ajout impossible\n" + ex.getMessage());
        }
    }

    //faire une methode sur un attribut particulier un boolean active par exemple
}
