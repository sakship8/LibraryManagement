package org.practice.dto.response;

import java.time.LocalDate;

public record MemberResponse(
        Long memberId,
        String memberName,
        String email,
        String phoneNo,
        LocalDate membershipDate,
        Boolean active
) {
}
