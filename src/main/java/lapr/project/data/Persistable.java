package lapr.project.data;

import lapr.project.utils.DatabaseConnection;

/**
 * @author nunocastro
 */
public interface Persistable {
    /**
     * Save an objet to the database.
     *
     * @param databaseConnection
     * @param object
     * @return Operation success.
     */
    boolean save(DatabaseConnection databaseConnection, Object object);

    /**
     * Delete an object from the database.
     *
     * @param databaseConnection
     * @param object
     * @return Operation success.
     */
    boolean delete(DatabaseConnection databaseConnection, Object object);

    // não faltará aqui uma operação para obter um objeto da base de dados?
}
