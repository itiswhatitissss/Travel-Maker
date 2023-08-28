//package org.zerock.travelmaker.service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class SchedulerServiceImpl implements SchedulerService{
//    @Override
//    public List<Date> getDatesBetween(Date startDate, Date endDate) {
//        List<Date> datesBetween = new ArrayList<>();
//        Date currentDate = startDate;
//
//        while (!currentDate.toInstant().isAfter(endDate.toInstant())) {
//            datesBetween.add(currentDate);
//            currentDate = currentDate.getDay(1);
//        }
//
//        return datesBetween;
//    }
//}
