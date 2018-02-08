package com.yl.study.example2;

/**
 * @author DELL
 * @date 2017/11/7
 */
public class Test {

    public static void main(String[] args) {

        Programmer programmer = new Programmer.ProgrammerBuilder().setFirstName("F").setLastName("L")
                .setCity("City").setZipCode("0000A").setAddress("Street 39")
                .setLanguages(new String[]{"bash", "Perl"}).setProjects(new String[]{"Linux kernel"}).build();

        System.out.println(programmer.toString());

    }

}
