package be.he2b.atlir5.skyjo.dbManagement.business;

import be.he2b.atlir5.skyjo.dbManagement.db.DBManager;
import be.he2b.atlir5.skyjo.dbManagement.db.SequenceDB;
import be.he2b.atlir5.skyjo.dbManagement.dbdto.DataGameDto;
import be.he2b.atlir5.skyjo.dbManagement.dbdto.DataUserDto;
import be.he2b.atlir5.skyjo.dbManagement.dbdto.UserDto;
import be.he2b.atlir5.skyjo.dbManagement.exception.DBBusinessException;
import be.he2b.atlir5.skyjo.dbManagement.exception.DBException;
import be.he2b.atlir5.skyjo.dbManagement.exception.DtoException;
import be.he2b.atlir5.skyjo.dbManagement.dbSpecification.UserSpecification;
import java.util.Collection;
import java.util.List;

/**
 * The type Admin model.
 *
 * @author mad8
 */
public class AdminModel implements AdminFacade {
    @Override
    public List<UserDto> getUsers() throws DBBusinessException {
        try {
            DBManager.startTransaction();
            List<UserDto> col = UserBusinessLogic.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DBException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DBBusinessException("Liste des Users inaccessible! \n" + msg);
            }
        }
    }

    /**
     *
     * @param userId
     * @return user
     * @throws DBBusinessException
     */
    @Override
    public UserDto getUser(int userId) throws DBBusinessException {
        try {
            DBManager.startTransaction();
            UserDto user = UserBusinessLogic.findById(userId);
            DBManager.validateTransaction();
            return user;
        } catch (DBException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DBBusinessException("Liste des Users getUser inaccessible! \n" + msg);
            }
        }
    }

    /**
     *
     * @param userName
     * @return user
     * @throws DBBusinessException
     */
    @Override
    public UserDto getUser(String userName) throws DBBusinessException {
        try {
            DBManager.startTransaction();
            UserDto user = UserBusinessLogic.findByName(userName);
            return user;
        } catch (DBException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DBBusinessException("Liste des Users    inaccessible! \n" + msg);
            }
        }
    }

    /**
     * Gets user id.
     *
     * @param userName the user mail
     * @return the user id
     * @throws DBBusinessException the db business exception
     */
    public int getUserId(String userName) throws DBBusinessException {
        UserDto ret = this.getUser(userName);
        if (ret == null) {
            return addUser(userName);
        }
        return ret.getId();
    }

    /**
     * Gets selected users.
     *
     * @param sel the sel
     * @return the selected users
     * @throws DBBusinessException the db business exception
     */
    public static Collection<UserDto> getSelectedUsers(UserSpecification sel) throws DBBusinessException {
        try {
            DBManager.startTransaction();
            Collection<UserDto> col = UserBusinessLogic.findBySel(sel);
            DBManager.validateTransaction();
            return col;
        } catch (DBException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DBBusinessException("Liste des Users get user inaccessible! \n" + msg);
            }
        }
    }

    /**
     *
     * @param userName
     * @return an int
     * @throws DBBusinessException
     */
    @Override
    public int addUser(String userName) throws DBBusinessException {
        try {
            DBManager.startTransaction();
            int b = UserBusinessLogic.add(userName);
            System.out.println(b);
            int c = DataUserBusinessLogic.add(b);
            System.out.println(c);
            DBManager.validateTransaction();
            return b;
        } catch (DBException ex) {
            try {
                DBManager.cancelTransaction();
                throw new DBBusinessException(ex.getMessage());
            } catch (DBException ex1) {
                throw new DBBusinessException(ex1.getMessage());
            }
        }
    }

    /**
     * 
     * @param user
     * @throws DBBusinessException
     */
    @Override
    public void updateUser(UserDto user) throws DBBusinessException {
        try {
            DBManager.startTransaction();
            UserBusinessLogic.update(user);
            DBManager.validateTransaction();
        } catch (DBException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DBBusinessException("Mise à jour de User impossible! \n" + msg);
            }
        }
    }

    /**
     * Update user scores.
     *
     * @param usersId   the users id
     * @param userScore the user score
     * @throws DBException         the db exception
     * @throws DBBusinessException the db business exception
     */
    public void updateUserScores(List<Integer> usersId, List<Integer> userScore) throws DBException, DBBusinessException {
        try {
            DBManager.startTransaction();
            for (int i = 0; i < usersId.size(); i++) {
                DataUserBusinessLogic.updateUserStat(usersId.get(i), userScore.get(i));
            }
            DBManager.validateTransaction();
        } catch (DBException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DBBusinessException("Mise à jour des résultats des utilisateurs impossible! \n" + msg);
            }
        }
    }

    /**
     * Add win.
     *
     * @param Userid the userid
     * @throws DBBusinessException the db business exception
     */
    public void addWin(int Userid) throws DBBusinessException {
        try {
            DBManager.startTransaction();
            DataUserBusinessLogic.addWin(Userid);
            DBManager.validateTransaction();
        } catch (DBException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DBBusinessException("Mise à jour des résultats des winner des gains impossible! \n" + msg);
            }
        }
    }

    /**
     * Add data from game.
     *
     * @param usersId    the users id
     * @param usersScore the users score
     * @param srvID      the srv id
     * @throws DBBusinessException the db business exception
     */
    public void addDataFromGame(List<Integer> usersId, List<Integer> usersScore, int srvID) throws DBBusinessException {
        try {
            DBManager.startTransaction();
            for (int i = 0; i < usersId.size(); i++) {
                try {
                    DataGameBusinessLogic.add(new DataGameDto(srvID, usersId.get(i), usersScore.get(i)));
                } catch (DtoException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            DBManager.validateTransaction();
        } catch (DBException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DBBusinessException("Mise à jour des résultats impossible! \n" + msg);
            }
        }
    }

    /**
     * Gets next num.
     *
     * @return the next num
     */
    public static int getNextNum() {
        int ret = 0;
        try {
            ret = SequenceDB.getNextNum("GAMEID");
        } catch (DBException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    /**
     * Gets user stats.
     *
     * @param idUser the id user
     * @return List
     * @throws DBBusinessException the db business exception
     */
    public DataUserDto getUserStats(int idUser) throws DBBusinessException {
        try {
            DBManager.startTransaction();
            DataUserDto user = DataUserBusinessLogic.findById(idUser);
            DBManager.validateTransaction();
            return user;
        } catch (DBException eDB) {
            String msg = eDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DBException ex) {
                msg = ex.getMessage() + "\n" + msg;
                System.out.println(msg);
            }
        }
        return null;
    }

}
