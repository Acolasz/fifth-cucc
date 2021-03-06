package hu.kukutyin.fifth.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.kukutyin.fifth.dao.mapper.MoveMapper;
import hu.kukutyin.fifth.domain.Move;

@Service
public class MoveServiceImpl implements MoveService {

    private MoveMapper moveMapper;

    @Autowired
    public MoveServiceImpl(MoveMapper moveMapper) {
        this.moveMapper = moveMapper;
    }

    @Override
    public List<Move> findAll() {
        return moveMapper.findAll();
    }

}
