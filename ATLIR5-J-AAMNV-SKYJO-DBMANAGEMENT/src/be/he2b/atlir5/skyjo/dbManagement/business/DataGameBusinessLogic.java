package be.he2b.atlir5.skyjo.dbManagement.business;

import be.he2b.atlir5.skyjo.dbManagement.db.DataGameDB;
import be.he2b.atlir5.skyjo.dbManagement.dbdto.DataGameDto;
import be.he2b.atlir5.skyjo.dbManagement.exception.DBException;
import be.he2b.atlir5.skyjo.dbManagement.dbSpecification.DataGameSpecification;
import java.util.Collection;
import java.util.List;

/**
 * The type Data game business logic.
 *
 * @author mad8
 */
public class DataGameBusinessLogic {

    /**
     * Insert an user in the database. Returns the user's id.
     *
     * @param dataGame the data game
     * @return the user's id.
     * @throws DBException the db exception
     */
    static int add(DataGameDto dataGame) throws DBException {
        return DataGameDB.insertDb(dataGame);
    }

    /**
     * Returns the unique user with the given mail.
     *
     * @param idGame the id game
     * @return the unique user with the given mail.
     * @throws DBException the db exception
     */
    static Collection<DataGameDto> findByGameID(int idGame) throws DBException {
        DataGameSpecification sel = new DataGameSpecification(idGame);
        Collection<DataGameDto> col = DataGameDB.getCollection(sel);
        return col; 
    }

    /**
     * Find by i ds data game dto.
     *
     * @param idGame the id game
     * @param idUser the id user
     * @return the data game dto
     * @throws DBException the db exception
     */
    static DataGameDto findByIDs(int idGame, int idUser) throws DBException {
        DataGameSpecification sel = new DataGameSpecification(idGame,idUser);
        Collection<DataGameDto> col = DataGameDB.getCollection(sel);
        if (col.size() == 1) {
            return col.iterator().next();
        } else {
            return null;
        }
    }

    /**
     * Returns a list of users with the given specifications.
     *
     * @param sel specifications (where clause)
     * @return a list of users with the given specifications.
     * @throws DBException the db exception
     */
    static List<DataGameDto> findBySel(DataGameSpecification sel) throws DBException {
        return DataGameDB.getCollection(sel);
    }

    /**
     * Returns a list of all users.
     *
     * @return a list of all users.
     * @throws DBException the db exception
     */
    static List<DataGameDto> findAll() throws DBException {
        DataGameSpecification sel = new DataGameSpecification(0);
        List<DataGameDto> col = DataGameDB.getCollection(sel);
        return col;
    }

    //ajouter les methodes avec une logique métier
}
