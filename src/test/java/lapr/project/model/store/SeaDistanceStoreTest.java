package lapr.project.model.store;

import lapr.project.data.SeaDistanceStoreDb;
import lapr.project.model.SeaDistance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
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
        SeaDistanceStore seaDistanceStore = new SeaDistanceStore(null);

        seaDistanceStore.addSeaDistance(seaDistance1);
        seaDistanceStore.addSeaDistance(seaDistance2);

        Assertions.assertEquals(seaDistances, seaDistanceStore.getSeadists());
    }

    @Test
    void refresh() throws SQLException {
        SeaDistanceStoreDb seaDistanceStoreDb = mock(SeaDistanceStoreDb.class);
        SeaDistanceStore seaDistanceStore = new SeaDistanceStore(seaDistanceStoreDb);

        when(seaDistanceStoreDb.getAllSeaDistances()).thenReturn(seaDistances);
        seaDistanceStore.refresh();
        Assertions.assertEquals(seaDistances, seaDistanceStore.getSeadists());

        when(seaDistanceStoreDb.getAllSeaDistances()).thenThrow(new SQLException());
        Assertions.assertFalse(seaDistanceStore.refresh());
    }
}