package lapr.project.controller;

import oracle.ucp.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PositionContainersTest {
    List<Pair<String, Double>> containers;
    PositionContainersController.Coordinates max;
    PositionContainersController.Coordinates centerMassOriginal;
    double shipMass;
    String[] containerName = {"BICU1234561", "NIKR1352462", "ADIJ6543223", "FILZ6543214", "BICZ1231235", "NIKJ1111116", "ADIJ2222227", "FILR4564568", "BICJ1212129", "BICJ4444445", "NIKJ5555556", "ADIZ6666667", "FILR7777778", "BICR8888889", "NIKJ1111116", "ADIJ2222227", "FILR4564568", "BICJ1212129", "BICJ4444445", "NIKJ5555556", "ADIZ6666667", "FILR7777778", "BICR8888889", "BICU1234561", "NIKR1352462", "ADIJ6543223", "FILZ6543214", "BICZ1231235", "NIKJ1111116", "ADIJ2222227", "FILR4564568", "BICJ1212129", "BICJ4444445", "NIKJ5555556", "ADIZ6666667", "FILR7777778", "BICR8888889", "NIKJ1111116", "ADIJ2222227", "FILR4564568", "BICJ1212129", "BICJ4444445", "NIKJ5555556", "ADIZ6666667", "FILR7777778", "BICR8888889", "BICU1234561", "NIKR1352462", "ADIJ6543223", "FILZ6543214", "BICZ1231235", "NIKJ1111116", "ADIJ2222227", "FILR4564568", "BICJ1212129", "BICJ4444445", "NIKJ5555556", "ADIZ6666667", "FILR7777778", "BICR8888889", "NIKJ1111116", "ADIJ2222227", "FILR4564568", "BICJ1212129", "BICJ4444445", "NIKJ5555556", "ADIZ6666667", "FILR7777778", "BICR8888889", "BICU1234561", "NIKR1352462", "ADIJ6543223", "FILZ6543214", "BICZ1231235", "NIKJ1111116", "ADIJ2222227", "FILR4564568", "BICJ1212129", "BICJ4444445", "NIKJ5555556", "ADIZ6666667", "FILR7777778", "BICR8888889", "NIKJ1111116", "ADIJ2222227", "FILR4564568", "BICJ1212129", "BICJ4444445", "NIKJ5555556", "ADIZ6666667", "FILR7777778", "BICR8888889", "BICU1234561", "NIKR1352462", "ADIJ6543223", "FILZ6543214", "BICZ1231235", "NIKJ1111116", "ADIJ2222227", "FILR4564568", "BICJ1212129"};
    double[] containerWeight = {3, 6, 3, 7, 3, 7, 3, 8, 3, 1, 6, 8, 5, 8, 8, 3, 1, 6, 8, 5, 8, 8, 5, 8, 8, 3, 1, 6, 8, 5, 8, 3, 6, 3, 7, 3, 7, 3, 8, 3, 1, 6, 8, 5, 8, 8, 3, 1, 6, 8, 5, 8, 8, 5, 8, 8, 3, 1, 6, 8, 5, 8, 3, 6, 3, 7, 3, 7, 3, 8, 3, 1, 6, 8, 5, 8, 8, 3, 1, 6, 8, 5, 8, 8, 5, 8, 8, 3, 1, 6, 8, 5, 8, 3, 6, 3, 7, 3, 7, 3, 8, 3, 1, 6, 8, 5, 8, 8, 3, 1, 6, 8, 5, 8, 8, 5, 8, 8, 3, 1, 6, 8, 5, 8, 3, 6, 3, 7, 3, 7, 3, 8, 3,};

    public PositionContainersTest() {
        containers = new ArrayList<>();
        for (int i = 0; i < containerName.length; i++)
            containers.add(new Pair<>(containerName[i], containerWeight[i] * 100));
        int total = 0;
        for (int i = 0; i < containerName.length; i++) {
            total += containerWeight[i];
        }
        max = new PositionContainersController.Coordinates(13, 6, 3);
        centerMassOriginal = new PositionContainersController.Coordinates(2, 2, 0);
        shipMass = 1067000;
    }

    @Test
    void positionContainer() {
        Assertions.assertEquals("Original Center of Mass:Coordinates{x=2.0, y=2.0, z=0.0}\n" +
                "Final Center of Mass:Coordinates{x=2.0516042780748664, y=1.976292335115865, z=0.030837789661319073}\n" +
                "Difference in positioning of the ceneter of mass in meters:Coordinates{x=0.31478609625668597, y=-0.05784670231728928, z=0.07524420677361854}\n" +
                "Position of containers:\n" +
                "Level 0:\n" +
                "BICJ1212129 ADIJ6543223 BICJ4444445 BICJ1212129 null null \n" +
                "NIKR1352462 ADIJ2222227 NIKJ1111116 FILR4564568 null null \n" +
                "ADIZ6666667 BICR8888889 FILR7777778 NIKJ1111116 null null \n" +
                "NIKR1352462 BICJ1212129 BICR8888889 BICJ4444445 null null \n" +
                "NIKJ5555556 FILR4564568 FILR7777778 BICJ1212129 null null \n" +
                "ADIJ2222227 ADIJ2222227 ADIJ6543223 NIKJ5555556 null null \n" +
                "ADIJ6543223 NIKR1352462 FILZ6543214 BICJ1212129 ADIZ6666667 BICJ4444445 \n" +
                "null null BICU1234561 BICZ1231235 BICJ1212129 ADIJ6543223 \n" +
                "null null NIKJ5555556 NIKJ1111116 BICU1234561 FILR4564568 \n" +
                "null null ADIZ6666667 NIKR1352462 BICJ4444445 FILZ6543214 \n" +
                "null null FILR4564568 NIKJ5555556 ADIJ2222227 BICZ1231235 \n" +
                "null null NIKJ1111116 FILR7777778 BICR8888889 NIKJ5555556 \n" +
                "null null BICZ1231235 BICJ4444445 FILR7777778 FILZ6543214 \n" +
                "\n" +
                "Level 1:\n" +
                "null null null null null null \n" +
                "null BICJ1212129 FILR7777778 FILR4564568 null null \n" +
                "BICR8888889 FILR4564568 BICZ1231235 ADIJ2222227 null null \n" +
                "BICJ4444445 ADIJ2222227 FILZ6543214 FILR7777778 null null \n" +
                "ADIJ2222227 NIKJ1111116 BICU1234561 ADIZ6666667 null null \n" +
                "NIKJ1111116 ADIZ6666667 ADIJ2222227 FILR7777778 null null \n" +
                "BICR8888889 NIKJ1111116 ADIJ6543223 FILZ6543214 ADIZ6666667 NIKJ5555556 \n" +
                "null null BICZ1231235 FILR4564568 BICR8888889 BICJ4444445 \n" +
                "null null FILR7777778 NIKR1352462 FILR4564568 NIKJ5555556 \n" +
                "null null BICR8888889 NIKJ1111116 BICJ1212129 BICR8888889 \n" +
                "null null BICU1234561 ADIJ2222227 ADIZ6666667 NIKJ1111116 \n" +
                "null null BICJ1212129 BICU1234561 null null \n" +
                "null null null null null null \n" +
                "\n" +
                "Level 2:\n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "null null null null null null \n" +
                "\n" +
                "\n" +
                "Original mass:1067000.0 kg\n" +
                "Final mass:1122000.0 kg\n", new PositionContainersController().positionContainers(containers, max, centerMassOriginal, shipMass));
    }
}
