/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sparkjava.Person;

import com.example.sparkjava.config.DbConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Rojan Maharjan<rojan@moco.com.np>
 */
public class PersonServicesImpl implements PersonServices {

    private static final Logger logger = Logger.getLogger(PersonServicesImpl.class);

    @Override
    public List<Person> getAll() {
        String sql = "SELECT id,name,email FROM person";
        List<Person> persons = new ArrayList<>();
        try (Connection conn = DbConnection.getCon();
                PreparedStatement psList = conn.prepareStatement(sql);) {
            System.out.println("sql:" + psList.executeQuery());
            ResultSet rs = psList.executeQuery();
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                persons.add(person);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return persons;
    }

    @Override
    public List<Person> getbyId(String id) {
        String sql = "SELECT id,name,email FROM person WHERE id=?";
        List<Person> persons = new ArrayList<>();
        try (Connection conn = DbConnection.getCon();
                PreparedStatement psList = conn.prepareStatement(sql);) {
            psList.setString(1, id);
            ResultSet rs = psList.executeQuery();
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                persons.add(person);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return persons;
    }

    @Override
    public String createPerson(String reqBody) {
        int res = 0;
        Gson gson = new GsonBuilder().create();
        Person data = gson.fromJson(reqBody, Person.class);
        String sql = "INSERT INTO person (name,email) VALUES (?,?)";
        try (Connection conn = DbConnection.getCon();
                PreparedStatement psList = conn.prepareStatement(sql);) {
            psList.setString(1, data.getName());
            psList.setString(2, data.getEmail());
            res = psList.executeUpdate();
            if (res == 1) {
                return "Sucessfull added";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Oops! Something went wrong.";
    }

    @Override
    public String updatePerson(String reqBody) {
        int res = 0;
        Gson gson = new GsonBuilder().create();
        Person data = gson.fromJson(reqBody, Person.class);
        logger.info(data);
        String sql = "UPDATE person SET name=?, email=? WHERE id = ?";
        try (Connection conn = DbConnection.getCon();
                PreparedStatement psList = conn.prepareStatement(sql);) {
            psList.setString(1, data.getName());
            psList.setString(2, data.getEmail());
            psList.setInt(3, data.getId());
            res = psList.executeUpdate();
            if (res == 1) {
                return "Sucessfully updated";
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return "Oops! Something went wrong.";
    }

    @Override
    public String deletePerson(String id) {
        int res = 0;
        String sql = "DELETE FROM person WHERE id = ?";
        try (Connection conn = DbConnection.getCon();
                PreparedStatement psList = conn.prepareStatement(sql);) {
            psList.setString(1, id);
            res = psList.executeUpdate();
            if (res == 1) {
                return "Sucessfully deleted";
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return "Oops! Something went wrong.";
    }

}
