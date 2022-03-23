package com.ssafy.vacsa.service;

import com.ssafy.vacsa.model.*;
import com.ssafy.vacsa.dto.*;
import com.ssafy.vacsa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    // private final을 사용해야 RequiredArgsConstructor 을 통해 초기화 됨
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;


    @Override
    public Long create(BoardDto boardDto) throws Exception {  // 게시글 생성

        //userid로 작성자 User 객체 받아오기
        User author = userRepository.findByUsername(boardDto.getAuthor()).get();

        // vote Entity 객체를 생성해서 dto에서 값 받아오기
        Board board = Board.builder()  // vote entity 객체에 vote dto 객체 값 전달
                .boardTitle(boardDto.getBoardTitle())
                .boardContent(boardDto.getBoardContent())
                .boardCreateTime(LocalDateTime.now())
                .author(author)
                .build();
        Long boardId = boardRepository.save(board).getBoardId();  // DB에 전달 받은 vote 정보 저장
        return boardId;
    }

    @Override
    public void delete(Long boardId) throws Exception {  // 게시글 삭제
        Optional<Board> board = boardRepository.findById(boardId);  // 파라미터로 받아온 id를 이용해서 삭제할 게시글 가져오기
        boardRepository.delete(board.get());  // 해당 게시글 DB에서 삭제
    }

    @Override
    public BoardDto detail(Long boardId,String username) throws Exception {   // 게시글 상세보기
        Board board = boardRepository.findById(boardId).get();  // 아이디로 해당 게시글 찾기
        User user = userRepository.findByUsername(username).get();

        return new BoardDto(board.getBoardId(),board.getBoardTitle(),board.getAuthor().getUsername(),board.getBoardContent()
                , LocalDate.from(board.getBoardCreateTime()),board.getAuthor()); // 해당 게시글 dto로 변환 후 리턴
    }

    @Override
    public List<BoardDto> getBoardList(String username) throws Exception {       //게시글 전체 목록 반환
        List<Board> list = boardRepository.findByOrderByBoardCreateTimeDesc(); //생성시간 내림차순으로 정렬 최신순으로
        List<BoardDto> dtoList =convertEntityListToDtoListWithUser(list,username);
        return dtoList;
    }

    public List<BoardDto> convertEntityListToDtoList(List<Board> entityList) throws Exception{    //엔티티리스트 -> dto리스트 함수화화
       List<BoardDto> dtoList = new ArrayList<>();
        for (Board entity: entityList) {
            BoardDto dto = new BoardDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<BoardDto> convertEntityListToDtoListWithUser(List<Board> entityList,String username) throws Exception{    //엔티티리스트 -> dto리스트 함수화화
        List<BoardDto> dtoList = new ArrayList<>();
        for (Board entity: entityList) {
            User author = entity.getAuthor();
            if(username!=null){
                User user = userRepository.findByUsername(username).get();
            }
            BoardDto dto = new BoardDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

}
