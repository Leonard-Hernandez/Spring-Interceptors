package com.leonard.curso.springboot.app.interceptor.springboot_interceptor.interceptors;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("loadingTimeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

        private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                        throws Exception {
                HandlerMethod method = (HandlerMethod) handler;
                logger.info("LoadingTimeInterceptor: preHandle is called " + method.getMethod().getName());
                long start = System.currentTimeMillis();
                request.setAttribute("start", start);
                Random rand = new Random();
                int delay = rand.nextInt(500);
                Thread.sleep(delay);
                return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                        ModelAndView modelAndView) throws Exception {
                HandlerMethod method = (HandlerMethod) handler;
                long end = System.currentTimeMillis();
                long start = (Long) request.getAttribute("start");
                logger.info("" + method.getMethod().getName() + " took " + (end - start) + " ms");
        }

}
