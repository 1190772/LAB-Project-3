package lapr.project.model.store;

import lapr.project.model.SeaDistance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SeaDistanceStoreTest {

    private final ArrayList<SeaDistance> seaDistances;
    private final SeaDistance seaDistance1;
    private final SeaDistance seaDistance2;

    public SeaDistanceStoreTest() {
        seaDistances = new ArrayList<>();
        seaDistance1 = new SeaDistance("10358","246265",3673);
        seaDistance2 = new SeaDistance("10358","21863",3377);
        seaDistances.add(seaDistance1);
        seaDistances.add(seaDistance2);
    }

    @Test
    void addSeaDistanceGetSeaDistances() {
        SeaDistanceStore seaDistanceStore = new SeaDistanceStore();

        seaDistanceStore.addSeaDistance(seaDistance1);
        seaDistanceStore.addSeaDistance(seaDistance2);

        Assertions.assertEquals(seaDistances, seaDistanceStore.getSeadists());
    }

    @Test
    void refresh() {
        SeaDistanceStore seaDistanceStore = mock(SeaDistanceStore.class);

        when(seaDistanceStore.refresh()).thenReturn(true);

        Assertions.assertTrue(seaDistanceStore.refresh());
    }
}