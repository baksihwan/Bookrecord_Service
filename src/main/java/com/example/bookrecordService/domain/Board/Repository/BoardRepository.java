package com.example.bookrecordService.domain.Board.Repository;

import com.example.bookrecordService.domain.Board.Entity.Board;
import com.example.bookrecordService.domain.Comment.Entity.Comment;
import com.example.bookrecordService.global.exception.ExceptionType;
import com.example.bookrecordService.global.exception.NotFoundByIdException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    default Board findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundByIdException(ExceptionType.BOARD_NOT_FOUND));
    }

    Page<Board> findBoardByUserIdOrderByCreatedAtDesc(Pageable pageable, Long userId);
    }

