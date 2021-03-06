package hu.kukutyin.fifth.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.apache.ibatis.annotations.Mapper;

import hu.kukutyin.fifth.domain.Move;

@Repository
@Mapper
public interface MoveMapper {
    List<Move> findAll();
}
