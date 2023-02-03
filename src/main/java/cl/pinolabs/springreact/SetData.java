package cl.pinolabs.springreact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class SetData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DataSource dataSource;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM roles");
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 0) {
                statement.executeUpdate("INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_MOD')");
                System.out.println("Se han creado los roles!");
            }else{
                System.out.println("No se ha añadido nada!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM test");
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 0) {
                statement.executeUpdate("INSERT INTO test (id, mensaje) VALUES (1, 'holanda')");
                System.out.println("---------- Se han creado el usuario test!");
            }else{
                System.out.println("No se ha creado el usuario test!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}