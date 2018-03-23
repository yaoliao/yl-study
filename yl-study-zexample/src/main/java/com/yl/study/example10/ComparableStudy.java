package com.yl.study.example10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by DELL on 2018/2/28.
 */
public class ComparableStudy implements Comparable<ComparableStudy> {

    private Long time;

    public ComparableStudy(Long time) {
        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public int compareTo(ComparableStudy o) {
        Long l = this.time - o.getTime();
        if (l > 0) {
            return 1;
        } else if (l < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {

        /*List<ComparableStudy> list = new ArrayList<>();
        ComparableStudy c1 = new ComparableStudy(2L);
        ComparableStudy c2 = new ComparableStudy(1L);
        ComparableStudy c3 = new ComparableStudy(3L);
        ComparableStudy c4 = new ComparableStudy(0L);
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);

        Collections.sort(list);
        list.forEach(e -> System.out.println(e.getTime()));

        list.add(1,new ComparableStudy(10L));
        list.forEach(e -> System.out.println(e.getTime()));*/



    }
}
