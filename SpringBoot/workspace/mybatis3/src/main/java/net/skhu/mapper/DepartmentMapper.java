package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import net.skhu.dto.Department;

@Mapper
public interface DepartmentMapper {//학과 정보 DB작업

  @Select("SELECT * FROM department ORDER BY id")
  List<Department>	findAll();


}
