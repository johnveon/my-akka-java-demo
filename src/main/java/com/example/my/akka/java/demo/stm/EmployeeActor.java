package com.example.my.akka.java.demo.stm;

/**
 * @Description: actor
 * @Author: zhoufengen
 * @Create At: 2019-12-18 10:16
 **/
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.transactor.Coordinated;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

/**
 * Created by liubenlong on 2017/1/12.
 */
public class EmployeeActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    /**
     * 员工账户
     */
    private Ref.View<Integer> count = STM.newRef(20);

    @Override
    public void onReceive(Object o) {
        if(o instanceof Coordinated){
            Coordinated coordinated = (Coordinated) o;
            //员工增加的工资
            int downCount = (int) coordinated.getMessage();

            try {
                //注意这里异常要及时处理，否则异常会一直扩散，导致回退到系统刚启动时的初始状态！
                //Employee增加工资
                coordinated.atomic(() -> STM.increment(count, downCount));
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if("getCount".equals(o)){
            getSender().tell(count.get(), getSelf());
        }else{
            unhandled(o);
        }
    }
}