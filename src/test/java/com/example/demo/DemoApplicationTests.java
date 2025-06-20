package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
class DemoApplicationTests {

    private static final String url = "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&useSSL=false";
    private static final String username = "root";
    private static final String password = "root";


    @Test
    void contextLoads() {
        String path = "D:\\software\\developmentTools\\Git\\gitee\\demo\\src\\main\\resources\\a.sql";
        URI uri = new File(path).toURI();
        Path path1 = Paths.get(uri);

        try (Connection con = DriverManager.getConnection(url, username, password);
             Statement stat = con.createStatement()
        ) {
            StringBuilder sql = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sql.append(line).append("\n");
                }

                stat.execute(sql.toString());

                System.out.println("Stored procedure created successfully");


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
