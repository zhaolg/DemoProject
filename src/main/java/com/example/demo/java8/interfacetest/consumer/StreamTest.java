package com.example.demo.java8.interfacetest.consumer;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lingang.zhao
 * @version 1.0
 * @date 2019/9/16 8:21 下午
 */
public class StreamTest {


    public static void main(String[] args) {


//        ArrayList<Person> rtn = Lists.newArrayList();
//
//        ArrayList<Student> students = Lists.newArrayList();
//        students.add(Student.builder().name("账上").build());
//        students.add(Student.builder().name("账上2").build());
//        students.add(Student.builder().name("账上3").build());
//        students.add(Student.builder().name("账上4").build());
//        students.add(Student.builder().name("账上5").build());
//
//        rtn.add(Person.builder().list(students).build());
//
//        ArrayList<Student> students2 = Lists.newArrayList();
//        students2.add(Student.builder().name("上").build());
//        students2.add(Student.builder().name("上2").build());
//        students2.add(Student.builder().name("上3").build());
//        students2.add(Student.builder().name("上4").build());
//        students2.add(Student.builder().name("上5").build());
//
//        rtn.add(Person.builder().list(students2).build());

    }


    @Data
    class Person {

        List<Student> list;

    }

    @Data
    class Student {

        private String name;

    }


}
