package com.example.bookrecordService.domain.Comment.Service;

import com.example.bookrecordService.domain.Comment.Dto.CommentResponseDto;
import com.example.bookrecordService.domain.Comment.Entity.Comment;
import com.example.bookrecordService.domain.Comment.Repository.CommentRepository;
import com.example.bookrecordService.domain.Board.Entity.Board;
import com.example.bookrecordService.domain.Board.Repository.BoardRepository;
import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public CommentResponseDto saveComments(Long boardId, Long userId) {

        Board board = boardRepository.findByIdOrElseThrow(boardId);
        User user = userRepository.findByIdOrElseThrow(userId);
        Comment comment = new Comment(board, user);
        Comment savedComment = commentRepository.save(comment);
        return CommentResponseDto.toDto(savedComment);

    }

    public List<CommentResponseDto> findAllComments(Pageable pageable,Long boardId) {

        Page<Comment> comment = boardRepository.findCommentsByBoardIdOrderByCreatedAtDesc(pageable, boardId);
        return comment.stream().map(CommentResponseDto::toDto).toList();

    }

    public CommentResponseDto findCommentById(Long id) {

        Comment comment = commentRepository.findCommentsByIdOrElseThrow(id);
        return CommentResponseDto.toDto(comment);

    }

    public CommentResponseDto updateComment(Long id, Long boardId, Long userId) {

        Comment comment = commentRepository.findCommentsByIdOrElseThrow(id);
        Board board = boardRepository.findByIdOrElseThrow(boardId);
        User user = userRepository.findByIdOrElseThrow(userId);
        Comment comments = new Comment(comment, board, user);
        Comment updatedComment = commentRepository.save(comments);
        return CommentResponseDto.toDto(updatedComment);

    }

    public void deleteCommentById(Long id) {

        Comment comment = commentRepository.findCommentsByIdOrElseThrow(id);
        commentRepository.delete(comment);

    }
}
