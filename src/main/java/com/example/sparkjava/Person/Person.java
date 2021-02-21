/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sparkjava.Person;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Rojan Maharjan<rojan@moco.com.np>
 */
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("serial")
public class Person {

   
    private int id;
    
    private String name;
    
    private String email;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
    }
    
}
