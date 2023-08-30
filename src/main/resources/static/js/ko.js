document.addEventListener('DOMContentLoaded', function() {
  var localeData = {
    code: "ko",
    buttonText: {
      today: "오늘",
      month: "월",
      week: "주",
      day: "일",
      list: "일정목록"
    },
    weekLabel: "주",
    allDayText: "종일",
    eventLimitText: "개",
    noEventsMessage: "일정이 없습니다"
  };

  // FullCalendar에 한국어 지원을 추가합니다.
  FullCalendar.globalLocales.push(localeData);

  // FullCalendar를 초기화합니다.
  var calendarEl = document.getElementById('calendar');
  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    locale: 'ko' // 한국어 지역 설정
  });
  calendar.render();
});