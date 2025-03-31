package com.example.bookrecordService.domain.Board.Service;

import com.example.bookrecordService.domain.Board.Dto.BoardResponseDto;
import com.example.bookrecordService.domain.Board.Entity.Board;
import com.example.bookrecordService.domain.Board.Repository.BoardRepository;
import com.example.bookrecordService.domain.User.Entity.User;
import com.example.bookrecordService.domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
