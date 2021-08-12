package jdbctest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.JDBCConnectionFactory;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.db.ActualTableName;
import org.mybatis.generator.internal.db.DatabaseIntrospector;

import java.sql.*;
import java.util.*;

import static org.mybatis.generator.internal.util.StringUtility.*;
import static org.mybatis.generator.internal.util.StringUtility.stringContainsSQLWildcard;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

@Slf4j
public class GeneratorMain {


    private DatabaseMetaData databaseMetaData;

    @Test
    public void testGetColumns() throws SQLException {
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
        jdbcConnectionConfiguration.setConnectionURL("jdbc:mysql://localhost:3306/test");
        jdbcConnectionConfiguration.setUserId("root");
        jdbcConnectionConfiguration.setPassword("h123");
        JDBCConnectionFactory connectionFactory = new JDBCConnectionFactory(jdbcConnectionConfiguration);

        Connection connection = connectionFactory.getConnection();
        databaseMetaData = connection.getMetaData();
        Map<ActualTableName, List<IntrospectedColumn>> columns = getColumns(null, null, "%");
        System.out.println(columns);
    }

    private Map<ActualTableName, List<IntrospectedColumn>> getColumns(String localCatalog,String localSchema,String localTableName) throws SQLException {
        Map<ActualTableName, List<IntrospectedColumn>> answer = new HashMap<ActualTableName, List<IntrospectedColumn>>();

        if (log.isDebugEnabled()) {
            String fullTableName = composeFullyQualifiedTableName(localCatalog, localSchema,
                    localTableName, '.');
            log.debug(getString("Tracing.1", fullTableName)); //$NON-NLS-1$
        }

        ResultSet rs = databaseMetaData.getColumns(localCatalog, localSchema,
                localTableName, "%"); //$NON-NLS-1$

        boolean supportsIsAutoIncrement = false;
        boolean supportsIsGeneratedColumn = false;
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        for (int i = 1; i <= colCount; i++) {
            if ("IS_AUTOINCREMENT".equals(rsmd.getColumnName(i))) { //$NON-NLS-1$
                supportsIsAutoIncrement = true;
            }
            if ("IS_GENERATEDCOLUMN".equals(rsmd.getColumnName(i))) { //$NON-NLS-1$
                supportsIsGeneratedColumn = true;
            }
        }

        while (rs.next()) {
            IntrospectedColumn introspectedColumn = new IntrospectedColumn();

//            introspectedColumn.setTableAlias(null);
            introspectedColumn.setJdbcType(rs.getInt("DATA_TYPE")); //$NON-NLS-1$
            introspectedColumn.setLength(rs.getInt("COLUMN_SIZE")); //$NON-NLS-1$
            introspectedColumn.setActualColumnName(rs.getString("COLUMN_NAME")); //$NON-NLS-1$
            introspectedColumn
                    .setNullable(rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable); //$NON-NLS-1$
            introspectedColumn.setScale(rs.getInt("DECIMAL_DIGITS")); //$NON-NLS-1$
            introspectedColumn.setRemarks(rs.getString("REMARKS")); //$NON-NLS-1$
            introspectedColumn.setDefaultValue(rs.getString("COLUMN_DEF")); //$NON-NLS-1$

            if (supportsIsAutoIncrement) {
                introspectedColumn.setAutoIncrement("YES".equals(rs.getString("IS_AUTOINCREMENT"))); //$NON-NLS-1$ //$NON-NLS-2$
            }

            if (supportsIsGeneratedColumn) {
                introspectedColumn.setGeneratedColumn("YES".equals(rs.getString("IS_GENERATEDCOLUMN"))); //$NON-NLS-1$ //$NON-NLS-2$
            }

            ActualTableName atn = new ActualTableName(
                    rs.getString("TABLE_CAT"), //$NON-NLS-1$
                    rs.getString("TABLE_SCHEM"), //$NON-NLS-1$
                    rs.getString("TABLE_NAME")); //$NON-NLS-1$

            List<IntrospectedColumn> columns = answer.get(atn);
            if (columns == null) {
                columns = new ArrayList<IntrospectedColumn>();
                answer.put(atn, columns);
            }

            columns.add(introspectedColumn);

            if (log.isDebugEnabled()) {
                log.debug(getString(
                        "Tracing.2", //$NON-NLS-1$
                        introspectedColumn.getActualColumnName(), Integer
                                .toString(introspectedColumn.getJdbcType()),
                        atn.toString()));
            }
        }

        closeResultSet(rs);

        return answer;
    }

    /**
     * Close result set.
     *
     * @param rs
     *            the rs
     */
    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // ignore
            }
        }
    }
}
