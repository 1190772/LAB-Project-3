package lapr.project.model;

import lapr.project.model.shared.SQL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class SQLTest {

    @Test
    public void startDataBaseTest(){
        SQL sql = mock(SQL.class);
        when(sql.scriptInTextFile("scriptSQL/startDataBase(us108).sql")).thenReturn(true);
        Assertions.assertTrue(sql.scriptInTextFile("scriptSQL/startDataBase(us108).sql"));
    }
}
 