package com.yl.study.leetcode;

/**
 * @author yaoliao
 * @date 2018/10/19
 * @time 0:16
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class CallByValue {


//    private static User user = null;
//    private static User stu = null;

    /**
     * 交换两个对象
     *
     * @param x
     * @param y
     */
    public static void swap(User x, User y) {
        User temp = x;
        x = y;
        y = temp;
    }


    public static void main(String[] args) {
        User user = new User("user", 26);
        User stu = new User("stu", 18);
        System.out.println("调用前user的值：" + user.toString());
        System.out.println("调用前stu的值：" + stu.toString());

        //swap(user, stu);

        User temp = user;
        user = stu;
        stu = temp;

        System.out.println("调用后user的值：" + user.toString());
        System.out.println("调用后stu的值：" + stu.toString());

    }



    public static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

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

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
