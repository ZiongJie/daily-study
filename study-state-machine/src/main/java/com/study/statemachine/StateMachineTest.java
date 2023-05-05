package com.study.statemachine;

import com.study.statemachine.event.PlayBasketballEvent;
import com.study.statemachine.event.SingDanceRapEvent;
import com.study.statemachine.utils.ActionEnum;
import com.study.statemachine.utils.StateEnum;

public class StateMachineTest {

    public static void main(String[] args) {

        StateMachine machine = new StateMachine(StateEnum.PLAY_BASKETBALL);
        // 打篮球的时候，一旦音乐起，就会开始唱跳rap
        machine.addone().source(StateEnum.PLAY_BASKETBALL).when(ActionEnum.MUSIC_ON)
                .target(StateEnum.SING_DANCE_RAP).how(new SingDanceRapEvent());
        // 唱跳rap的时候，一旦音乐停止，就会开始打篮球
        machine.addone().source(StateEnum.SING_DANCE_RAP).when(ActionEnum.MUSIC_OFF)
                .target(StateEnum.PLAY_BASKETBALL).how(new PlayBasketballEvent());
        machine.execute(ActionEnum.MUSIC_ON);
        machine.execute(ActionEnum.MUSIC_OFF);
    }
}
