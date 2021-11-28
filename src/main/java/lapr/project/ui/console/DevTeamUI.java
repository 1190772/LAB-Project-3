package lapr.project.ui.console;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable {

    public DevTeamUI() {

    }

    public void run() {
        System.out.println("\n");
        System.out.print("Development Team:\n");
        System.out.print("\t David Magalhães - 1201237@isep.ipp.pt \n");
        System.out.print("\t José Silva - 1190772@isep.ipp.pt \n");
        System.out.print("\t Ruben Silva - 1200546@isep.ipp.pt \n");
        System.out.print("\t Samuel Pereira - 1201274@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
