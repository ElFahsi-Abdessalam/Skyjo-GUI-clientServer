package be.he2b.atlir5.skyjo.dbManagement.db;

import be.he2b.atlir5.skyjo.dbManagement.dbdto.UserDto;
import be.he2b.atlir5.skyjo.dbManagement.exception.DBException;
import be.he2b.atlir5.skyjo.dbManagement.exception.DtoException;
import be.he2b.atlir5.skyjo.dbManagement.dbSpecification.UserSpecification;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mad8
 */
public class UserDB {

    private static final String recordName = "USER";

    /**
     *
     * @return @throws be.he2b.atlir5.skyjo.dbManagement.exception.DtoException
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBException
     */
    public static List<UserDto> getAllUsers() throws DtoException, DBException {
        List<UserDto> users = getCollection(new UserSpecification(0));
        return users;
    }

    /**
     *
     * @param sel
     * @return
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBException
     */
    public static List<UserDto> getCollection(UserSpecification sel) throws DBException {
        List<UserDto> al = new ArrayList<>();
        try {
            String query = "Select (\"id\"),(\"name\") FROM \"main\".\"USERS\"";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getId() != 0) {
                where = where + " (\"id\") = ? ";
            }
            if (sel.getName() != null && !sel.getName().isEmpty()) {
                if (!where.isEmpty()) {
                    where = where + " AND ";
                }
                where = where + " (\"name\") like ? ";
            }

            if (where.length() != 0) {
                where = " where " + where + " order by (\"name\")";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getId() != 0) {
                    stmt.setInt(i, sel.getId());
                    i++;

                }
                if (sel.getName() != null && !sel.getName().isEmpty()) {
                    stmt.setString(i, sel.getName() + "%");
                    i++;
                }
            } else {
                query = query + " Order by (\"name\")";
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new UserDto(rs.getInt("id"), rs.getString("name")));
            }
        } catch (DtoException ex) {
            throw new DBException("Instanciation de " + recordName + " impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DBException("Instanciation de " + recordName + " impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    /**
     *
     * @param id
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBException
     */
    public static void deleteDb(int id) throws DBException {
        try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("delete from \"main\".\"USERS\" where (\"id\")=" + id);
        } catch (Exception ex) {
            throw new DBException(recordName + ": suppression impossible\n" + ex.getMessage());
        }
    }

    /**
     *
     * @param record
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBException
     */
    public static void updateDb(UserDto record) throws DBException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update \"main\".\"USERS\" set "
                    + "(\"NAME\")=?, "
                    + "where (\"id\")=?";
            update = connexion.prepareStatement(sql);
            update.setString(1, record.getName());
            update.setInt(2, record.getId());
            update.executeUpdate();
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName + ", modification impossible:\n" + ex.getMessage());
        }
    }

    /**
     *
     * @param  userName
     * @return
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBException
     */
    public static int insertDb(String userName) throws DBException {
        try {
            //int num = SequenceDB.getNextNum(SequenceDB.USERS);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement("INSERT INTO main.USERS (NAME) VALUES (?)");
            insert.setString(1, userName);
            insert.executeUpdate();
            List<UserDto> users = getCollection(new UserSpecification(userName));
            if(users.size()==1)return users.get(0).getId();
            else return 0;
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName + ": ajout impossible\n" + ex.getMessage());
        }
    }

    //faire une methode sur un attribut particulier un boolean active par exemple
}
