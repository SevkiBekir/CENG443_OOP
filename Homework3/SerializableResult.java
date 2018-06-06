//***************************************************************************************************************************************************

// TODO : imports

//***************************************************************************************************************************************************


//***************************************************************************************************************************************************

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class SerializableResult implements Serializable {
    //=================================================================================================================================================

    public int updateReturnValue;
    public List<String> columnNames;
    public List<List<Object>> rows;

    //=================================================================================================================================================

    public SerializableResult(int updateReturnValue) {
        this.updateReturnValue = updateReturnValue;
        this.columnNames = null;
        this.rows = null;
    }

    //=================================================================================================================================================

    public SerializableResult(ResultSet resultSet) throws Exception {
        this.updateReturnValue = -1;
        this.columnNames = new ArrayList<>();
        this.rows = new ArrayList<>();

        // TODO
        // ....

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnSize = resultSetMetaData.getColumnCount();
        for (int i = 0; i < columnSize; i++) {
            columnNames.add(resultSetMetaData.getColumnName(i));
        }
        while (resultSet.next()) {
            List<Object> row = new ArrayList<>();
            for (int i = 0; i < columnSize; i++) {
                row.add(resultSet.getObject(i));
            }

            rows.add(row);
        }


    }

    //=================================================================================================================================================
}

//***************************************************************************************************************************************************
