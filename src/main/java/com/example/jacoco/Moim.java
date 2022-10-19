package com.example.jacoco;

public class Moim {

    int maxNumberOfAttendees;

    int numberOfEnrollment;

    public boolean isEnrollmentFull(){
        // 무한히 받는다
        if ( maxNumberOfAttendees == 0 ) {
            return false;
        }

        if ( numberOfEnrollment < maxNumberOfAttendees ) {
            return false;
        }

        return true;
    }
}
