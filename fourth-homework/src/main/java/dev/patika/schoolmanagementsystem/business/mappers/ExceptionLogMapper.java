package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.ExceptionLogCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.ExceptionLogDto;
import dev.patika.schoolmanagementsystem.entities.ExceptionLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExceptionLogMapper {

    ExceptionLogMapper INSTANCE = Mappers.getMapper(ExceptionLogMapper.class);

    List<ExceptionLogDto> toExceptionLogDtoList(List<ExceptionLog> exceptionLogs);

    ExceptionLogDto toExceptionLogDto(ExceptionLog exceptionLog);

    ExceptionLog fromExceptionLogCreateDto(ExceptionLogCreateDto exceptionLogCreateDto);

}
