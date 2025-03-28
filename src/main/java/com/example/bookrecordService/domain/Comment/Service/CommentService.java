package com.example.bookrecordService.domain.Comment.Service;

import com.example.bookrecordService.domain.Comment.Dto.CommentResponseDto;
import com.example.bookrecordService.domain.Comment.Entity.Comment;
import com.example.bookrecordService.domain.Comment.Repository.CommentRepository;
import com.example.bookrecordService.domain.Friend.Entity.Friend;
import com.example.bookrecordService.domain.Friend.Repository.FriendRepository;
import com.example.bookrecordService.domain.User.Repository.UserRepository;
import com.example.bookrecordService.domain.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    public CommentResponseDto saveComments(Long friendId, Long userId) {
        Friend friend = friendRepository.findByIdOrElseThrow(friendId);
        User user = userRepository.findByIdOrElseThrow(userId);
        Comment comment = new Comment(friend, user);
        Comment savedComment = commentRepository.save(comment);
        return CommentResponseDto.toDto(savedComment);
    }

    public List<CommentResponseDto> findAllComments(Pageable pageable,Long friendId) {
        Page<Comment> comment = friendRepository.findCommentsByFriendIdOrderByCreatedAtDesc(pageable, friendId);

        return comment.stream().map(CommentResponseDto::toDto).toList();
    }

    public CommentResponseDto findCommentById(Long id) {
        Comment comment = commentRepository.findCommentsByIdOrElseThrow(id);

        return CommentResponseDto.toDto(comment);
    }

    public CommentResponseDto updateComment(Long id, Long friendId, Long userId) {
        Comment comment = commentRepository.findCommentsByIdOrElseThrow(id);
        Friend friend = friendRepository.findByIdOrElseThrow(friendId);
        User user = userRepository.findByIdOrElseThrow(userId);
        Comment comments = new Comment(comment, friend, user);
        Comment updatedComment = commentRepository.save(comments);
        return CommentResponseDto.toDto(updatedComment);
    }

    public void deleteCommentById(Long id) {
        Comment comment = commentRepository.findCommentsByIdOrElseThrow(id);
        commentRepository.delete(comment);






    }
}
