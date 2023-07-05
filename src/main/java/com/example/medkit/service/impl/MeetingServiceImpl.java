package com.example.medkit.service.impl;

import com.example.medkit.domain.Meeting;
import com.example.medkit.dto.GeneralResponse;
import com.example.medkit.repository.MeetingRepository;
import com.example.medkit.service.MeetingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    private MeetingRepository repository;

    public MeetingServiceImpl(MeetingRepository repository) {
        this.repository = repository;
    }

    @Override
    public GeneralResponse<List<Meeting>> getAllMyMeetings(Long id) {

        List<Meeting> allByDoctorIdOrPatientId = repository.findAllByDoctorIdOrPatientId(id, id); // test
        if(allByDoctorIdOrPatientId.isEmpty())
            return new GeneralResponse<>(false, -1, "Meetinglar topilmadi", null);
        return new GeneralResponse<>(true, 1, "success", allByDoctorIdOrPatientId);
    }

    @Override
    public GeneralResponse<Meeting> saveNewMeeting(Long doctorId, Long userId, String date) { // dd-MM-yyyy hh:mm
        Meeting meeting = new Meeting();

        meeting.setMeeting_date(LocalDateTime.now());
        meeting.setDoctorId(doctorId);
        meeting.setPatientId(userId);
        Meeting save = repository.save(meeting);
        return new GeneralResponse<>(true, 1, "success", save);
    }
}
