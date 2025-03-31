package com.example.bookrecordService.domain.Board.Controller;

import com.example.bookrecordService.domain.Board.Dto.BoardRequestDto;
import com.example.bookrecordService.domain.Board.Dto.BoardResponseDto;
import com.example.bookrecordService.domain.Board.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> saveBoard(@RequestBody BoardRequestDto requestDto){
        BoardResponseDto boardResponseDto = boardService.saveBoard(requestDto);
        return ResponseEntity.ok(boardResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAllBoard(@RequestBody BoardRequestDto requestDto){
        List<BoardResponseDto> boardResponseDto = boardService.findAllBoard(requestDto);
        return ResponseEntity.ok(boardResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> findByIdBoard(@PathVariable Long id){
    BoardResponseDto boardResponseDto = boardService.findByIdBoard(id);
    return ResponseEntity.ok(boardResponseDto);
}

    @PatchMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id,
                                                        @RequestBody BoardRequestDto requestDto){
    BoardResponseDto boardResponseDto = boardService.updateBoard(id, requestDto);
    return ResponseEntity.ok(boardResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteByIdBoard(@PathVariable Long id){
    boardService.delete(id);
    }


