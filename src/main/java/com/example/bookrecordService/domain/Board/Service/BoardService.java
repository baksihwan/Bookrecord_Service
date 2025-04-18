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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardResponseDto saveBoard(Long userId, String title, String contents) {
        // 보드 생성하는 법 (Post)
        User user = userRepository.findByIdOrElseThrow(userId);  // 1. 우선 유저 아이디를 불러온다 -->이유 : 유저안에서 보드생성이 이루어지기 때문
        Board board = new Board(title,contents);   // 2. 보드 엔티티를 불러와서 매개변수로 유저를 대입한다.
        board.setUser(user);
        Board saveBoard = boardRepository.save(board);  // 3. 유저 객체 안에서 보드를 저장한다(save)
        return BoardResponseDto.toDto(saveBoard);
    }

    public List<BoardResponseDto> findAllBoard(Pageable pageable, Long userId){
        // 보드 전체조회하는 법(Get)
        Page<Board> board = boardRepository.findBoardByUserIdOrderByCreatedAtDesc(pageable, userId);
        List<BoardResponseDto> responseDtoList = board.getContent().stream().map(BoardResponseDto::toDto).collect(Collectors.toList());
        // 스트링 기법을 이용해서 리스트화
        return responseDtoList;
    }

    public BoardResponseDto findBoardById(Long id) {
        // 보드 단건조회하는 법(Get)
        Board findboard = boardRepository.findByIdOrElseThrow(id); // 1. 보드아이디 예외처리하기
        User wrtier = findboard.getUser();
        return BoardResponseDto.toDto(findboard);
    }

    public BoardResponseDto updateBoard(Long id){
        // 보드 수정하는 법(Patch)
        Board findboard = boardRepository.findByIdOrElseThrow(id); // 1. id, userId 예외처리하기
        Board saveBoard = boardRepository.save(findboard);
        return BoardResponseDto.toDto(saveBoard);
    }

    public void deleteBoard(Long id){
        // 보드 삭제하는 법(Delete)
        Board board = boardRepository.findByIdOrElseThrow(id);  // 1. 아이디 예ㅚ처리
        boardRepository.delete(board); // 2. delete 기능
    }
}
