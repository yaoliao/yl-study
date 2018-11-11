package com.yl.study.beancopy;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/26 0026.
 */
public class BeanCopy {

    private static final Logger logger = LoggerFactory.getLogger(BeanCopy.class);


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        Source source = new Source();
        source.setName("Tom");
        source.setAge(22);
        source.setList(list);
        source.setTime(new Date());

        source.setType(1);

        Target target = new Target();
        BeanUtils.copyProperties(source, target);

        System.out.println(target);

        //===========================

        // 字段映射
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Target target1 = mapper.map(source, Target.class);
        System.out.println(target1);

        // 集合映射
        List<Source> sourcelist = new ArrayList<>();
        sourcelist.add(source);
        sourcelist.add(source);
        List<Target> targetList = mapper.mapAsList(sourcelist, Target.class);
        System.out.println(targetList.toString());

        /*mapperFactory.getConverterFactory().registerConverter("TypeConvert", new BidirectionalConverter<Type, Integer>() {
            @Override
            public Integer convertTo(Type type, ma.glasnost.orika.metadata.Type<Integer> type2, MappingContext mappingContext) {
                return type.getType();
            }

            @Override
            public Type convertFrom(Integer integer, ma.glasnost.orika.metadata.Type<Type> type, MappingContext mappingContext) {
                return Type.valueOf(integer);
            }
        });

        mapperFactory.classMap(Source.class, Target.class)
                .fieldMap("type","typeEnum").converter("TypeConvert").add()
                .byDefault().register();

        MapperFacade mapper = mapperFactory.getMapperFacade();
        Target target1 = mapper.map(source, Target.class);
        System.out.println(target1);*/


    }

    enum Type {

        TYPE_1(1),
        TYPE_2(2);

        private Integer type;

        Type(Integer type) {
            this.type = type;
        }

        public static Type valueOf(Integer type) {
            if (type == null) return null;
            Type[] values = Type.values();
            return Arrays.stream(values).filter(e -> type.equals(e.getType())).findFirst().orElse(null);
        }

        public Integer getType() {
            return type;
        }

    }

    static class Source {

        private String name;
        private Integer age;
        private List<String> list;
        private Date time;
        private Integer type;

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

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Source{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", list=" + list +
                    ", time=" + time +
                    ", type=" + type +
                    '}';
        }
    }

    static class Target {
        private String name;
        private Integer age;
        private List<String> list;
        private Date time;
        private Type type;

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

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Target{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", list=" + list +
                    ", time=" + time +
                    ", type=" + type +
                    '}';
        }
    }

}
