package com.example.demo.assembler.school;

import com.example.demo.pojo.dto.school.StudentDTO;
import com.example.demo.pojo.vo.school.StudentVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author dongxu
 * @create 2023-04-09 下午8:50
 */
@Mapper
public interface StudentConvert {

    StudentConvert INSTANCE = Mappers.getMapper(StudentConvert.class);

    StudentVO convert(StudentDTO bean);

    List<StudentVO> convert(List<StudentDTO> bean);
}
