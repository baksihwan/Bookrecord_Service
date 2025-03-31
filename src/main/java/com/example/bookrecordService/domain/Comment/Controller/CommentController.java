package com.example.bookrecordService.domain.Comment.Controller;

import com.example.bookrecordService.domain.Comment.Dto.CommentRequestDto;
import com.example.bookrecordService.domain.Comment.Dto.CommentResponseDto;
import com.example.bookrecordService.domain.Comment.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> saveComments(@RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.saveComments(commentRequestDto.getBoardId(),
                                                                            commentRequestDto.getUserId());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAllComments(@PageableDefault(size =20) Pageable pageable,
                                    @RequestBody CommentRequestDto commentRequestDto) {
        List<CommentResponseDto>  ListCommentResponseDto = commentService.findAllComments(pageable,
                                                                                        commentRequestDto.getBoardId());
        return new ResponseEntity<>(ListCommentResponseDto, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findCommentById(@PathVariable Long id) {
        CommentResponseDto commentResponseDto = commentService.findCommentById(id);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,
                                                            @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.updateComment(id, commentRequestDto.getBoardId(),
                                                                            commentRequestDto.getUserId());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
