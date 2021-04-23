package com.neo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neo.demo.entity.TableNameDemo;
import com.neo.demo.mapper.TableNameDemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisPlusTest {

    @Resource
    private TableNameDemoMapper TableNameDemoMapper;


    @Test
    public void testSelectOne() {
        TableNameDemo TableNameDemo = TableNameDemoMapper.selectById(1L);
        System.out.println(TableNameDemo);
    }

    @Test
    public void testInsert() throws InterruptedException, IOException {
        int numbers = 8000000;
        CountDownLatch  countDownLatch = new CountDownLatch(numbers);
        ThreadPoolExecutor  executor = new ThreadPoolExecutor(12,Integer.MAX_VALUE,3000, TimeUnit.SECONDS,new ArrayBlockingQueue<>(8000000),new ThreadPoolTaskExecutor());

        while (numbers-->0){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    insertRandomInfo();
                    countDownLatch.countDown();
                }
            });
            System.out.println(numbers);
        }
        countDownLatch.await();
            System.out.println("OVER");
    }

    @Test
    public void testSelect() throws InterruptedException, IOException {
        int numbers = 8000;
        CountDownLatch  countDownLatch = new CountDownLatch(numbers);
        ThreadPoolExecutor  executor = new ThreadPoolExecutor(20,Integer.MAX_VALUE,3000, TimeUnit.SECONDS,new ArrayBlockingQueue<>(8000000),new ThreadPoolTaskExecutor());

        while (numbers-->0){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(selectInfo().toString());
                    countDownLatch.countDown();
                }
            });
            System.out.println(numbers);
        }
        countDownLatch.await();
        System.out.println("OVER");
    }

    private void insertRandomInfo(){
        TableNameDemo tableNameDemo = new TableNameDemo();
        Integer a = UUID.randomUUID().hashCode();
        tableNameDemo.setColumn2(a).setColumn3(a).setColumn4(a).setColumn5(a).setColumn6(a).setColumn7(a).setColumn8(a).setColumn9(a).setColumn10(a).setColumn11(a);
        TableNameDemoMapper.insert(tableNameDemo);
    }

    private TableNameDemo selectInfo(){
        List<TableNameDemo> tableNameDemos = TableNameDemoMapper.selectList(new LambdaQueryWrapper<TableNameDemo>().orderBy(true,true,TableNameDemo::getColumn6).last("limit "+ new Random().nextInt(100)));
        return tableNameDemos.get(0);
    }






}