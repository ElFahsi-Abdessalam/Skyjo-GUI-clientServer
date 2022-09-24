package be.he2b.atlir5.skyjo.dbManagement.db;

import be.he2b.atlir5.skyjo.dbManagement.dbdto.DataUserDto;
import be.he2b.atlir5.skyjo.dbManagement.dbdto.UserDto;
import be.he2b.atlir5.skyjo.dbManagement.dbSpecification.DataUserSpecification;
import be.he2b.atlir5.skyjo.dbManagement.exception.DBException;
import be.he2b.atlir5.skyjo.dbManagement.exception.DtoException;
import be.he2b.atlir5.skyjo.dbManagement.dbSpecification.UserSpecification;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Data user db.
 *
 * @author mad8
 */
public class DataUserDB {

    private static final String recordName = "DATA_USER";

    /**
     * Gets all users.
     *
     * @return @throws be.he2b.atlir5.skyjo.dbManagement.exception.DtoException
     * @throws DtoException the dto exception
     * @throws DBException  the db exception
     */
    public static List<DataUserDto> getAllUsers() throws DtoException, DBException {
        List<DataUserDto> users = getCollection(new DataUserSpecification(0));
        return users;
    }

    /**
     * Insert db int.
     *
     * @param record the record
     * @return int
     * @throws DBException the db exception
     */
    public static int insertDb(DataUserDto record) throws DBException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement( "INSERT INTO main.DATA_USER VALUES (?,?,?,?);");
            insert.setString(1, Integer.toString(record.getIdUser()));
            insert.setString(2, Integer.toString(record.getNbrWins()));
            insert.setString(3, Integer.toString(record.getNbrGames()));
            insert.setString(4, Integer.toString(record.getLowestScore()));
            insert.executeUpdate();
            return record.getIdUser();
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName+": ajout impossible\n" + ex.getMessage());
        }
    }

    /**
     * Gets collection.
     *
     * @param sel the sel
     * @return collection
     * @throws DBException the db exception
     */
    public static List<DataUserDto> getCollection(DataUserSpecification sel) throws DBException {
        List<DataUserDto> al = new ArrayList();
        try {
            String query = "Select * FROM \"main\".\"DATA_USER\"";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = " ";
            if (sel.getIdUser()!= 0) {
                where = where + " id_User = (?) ";
            }
            if (where.length() != 0) {
                where = " where " + where + " order by id_User";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdUser() != 0) {
                    stmt.setString(i, Integer.toString(sel.getIdUser()));
                    i++;
                }
            } else {
                query = query + " Order by id_User";
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new DataUserDto(rs.getInt("id_User"), rs.getInt("nbr_games"), rs.getInt("lowest_score"), rs.getInt("nbr_wins")));
            }
        } catch (DtoException ex) {
            throw new DBException("Instanciation de " + recordName + " impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DBException("Instanciation de " + recordName + " impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    /**
     * Update win player.
     *
     * @param userId the user id
     * @throws DBException the db exception
     */
    public static void updateWinPlayer(int userId) throws DBException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            String sql = "Update \"main\".\"DATA_USER\" set(\"nbr_Wins\")=(\"nbr_Wins\")+1 where (\"id_USER\")=?";
            update = connexion.prepareStatement(sql);
            update.setString(1, Integer.toString(userId));
            update.executeUpdate();
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName + ", modification impossible:\n" + ex.getMessage());
        }
    }

    /**
     * Update played games.
     *
     * @param idUser the id user
     * @param score  the score
     */
    public static void updatePlayedGames(int idUser, int score) {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            String sql = "Update \"main\".\"DATA_USER\" set (\"nbr_games\")=(\"nbr_games\")+1 where (\"id_USER\")=?";
            update = connexion.prepareStatement(sql);
            update.setString(1, Integer.toString(idUser));
            update.executeUpdate();
            sql = "Update \"main\".\"DATA_USER\" set (\"lowest_Score\")=(?) where (\"id_USER\")=? AND (\"lowest_Score\")>(?)";
            update = connexion.prepareStatement(sql);
            update.setString(1, Integer.toString(score));
            update.setString(2, Integer.toString(idUser));
            update.setString(3, Integer.toString(score));
            update.executeUpdate();
        } catch (DBException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
