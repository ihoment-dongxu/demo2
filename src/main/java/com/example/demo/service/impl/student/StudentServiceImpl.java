package com.example.demo.service.impl.student;

import com.example.demo.assembler.school.StudentConvert;
import com.example.demo.pojo.dto.school.StudentDTO;
import com.example.demo.pojo.vo.school.StudentVO;
import com.example.demo.service.student.StudentService;
import com.google.common.collect.Lists;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dongxu
 * @create 2023-04-09 下午9:06
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public StudentVO getStudent() {
        return StudentConvert.INSTANCE.convert(getStudentDTO());
    }

    @Override
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 1))
    public List<StudentVO> listStudent() {
        List<StudentDTO> studentList = Lists.newArrayList(getStudentDTO());
        return StudentConvert.INSTANCE.convert(studentList);
    }

    private StudentDTO getStudentDTO() {
        // 模拟查询数据库
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("小张宇");
        studentDTO.setAge(18);
        studentDTO.setClassName("电子信息4班");
        studentDTO.setTeacherName("董旭");
        return studentDTO;
    }

}
