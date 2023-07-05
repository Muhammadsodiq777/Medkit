package com.example.medkit.service;

import com.example.medkit.domain.Meeting;
import com.example.medkit.dto.GeneralResponse;

import java.util.List;

public interface MeetingService {

    GeneralResponse<List<Meeting>> getAllMyMeetings(Long id);

    GeneralResponse<Meeting> saveNewMeeting(Long doctorId, Long userId, String date);
}
