package com.example.demo.practice.statusMachine.demo3;

import lombok.Getter;

/**
 * @author dongxu
 * @create 2023-04-08 下午9:09
 */
@Getter
public enum State {
    // 已完成
    FINISH {
        @Override
        State getNext() {
            return this;
        }
    },
    // 未提交
    UN_SUBMIT {
        @Override
        State getNext() {
            return LEADER_CHECK;
        }
    },
    // 领导审核
    LEADER_CHECK {
        @Override
        State getNext() {
            return HR_CHECK;
        }
    },
    // HR审核
    HR_CHECK {
        @Override
        State getNext() {
            return FINISH;
        }
    };

    abstract State getNext();
}

class Test {
    static State getNext(State state) {
        if (state == State.UN_SUBMIT)
            return State.LEADER_CHECK;
        if (state == State.LEADER_CHECK)
            return State.FINISH;
        if (state == State.HR_CHECK)
            return State.FINISH;
        throw new IllegalStateException("非法状态");
    }

    public static void main(String[] args) {
        State state = State.UN_SUBMIT;
        System.out.println(getNext(state));
        System.out.println(state.getNext());

    }
}

