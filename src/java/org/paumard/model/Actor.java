/*
 * Copyright (C) 2014 José Paumard
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package org.paumard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author José
 */
public class Actor {
    public String lastName, firstName ;
    
    public Actor(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
    
    public String lastName() {
        return this.lastName ;
    }
    
    public String firstName() {
        return this.firstName ;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.lastName);
        hash = 67 * hash + Objects.hashCode(this.firstName);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actor other = (Actor) obj;
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return Objects.equals(this.firstName, other.firstName);
    }
    
    @Override
    public String toString() {
        return "Actor{" + "lastName=" + lastName + ", firstName=" + firstName + '}';
    }

    public static void main(String[] args) {

        List<User> userList = new ArrayList<User>();
        userList.add(new User("杨胜军", 23, '男'));
        userList.add(new User("杨胜军", 23, '男'));
        userList.add(new User("杨胜军", 22, '女'));
        userList.add(new User("杨胜军", 23, '女'));
        userList.add(new User("黄海明", 23, '男'));
        userList.add(new User("黄海明", 22, '男'));
        userList.add(new User("黄海明", 22, '女'));
        userList.add(new User("黄海明", 23, '女'));
        userList.add(new User("赵忠有", 23, '男'));
        userList.add(new User("赵忠有", 22, '男'));
        userList.add(new User("赵忠有", 22, '女'));
        userList.add(new User("赵忠有", 23, '女'));

        Map<String, List<User>> boxBarcodeMap2 = userList.stream().collect(Collectors.groupingBy(User::getName));
        Map<String, Map<Integer, List<User>>> boxBarcodeMap3 = userList.stream().collect(
                Collectors.groupingBy(User::getName, Collectors.groupingBy(User::getAge))
        );
        Map<String, Map<Integer, Long>> boxBarcodeMap = userList.stream().collect(
                Collectors.groupingBy(User::getName, Collectors.groupingBy(User::getAge, Collectors.counting()))
        );

        for (Map.Entry<String, Map<Integer, Long>> childMap : boxBarcodeMap.entrySet()) {
            System.out.print("姓名:" + childMap.getKey());
            for (Map.Entry<Integer, Long> item : childMap.getValue().entrySet()) {
                System.out.print(" \t 年龄:" + item.getKey() + " \t 数量:" + item.getValue());
            }
            System.out.print("\n");
        }
        for (Map.Entry<String, Map<Integer,  List<User>>> childMap : boxBarcodeMap3.entrySet()) {
            System.out.print("姓名:" + childMap.getKey());
            for (Map.Entry<Integer,  List<User>> item : childMap.getValue().entrySet()) {
                System.out.print(" \t 年龄:" + item.getKey() + " \t 数量:" + item.getValue());
            }
            System.out.print("\n");
        }
    }
    public static class User{


        public User(String name, int age, char sex) {
            super();
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
        private String name;
        private int age;
        private char sex;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public char getSex() {
            return sex;
        }
        public void setSex(char sex) {
            this.sex = sex;
        }
    }
}

