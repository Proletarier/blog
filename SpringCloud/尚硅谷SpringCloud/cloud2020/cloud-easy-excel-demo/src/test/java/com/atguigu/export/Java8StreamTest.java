package com.atguigu.export;

import org.junit.Test;
import org.omg.CORBA.INTERNAL;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SpringBootTest
public class Java8StreamTest {


    //使用Stream中的静态方法：of()、iterate()、generate()
    @Test
    public void test() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6); //创建
        stream.forEach(System.out::print);

        System.out.println("------------------------------");
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(6); //
        stream2.forEach(System.out::println); // 0 2 4 6 8 10

        System.out.println("------------------------------");
        Stream<Double> stream3 = Stream.generate(Math::random).limit(2); //随机生成对象
        stream3.forEach(System.out::println);
    }

    //使用 BufferedReader.lines() 方法，将每行内容转成流
    @Test
    public void test2() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\wh\\Desktop\\新建文本文档 (2).txt"));
        Stream<String> lineStream = reader.lines();
        lineStream.forEach(System.out::println);
    }

    @Test //使用 Pattern.splitAsStream() 方法，将字符串分隔成流
    public void test3() throws FileNotFoundException {
        Pattern pattern = Pattern.compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);
    }


    @Test //映射
    public void test4() throws FileNotFoundException {
        List<String> list = Arrays.asList("a,b,c", "1,2,3");

        //将每个元素转成一个新的且不带逗号的元素
        Stream<String> s1 = list.stream().map(s -> s.replaceAll(",", ""));
        s1.forEach(System.out::println); // abc  123

        Stream<String> s3 = list.stream().flatMap(s -> {
            //将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        });
        s3.forEach(System.out::println); // a b c 1 2 3
    }

    //排序
    @Test
    public void test5() {
        List<String> list = Arrays.asList("aa", "ff", "dd");
        //String 类自身已实现Compareable接口
        list.stream().sorted().forEach(System.out::println);// aa dd ff

        Student s1 = new Student("aa", 10);
        Student s2 = new Student("bb", 20);
        Student s3 = new Student("aa", 30);
        Student s4 = new Student("dd", 40);
        List<Student> studentList = Arrays.asList(s1, s2, s3, s4);

       //自定义排序：先按姓名升序，姓名相同则按年龄升序
        studentList.stream().sorted(
                (o1, o2) -> {
                    if (o1.getName().equals(o2.getName())) {
                        return o1.getAge() - o2.getAge();
                    } else {
                        return o1.getName().compareTo(o2.getName());
                    }
                }
        ).forEach(System.out::println);
    }

    @Test //peek：如同于map，能得到流中的每一个元素。但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值
    public void test6() throws FileNotFoundException {
        Student s1 = new Student("aa", 10);
        Student s2 = new Student("bb", 20);
        List<Student> studentList = Arrays.asList(s1, s2);

        studentList.stream()
                .peek(o -> o.setAge(100))
                .forEach(System.out::println);

    }


    class Student {


        private String name;

        private Integer age;

        public Student(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }


}
