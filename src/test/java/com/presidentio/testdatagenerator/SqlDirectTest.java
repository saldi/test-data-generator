/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.presidentio.testdatagenerator;

import com.presidentio.testdatagenerator.model.Output;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SqlDirectTest extends AbstractSqlTest {

    @Override
    protected List<String> getSchemaResource() {
        return Arrays.asList("test-sql-direct-schema.json");
    }

    @Override
    protected void testResult(Output output) {
        try {
            testDbContent(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void testDbContent(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(id) FROM user");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        int count = resultSet.getInt(1);
        Assert.assertEquals(11, count);

        preparedStatement = connection.prepareStatement("SELECT count(id) FROM training");
        preparedStatement.execute();
        resultSet = preparedStatement.getResultSet();
        resultSet.next();
        count = resultSet.getInt(1);
        Assert.assertEquals(55, count);

        preparedStatement = connection.prepareStatement("SELECT count(id) FROM exercise");
        preparedStatement.execute();
        resultSet = preparedStatement.getResultSet();
        resultSet.next();
        count = resultSet.getInt(1);
        Assert.assertEquals(275, count);
    }
}
