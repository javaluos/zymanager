package com.zy.cms.common;

import java.io.Serializable;
import java.text.MessageFormat;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 描述：日志记录器。使用委托代理Log4j日志来实现。

 * @author hmj
 * @date 2014-11-28
 */
public class ZyLogger implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 日志器记录类完全规范名称，代表本类
     */
    private static final String FQCN = ZyLogger.class.getName();

    /**
     * 被委托代理的Log4j日志器
     */
    private transient Logger logger = null;

    /**
     * 日志器名称
     */
    private String name = null;

    /**
     * 描述：构造方法。
     *
     * @param name 日志器名称
     */
    private ZyLogger(String name) {
        this.name = name;
        this.logger = getLog4jLogger();
    }

    /**
     * 描述：构造方法。
     *
     * @param clazz 日志器类
     */
    private ZyLogger(Class clazz) {
        this.name = clazz.getName();
        this.logger = getLog4jLogger();
    }

    /**
     * 描述：按照名称取得日志记录器。
     *
     * @param name 日志器名称
     * @return
     */
    public static ZyLogger getLogger(String name) {
        return new ZyLogger(name);
    }

    /**
     * 描述：按照类取得日志记录器。
     *
     * @param clazz 日志器类
     * @return
     */
    public static ZyLogger getLogger(Class clazz) {
        return new ZyLogger(clazz);
    }

    /**
     * 描述：取得被委托代理的Log4j日志器。
     *
     * @return
     */
    public Logger getLog4jLogger() {
        if (logger == null) {
            logger = Logger.getLogger(name);
        }
        return (this.logger);
    }

    /**
     * 描述：可记录严重错误级别日志。
     *
     * @return
     */
    public boolean isFatalEnabled() {
        return getLog4jLogger().isEnabledFor(Level.FATAL);
    }

    /**
     * 描述：可记录错误级别日志。
     *
     * @return
     */
    public boolean isErrorEnabled() {
        return getLog4jLogger().isEnabledFor(Level.ERROR);
    }

    /**
     * 描述：可记录警告级别日志。
     *
     * @return
     */
    public boolean isWarnEnabled() {
        return getLog4jLogger().isEnabledFor(Level.WARN);
    }

    /**
     * 描述：可记录信息级别日志。
     *
     * @return
     */
    public boolean isInfoEnabled() {
        return getLog4jLogger().isInfoEnabled();
    }

    /**
     * 描述：可记录调试级别日志。
     *
     * @return
     */
    public boolean isDebugEnabled() {
        return getLog4jLogger().isDebugEnabled();
    }

    /**
     * 描述：可记录跟踪级别日志。
     *
     * @return
     */
    public boolean isTraceEnabled() {
        return getLog4jLogger().isTraceEnabled();
    }

    /**
     * 描述：记录严重错误日志。
     *
     * @param message 错误消息
     */
    public void fatal(Object message) {
        getLog4jLogger().log(FQCN, Level.FATAL, message, null);
    }

    /**
     * 描述：记录严重错误日志。
     *
     * @param message 错误消息
     * @param t 错误异常
     */
    public void fatal(Object message, Throwable t) {
        getLog4jLogger().log(FQCN, Level.FATAL, message, t);
    }

    /**
     * 描述：记录严重错误日志。
     *
     * @param message 错误消息
     * @param words 格式化变量对应的值
     * @param t 错误异常
     */
    public void fatal(Object message, Object[] words, Throwable t) {
        message = format(message, words);
        getLog4jLogger().log(FQCN, Level.FATAL, message, t);
    }

    /**
     * 描述：记录错误日志。
     *
     * @param message 错误消息
     */
    public void error(Object message) {
        getLog4jLogger().log(FQCN, Level.ERROR, message, null);
    }

    /**
     * 描述：记录错误日志。
     *
     * @param message 错误消息
     * @param t 错误异常
     */
    public void error(Object message, Throwable t) {
        getLog4jLogger().log(FQCN, Level.ERROR, message, t);
    }

    /**
     * 描述：记录错误日志。
     *
     * @param message 错误消息
     * @param words 格式化变量对应的值
     * @param t 错误异常
     */
    public void error(Object message, Object[] words, Throwable t) {
        message = format(message, words);
        getLog4jLogger().log(FQCN, Level.ERROR, message, t);
    }

    /**
     * 描述：记录警告日志。
     *
     * @param message 警告消息
     */
    public void warn(Object message) {
        getLog4jLogger().log(FQCN, Level.WARN, message, null);
    }

    /**
     * 描述：记录警告日志。
     *
     * @param message 警告消息
     * @param t 警告异常
     */
    public void warn(Object message, Throwable t) {
        getLog4jLogger().log(FQCN, Level.WARN, message, t);
    }

    /**
     * 描述：记录警告日志。
     *
     * @param message 警告消息
     * @param words 格式化变量对应的值
     * @param t 警告异常
     */
    public void warn(Object message, Object[] words, Throwable t) {
        message = format(message, words);
        getLog4jLogger().log(FQCN, Level.WARN, message, t);
    }

    public void warn(Object message, Object[] words) {
    	warn(message, words, null);
    }

    /**
     * 描述：记录信息日志。
     *
     * @param message 信息消息
     */
    public void info(Object message) {
        getLog4jLogger().log(FQCN, Level.INFO, message, null);
    }

    /**
     * 描述：记录信息日志。
     *
     * @param message 信息消息
     * @param t 信息异常
     */
    public void info(Object message, Throwable t) {
        getLog4jLogger().log(FQCN, Level.INFO, message, t);
    }

    /**
     * 描述：记录信息日志。
     *
     * @param message 信息消息
     * @param words 格式化变量对应的值
     * @param t 信息异常
     */
    public void info(Object message, Object[] words, Throwable t) {
        message = format(message, words);
        getLog4jLogger().log(FQCN, Level.INFO, message, t);
    }

    public void info(Object message, Object[] words) {
        info(message, words, null);
    }

    /**
     * 描述：记录调试日志。
     *
     * @param message 调试消息
     */
    public void debug(Object message) {
        getLog4jLogger().log(FQCN, Level.DEBUG, message, null);
    }

    /**
     * 描述：记录调试日志。
     *
     * @param message 调试消息
     * @param t 调试异常
     */
    public void debug(Object message, Throwable t) {
        getLog4jLogger().log(FQCN, Level.DEBUG, message, t);
    }

    /**
     * 描述：记录调试日志。
     *
     * @param message 调试消息
     * @param words 格式化变量对应的值
     * @param t 调试异常
     */
    public void debug(Object message, Object[] words, Throwable t) {
        message = format(message, words);
        getLog4jLogger().log(FQCN, Level.DEBUG, message, t);
    }

    /**
     * 描述：记录跟踪日志。
     *
     * @param message 跟踪消息
     */
    public void trace(Object message) {
        getLog4jLogger().log(FQCN, Level.TRACE, message, null);
    }

    /**
     * 描述：记录跟踪日志。
     *
     * @param message 跟踪消息
     * @param t 跟踪异常
     */
    public void trace(Object message, Throwable t) {
        getLog4jLogger().log(FQCN, Level.TRACE, message, t);
    }

    /**
     * 描述：记录跟踪日志。
     *
     * @param message 跟踪消息
     * @param words 格式化变量对应的值
     * @param t 跟踪异常
     */
    public void trace(Object message, Object[] words, Throwable t) {
        message = format(message, words);
        getLog4jLogger().log(FQCN, Level.TRACE, message, t);
    }

    /**
     * 描述：格式化信息对象。<br/>
     * 变量索引从0开始，变量与值对应<br>
     * 功能类似于：<br/>
     * format("{0} is {1}.", new Object[]{"Time", "out"}); //结果为"Time is out."<br/>
     * format("{1} is {0}.", new Object[]{"out", "Time"}); //结果为"Time is out."<br/>
     * format("{0} is {1}.", new Object[]{ "Time"}); //结果为"Time is {1}."<br/>
     * format("{0} is {1}.", new Object[]{ 100}); //结果为"100 is {1}."<br/>
     * format("{0} is {1}.", new Object[]{ 10000}); //结果为"10,000 is {1}."<br/>
     * format("{1} is good.", new Object[]{ "Jerry","Tom"}); //结果为"Tom is good."<br/>
     *
     * @param message 信息对象
     * @param words 格式化变量对应的值
     * @see MessageFormat#format
     * @return
     */
    private static final Object format(Object message, Object[] words) {
        if (words != null && words.length > 0 && message instanceof String) {
            message = MessageFormat.format((String) message, words);
        }
        return message;
    }

    public static void main(String[] args) {
        Object msg = null;
        msg = format("{0} is {1}.", new Object[] { "Time", "out" }); // 结果为"Time is out."
        System.out.println(msg);
        msg = format("{1} is {0}.", new Object[] { "out", "Time" }); // 结果为"Time is out."
        System.out.println(msg);
        msg = format("{0} is {1}.", new Object[] { "Time" }); // 结果为"Time is {1}."
        System.out.println(msg);
        msg = format("{0} is {1}.", new Object[] { 100 }); // 结果为"10000 is {1}."
        System.out.println(msg);
        msg = format("{0} is {1}.", new Object[] { 10000 }); // 结果为"10,000 is {1}."
        System.out.println(msg);
        msg = format("{1} is good.", new Object[] { "Jerry", "Tom" }); // 结果为"Tom is good."
        System.out.println(msg);
    }

}
