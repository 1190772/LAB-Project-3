package lapr.project.model;

import java.time.LocalDateTime;

/**
 * Represents a Container Operation.
 *
 * @author David Magalhães 1201237
 */
public class ContainerOperation {

    /**
     * The User who performed the operation.
     */
    private final String user;

    /**
     * The date and time the operation was executed.
     */
    private final LocalDateTime dateTime;

    /**
     * The type of operation.
     */
    private final String type;

    /**
     * The ID of the container affected by the operation.
     */
    private final String containerID;

    /**
     * The ID of the cargo manifest on which the operation was performed.
     */
    private final int cargoManifestID;

    /**
     * Builds an instance of Container Operation receiving user, dateTime, type, containerID and cargoManifestID.
     *
     * @param user The User who performed the operation.
     * @param dateTime The date and time the operation was executed.
     * @param type The type of operation.
     * @param containerID The ID of the container affected by the operation.
     * @param cargoManifestID The ID of the cargo manifest on which the operation was performed.
     */
    public ContainerOperation(String user, LocalDateTime dateTime, String type, String containerID, int cargoManifestID) {
        this.user = user;
        this.dateTime = dateTime;
        this.type = type;
        this.containerID = containerID;
        this.cargoManifestID = cargoManifestID;
    }


    /**
     * Returns a textual representation of the Container Operation.
     *
     * @return textual representation of the Container Operation.
     */
    @Override
    public String toString() {
        return "ContainerOperation{" +
                "user='" + user + '\'' +
                ", dateTime=" + dateTime +
                ", type='" + type + '\'' +
                ", containerID='" + containerID + '\'' +
                ", cargoManifestID='" + cargoManifestID + '\'' +
                '}';
        }
}
