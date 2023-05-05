package com.study.statemachine.event;

public class SingDanceRapEvent implements Event{

    @Override
    public String handle() {
        System.out.println("开始唱，跳，rap");
        return "开始唱，跳，rap";
    }
}
