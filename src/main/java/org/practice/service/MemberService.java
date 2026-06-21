package org.practice.service;

import org.practice.dto.request.CreateMemberRequest;
import org.practice.dto.request.UpdateMemberRequest;
import org.practice.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {
    MemberResponse createMember(CreateMemberRequest request);

    MemberResponse getMemberById(Long memberId);

    List<MemberResponse> getAllMembers();

    MemberResponse updateMember(Long memberId,
                                UpdateMemberRequest request);

    void deleteMember(Long memberId);
}
