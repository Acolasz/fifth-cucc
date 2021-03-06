package hu.kukutyin.fifth.api.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.kukutyin.fifth.api.MoveServiceApi;
import hu.kukutyin.fifth.api.MoveServiceApiDelegate;
import hu.kukutyin.fifth.dao.MoveService;
import hu.kukutyin.fifth.domain.Move;
import hu.kukutyin.fifth.domain.Rate;

/**
 * see https://www.techiedelight.com/remove-elements-list-satisfies-predicate-java/
 */
@Slf4j
@Service
public class MoveServiceApiDelegateImpl implements MoveServiceApiDelegate {
    private static final Move EMPTY_MOVE = new Move().name("EMPTY");

    private MoveService moveService;

    @Autowired
    public MoveServiceApiDelegateImpl(MoveService moveService) {
        this.moveService = moveService;
    }


    /**
     * POST /move
     * Add a new Move to the store
     *
     * @param move A JSON object containing Move information (required)
     * @return Success (status code 200)
     * or Invalid input (status code 405)
     * or Internal server error (status code 500)
     * @see MoveServiceApi#create
     */
    @Override
    public ResponseEntity<Move> create(Move move) {
        return null;
    }

    /**
     * DELETE /move/{id}
     * delete a Move by id
     *
     * @param id Id of Move to return (required)
     * @return Success (status code 200)
     * or Invalid ID supplied (status code 400)
     * or Move not found (status code 404)
     * @see MoveServiceApi#delete
     */
    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return null;
    }

    /**
     * GET /move/all
     * All Moves
     *
     * @return Success (status code 200)
     * or Internal server error (status code 500)
     * @see MoveServiceApi#findAll
     */
    @Override
    public ResponseEntity<List<Move>> findAll() {
        return new ResponseEntity<>(moveService.findAll(), HttpStatus.OK);
    }

    /**
     * GET /move/{id}
     * return a Move by id
     *
     * @param id Id of Move to return (required)
     * @return Success (status code 200)
     * or Internal server error (status code 500)
     * @see MoveServiceApi#getById
     */
    @Override
    public ResponseEntity<Move> getById(Integer id) {
        return null;
    }

    /**
     * PUT /move/{id}
     * Update Move
     *
     * @param id   Id of product to return (required)
     * @param move A JSON object containing Move information (required)
     * @return Success (status code 200)
     * or Internal server error (status code 500)
     * @see MoveServiceApi#update
     */
    @Override
    public ResponseEntity<Move> update(Integer id, Move move) {
        return null;
    }

    /**
     * PUT /move/{id}/rate
     * Update Move Rate
     *
     * @param id   Id of Move to return (required)
     * @param rate A JSON object containing Move information (required)
     * @return Success (status code 200)
     * or Internal server error (status code 500)
     * @see MoveServiceApi#updateMoveRateById
     */
    @Override
    public ResponseEntity<Move> updateMoveRateById(Integer id, Rate rate) {
        return null;
    }
}
