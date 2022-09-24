package be.he2b.atlir5.skyjo.dbManagement.db;

import be.he2b.atlir5.skyjo.dbManagement.exception.DBException;
import java.sql.*;

/**
 *
 * @author mad8
 */
public class DBManager {
    private static final String source = "jdbc:sqlite:resources/sqliteDB/skyjo.db"; 
    private static Connection connection;

    /**
     * Retourne la connexion établie ou à défaut, l'établit
     * @return la connexion établie.
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBException
     */
    public static Connection getConnection() throws DBException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(source);
                //jdbc:sqlite:resources/sqliteDB/threeLayer.db
            } catch (SQLException ex) {
                throw new DBException("Connexion impossible: " + ex.getMessage());
            }
        }
        return connection;
    }

    /**
     * débute une transaction
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBException 
     */
    public static void startTransaction() throws DBException {
        try {

            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            throw new DBException("Impossible de démarrer une transaction: "+ex.getMessage());
        }
    }

    /**
     * débute une transaction en spécifiant son niveau d'isolation
     * Attention, ceci n'est pas implémenté sous Access!
     * (cette notion sera abordée ultérieurement dans le cours de bd)
     * @param isolationLevel
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBException
     */
    public static void startTransaction(int isolationLevel) throws DBException {
        try {
            getConnection().setAutoCommit(false);

            int isol = 0;
            switch (isolationLevel) {
                case 0 -> isol = java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
                case 1 -> isol = java.sql.Connection.TRANSACTION_READ_COMMITTED;
                case 2 -> isol = java.sql.Connection.TRANSACTION_REPEATABLE_READ;
                case 3 -> isol = java.sql.Connection.TRANSACTION_SERIALIZABLE;
                default -> throw new DBException("Degré d'isolation inexistant!");
            }
            getConnection().setTransactionIsolation(isol);
        } catch (SQLException ex) {
            throw new DBException("Impossible de démarrer une transaction: "+ex.getMessage());
        }
    }

    /**
     * valide la transaction courante
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBException
     */
    public static void validateTransaction() throws DBException {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new DBException("Impossible de valider la transaction: "+ex.getMessage());
        }
    }

    /**
     * annule la transaction courante
     * @throws be.he2b.atlir5.skyjo.dbManagement.exception.DBException
     */
    public static void cancelTransaction() throws DBException {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
            throw new DBException("Impossible d'annuler la transaction: "+ex.getMessage());
        }
    }
}
