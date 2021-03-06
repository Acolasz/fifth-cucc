package hu.kukutyin.fifth.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import hu.kukutyin.fifth.domain.Move;


@Service
public interface MoveService {
    List<Move> findAll();
}
