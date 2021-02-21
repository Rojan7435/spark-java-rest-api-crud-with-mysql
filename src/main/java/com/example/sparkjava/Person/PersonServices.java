/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sparkjava.Person;

import java.util.List;

/**
 *
 * @author Rojan Maharjan<rojan@moco.com.np>
 */
public interface PersonServices {

    public List<Person> getAll();

    public String createPerson(String reqBody);
    
    public List<Person> getbyId(String id);

    public String updatePerson(String reqBody);

    public String deletePerson(String id);
}
