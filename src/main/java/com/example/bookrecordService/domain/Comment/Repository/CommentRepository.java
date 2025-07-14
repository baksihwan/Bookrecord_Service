package com.example.bookrecordService.domain.Comment.Repository;

import com.example.bookrecordService.Exception.ExceptionType;
import com.example.bookrecordService.Exception.NotFoundByIdException;
import com.example.bookrecordService.domain.Comment.Entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findCommentsByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()-> new NotFoundByIdException(ExceptionType.COMMENT_NOT_FOUND));
    }

    Page<Comment> findCommentsByBoardIdOrderByCreatedAtDesc(Pageable pageable, Long boardId);
}
