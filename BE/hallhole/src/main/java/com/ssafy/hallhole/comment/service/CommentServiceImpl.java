package com.ssafy.hallhole.comment.service;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.comment.domain.Comment;
import com.ssafy.hallhole.comment.dto.*;
import com.ssafy.hallhole.comment.repository.CommentRepository;
import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.repository.MemberRepository;
import com.ssafy.hallhole.review.domain.Review;
import com.ssafy.hallhole.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;

    @Override
    public void writeComment(CommentWriteInputDTO inputDTO) throws NotFoundException {

        Member member = memberRepository.findById(inputDTO.getMemberId()).get();
        if (member==null || member.isOut()){
            throw new NotFoundException("유효한 사용자가 작성한 후기가 아닙니다.");
        }

        Review review = reviewRepository.findById(inputDTO.getReviewId()).get();
        if (review==null || review.isDelete()){
            throw new NotFoundException("후기가 존재하지 않습니다.");
        }

        Comment comment = new Comment(member, review, inputDTO.getContents());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(CommentDeleteInputDTO inputDTO) throws NotFoundException{
        Member member = memberRepository.findById(inputDTO.getMemberId()).get();
        if (member==null || member.isOut()){
            throw new NotFoundException("유효한 사용자가 아닙니다.");
        }

        Comment comment = commentRepository.findOneCommentById(inputDTO.getCommentId());
        if (comment==null || comment.isDelete()){
            throw new NotFoundException("존재하지 않는 댓글입니다.");
        }

        comment.setDelete(true);
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(CommentUpdateInputDTO inputDTO) throws NotFoundException{
        Member member = memberRepository.findById(inputDTO.getMemberId()).get();
        if (member==null || member.isOut()){
            throw new NotFoundException("유효한 사용자가 아닙니다.");
        }

        Comment comment = commentRepository.findOneCommentById(inputDTO.getCommentId());
        if (comment==null || comment.isDelete()){
            throw new NotFoundException("존재하지 않는 댓글입니다.");
        }

        comment.setContents(inputDTO.getContents());
        commentRepository.save(comment);
    }


    @Override
    public List<Comment> CommentListfindByMemberId(CommentFindByMemberIdDTO inputDTO) throws NotFoundException{
        Member member = memberRepository.findById(inputDTO.getMemberId()).get();
        if (member==null || member.isOut()){
            throw new NotFoundException("유효한 사용자가 아닙니다.");
        }

        List<Comment> commentList = commentRepository.findAllCommentPagingByMemberId(
                inputDTO.getStart(), inputDTO.getSize(), inputDTO.getMemberId());

        if (commentList.size()==0){
            throw new NotFoundException("해당 사용자가 작성한 댓글이 없습니다.");
        }

        return commentList;
    }

    @Override
    public List<Comment> CommentListfindByReviewId(CommentFindByReviewIdDTO inputDTO) throws NotFoundException{

        Review review = reviewRepository.findById(inputDTO.getReviewId()).get();
        if (review==null || review.isDelete()){
            throw new NotFoundException("후기가 존재하지 않습니다.");
        }

        List<Comment> commentList = commentRepository.findAllCommentPagingByReviewId(
                inputDTO.getStart(), inputDTO.getSize(), inputDTO.getReviewId());

        if (commentList.size()==0){
            throw new NotFoundException("해당 후기에 대한 댓글이 없습니다.");
        }

        return commentList;
    }



}
