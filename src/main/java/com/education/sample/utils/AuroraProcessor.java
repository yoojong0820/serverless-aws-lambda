package com.education.sample.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuroraProcessor {

    private String endPoint = System.getenv("RDS_ENDPOINT");

    private String userName = System.getenv("RDS_USER");

    private String password = System.getenv("RDS_PASSWORD");

    private String schema;

    public AuroraProcessor(String schema) {
        this.schema = schema;
    }

    public Integer executeWithoutResult(String query) {
        Connection conn;
        int response = 200;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(buildConnectionUrl(), userName, password);
            Statement statement = conn.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            response = e.getErrorCode();
        } catch (ClassNotFoundException e) {
            response = -1;
        }
        return response;
    }

    // NOTE: Please note that the high-level column aliasing does not work. Please wrap with "SELECT * ..."
    public List<Map<String, String>> execute(String query) {
        Connection conn;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(buildConnectionUrl(), userName, password);
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            List<String> columns = new ArrayList<>(resultSetMetaData.getColumnCount());

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                columns.add(resultSetMetaData.getColumnName(i));
            }
            return populateResultSet(resultSet, columns);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String buildConnectionUrl() {
        return String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf-8", endPoint, 3306, schema);
    }

    private List<Map<String, String>> populateResultSet(ResultSet rs, List<String> columns) throws SQLException {
        List<Map<String, String>> out = new ArrayList<>();

        while (rs.next()) {
            Map<String, String> row = new HashMap<>(columns.size());
            for (String col : columns) {
                row.put(col, rs.getString(col));
            }
            out.add(row);
        }
        return out;
    }
}
