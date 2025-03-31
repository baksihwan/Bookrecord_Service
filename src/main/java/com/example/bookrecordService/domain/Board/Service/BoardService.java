package com.example.bookrecordService.domain.Board.Service;

import com.example.bookrecordService.domain.Board.Dto.BoardResponseDto;
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
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardResponseDto saveBoard(Long userId) {
        // 보드 생성하는 법 (Post)

        User user = userRepository.findByIdOrElseThrow(userId);  // 1. 우선 유저 아이디를 불러온다 -->이유 : 유저안에서 보드생성이 이루어지기 때문
        Board board = new Board(user); // 2. 보드 엔티티를 불러와서 매개변수로 유저를 대입한다.
        Board saveBoard = boardRepository.save(board);
        return BoardResponseDto.toDto(saveBoard); // 3. 유저 객체 안에서 보드를 저장한다(save) != 보드 객체??  <--- 결국 보드객체 안에서 기능을 구현해야 하는 게 아닐까??
    }

    public List<BoardResponseDto> findAllBoard(Pageable pageable, Long userid){
        // 보드 전체조회하는 법(Get)
        Page<Board> board = boardRepository.findByIdByBoardIdOrderByCreatedAtDesc(pageable, userid);    // 스트링 기법을 이용해서 리스트화
        return board.stream().map(BoardResponseDto ::toDto).toList();
    }

    public BoardResponseDto findBoardById(Long id) {
        // 보드 단건조회하는 법(Get)
        Board board = boardRepository.findByIdOrElseThrow(id);   // 1. 보드아이디 예외처리하기
        return BoardResponseDto.toDto(board);
    }

    public BoardResponseDto updateBoard(Long id, Long userId){
        // 보드 수정하는 법(Patch)

        Board board = boardRepository.findByIdOrElseThrow(id); // 1. id, userId 예외처리하기
        User user = userRepository.findByIdOrElseThrow(userId);
        Board board = new Board(board, user);      // 2. Board 객체에 id, userId 저장
        Board saveBoard = boardRepository.save(board);
        return BoardResponseDto.toDto(saveBoard);
    }

    public void deleteBoard(Long id){
        Board board = boardRepository.findByIdOrElseThrow(id);
        boardRepository.delete(board);
    }




}
