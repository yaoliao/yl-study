package com.yl.study.example6;

/**
 * Created by DELL on 2017/12/11.
 */
public enum MyEnum {

    MY_ENUM("enum") {
        public void method1(int a) {
            System.out.println("method1 " + a);
        }

        public int method2(int a) {
            return a;
        }

        @Override
        public Long method3(Long l) {
            return l;
        }
    },

    MY_ENUM_1("enum1") {
        public void method1(int a) {
            System.out.println("method1 " + a);
        }

        public int method2(int a) {
            return a;
        }

        @Override
        public Long method3(Long l) {
            return l;
        }
    };

    private String s;

    MyEnum(String s1){
        s = s1;
    }

    public void method1(int a) {
        System.out.println("method1 " + a);
    }

    public int method2(int a) {
        return a;
    }

    public abstract Long method3(Long l);

    public int newMethod(int a) {
        System.out.println("newMethod " + a);
        return a;
    }

    public String getContent(){
        return this.s;
    }

}
