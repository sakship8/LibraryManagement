package org.practice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.practice.dto.request.CreateMemberRequest;
import org.practice.dto.request.UpdateMemberRequest;
import org.practice.dto.response.MemberResponse;
import org.practice.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(){
        List<MemberResponse> members=memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMemeberById(@PathVariable Long memberId){
        MemberResponse member=memberService.getMemberById(memberId);
        return ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody CreateMemberRequest request){
        MemberResponse response=memberService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long memberId,@Valid @RequestBody UpdateMemberRequest request){
        MemberResponse response=memberService.updateMember(memberId,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

}
