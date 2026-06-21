package org.practice.dto.request;

public record UpdateMemberRequest(
        String memeberName,
        String phoneNo,
        Boolean active
) {
}
