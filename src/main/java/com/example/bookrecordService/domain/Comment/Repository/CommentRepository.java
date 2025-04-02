package com.example.bookrecordService.domain.Comment.Repository;

import com.example.bookrecordService.domain.Comment.Entity.Comment;
import com.example.bookrecordService.global.exception.ExceptionType;
import com.example.bookrecordService.global.exception.NotFoundByIdException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findCommentsByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()-> new NotFoundByIdException(ExceptionType.COMMENT_NOT_FOUND));
    }

    Page<Comment> findCommentsByFriendIdOrderByCreatedAtDesc(Pageable pageable, Long boardId);
}
