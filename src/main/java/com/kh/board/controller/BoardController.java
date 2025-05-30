package com.kh.board.controller;

import com.kh.board.controller.dto.request.BoardRequest;
import com.kh.board.controller.dto.response.BoardResponse;
import com.kh.board.entity.Board;
import com.kh.board.entity.Member;
import com.kh.board.mapper.BoardMapper;
import com.kh.board.service.BoardService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //게시글목록을 가져오는 api
    @GetMapping
    public ResponseEntity<List<BoardResponse.SimpleDTO>> getBoardList() {
        List<Board> boardList = boardService.findAll();

        List<BoardResponse.SimpleDTO> result = new ArrayList<>();
        for (Board board : boardList) {
            result.add(BoardResponse.SimpleDTO.formEntity(board));

        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> setBoard(BoardRequest.SimpleDTO dto, MultipartFile upfile) {


        String upFile = upfile.getOriginalFilename();

        Board board = dto.toEntity();

        System.out.println("BOARDID:"+ board.getTitle());


        int result = boardService.insertBoard(board,upFile);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("게시글 등록 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 등록 실패");
        }

    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse.DetailDTO> getBoardDetail(@PathVariable("boardId") int boardId) {

        BoardResponse.DetailDTO DDto = boardService.selectBoard(boardId);

        System.out.println(DDto.getTitle());
        System.out.println(DDto.getContents());
        System.out.println(DDto.getMember_email());
        System.out.println(DDto.getUpFile());
        System.out.println(DDto.getNickName());
        return new ResponseEntity<>(DDto, HttpStatus.OK);
    }

}
