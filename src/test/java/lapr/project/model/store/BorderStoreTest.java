package lapr.project.model.store;

import lapr.project.data.BorderStoreDb;
import lapr.project.model.Border;
import lapr.project.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BorderStoreTest {

    private final ArrayList<Border> borders;
    private final Border border1;
    private final Border border2;

    public BorderStoreTest() {
        borders = new ArrayList<>();
        border1 = new Border(new Country("CY", "CYP","Cyprus","Nicosia","Europe", 0.85,35.16666667,33.366667), new Country("MT", "MLT", "Malta","Valletta","Europe",0.44,35.88333333,14.5));
        border2 = new Border(new Country("MT", "MLT", "Malta","Valletta","Europe",0.44,35.88333333,14.5), new Country("GR","GRC","Greece","Athens","Europe",10.76,37.98333333,23.733333));
        borders.add(border1);
        borders.add(border2);
    }

    @Test
    void refresh() throws SQLException {
        BorderStoreDb borderStoreDb = mock(BorderStoreDb.class);

        when(borderStoreDb.getAllBorders(anyList())).thenReturn(borders);

        Assertions.assertEquals(borders, borderStoreDb.getAllBorders(new ArrayList<>()));
    }

    @Test
    void addBorderGetBorders() {
        BorderStore borderStore = new BorderStore();

        borderStore.addBorder(border1);
        borderStore.addBorder(border2);

        Assertions.assertEquals(borders, borderStore.getBorders());
    }
}