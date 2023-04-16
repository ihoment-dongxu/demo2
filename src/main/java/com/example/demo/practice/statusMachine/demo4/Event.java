package com.example.demo.practice.statusMachine.demo4;

import com.google.common.collect.HashBasedTable;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 模拟钉钉审核流程，状态机实现
 *
 * @author dongxu
 * @create 2023-04-08 下午9:25
 */
@Getter
public enum Event {
    SUBMIT,
    PASS,
    REJECT
}

@Getter
enum State {
    REJECT,
    FINISH,
    LEADER_CHECK,
    HR_CHECK,
    UN_SUBMIT
}

/**
 * 简易版
 */
class StateMachine {
    /**
     * 用两个key确定一个内容
     */
    private static final HashBasedTable<State, Event, State> hashBasedTable = HashBasedTable.create();

    static {
        hashBasedTable.put(State.UN_SUBMIT, Event.SUBMIT, State.LEADER_CHECK);
        hashBasedTable.put(State.LEADER_CHECK, Event.PASS, State.HR_CHECK);
        hashBasedTable.put(State.LEADER_CHECK, Event.REJECT, State.REJECT);
        hashBasedTable.put(State.HR_CHECK, Event.PASS, State.FINISH);
        hashBasedTable.put(State.HR_CHECK, Event.REJECT, State.REJECT);
    }

    public static State getNext(State state, Event event) {
        State next = hashBasedTable.get(state, event);
        if (next == null) {
            throw new RuntimeException("错误状态");
        }
        return next;
    }
}

/**
 * 简易版测试
 */
class MachineTest {
    public static void main(String[] args) {
        State next = StateMachine.getNext(State.LEADER_CHECK, Event.PASS);
        System.out.println(next);
    }
}

/**
 * 过程
 */
@Data
class SopProcess {
    private State from;
    private State to;
    private Event event;
    private IStateHandler handler;
}

/**
 * 过程建造者
 */
class SopProcessBuilder {

    private SopProcess sopProcess;

    private void setSopProcess(SopProcess sopProcess) {
        this.sopProcess = sopProcess;
    }

    public static SopProcessBuilder getInstance() {
        SopProcessBuilder sopProcessBuilder = new SopProcessBuilder();
        sopProcessBuilder.setSopProcess(new SopProcess());
        return sopProcessBuilder;
    }

    public SopProcessBuilder from(State state) {
        sopProcess.setFrom(state);
        return this;
    }

    public SopProcessBuilder to(State state) {
        sopProcess.setTo(state);
        return this;
    }

    public SopProcessBuilder event(Event event) {
        sopProcess.setEvent(event);
        return this;
    }

    public SopProcessBuilder handler(IStateHandler handler) {
        sopProcess.setHandler(handler);
        return this;
    }

    public SopProcess build() {
        return sopProcess;
    }
}

/**
 * 抽象状态机
 */
abstract class AbstractStateMachine {

    /**
     * 执行类
     */
    @Data
    static class SopExec {
        private State nextState;
        private IStateHandler stateHandler;
    }

    private HashBasedTable<State, Event, SopExec> hashBasedTable = HashBasedTable.create();

    // 数据初始化
    {
        List<SopProcess> sopProcesses = init();
        sopProcesses.forEach(item -> {
            SopExec sopExec = new SopExec();
            sopExec.setNextState(item.getTo());
            sopExec.setStateHandler(item.getHandler());
            hashBasedTable.put(item.getFrom(), item.getEvent(), sopExec);
        });
    }

    abstract List<SopProcess> init();

    /**
     * 获取下一个状态
     *
     * @param from  初始状态
     * @param event 事件
     * @return 下一个状态
     */
    public State getNext(State from, Event event) {
        SopExec sopExec = hashBasedTable.get(from, event);
        if (sopExec == null) {
            throw new RuntimeException("状态错误");
        }
        return sopExec.getNextState();
    }

    /**
     * 获取下一个处理器
     *
     * @param from  初始状态
     * @param event 事件
     * @return 下一个处理器
     */
    public IStateHandler getHandle(State from, Event event) {
        SopExec sopExec = hashBasedTable.get(from, event);
        if (sopExec == null) {
            throw new RuntimeException("状态错误");
        }
        return sopExec.getStateHandler();
    }
}

/**
 * 新状态机/复杂状态机
 */
class NewStateMachine extends AbstractStateMachine {

    /**
     * 初始化数据
     *
     * @return 初始化数据
     */
    @Override
    List<SopProcess> init() {
        return Arrays.asList(
                SopProcessBuilder.getInstance()
                        .from(State.UN_SUBMIT)
                        .event(Event.SUBMIT)
                        .to(State.LEADER_CHECK)
                        .handler(new SubmitHandler())
                        .build(),
                SopProcessBuilder.getInstance()
                        .from(State.LEADER_CHECK)
                        .event(Event.PASS)
                        .to(State.HR_CHECK)
                        .handler(new LeaderPassHandler())
                        .build(),
                SopProcessBuilder.getInstance()
                        .from(State.LEADER_CHECK)
                        .event(Event.REJECT)
                        .to(State.REJECT)
                        .handler(new LeaderRejectHandler())
                        .build(),
                SopProcessBuilder.getInstance()
                        .from(State.HR_CHECK)
                        .event(Event.PASS)
                        .to(State.FINISH)
                        .handler(new HrPassHandler())
                        .build(),
                SopProcessBuilder.getInstance()
                        .from(State.HR_CHECK)
                        .event(Event.REJECT)
                        .handler(new HrRejectHandler())
                        .to(State.REJECT)
                        .build()
        );
    }
}

/**
 * 处理器接口
 */
interface IStateHandler {
    String handle();
}

/**
 * 提交处理器
 */
class SubmitHandler implements IStateHandler {
    @Override
    public String handle() {
        System.out.println("流程提交，开始审批......");
        return "已提交，转为领导审批";
    }
}

/**
 * Leader审核通过处理器
 */
class LeaderPassHandler implements IStateHandler {
    @Override
    public String handle() {
        System.out.println("领导开始审批......");
        return "领导审批通过，转为HR审批";
    }
}

/**
 * Leader审核拒绝处理器
 */
class LeaderRejectHandler implements IStateHandler {
    @Override
    public String handle() {
        System.out.println("领导开始审批......");
        return "领导审批拒绝，结果：拒绝";
    }
}

/**
 * HR审核通过处理器
 */
class HrPassHandler implements IStateHandler {
    @Override
    public String handle() {
        System.out.println("Hr开始审批......");
        return "Hr审批通过，结果：完成";
    }
}

/**
 * HR审核拒绝处理器
 */
class HrRejectHandler implements IStateHandler {
    @Override
    public String handle() {
        System.out.println("Hr开始审批......");
        return "Hr审批拒绝，结果：拒绝";
    }
}

/**
 * 新状态机测试
 */
class NewMachineTest {
    public static void main(String[] args) {
        State from = State.HR_CHECK;
        Event event = Event.PASS;

        System.out.println("当前状态：" + from);
        System.out.println("当前动作：" + event);

        NewStateMachine newStateMachine = new NewStateMachine();
        State nextState = newStateMachine.getNext(from, event);
        System.out.println("下一个状态：" + nextState);

        IStateHandler stateHandler = newStateMachine.getHandle(from, event);
        System.out.print("处理过程：");
        String handleResult = stateHandler.handle();
        System.out.println("处理结果：" + handleResult);
    }
}
