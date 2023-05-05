package com.study.statemachine;

import com.study.statemachine.utils.ActionEnum;
import com.study.statemachine.utils.StateEnum;

import java.util.ArrayList;
import java.util.List;

public class StateMachine {

    /**
     * 存储状态信息
     */
    private List<StateTransaction> stateTransactionList;

    /**
     * 记录当前状态
     */
    private StateEnum currentState;

    public StateMachine(StateEnum state) {
        stateTransactionList = new ArrayList<>();
        currentState = state;
    }

    /**
     * 添加一条状态信息
     * @return
     */
    public StateTransaction addone() {
        StateTransaction stateTransaction = new StateTransaction();
        stateTransactionList.add(stateTransaction);
        return stateTransaction;
    }

    public StateTransaction execute(ActionEnum action) {

        for (StateTransaction stateTransaction : stateTransactionList) {
            if (currentState == stateTransaction.getCurrentState() && action == stateTransaction.getAction()) {
                stateTransaction.getEvent().handle();
                currentState = stateTransaction.getNextState();
                return stateTransaction;
            }
        }
        return null;
    }
}
