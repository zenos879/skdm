//package com.cctv.project.noah.system.controller;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
///**
// * @author by yanhao
// * @Classname ApiTestController
// * @Description TODO
// * @Date 2019/11/15 2:55 下午
// */
//
//
//@RestController
//@RequestMapping("API")
//@Api(value = "学生管理",tags = "学生信息接口")
//public class ApiTestController extends BaseController{
//
//    @RequestMapping("student/{studentId}")
//    @ApiOperation(value = "获取学生对象", notes = "调用接口返回学生JSON", httpMethod = "POST")
//    @ApiImplicitParam(name = "studentId",value = "1",dataType = "int",paramType = "form",required=true)
//    public Student getStudentById(@PathVariable("studentId") Long studentId){
//        Student student=new Student();
//        student.setClassId(1L);
//        student.setStudentName("闫浩");
//        student.setAdmissionTime(new Date());
//        student.setGraduationTime(new Date());
//        student.setStudentAge("11");
//        student.setStudentSex("男");
//        student.setStudentId(1L);
//        return student;
//    }
//
//
//
//    @ApiOperation(value = "获取学生对象", notes = "调用接口返回学生JSON", httpMethod = "POST")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "studentId",value = "1",dataType = "int",paramType = "path",required=true),
//            @ApiImplicitParam(name = "studentName",value = "闫浩",dataType = "String",paramType = "query",required=true),
//            @ApiImplicitParam(name = "startTime",value = "2019-01-01 12:00:00",dataType = "Integer",paramType = "query"),
//            @ApiImplicitParam(name = "endTime",value = "2019-09-01 12:00:00",dataType = "Integer",paramType = "query")
//    })
//    @RequestMapping("student/getStudent")
//    public Student getStudent(Long studentId,String studentName,Date startTime,Date endTime){
//        Student student=new Student();
//        student.setClassId(1L);
//        student.setStudentName("闫浩");
//        student.setAdmissionTime(new Date());
//        student.setGraduationTime(new Date());
//        student.setStudentAge("11");
//        student.setStudentSex("男");
//        student.setStudentId(1L);
//        return student;
//    }
//
//
//}
