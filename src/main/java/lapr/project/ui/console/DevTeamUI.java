package lapr.project.ui.console;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable{

    public DevTeamUI()
    {

    }
    public void run()
    {
        System.out.println("\n");
        System.out.printf("Development Team:\n");
        System.out.printf("\t David Magalhães - 1201237@isep.ipp.pt \n");
        System.out.printf("\t José Silva - 1190772@isep.ipp.pt \n");
        System.out.printf("\t Ruben Silva - 1200546@isep.ipp.pt \n");
        System.out.printf("\t Samuel Pereira - 1201274@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
