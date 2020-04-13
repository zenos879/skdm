package com.cctv.project.noah.task;

import org.springframework.stereotype.Component;

/**
 * @author by yanhao
 * @Classname DemoTask
 * @Description TODO
 * @Date 2019/10/17 9:59 上午
 */
@Component("demoTask")
public class DemoTask {

    public void sayHi(){
        System.out.println("Hello Word");
    }

    public void sayName(String name){
        System.out.println("Hello My Name is "+name);
    }

    public void sayDetail(String name,Integer age){
        System.out.println("Hello My Name is "+name +" age is "+ age);
    }
}
