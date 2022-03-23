package com.ssafy.vacsa.dto;

import com.ssafy.vacsa.model.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "CommentDto : 댓글정보", description = "댓글의 상세 정보를 나타낸다.")
public class CommentDto {

    @ApiModelProperty(value = "댓글 아이디", notes="자동으로 생성되므로 작성 보내지 않는다")
    private Long commentId;
    @ApiModelProperty(value = "댓글을 작성한 유저 id")
    private String commentUser;
    @ApiModelProperty(value = "댓글 작성 유저 닉네임")
    private String commentUserNickname;
    @ApiModelProperty(value = "댓글이 작성된 게시글의 id")
    private Long BoardComment;
    @ApiModelProperty(value = "댓글의 내용")
    private String commentContent;
    @ApiModelProperty(value = "댓글의 생성 시간")
    private LocalDateTime commentCreateTime;

    public CommentDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentUser = comment.getCommentUser().getUsername();
        this.commentUserNickname = comment.getCommentUser().getNickname();
        this.BoardComment = comment.getBoardComment().getBoardId();
        this.commentContent = comment.getCommentContent();
        this.commentCreateTime = LocalDateTime.from(comment.getCommentCreateTime());
    }
}
