package com.example.my.akka.java.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhoufengen
 * @Create At: 2019-12-18 17:09
 **/
@Service("countingService")
public class CountingServiceImpl implements CountingService {

    private static Logger logger = LoggerFactory.getLogger(CountingServiceImpl.class);

    /**
     * (non-Javadoc)
     *
     * @see com.example.my.akka.java.demo.spring.CountingService#increment(int)
     */
    @Override
    public int increment(int count) {
        logger.info("increase " + count + "by 1.");
        return count + 1;
    }

}
