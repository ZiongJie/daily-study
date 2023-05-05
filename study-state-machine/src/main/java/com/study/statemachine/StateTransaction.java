package com.study.statemachine;

import com.study.statemachine.event.Event;
import com.study.statemachine.utils.ActionEnum;
import com.study.statemachine.utils.StateEnum;

public class StateTransaction {

    /**
     * 当前状态
     */
    private StateEnum currentState;

    /**
     * 相对应动作
     */
    private ActionEnum action;

    /**
     * 下一个状态
     */
    private StateEnum nextState;

    /**
     * 相应事件
     */
    private Event event;

    public StateTransaction() {

    }

    public StateEnum getCurrentState() {
        return currentState;
    }

    public ActionEnum getAction() {
        return action;
    }

    public StateEnum getNextState() {
        return nextState;
    }

    public Event getEvent() {
        return event;
    }

    // 链式初始化对象
    public StateTransaction source(StateEnum state) {
        currentState = state;
        return this;
    }
    public StateTransaction when(ActionEnum action) {
        this.action = action;
        return this;
    }
    public StateTransaction target(StateEnum state) {
        nextState = state;
        return this;
    }
    public StateTransaction how(Event event) {
        this.event = event;
        return this;
    }
}
