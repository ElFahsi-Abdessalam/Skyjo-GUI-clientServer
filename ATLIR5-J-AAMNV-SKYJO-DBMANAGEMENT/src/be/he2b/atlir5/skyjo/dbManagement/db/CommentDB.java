package be.he2b.atlir5.skyjo.dbManagement.db;

import be.he2b.atlir5.skyjo.dbManagement.dbSpecification.CommentSpecification;
import be.he2b.atlir5.skyjo.dbManagement.dbdto.CommentDto;
import be.he2b.atlir5.skyjo.dbManagement.exception.DBException;
import be.he2b.atlir5.skyjo.dbManagement.exception.DtoException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jj
 */
public class CommentDB {

    private static final String recordName = "COMMENT_SECTION";

    public static List<CommentDto> getCollection(CommentSpecification spec) throws DBException {
        List<CommentDto> comsDto = new ArrayList<>();
        try {
            var query = "Select (\"id\"),(\"comment\") FROM \"main\".\"COMMENT_SECTION\"";
            var dbConnexion = DBManager.getConnection();
            PreparedStatement stmnt;
            var where = "";
            if (spec.getId() != 0) {
                where = where + " (\"id\") = ? ";
            }
            if (where.length() != 0) {
                query = query + where;
                stmnt = dbConnexion.prepareStatement(query);
                var index = 0;
                if (spec.getId() != 0) {
                    stmnt.setInt(index, spec.getId());
                    index++;
                }
            } else {
                stmnt = dbConnexion.prepareStatement(query);
            }
            var queryResult = stmnt.executeQuery();
            while(queryResult.next()){
                comsDto.add(new CommentDto(queryResult.getInt("id_user"),queryResult.getString("comment")));
            }

        } catch (DBException ex) {
            throw new DBException("Instanciation de " + recordName + " impossible:\nDTOException: " + ex.getMessage());
        }catch(SQLException eSQL){
            throw new DBException("Instanciation de " + recordName + " impossible:\nSQLException: " + eSQL.getMessage());
        }
        return comsDto;
    }
    
    

}
