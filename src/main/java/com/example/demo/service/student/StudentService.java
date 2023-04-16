package com.example.demo.service.student;

import com.example.demo.pojo.vo.school.StudentVO;

import java.util.List;

/**
 * @author dongxu
 * @create 2023-04-09 下午9:05
 */
public interface StudentService {

    StudentVO getStudent();

    List<StudentVO> listStudent();
}
