package com.kh.board.controller.dto.response;

import com.kh.board.entity.Board;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class BoardResponse {

    @Getter
    @Setter
    @Builder
    public static class SimpleDTO {

        private Long board_id;
        private String member_email;
        private String title;
        private LocalDateTime created_at;

        public static SimpleDTO formEntity(Board board) {
            return SimpleDTO.builder()
                    .board_id(board.getBoardId())
                    .member_email(board.getMemberEmail())
                    .title(board.getTitle())
                    .created_at(board.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class DetailDTO {

        private String title;
        private String member_email;
        private String contents;
        private String upFile;
        private String nickName;



        public static DetailDTO formEntity(Board board, String nickName) {
            return DetailDTO.builder()

                    .title(board.getTitle())
                    .member_email(board.getMemberEmail())
                    .contents(board.getContents())
                    .upFile(board.getFileName())
                    .nickName(nickName)
                    .build();
        }
    }
}