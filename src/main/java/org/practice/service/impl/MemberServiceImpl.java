package org.practice.service.impl;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.practice.dto.request.CreateMemberRequest;
import org.practice.dto.request.UpdateMemberRequest;
import org.practice.dto.response.MemberResponse;
import org.practice.entity.Member;
import org.practice.exception.DuplicateEmailException;
import org.practice.exception.MemberNotFoundException;
import org.practice.repository.MemberRepository;
import org.practice.service.MemberService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponse createMember(CreateMemberRequest request) {

        if(memberRepository.findByEmail(request.email()).isPresent()){
            throw new DuplicateEmailException(request.email());
        }

        Member member= Member.builder()
                .memberName(request.memberName())
                .email(request.email())
                .phoneNo(request.phoneNo())
                .membershipDate(LocalDate.now())
                .active(true)
                .build();

        Member savedMember=memberRepository.save(member);
        return mapToResponse(savedMember);
    }

    @Override
    public MemberResponse getMemberById(Long memberId) {

        Member member=memberRepository.findById(memberId)
                .orElseThrow(()->new MemberNotFoundException(memberId));

        return mapToResponse(member);
    }

    @Override
    public List<MemberResponse> getAllMembers() {

        return memberRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MemberResponse updateMember(Long memberId,
                                       UpdateMemberRequest request) {

        Member member=memberRepository.findById(memberId)
                .orElseThrow(()->new MemberNotFoundException(memberId));

        member.setMemberName(request.memeberName());
        member.setEmail(request.phoneNo());
        member.setActive(request.active());

        Member updatedMember=memberRepository.save(member);
        return mapToResponse(updatedMember);
    }

    @Override
    public void deleteMember(Long memberId) {

    }

    private MemberResponse mapToResponse(Member member){
        return new MemberResponse(
                member.getMemberId(),
                member.getMemberName(),
                member.getEmail(),
                member.getPhoneNo(),
                member.getMembershipDate(),
                member.getActive()
        );
    }
}
