package com.study.statemachine.event;

public class PlayBasketballEvent implements Event{

    @Override
    public String handle() {
        System.out.println("开始打篮球");
        return "开始打篮球";
    }
}
