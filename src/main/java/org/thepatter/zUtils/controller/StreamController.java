package org.thepatter.zUtils.controller;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cn.hutool.poi.excel.sax.AttributeName.t;

@RestController
@RequestMapping("/stream")
@Slf4j
public class StreamController {


    @GetMapping()
    public List stream() {
        //首先初始化输入列表
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("5");
        list.add("7");
        list.add("1");
        list.add("3");
        list.add("2");
        list.add("8");

        //开始执行操作
        List<Integer> list2 = list.stream().map(Integer::valueOf).filter(x -> {
            return x > 5;
        }).collect(Collectors.toList());

        return list2;
    }

    @GetMapping("/flatmap")
    public List flatMap() {
        //首先初始化输入列表
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        list1.add("1");
        list2.add("2");
        list3.add("3");
        list4.add("4");
        list4.add("5");
        List<List<String>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

        //开始执行操作
        List<String> listT = list.stream().flatMap(Collection::stream).collect(Collectors.toList());

        return listT;
    }

    @GetMapping("/unordered")
    public List unordered() {
        //首先初始化输入列表
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        list1.add("1");
        list2.add("3");
        list3.add("2");
        list4.add("5");
        list4.add("4");
        List<List<String>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

        //开始执行操作
        List<String> listT = list.stream().flatMap(Collection::stream).collect(Collectors.toList());

        List<String> listU = listT.stream().unordered().collect(Collectors.toList());

        return listU;
    }

    @GetMapping("/peek")
    public List peek() {
        //首先初始化输入列表
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        list1.add("1");
        list2.add("3");
        list3.add("2");
        list4.add("5");
        list4.add("4");
        List<List<String>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

        //开始执行操作
        List<String> listT = list.stream().flatMap(Collection::stream).peek(e -> System.out.println(e)).collect(Collectors.toList());

        return listT;
    }

    @GetMapping("/distinct")
    public List distinct() {
        //首先初始化输入列表
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        list1.add("1");
        list2.add("3");
        list3.add("3");
        list4.add("5");
        list4.add("4");
        List<List<String>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

        //开始执行操作
        List<String> listT = list.stream().flatMap(Collection::stream).peek(e -> System.out.println(e)).distinct().collect(Collectors.toList());

        return listT;
    }

    @GetMapping("/sorted")
    public List sorted() {
        //首先初始化输入列表
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        list1.add("1");
        list2.add("3");
        list3.add("3");
        list4.add("5");
        list4.add("4");
        List<List<String>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

        //开始执行操作
        List<String> listT = list.stream().flatMap(Collection::stream).sorted(String::compareTo).skip(1).limit(3).collect(Collectors.toList());

        return listT;
    }


}
